package org.echangesin.user

@grails.validation.Validateable
class ChangePasswordCommand {

    String currentPassword
    String newPassword
    String confirmPassword

    static constraints = {
        currentPassword (blank: false)
        newPassword (blank: false, minSize: 6)
        confirmPassword (blank: false, minSize: 6,
                validator: { val, obj ->
            if(!val?.equals(obj.confirmPassword)){
                return "user.password.new.not.matching.confirm"
            }

        })
    }
}
