package org.echangesin
/**
 * Gestion d'un utilisateur d'une communauté : vision de la liste des membres d'une communauté,
 * affichage d'un utilisateur avec ses échanges, ...
 */
class CommunauteUserController {

//    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.communaute = request.communaute
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
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

}
