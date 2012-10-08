import echanges.shiro.User
import org.apache.shiro.authc.AccountException
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.SimpleAccount
import org.apache.shiro.authc.UnknownAccountException

/**
 * Classe centralisant les fonctions de vérification de la sécurité dont l'authentification
 * et la vérification des droits (permissions)
 */
class ShiroDbRealm {

    static authTokenClass = org.apache.shiro.authc.UsernamePasswordToken

    def credentialMatcher

    def shiroPermissionResolver

    /**
     * Authentification de l'utilisateur, via login (email) et mot de passe.
     * @param authToken
     * @return
     */
    def authenticate(authToken) {
        log.info "Attempting to authenticate ${authToken.username} in DB realm..."
        def username = authToken.username

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Il doit y avoir un login (email) pour réaliser l'authentification.")
        }

        def user = User.findByMail(username)
        if (!user) {
            throw new UnknownAccountException("No account found for user [${username}]")
        }

        log.info "Found user '${user.username}' in DB"

        def account = new SimpleAccount(username, user.passwordHash,
                new org.apache.shiro.util.SimpleByteSource(user.passwordSalt), "ShiroDbRealm")
//        def account = new SimpleAccount(username, user.passwordHash, "ShiroDbRealm")
        if (!credentialMatcher.doCredentialsMatch(authToken, account)) {
            log.info "Invalid password (DB realm)"
            throw new IncorrectCredentialsException("Invalid password for user '${username}'")
        }

        return account
    }

    /**
     * Vérification des droits : l'utilisateur dispose-t-il de la permission nécessaire?
     * @param principal le login de l'utilisateur en cours
     * @param requiredPermission la permission recherchée.
     * @return
     */
    def isPermitted(principal, requiredPermission) {

        if (requiredPermission instanceof echanges.shiro.Permission) {
            def User user = User.findByMail(principal)
            return user.permissions.any {permission ->
                permission.implies(requiredPermission)
            }
        } else {
            return false
        }
    }
}
