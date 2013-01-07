package org.echangesin
/**
 * Gestion des invitations de membres pour un SEL.
 * Les invitations sont possibles par mail, génération de tract/affiches, facebook, ...
 */
class SelInviteController {

    def mailService

    def index() {
        [communauteInstance: request.communaute]
    }

    /**
     * Envoi de l'email aux destinataires
     */
    def send() {
        mailService.sendMail {
           from "g.zoritchak@gmail.com"
           to "g.zoritchak@gmail.com"
           subject "Invitation à échanger"
           text "Je vous invite dans mon SEL..."
        }
        flash.message = "L'email a été envoyé"
        redirect(action: "index", params: [communauteCode: request.user.communaute.code])
    }
}
