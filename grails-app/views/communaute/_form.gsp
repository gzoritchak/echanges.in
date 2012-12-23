<%@ page import="org.echangesin.Communaute" %>



<div class="fieldcontain ${hasErrors(bean: communauteInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="communaute.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${communauteInstance?.nom}"/>
</div>

