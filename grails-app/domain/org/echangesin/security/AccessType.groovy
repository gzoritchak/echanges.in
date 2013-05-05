package org.echangesin.security

/**
 * Le type d'accès à une donnée.
 *
 * En plus les accès classiques on ajoute l'accès de type READ_ANOM qui consiste à laisser l'accès en lecture
 * mais de manière anonymisée. On ne voit une partie des informations mais sans les noms, prénoms, téléphones,...
 *
 * L'accès ADMIN contient tous les autres accès.
 */
public enum AccessType {
    READ_ANOM, READ, CREATE, WRITE, DELETE, ADMIN

}