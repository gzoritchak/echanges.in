import org.apache.shiro.SecurityUtils
import org.echangesin.AccessType
import org.echangesin.Permission
import org.echangesin.User

/**
 * This filters class protects all URLs via access control by convention.
 */
class ShiroSecurityFilters {

//    def dependsOn = [CommunauteFilters]

    def filters = {

        allURIs(uri: '/**') {
            before = {
                if (SecurityUtils.subject.isAuthenticated())
                    request.user = User.findByMail(SecurityUtils.subject.getPrincipal() as String)
            }
        }

        adminGeneral(controller:"admin"){
            before = {
                accessControl{
                    SecurityUtils.subject.isPermitted(new Permission(accessType: AccessType.ADMIN, domain: '*'))
                }
            }
        }

    }
}
