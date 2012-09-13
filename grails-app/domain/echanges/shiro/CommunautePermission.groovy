package echanges.shiro

/**
 * Une permission sur une communauté. La permission est ajoutée à l'utilisateur lorsqu'il rentre dans une
 * communauté.
 */
class CommunautePermission extends Permission{

    Communaute communaute

    @Override
    boolean implies(org.apache.shiro.authz.Permission permission) {
        if (permission instanceof CommunautePermission){
            if (communaute.id == permission.communaute?.id){
                return true;
            }
        }
        return false;
    }

    @Override
    String toString() {"CommunautePermission : ${communaute}" }
}
