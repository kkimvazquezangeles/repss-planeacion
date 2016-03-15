define([
    'jquery',
    'backbone',
    'core/BaseView',
    'views/private/util/ModalGenericView',
    'views/private/perfil/FilesView',
    'collections/FilesCollection',
    'text!templates/private/perfil/tplPerfilAdmin.html'
], function($, Backbone, BaseView, ModalGenericView, FilesView, FilesCollection, tplPerfilAdmin){

    var PerfilAdminView = BaseView.extend({
        template: _.template(tplPerfilAdmin),

        events: {
            'click #btn-upload' : 'uploadFile'
        },

        initialize: function() {
            this.files = new FilesCollection();
            this.listenTo(this.files, 'add', this.agregarFile);
            this.listenTo(this.files, 'sync', this.syncFiles);
            this.files.fetch();
        },

        render: function() {
            return this;
        },

        uploadFile: function(){
        },

        agregarFile: function(modelo){
            new FilesView(modelo);
        },

        syncFiles: function(){
        }

    });

    return PerfilAdminView;

});