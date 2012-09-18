package echanges.shiro

class User {
    String mail
    String username
    String passwordHash
    byte[] passwordSalt

    Communaute communaute

    void setCommunaute(Communaute newCommunaute){
        if (communaute != null){
            def oldPermission = permissions.find {item->
                item.id = communaute.id
            }
            removeFromPermissions(oldPermission)
        }
        communaute = newCommunaute
        if(communaute != null)
            addToPermissions(new CommunautePermission(communaute: newCommunaute))
    }

    static hasMany = [
            roles: Role,
            permissions: Permission ]

    static constraints = {
        username(nullable: false)
        mail(nullable: false, blank: false, unique: true, email: true)
        communaute(nullable: true)
    }
}
