package org.echangesin



import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {

    void testSomething() {
        def user = new User()
        assertFalse user.validate()
        user.mail
    }
}
