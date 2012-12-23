package org.echangesin

/**
 * La représentation d'une communauté, d'un SEL
 */
class Communaute {

    String nom

    @Override
    public String toString() { return nom; }

    static constraints = {
        nom(blank: false, unique: true)
    }


}
