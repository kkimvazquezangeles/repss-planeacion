define([
	'jquery',
	'underscore',
	'core/BaseView',
	'text!templates/private/perfil/tplRowFile.html'
], function($, _, BaseView, tplRowFile){

	var FilesView = BaseView.extend({
        template: _.template(tplRowFile),
        tagName: 'tr',

        events: {
            'click #btn-delete' : 'deleteFile',
            'click #btn-download' : 'downloadFile'
        },

        initialize: function(modelo) {
            this.model = modelo;
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        deleteFile: function(event){
            var res = confirm("¿Desea eliminar este archivo?");
            if(res){
                this.model.setTipo(2);
                that = this;
                this.model.destroy({ contentType: 'application/json',
                wait:true,
                    success: function(model, response) {
                        that.destroyView();
                        alert(response.message);
                    },
                    error: function(model, error) {
                        alert(error);
                    }
                });
            }
        },

        downloadFile: function(){
        }

	});

	return FilesView;

});