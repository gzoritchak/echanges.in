package org.echangesin

class HomeController {

    def index() {
        if (request.user?.communaute)
            redirect(controller: 'communaute', action: 'index', params: [communauteCode: request.user.communaute.code])
    }
}
