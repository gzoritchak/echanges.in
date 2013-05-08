package org.echangesin.user

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.Sha512Hash
import org.echangesin.User
import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
        [userInstance: new User(params)]
    }

    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'),
                userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    /**
     * Édition du profil courant
     */
    def profil() {
        redirect(action: "edit", id: request.user.id)
    }

    def edit() {
        def userInstance = request.user
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message',
                    args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }
        [userInstance: userInstance]
    }
    def password() {
        redirect(action: "changePassword", id: request.user.id)
    }

    def changePassword(ChangePasswordCommand changePasswordCommand) {
        changePasswordCommand.clearErrors()
        [changePasswordCommand: changePasswordCommand]
    }

    def changePasswordSave(ChangePasswordCommand command) {
        if (command.hasErrors()) {
            render(view: "changePassword", model: [changePasswordCommand: command])
        } else if(incorrectPassword(command.currentPassword)) {
            command.errors.reject("incorrectPassword")
            render(view: "changePassword", model: [changePasswordCommand: command])
        } else {
            User user = request.user
            def salt = new SecureRandomNumberGenerator().nextBytes().getBytes()
            user.passwordHash = new Sha512Hash(command.newPassword, salt, 1024).toBase64()
            user.passwordSalt = salt
            user.save(flush: true, failOnError: true)
            flash.message = "Votre mot de passe a bien été modifié"
            redirect(controller: 'home')
        }
    }

    def incorrectPassword(String pass){

        def authToken = new UsernamePasswordToken(request.user.mail, pass as String)

        try{
            SecurityUtils.subject.login(authToken)
            return false
        } catch (AuthenticationException e){
            log.error "Authentication failure for user '${request.user.username}'."
            return true
        }
    }


    def update(Long id, Long version) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'user.label', default: 'User')] as Object[],
                        "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message',
                args: [message(code: 'user.label', default: 'Membre'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }
}
