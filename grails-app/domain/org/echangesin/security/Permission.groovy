package org.echangesin.security

import org.echangesin.Communaute

/**
 * Les permissions d'échanges In.
 *
 * Une permission est définie par un type d'objet domain (Agenda, Blog, ...) et par un type d'accès. On peut également
 * préciser l'instance sur lequel cette permission porte en indiquant l'id. Ainsi, un utilisateur peut avoir un accès
 * ADMIN sur l'objet domain User correspondant à lui-même et n'avoir accès aux autres instances qu'en mode READ_ANOM.
 */
class Permission implements org.apache.shiro.authz.Permission{

    /**
     * Le type d'objet sur lequel porte la permission : Blog, User, ... ou le wildcard *.
     * Le nom correspond à l'entité.
     * Le wildcard est essentiellement utilisé pour les administrateurs qui peuvent accéder à tous les types
     * d'entité.
     *
     * Le domain est case sensitive.
     */
    String domain

    /**
     * L'identifiant de l'objet domaine accédé. La plupart du temps ce champs sera nul pour indiquer que la permission
     * porte sur toutes les instances du type d'objet.
     *
     * Dans quelques rares cas, cet id sera précisé pour limiter l'accès à une instance particulière.
     * Ex : seul l'utilisateur a le droit de modifier ses propres données.
     */
    Long id

    /**
    * Dans la plupart des cas on définira les accès en fonction de l'appartenance à une communauté. Si j'appartiens
     * à la communauté d'Archamps, j'ai la permission de lecture de toutes les offres en cours, les membres, d'Archamps.
     * Pas besoin de descendre au niveau des instances.
     */
    Communaute communaute

    /**
     * Le type d'accès testé (READ, READ_ANOM, CREATE, WRITE, DELETE, ADMIN)
     */
    AccessType accessType


    static constraints = {
        domain(nullable: false)
        accessType(nullable: false)
        communaute(nullable: true)
    }

    /**
     * Implémentation des règles de permission.
     * @param permission
     * @return
     */
    boolean implies(org.apache.shiro.authz.Permission permission) {

        //On n'autorise la comparaison qu'avec des permissions de même type
        if (permission instanceof Permission) {
            Permission perm = (Permission) permission;

            //Vérification par domaine. Autorisation si wildcard ou même domaine.
            if (!domain.equals('*') && !domain.equals(perm.domain)) return false

            //Vérification type accès. Autorisation si ADMIN ou même type d'accès
            if (!accessType.equals(AccessType.ADMIN) && !accessType.equals(perm.accessType))return false

            //si l'id est positionné, on vérifie qu'ils correspondent
            if (perm.id && id) {
                if (!id.equals(perm.id)) return false
            }

            //Si la communauté est positionnée dans la permission recherchée, on vérifie qu'elles correspondent.
            //la vérification est réalisée par le nom, puisqu'il sera systématiquement présent dans les URLs.
            if (perm.communaute)
                if (communaute && !communaute.nom.equals(perm.communaute.nom)) return false

            return true

        }
        return false
    }

    /**
     * L'id et la version sont volontairement exclu de l'évaluation de l'égalité.
     */
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Permission that = (Permission) o

        if (accessType != that.accessType) return false
        if (communaute != that.communaute) return false
        if (domain != that.domain) return false

        return true
    }

    int hashCode() {
        int result
        result = domain.hashCode()
        result = 31 * result + (communaute != null ? communaute.hashCode() : 0)
        result = 31 * result + accessType.hashCode()
        return result
    }
}
