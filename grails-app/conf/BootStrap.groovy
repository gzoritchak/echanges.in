import echanges.shiro.Communaute
import echanges.shiro.User
import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.Sha512Hash

class BootStrap {

    def init = { servletContext ->

//        def superUserRole = Role.findByName(RoleName.SUPER_USER)?: new Role(name: RoleName.SUPER_USER).save(failOnError: true)
//        def adminRole = Role.findByName(RoleName.ADMIN)?: new Role(name: RoleName.ADMIN).save(failOnError: true)
//        def auteurRole = Role.findByName(RoleName.AUTEUR)?: new Role(name: RoleName.AUTEUR).save(failOnError: true)
//        def membre = Role.findByName(RoleName.MEMBRE)?: new Role(name: RoleName.MEMBRE).save(failOnError: true)

        def archamps = Communaute.findByNom("archamps")?: new Communaute(nom: "archamps").save(failOnError: true)

        def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()

        User.findByMail("g.zoritchak@gmail.com")?:
            new User(mail: "g.zoritchak@gmail.com",
                    username: "Gaetan",
                    passwordHash: new Sha512Hash("pass",passwordSalt,1024).toBase64(),
                    passwordSalt: passwordSalt)
//                    .addToRoles(superUserRole)
                    .save(failOnError: true)

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
