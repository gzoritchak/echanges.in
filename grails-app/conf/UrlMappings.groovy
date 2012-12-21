class UrlMappings {

	static mappings = {


        "/$communaute/admin"		(controller: "")
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
