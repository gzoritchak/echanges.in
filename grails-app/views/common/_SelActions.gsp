<div class="actions">
    <h4>${communaute?.nom}</h4>
    <ul class="nav nav-list">
        <g:set var="current" value="${request.forwardURI}" />
        <g:set var="actu" value="${createLink(controller: 'communaute', params: [communauteCode:user.communaute.code])}" />
        <g:set var="invit" value="${createLink(controller: 'selInvite', params: [communauteCode:user.communaute.code])}" />

        <li <g:if test="${current == actu}" >class="active"</g:if> ><a href="${actu}"><i class="icon-home <g:if test="${current == actu}" >icon-white</g:if>"></i> Actualités</a></li>
        <li <g:if test="${current == invit}" >class="active"</g:if> ><a href="${invit}"><i class="icon-glass <g:if test="${current == invit}" >icon-white</g:if>"></i> Inviter des membres</a></li>
        <li><a href="#"><i class="icon-user"></i> Voir tous les membres</a></li>
        <li><a href="#"><i class="icon-envelope"></i> Envoyer un message</a></li>
    </ul>
</div>
