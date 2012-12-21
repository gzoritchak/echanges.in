package org.echangesin

class Communaute {

    String nom

    @Override
    public String toString() { return nom; }

    static constraints = {
        nom(blank: false, unique: true)
    }


}
