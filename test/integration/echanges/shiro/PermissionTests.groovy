package echanges.shiro

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin

/**
 * Test d'intégration sur les Permissions.
 */
@TestFor(Permission)
@TestMixin(DomainClassUnitTestMixin)
class PermissionTests {

    void testAdmin() {

        def archamps = new Communaute(id:1L, nom: 'Archamps').save()
        def collonges = new Communaute(id: 2L, nom: 'Collonges').save()

        //Un administrateur à le droit de création sur le domaine Book de la communauté d'Archamps
        def admin = new Permission(domain: '*', accessType: AccessType.ADMIN ).save()
        println admin.implies(new Permission(domain:'Book', accessType: AccessType.CREATE, id: 123L, communaute: archamps))

        //Un utilisateur anonyme ne peut ni lire, ni créer, ni modifier, ni supprimer un objet de type Book de la communauté d'Archamps
        def anomUser = new Permission(domain: '*', accessType: AccessType.READ_ANOM)
        assert !anomUser.implies(new Permission(domain:'Book', accessType: AccessType.READ, id: 123L, communaute: archamps))
        assert !anomUser.implies(new Permission(domain:'Book', accessType: AccessType.CREATE, communaute: archamps))
        assert !anomUser.implies(new Permission(domain:'Book', accessType: AccessType.WRITE,  communaute: archamps))
        assert !anomUser.implies(new Permission(domain:'Book', accessType: AccessType.DELETE, id: 123L, communaute: archamps))
        assert anomUser.implies(new Permission(domain:'Book', accessType: AccessType.READ_ANOM, id: 123L, communaute: archamps))

        def accesAdminCommunaute = new Permission(communaute: archamps)

        assert accesAdminCommunaute.communaute != null

        //Un membre qui a la permission de lecture sur les objets de la communauté d'Archamps peut
        // lire les données de l'objet Communauté
        def membre = new Permission(domain: '*', accessType: AccessType.READ, communaute: archamps)
        assert membre.implies(new Permission(domain: 'Communaute', accessType: AccessType.READ, communaute: archamps))

        //Mais il n'a pas accès en lecture à la communauté voisine
        assert !membre.implies(new Permission(domain: 'Communaute', accessType: AccessType.READ, communaute: collonges))
    }
}
