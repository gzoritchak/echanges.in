package org.echangesin.echanges

class Tag {

    static belongsTo = EchangeDef
    static hasMany = [usedBy:EchangeDef]
    static constraints = {
    }

    String nom;
    Tag parent;
}
