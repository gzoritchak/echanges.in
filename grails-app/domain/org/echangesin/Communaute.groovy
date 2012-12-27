package org.echangesin

/**
 * La représentation d'une communauté, d'un SEL
 */
class Communaute {

    String nom
    void setNom(String nom) {
        this.nom = nom
        this.code = StringUtils.cleanForUrl(nom)
    }
    public String getNom() { return nom}

    /**
     * Code utilisé dans les URLs. Version trimée, nettoyée du nom.
     */
    String code

    @Override
    public String toString() { return nom; }

    static constraints = {
        nom(blank: false, unique: true)
    }


}
