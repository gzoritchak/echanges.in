import echanges.shiro.RoleName
import echanges.shiro.CommunautePermission

/**
 * This filters class protects all URLs via access control by convention.
 */
class ShiroSecurityFilters {

    def dependsOn = [CommunauteFilters]

    def filters = {

        adminGeneral(controller:"admin"){
            before = {
                accessControl{
                    role(RoleName.SUPER_USER.name())
                }
            }
        }

        adminSEL(controller:"adminsel"){
            before = {
                accessControl{
                    permission(new CommunautePermission(communaute: request.communaute))
                    role(RoleName.ADMIN.name()) && permission()
                }
            }
        }

    }
}
