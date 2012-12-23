package org.echangesin



import org.junit.*
import grails.test.mixin.*

@TestFor(CommunauteController)
@Mock(Communaute)
class CommunauteControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/communaute/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.communauteInstanceList.size() == 0
        assert model.communauteInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.communauteInstance != null
    }

    void testSave() {
        controller.save()

        assert model.communauteInstance != null
        assert view == '/communaute/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/communaute/show/1'
        assert controller.flash.message != null
        assert Communaute.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/communaute/list'

        populateValidParams(params)
        def communaute = new Communaute(params)

        assert communaute.save() != null

        params.id = communaute.id

        def model = controller.show()

        assert model.communauteInstance == communaute
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/communaute/list'

        populateValidParams(params)
        def communaute = new Communaute(params)

        assert communaute.save() != null

        params.id = communaute.id

        def model = controller.edit()

        assert model.communauteInstance == communaute
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/communaute/list'

        response.reset()

        populateValidParams(params)
        def communaute = new Communaute(params)

        assert communaute.save() != null

        // test invalid parameters in update
        params.id = communaute.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/communaute/edit"
        assert model.communauteInstance != null

        communaute.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/communaute/show/$communaute.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        communaute.clearErrors()

        populateValidParams(params)
        params.id = communaute.id
        params.version = -1
        controller.update()

        assert view == "/communaute/edit"
        assert model.communauteInstance != null
        assert model.communauteInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/communaute/list'

        response.reset()

        populateValidParams(params)
        def communaute = new Communaute(params)

        assert communaute.save() != null
        assert Communaute.count() == 1

        params.id = communaute.id

        controller.delete()

        assert Communaute.count() == 0
        assert Communaute.get(communaute.id) == null
        assert response.redirectedUrl == '/communaute/list'
    }
}
