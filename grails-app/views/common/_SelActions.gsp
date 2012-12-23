<div class="actions">
    <h4>${request.communaute.nom}</h4>
    <ul class="nav nav-list">
        <li class="active"><a href="#"><i class="icon-home icon-white"></i> Actualités</a></li>
        <li><a href="${createLink(controller:'selInvite',params: [communauteNom:request.communaute.nom])}"><i class="icon-glass"></i> Inviter des membres</a></li>
        <li><a href="#"><i class="icon-user"></i> Voir tous les membres</a></li>
        <li><a href="#"><i class="icon-envelope"></i> Envoyer un message</a></li>
    </ul>
</div>
