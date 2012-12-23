package org.echangesin

/**
 * Gestion des invitations de membres pour un SEL.
 * Les invitations sont possibles par mail, génération de tract/affiches, facebook, ...
 */
class SelInviteController {

    def index() {
        [communauteInstance: request.communaute]
    }
}
