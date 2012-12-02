<%@ page import="org.echangesin.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'mail', 'error')} required">
	<label for="mail">
		<g:message code="user.mail.label" default="Mail" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="mail" required="" value="${userInstance?.mail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'communaute', 'error')} ">
	<label for="communaute">
		<g:message code="user.communaute.label" default="Communaute" />
		
	</label>
	<g:select id="communaute" name="communaute.id" from="${org.echangesin.Communaute.list()}" optionKey="id" value="${userInstance?.communaute?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordHash', 'error')} ">
	<label for="passwordHash">
		<g:message code="user.passwordHash.label" default="Password Hash" />
		
	</label>
	<g:textField name="passwordHash" value="${userInstance?.passwordHash}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordSalt', 'error')} required">
	<label for="passwordSalt">
		<g:message code="user.passwordSalt.label" default="Password Salt" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="passwordSalt" name="passwordSalt" />
</div>

