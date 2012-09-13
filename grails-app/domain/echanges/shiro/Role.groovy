package echanges.shiro

class Role {

    RoleName name

    static constraints = {

    }
}

enum RoleName {
    SUPER_USER, ADMIN, AUTEUR, MEMBRE
}
