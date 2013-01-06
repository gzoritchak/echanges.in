<%@ page import="org.echangesin.User" %>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Nom affiché" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"
                 rel="popover" data-placement="right"
                 data-original-title="Nom affiché"
                 data-content="Nom affiché aux membres de votre SEL. Les utilisateurs non authentifiés et ne
                  faisant pas partie de votre SEL n'y auront pas accès. Utiliser votre prénom suivi
                  de votre nom facilite votre identification par les autres membres du SEL."/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'mail', 'error')} required">
	<label for="mail">
		<g:message code="user.mail.label" default="Adresse mail" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="mail" required="" value="${userInstance?.mail}"
             rel="popover" data-placement="right"
             data-original-title="Adresse mail"
             data-content="Votre adresse mail est également votre identifiant. Cette adresse doit être valide. Elle
             vous permettra, le cas échéant, de réinitialiser votre mot de passe."/>

</div>

<r:script> $(':input').popover({trigger:'focus'}); </r:script>


