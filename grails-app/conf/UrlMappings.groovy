class UrlMappings {

	static mappings = {

        "/$communauteNom/"                  (controller: 'communaute')
        "/$communauteNom/inviter/"          (controller: 'selInvite')
        "/$communauteNom/admin"		        (controller: "")

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/system"(view:"/system")
        "/"(controller:'home')
        "500"(view:'/error')
    }
}
