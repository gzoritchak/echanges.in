import org.echangesin.Communaute
import org.echangesin.Permission
import org.echangesin.User
import org.echangesin.UserPermission
import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.Sha512Hash
import org.echangesin.AccessType

/**
 * Initialisation des données du site pour les tests. Création de certains utilisateurs, de communauté et affectation des
 * permissions aux utilisateurs.
 */
class BootStrap {

    def shiroSecurityService

    def init = { servletContext ->

        def archamps = Communaute.findByNom("archamps")?: new Communaute(nom: "archamps").save(failOnError: true)

        def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()

        def admin = User.findByMail("g.zoritchak@gmail.com")?:
            new User(mail: "g.zoritchak@gmail.com",
                    username: "Gaetan",
                    passwordHash: new Sha512Hash("pass",passwordSalt,1024).toBase64(),
                    passwordSalt: passwordSalt)
                    .save(failOnError: true)

        Permission superPermission = Permission.findByCommunauteAndAccessTypeAndDomainAndCommunaute(
                null, AccessType.ADMIN, '*', null)?: new Permission(accessType: AccessType.ADMIN, domain: '*')
                .save(failOnError: true, flush: true)


        //le cas échéant, on affecte la permission à l'utilisateur
        UserPermission.findByUserAndPermission(admin, superPermission)?:
            new UserPermission(user: admin, permission: superPermission)
                    .save(failOnError: true, flush: true)

        def nath = User.findByMail('nath@yopmail.com') ?:
            new User(mail: 'nath@yopmail.com',
                    username: "nath",
                    passwordHash: new Sha512Hash("pass",passwordSalt,1024).toBase64(),
                    passwordSalt:passwordSalt)
//                    .addToRoles(membre)
//                    .addToRoles(adminRole)
        nath.communaute = archamps
        nath.save(flush: true, failOnError: true)

        def joe = User.findByMail('joe@yopmail.com') ?:
            new User(mail: 'joe@yopmail.com',
                    username: "joe",
                    passwordHash: new Sha512Hash("pass",passwordSalt,1024).toBase64(),
                    passwordSalt:passwordSalt)
        joe.communaute = archamps
        joe.save(flush: true, failOnError: true)

    }
    def destroy = {
    }
}
