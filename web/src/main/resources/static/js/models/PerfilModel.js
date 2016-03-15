define([
    'backbone'
], function(Backbone){

    var PerfilModel = Backbone.Model.extend({

        urlRoot: 'user',

        defaults: {
            nombreArea: '',
            nombrePersonal: ''
        },

        initialize: function() {
        }

    });

	return PerfilModel;
});