package org.echangesin.user

import grails.validation.Validateable

@Validateable
class ChangePasswordCommand {

    String currentPassword
    String newPassword
    String confirmPassword

    static constraints = {
        currentPassword blank: false
        newPassword     blank: false, minSize: 6
        confirmPassword blank: false, validator: { val, obj -> val.equals(obj.newPassword)}
    }
}
