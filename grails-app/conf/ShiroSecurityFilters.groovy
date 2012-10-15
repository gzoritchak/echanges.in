import org.echangesin.AccessType
import org.echangesin.Permission
import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject

/**
 * This filters class protects all URLs via access control by convention.
 */
class ShiroSecurityFilters {

//    def dependsOn = [CommunauteFilters]

    def filters = {

        adminGeneral(controller:"admin"){
            before = {
                accessControl{
                    SecurityUtils.subject.isPermitted(new Permission(accessType: AccessType.ADMIN, domain: '*'))
                }
            }
        }

    }
}
