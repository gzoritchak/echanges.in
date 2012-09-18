package echanges.shiro

import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Permission)
class PermissionTests {

    void testAdmin() {
        def admin = new Permission(domain: '*', accessType: AccessType.ADMIN )
        assertTrue (admin.implies(new Permission(domain:'Book', accessType: AccessType.CREATE, id: 123L, new Communaute(id: 1L))))
    }
}
