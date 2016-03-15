define([
    'jquery',
    'backbone',
    'core/BaseView',
    'models/PerfilModel',
    'views/private/util/ModalGenericView',
    'views/private/perfil/FilesView',
    'collections/FilesCollection',
    'text!templates/private/perfil/tplPerfilAdmin.html'
], function($, Backbone, BaseView, PerfilModel, ModalGenericView, FilesView, FilesCollection, tplPerfilAdmin){

    var PerfilAdminView = BaseView.extend({
        template: _.template(tplPerfilAdmin),

        events: {
            'click #btn-upload' : 'uploadFile'
        },

        initialize: function() {
            this.model = new PerfilModel();
            this.files = new FilesCollection();
            this.listenTo(this.files, 'add', this.agregarFile);
            this.listenTo(this.files, 'sync', this.syncFiles);
            this.files.fetch();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        uploadFile: function(){
        },

        agregarFile: function(modelo){
            var vista = new FilesView(modelo);
            $("#grid-data").find('tbody:last').append(vista.render().$el);
        },

        syncFiles: function(){
        }

    });

    return PerfilAdminView;

});