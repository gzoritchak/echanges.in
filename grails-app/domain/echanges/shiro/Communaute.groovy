package echanges.shiro

class Communaute {

    String nom

    static constraints = {
        nom(blank: false, unique: true)
    }


}
