import echanges.shiro.RoleName
import echanges.shiro.User
import org.apache.shiro.authc.AccountException
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.SimpleAccount
import org.apache.shiro.authc.UnknownAccountException

class ShiroDbRealm {

    static authTokenClass = org.apache.shiro.authc.UsernamePasswordToken

    def credentialMatcher
    def shiroPermissionResolver

    def authenticate(authToken) {
        log.info "Attempting to authenticate ${authToken.username} in DB realm..."
        def username = authToken.username

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.")
        }

        def user = User.findByMail(username)
        if (!user) {
            throw new UnknownAccountException("No account found for user [${username}]")
        }

        log.info "Found user '${user.username}' in DB"

        // Now check the user's password against the hashed value stored
        // in the database.
        def account = new SimpleAccount(username,user.passwordHash,new org.apache.shiro.util.SimpleByteSource(user.passwordSalt),"ShiroDbRealm")
//        def account = new SimpleAccount(username, user.passwordHash, "ShiroDbRealm")
        if (!credentialMatcher.doCredentialsMatch(authToken, account)) {
            log.info "Invalid password (DB realm)"
            throw new IncorrectCredentialsException("Invalid password for user '${username}'")
        }

        return account
    }

    def hasRole(principal, roleName) {
        def user = User.findByMail(principal, [fetch:[roles:'join']])
        return user.roles.any {it.name == RoleName.valueOf(roleName)}
    }

    def hasAllRoles(principal, roles) {
        def user = User.findByMail(principal, [fetch:[roles:'join']])
        return user.roles.size() == RoleName.values().size()
    }

    def isPermitted(principal, requiredPermission) {

        if (requiredPermission instanceof echanges.shiro.Permission){
            def permissions = echanges.shiro.Permission.withCriteria{
                user{
                    eq('mail', principal)
                }
            }
            return permissions.any{permission ->
                permission.implies(requiredPermission)
            }
        }else{
            return true
        }
    }
}
