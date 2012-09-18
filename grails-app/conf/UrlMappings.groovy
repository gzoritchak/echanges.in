class UrlMappings {

	static mappings = {
        "/$communaute/admin"		(controller: "")
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
