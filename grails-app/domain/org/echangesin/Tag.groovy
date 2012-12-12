package org.echangesin

class Tag {

    static belongsTo = EchangeDef
    static hasMany = [usedBy:EchangeDef]
    static constraints = {
    }

    String nom;
    Tag parent;
}
