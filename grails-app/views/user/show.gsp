<%@ page import="org.echangesin.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="span12">
            <div id="show-user" class="content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]"/></h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <ol class="property-list user">

                    <g:if test="${userInstance?.username}">
                        <li class="fieldcontain">
                            <span id="username-label" class="property-label"><g:message code="user.username.label"
                                                                                        default="Username"/></span>

                            <span class="property-value" aria-labelledby="username-label"><g:fieldValue
                                    bean="${userInstance}" field="username"/></span>

                        </li>
                    </g:if>

                    <g:if test="${userInstance?.mail}">
                        <li class="fieldcontain">
                            <span id="mail-label" class="property-label"><g:message code="user.mail.label"
                                                                                    default="Mail"/></span>

                            <span class="property-value" aria-labelledby="mail-label"><g:fieldValue
                                    bean="${userInstance}" field="mail"/></span>

                        </li>
                    </g:if>

                    <g:if test="${userInstance?.communaute}">
                        <li class="fieldcontain">
                            <span id="communaute-label" class="property-label"><g:message code="user.communaute.label"
                                                                                          default="Communaute"/></span>

                            <span class="property-value" aria-labelledby="communaute-label"><g:link
                                    controller="communaute" action="show"
                                    id="${userInstance?.communaute?.id}">${userInstance?.communaute?.encodeAsHTML()}</g:link></span>

                        </li>
                    </g:if>
                </ol>
                <g:form>
                    <fieldset class="buttons">
                        <g:hiddenField name="id" value="${userInstance?.id}"/>
                        <g:link class="edit" action="edit" id="${userInstance?.id}"><g:message
                                code="default.button.edit.label" default="Edit"/></g:link>
                        <g:actionSubmit class="delete" action="delete"
                                        value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                    </fieldset>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
