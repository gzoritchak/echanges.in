package echanges.shiro

class User {
    String mail
    String username
    String passwordHash
    byte[] passwordSalt
    Communaute communaute

    static hasMany = [
            roles: Role,
            permissions: Permission ]

    static constraints = {
        username(nullable: false)
        mail(nullable: false, blank: false, unique: true, email: true)
        communaute(nullable: true)
    }
}
