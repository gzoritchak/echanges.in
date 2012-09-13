import echanges.shiro.*

class BootStrap {

    def init = { servletContext ->

        def superUserRole = Role.findByName(RoleName.SUPER_USER)?: new Role(name: RoleName.SUPER_USER).save(failOnError: true)
        def adminRole = Role.findByName(RoleName.ADMIN)?: new Role(name: RoleName.ADMIN).save(failOnError: true)
        def auteurRole = Role.findByName(RoleName.AUTEUR)?: new Role(name: RoleName.AUTEUR).save(failOnError: true)
        def membre = Role.findByName(RoleName.MEMBRE)?: new Role(name: RoleName.MEMBRE).save(failOnError: true)

        def archamps = Communaute.findByNom("archamps")?: new Communaute(nom: "archamps").save(failOnError: true)


        def gaetan = User.findByMail("g.zoritchak@gmail.com")?: new User(mail: "g.zoritchak@gmail.com").save(failOnError: true)
        if(!archamps.id == gaetan.communaute?.id){ gaetan.communaute = archamps; }
        if(!gaetan.roles.contains(superUserRole)){ gaetan.addToRoles(superUserRole); }
    }
    def destroy = {
    }
}
