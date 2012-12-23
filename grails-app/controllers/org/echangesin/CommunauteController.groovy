package org.echangesin

import org.springframework.dao.DataIntegrityViolationException

class CommunauteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        [communauteInstance: request.communaute]
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [communauteInstanceList: Communaute.list(params), communauteInstanceTotal: Communaute.count()]
    }

    def create() {
        [communauteInstance: new Communaute(params)]
    }

    def save() {
        def communauteInstance = new Communaute(params)
        if (!communauteInstance.save(flush: true)) {
            render(view: "create", model: [communauteInstance: communauteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'communaute.label', default: 'Communaute'), communauteInstance.id])
        redirect(action: "show", id: communauteInstance.id)
    }

    def show(Long id) {
        def communauteInstance = Communaute.get(id)
        if (!communauteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'communaute.label', default: 'Communaute'), id])
            redirect(action: "list")
            return
        }

        [communauteInstance: communauteInstance]
    }

    def edit(Long id) {
        def communauteInstance = Communaute.get(id)
        if (!communauteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'communaute.label', default: 'Communaute'), id])
            redirect(action: "list")
            return
        }

        [communauteInstance: communauteInstance]
    }

    def update(Long id, Long version) {
        def communauteInstance = Communaute.get(id)
        if (!communauteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'communaute.label', default: 'Communaute'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (communauteInstance.version > version) {
                communauteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'communaute.label', default: 'Communaute')] as Object[],
                        "Another user has updated this Communaute while you were editing")
                render(view: "edit", model: [communauteInstance: communauteInstance])
                return
            }
        }

        communauteInstance.properties = params

        if (!communauteInstance.save(flush: true)) {
            render(view: "edit", model: [communauteInstance: communauteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'communaute.label', default: 'Communaute'), communauteInstance.id])
        redirect(action: "show", id: communauteInstance.id)
    }

    def delete(Long id) {
        def communauteInstance = Communaute.get(id)
        if (!communauteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'communaute.label', default: 'Communaute'), id])
            redirect(action: "list")
            return
        }

        try {
            communauteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'communaute.label', default: 'Communaute'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'communaute.label', default: 'Communaute'), id])
            redirect(action: "show", id: id)
        }
    }
}
