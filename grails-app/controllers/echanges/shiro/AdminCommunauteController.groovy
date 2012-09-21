package echanges.shiro

class AdminCommunauteController {

    def index() {
        render "Administration de la communauté de $request.communaute.nom"
    }
}
