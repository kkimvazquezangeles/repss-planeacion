define([
	'jquery',
	'core/BaseView',
    'backbone',
    'backboneValidation',
    'jquerySerializeObject',
    'models/UserModel',
	'text!templates/tplLogin.html',
	'Session'
], function($, BaseView, backbone, backboneValidation, jquerySerializeObject, UserModel, tplLogin, Session){

	var LoginView = BaseView.extend({
        template: _.template(tplLogin),

        events: {
            'click .btn.btn-lg.btn-primary.btn-block': 'login'
        },

        initialize: function() {
            this.model = new UserModel();
            Backbone.Validation.bind(this);
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        login: function(){
            var data = this.$el.find("#form-login").serializeObject();
            this.model.set(data);
            this.model.set({passwordConfirm: this.model.get('password')});

            if(this.model.isValid(true)){
                var user = this.model.get('username');
                var pass = this.model.get('password');
                var remember = $("#remember").is(":checked");
                Session.login(function(response){
                    Backbone.history.navigate('admin', { trigger : true });
                }, user, pass, remember);
            }

        }

	});

	return LoginView;

});