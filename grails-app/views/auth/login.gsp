<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="2cols"/>
    <title>S'authentifier</title>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="span12">
            <h2>Saisissez vos identifiants</h2>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:form action="signIn">
                <input type="hidden" name="targetUri" value="${targetUri}"/>
                <table>
                    <tbody>
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="username" value="${username}"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" value=""/></td>
                    </tr>
                    <tr>
                        <td>Se souvenir de moi ?:</td>
                        <td><g:checkBox name="rememberMe" value="${rememberMe}"/></td>
                    </tr>
                    <tr>
                        <td/>
                        <td><input type="submit" value="Se connecter"/></td>
                    </tr>
                    </tbody>
                </table>
            </g:form>
        </div>
    </div>
</div>

</body>
</html>
