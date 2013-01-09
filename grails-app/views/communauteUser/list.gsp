<g:applyLayout name="sel">

    <h1>Membres de votre communauté</h1>

    <div>
        <g:each in="${userInstanceList}" status="i" var="user">
            <span>
                <avatar:gravatar email="${user.mail}" alt="${user.username}" title="${user.username}" size="128"/>
            </span>
        </g:each>
        <div class="pagination">
            <g:paginate total="${userInstanceTotal}"/>
        </div>
    </div>
</g:applyLayout>