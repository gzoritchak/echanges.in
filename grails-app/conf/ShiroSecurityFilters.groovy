import echanges.shiro.RoleName

/**
 * This filters class protects all URLs via access control by convention.
 */
class ShiroSecurityFilters {

    def filters = {

        adminGeneral(controller:"admin"){
            before = {
                accessControl{
                    role('SUPER_USER')
                }
            }
        }

    }
}
