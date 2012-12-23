class UrlMappings {

	static mappings = {

        "/$communauteNom/"                  (controller: 'communaute', action: 'index')
        "/$communauteNom/inviter/$action?"  (controller: 'selInvite')
        "/$communauteNom/admin"		        (controller: "")

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/system"(view:"/system")
        "/"(controller:'home',action:'index')
        "500"(view:'/error')
    }
}
