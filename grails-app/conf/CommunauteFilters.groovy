import org.echangesin.Communaute

class CommunauteFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                if(params.communauteNom){
                    request.communaute = Communaute.findByNom(params.communauteNom)
                }

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
