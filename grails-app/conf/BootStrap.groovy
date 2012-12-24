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

    def createAdminArchamps(String mail) {
        def archamps = Communaute.findByNom("archamps") ?: new Communaute(nom: "archamps").save(failOnError: true)
        def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()
        def admin = User.findByMail(mail) ?:
            new User(mail: mail,
                    username: mail,
                    passwordHash: new Sha512Hash("pass", passwordSalt, 1024).toBase64(),
                    passwordSalt: passwordSalt)
                    .save(failOnError: true)

        Permission superPermission = Permission.findByCommunauteAndAccessTypeAndDomainAndCommunaute(
                null, AccessType.ADMIN, '*', null) ?: new Permission(accessType: AccessType.ADMIN, domain: '*')
                .save(failOnError: true, flush: true)

        //le cas échéant, on affecte la permission à l'utilisateur
        UserPermission.findByUserAndPermission(admin, superPermission) ?:
            new UserPermission(user: admin, permission: superPermission)
                    .save(failOnError: true, flush: true)

        admin.communaute = archamps
        admin.save(flush: true, failOnError: true)
    }

    def init = { servletContext ->
        createAdminArchamps('agnes.crepet@gmail.com')
        createAdminArchamps('eponty@mageos.com')
        createAdminArchamps('g.zoritchak@gmail.com')
        createAdminArchamps('cyril.lacote@gmail.com')
    }
    def destroy = {
    }
}
