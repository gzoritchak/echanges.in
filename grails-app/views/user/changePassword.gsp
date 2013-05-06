<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title>Changer votre mot de passe/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="span12">

            <div id="change-password" class="content scaffold-edit" role="main">
                <h1><g:message code="user.password.change" /></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <g:hasErrors bean="${changePasswordCommand}">
                    <ul class="errors" role="alert">
                        <g:eachError bean="${changePasswordCommand}" var="error">
                            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                                    error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>
                <g:form method="post" enctype="multipart/form-data" action="changePasswordSave">
                    <fieldset class="form">
                        <div class="fieldcontain ${hasErrors(bean: changePasswordCommand, field: 'currentPassword', 'error')} required">
                            <label for="currentPassword">
                                <g:message code="user.password.current" default="Mot de passe actuel" />
                                <span class="required-indicator">*</span>
                            </label>
                            <g:field type="password" name="currentPassword" required="" value="${changePasswordCommand?.currentPassword}"
                                         rel="popover" data-placement="right"/>
                        </div>

                        <div class="fieldcontain ${hasErrors(bean: changePasswordCommand, field: 'newPassword', 'error')} required">
                            <label for="newPassword">
                                <g:message code="user.password.new" default="Nouveau mot de passe" />
                                <span class="required-indicator">*</span>
                            </label>
                            <g:field type="password" name="newPassword" required="" value="${changePasswordCommand?.newPassword}"
                                     rel="popover" data-placement="right"
                                     data-original-title="Nouveau mot de passe, minimum 6 caractères"
                                     />
                        </div>
                        <div class="fieldcontain ${hasErrors(bean: changePasswordCommand, field: 'confirmPassword', 'error')} required">
                            <label for="confirmPassword">
                                <g:message code="user.password.confirm" default="Confirmez le nouveau mot de passe" />
                                <span class="required-indicator">*</span>
                            </label>
                            <g:field type="password" name="confirmPassword" required="" value="${changePasswordCommand?.confirmPassword}"
                                     rel="popover" data-placement="right"
                                     />
                        </div>

                        <r:script> $(':input').popover({trigger:'focus'}); </r:script>

                    </fieldset>
                    <fieldset class="buttons">
                        <g:actionSubmit class="save" action="changePasswordSave"
                                        value="Modifier le mot de passe"/>
                    </fieldset>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
