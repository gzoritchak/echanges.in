package echanges.shiro

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Permission)
@TestMixin(DomainClassUnitTestMixin)
class PermissionTests {

    void testAdmin() {

        def archamps = new Communaute(id:1L, nom: 'Archamps').save()
        def collonges = new Communaute(id: 2L, nom: 'Collonges').save()

        def admin = new Permission(domain: '*', accessType: AccessType.ADMIN )
        println admin.implies(new Permission(domain:'Book', accessType: AccessType.CREATE, id: 123L, communaute: archamps))

        def anomUser = new Permission(domain: '*', accessType: AccessType.READ_ANOM)
        assert !anomUser.implies(new Permission(domain:'Book', accessType: AccessType.CREATE, id: 123L, communaute: archamps))
        assert anomUser.implies(new Permission(domain:'Book', accessType: AccessType.READ_ANOM, id: 123L, communaute: archamps))

        def accesAdminCommunaute = new Permission(communaute: archamps)

        assert accesAdminCommunaute.communaute != null

        def membre = new Permission(domain: 'Communaute', accessType: AccessType.READ, communaute: archamps)
        assert membre.implies(new Permission(domain: 'Communaute', accessType: AccessType.READ, communaute: archamps))

        assert !membre.implies(new Permission(domain: 'Communaute', accessType: AccessType.READ, communaute: collonges))
    }
}
