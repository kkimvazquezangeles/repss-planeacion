define([
	'backbone',
    'models/FileModel'
], function(Backbone, FileModel){

    var FilesCollection = Backbone.Collection.extend({
        model: FileModel,
        url: 'archivo'
    });

	return FilesCollection;
});