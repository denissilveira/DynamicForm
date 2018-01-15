$(document).ready(function() {
	
	debugger;
	
	var jsonString = $("#form")[0].value;
	
	var jsonp =  jQuery.parseJSON(jsonString);
	var mainTemplate = $.templates("#mainTmpl");
	$("#main").append(mainTemplate.render(jsonp));
});