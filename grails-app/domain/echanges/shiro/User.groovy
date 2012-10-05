package echanges.shiro

class User {
    String mail
    String username
    String passwordHash
    byte[] passwordSalt

    Communaute communaute

    /**
     * @return toutes les permissions de l'utilisateur, qu'elles aient été attribuées unitairement ou via
     * le regroupement d'un rôle.
     *
     * Pour le moment la notion de rôle n'est pas encore implémentée.
     */
    Set<Permission> getPermissions(){
        return UserPermission.findAllByUser(this).collect {it.permission} as Set
    }


    static constraints = {
        username(nullable: false)
        mail(nullable: false, blank: false, unique: true, email: true)
        communaute(nullable: true)
    }
}
