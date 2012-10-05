package echanges.shiro

/**
 * Les permissions d'échanges In.
 *
 * Une permission est définie par un type de domain (Agenda, Blog, ...) et par un type d'accès. On
 *
 */
class Permission implements org.apache.shiro.authz.Permission{

    /**
     * Le type d'objet sur lequel porte la permission : Blog, User, ... ou le wildcard *.
     * Le nom correspond à l'entité.
     * Le wildcard est essentiellement utilisé pour les administrateurs qui peuvent accéder à tous les types
     * d'entité.
     *
     * le domain est case insensitive.
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
}
