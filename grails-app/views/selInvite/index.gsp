<g:applyLayout name="sel">
    <h1>Inviter des membres</h1>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form action="send" enctype="multipart/form-data">
        <fieldset class="form">

            <div class="row">
                <div class="span3">
                    <label>Emails des destinataires</label>
                    <g:field type="email" name="email1" placeholder="Email du 1er destinataire"/>
                    <g:field type="email" name="email2" placeholder="Email du 2ème destinataire"/>
                    <g:field type="email" name="email3" placeholder="Email du 3ème destinataire"/>
                    <g:field type="email" name="email4" placeholder="Email du 4ème destinataire"/>
                    <g:field type="email" name="email5" placeholder="Email du 5ème destinataire"/>
                    <g:field type="email" name="email6" placeholder="Email du 6ème destinataire"/>
                    <g:field type="email" name="email7" placeholder="Email du 7ème destinataire"/>
                    <g:field type="email" name="email8" placeholder="Email du 8ème destinataire"/>
                    <g:field type="email" name="email9" placeholder="Email du 9ème destinataire"/>
                    <g:field type="email" name="email10" placeholder="Email du 10ème destinataire"/>
                </div>

                <div class="span5">
                    <label>Objet du message</label>
                    <g:textField name="title" placeholder="Titre du message" alt="Titre du message"
                                 value="Invitation à échanger"/>
                    <g:textArea name="message" rows="13" class="span5" cols="20"
                                value="Bonjour,

Je participe à la mise en place d'un réseau local d'échange.

Le principe est simple : échanger localement des coups de mains, des savoirs, se prêter des livres, des films, des outils,... en utilisant une monnaie d'échanges locale.

Si cela vous intéresse, vous aurez plus d'informations en vous connectant sur notre site internet : http://echanges.in/archamps

Cela ne vous engage à rien, et je serai là pour répondre à toutes vos questions.

A très bientôt,

Gaetan
"/>
                    <g:submitButton name="create" class="save btn btn-success"
                                    value="Envoyer l'email"/>

                </div>
            </div>

        </fieldset>

    </g:form>

</g:applyLayout>