class UrlMappings {

	static mappings = {

        "/$communauteCode/"                                 (controller: 'communaute')
        "/$communauteCode/inviter/$action?/$id?"            (controller: 'selInvite')
        "/$communauteCode/membres/$action?/$id?"            (controller: 'communauteUser')
        "/$communauteCode/admin"		                    (controller: "")

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/faq"(view:"/faq/index")
        "/system"(view:"/system")
        "/"(controller:'home')
        "500"(view:'/error')
    }
}
