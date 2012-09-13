package echanges.shiro

class Permission implements org.apache.shiro.authz.Permission{

    static belongsTo = [user:User]

    static constraints = {
    }

    boolean implies(org.apache.shiro.authz.Permission permission) {
        return false
    }
}
