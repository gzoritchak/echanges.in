class UrlMappings {

	static mappings = {
        "/$communaute/admin"		(controller: "")
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/system"(view:"/system")
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
