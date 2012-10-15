package org.echangesin

class Communaute {

    String nom

    static constraints = {
        nom(blank: false, unique: true)
    }


}
