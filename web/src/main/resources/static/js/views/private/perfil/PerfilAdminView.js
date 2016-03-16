define([
    'jquery',
    'backbone',
    'core/BaseView',
    'models/PerfilModel',
    'views/private/util/ModalGenericView',
    'views/private/perfil/FilesView',
    'collections/FilesCollection',
    'text!templates/private/perfil/tplPerfilAdmin.html',
    'Session'
], function($, Backbone, BaseView, PerfilModel,
            ModalGenericView, FilesView, FilesCollection,
            tplPerfilAdmin, Session){

    var PerfilAdminView = BaseView.extend({
        template: _.template(tplPerfilAdmin),

        events: {
            'click #btn-upload'     : 'uploadFile',
            'click .menu-action'    : 'opcionMenu'
        },

        initialize: function() {
            this.model = new PerfilModel();
            this.model.set({id: Session.get('username')});
            this.files = new FilesCollection();
            this.listenTo(this.files, 'add', this.agregarFile);
            this.listenTo(this.files, 'sync', this.syncFiles);

            this.listenTo(this.model, 'sync', this.syncPerfil);
            this.model.fetch();
        },

        render: function() {
            return this;
        },

        uploadFile: function(){
        },

        agregarFile: function(modelo){
            var vista = new FilesView(modelo);
            $("#grid-data").find('tbody:last').append(vista.render().$el);
        },

        syncFiles: function(){
        },

        syncPerfil: function(){
            this.$el.html(this.template(this.model.toJSON()));

            var roles = Session.get('roles');
            if(roles[0] !== 'ADMIN'){
                $('#menu-container').hide();
                this.files.fetch();
            } else {
                $('#grid-data').hide();
            }
        },

        opcionMenu: function(event) {
        }

    });

    return PerfilAdminView;

});