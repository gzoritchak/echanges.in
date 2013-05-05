package org.echangesin.user



class ChangePasswordCommand {

    String currentPassword
    String newPassword
    String confirmPassword

    static constraints = {
        currentPassword (blank: false)
        newPassword (minSize: 6, validator: { val, obj ->
            if(!val?.equals(obj.confirmPassword)){
                return "user.password.new.not.matching.confirm"
            }

        })
    }
}
