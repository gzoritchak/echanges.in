package org.echangesin
/**
 * Gestion des invitations de membres pour un SEL.
 * Les invitations sont possibles par mail, génération de tract/affiches, facebook, ...
 */
class SelInviteController {

    def mailService

    def index() {
        [communauteInstance: request.communaute,
                title:"Invitation à échanger",
                message:
                        """\
Bonjour,

Je participe à la mise en place d'un réseau local d'échange.

Le principe est simple : échanger localement des coups de mains, des savoirs, se prêter des livres, des films, des outils,... en utilisant une monnaie d'échanges locale.

Si cela vous intéresse, vous aurez plus d'informations en vous connectant sur notre site internet : http://echanges.in/${request.communaute.code}

Cela ne vous engage à rien, et je serai là pour répondre à toutes vos questions.

A très bientôt,

${request.user.username}
"""
        ]
    }

    /**
     * Envoi de l'email aux destinataires
     */
    def send() {
        mailService.sendMail {
           from request.user.mail
           bcc params.email1, params.email2, params.email3, params.email4 , params.email5 , params.email6 , params.email7 , params.email8 , params.email9 , params.email10
           subject params.title
           text params.message
        }
        flash.message = "L'email a été envoyé"
        redirect(action: "index", params: [communauteCode: request.user.communaute.code])
    }
}
