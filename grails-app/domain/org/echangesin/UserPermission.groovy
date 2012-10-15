package org.echangesin

/**
 * Association entre un utilisateur et une permission
 */
class UserPermission {

    User user
    Permission permission

    static constraints = {
        user(nullable: false)
        permission(nullable: false)
    }
}
