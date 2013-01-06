class UrlMappings {

	static mappings = {

        "/$communauteCode/"                  (controller: 'communaute')
        "/$communauteCode/inviter/"          (controller: 'selInvite')
        "/$communauteCode/admin"		     (controller: "")

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
