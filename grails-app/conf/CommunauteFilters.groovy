import org.echangesin.Communaute

class CommunauteFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                if(params.communauteCode){
                    request.communaute = Communaute.findByCode(params.communauteCode)
                }

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
