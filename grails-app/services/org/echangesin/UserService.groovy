package org.echangesin

class UserService {

    static transactional = true

    /**
     * Attribue une communauté à un utilisateur et lui crée les droits nécessaire.
     * @param user
     * @param communaute
     */
    def affecteCommunaute(User user, Communaute communaute) {

        //Si l'utilisateur était affecté à une communauté, on supprime les permissions dont il disposait.
        if (user.communaute){
            Permission.executeQuery(
                    '''from UserPermission up
                        where up.user= :user
                        and up.permission.communaute = :communaute''',
                    [user:user, communaute: communaute])
            .each {it.delete()}
        }

        user.communaute = communaute

        //Attribution nouvelles permissions
        Permission perm = Permission.findByCommunauteAndAccessTypeAndDomain(communaute, AccessType.READ, '*')
        if (!perm)
            perm = new Permission(communaute: communaute, accessType: AccessType.READ, domain: '*').save(true)
        UserPermission userPermission = new UserPermission(user: user, permission: perm).save()
    }

    /**
     * Changement de mot de passe
     */
    def changePassword(User user, String oldPassword, String newPassword){

    }


}
