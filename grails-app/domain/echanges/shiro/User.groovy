package echanges.shiro

class User {

    String mail
    String username
    String passwordHash
    Communaute communaute

    static hasMany = [
            roles: Role,
            permissions: Permission ]

    static constraints = {
        username(nullable: false)
        mail(nullable: false, blank: false, unique: true)
    }
}
