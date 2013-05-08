package org.echangesin.user
import grails.test.mixin.TestMixin
import grails.test.mixin.web.ControllerUnitTestMixin
import org.junit.Before

import static grails.test.MockUtils.mockCommandObject

@TestMixin(ControllerUnitTestMixin)
class ChangePasswordCommandTests {

    def ChangePasswordCommand cmd

    @Before
    public void setUp(){
        mockCommandObject ChangePasswordCommand
        cmd = new ChangePasswordCommand(currentPassword: 'current',
                newPassword: 'password',
                confirmPassword: 'password' )
        cmd.clearErrors()
    }

    void testConfirmationDifferente() {
        cmd.currentPassword = '123'
        cmd.newPassword = '123456'
        cmd.confirmPassword = '1234'
        assertFalse cmd.validate()
        assertNotNull cmd.errors['confirmPassword']
    }

    void testNewPasswordTooSmall() {
        cmd.currentPassword = '123'
        cmd.newPassword = '1234'
        cmd.confirmPassword = '1234'
        assertFalse cmd.validate()
        assertNotNull cmd.errors['newPassword']
        assertNull cmd.errors['confirmPassword']
    }
}
