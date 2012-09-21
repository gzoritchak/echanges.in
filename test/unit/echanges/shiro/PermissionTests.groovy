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

        def archamps = new Communaute().save()
        def collonges = new Communaute(id: 2L, nom: 'Collonges').save()

        def admin = new Permission(domain: '*', accessType: AccessType.ADMIN )
        assertTrue (admin.implies(new Permission(domain:'Book', accessType: AccessType.CREATE, id: 123L,
                communaute:archamps)))

        def anomUser = new Permission(domain: '*', accessType: AccessType.READ_ANOM)
        assertFalse (anomUser.implies(new Permission(domain:'Book', accessType: AccessType.CREATE, id: 123L,
                communaute:archamps)))
        assertTrue (anomUser.implies(new Permission(domain:'Book', accessType: AccessType.READ_ANOM, id: 123L,
                communaute:archamps)))

        def accesAdminCommunaute =
            new Permission(communaute: archamps)

        assertNotNull(accesAdminCommunaute.communaute)

        def membre = new Permission(domain: 'Communaute', accessType: AccessType.READ, communaute: new Communaute(id:1L))
        assertTrue membre.implies(new Permission(domain: 'Communaute', accessType: AccessType.READ,
                communaute: archamps))

        assertFalse membre.implies(new Permission(domain: 'Communaute', accessType: AccessType.READ,
                communaute: collonges))
    }
}
