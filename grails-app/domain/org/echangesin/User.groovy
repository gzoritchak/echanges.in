package org.echangesin

class User {

    /**
     * Le mail est également le login.
     */
    String mail

    /**
     * Le username est le nom affiché. Il peut être changé.
     */
    String username


    String passwordHash
    byte[] passwordSalt

    /**
     * La communauté à laquelle l'utilisateur est rattaché.
     */
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
        username(nullable: false, blank: false)
        mail(nullable: false, blank: false, unique: true, email: true)
        communaute(nullable: true)
    }
    static mapping = {
        table("ech_user")  //user est un mot clef de postgres -> nécessité de prendre un autre nom de table
    }
}
