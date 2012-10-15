package org.echangesin

class CommunauteFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                if(request.communauteNom){
                    request.communaute = Communaute.findByNom(request.communauteNom)
                }

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
