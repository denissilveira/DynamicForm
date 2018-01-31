$(document).ready(function() {
	
	var tmpl = $.templates("#dynamicFormTmpl");
	var jsonString = $("#form")[0].value;
	var jsonp =  jQuery.parseJSON(jsonString);
	$("#dynamicFormResult").html(tmpl.render(jsonp));
	    
	$('#Salvar').click(function(){
	     
		 var fieldName;
		 var json = '{';
		 
		 var radioName = '';
		 
		 $("#main").find('input:text, input:radio, input:password,  select, textarea, password')
	      .each(function() {
	   	   
	   	   fieldName =  $(this).attr('id');
	   	   
	   	   if ($('#'+fieldName).is(':visible')){
		   	   if($(this).attr('type')!='radio'){
		   		   json = json + '"'+fieldName+'":"'+$("#"+fieldName).val()+'",';
		   	   }else if($(this).attr('type')=='radio'){
		   		fieldName =  $(this).attr('name');
		   		 if(radioName!=fieldName){
		       		var checkedValue = $('#main').find('input[name="'+fieldName+'"]:checked').val();
		       		json = json + '"'+fieldName+'":"'+checkedValue+'",';
		   		 }
		   		 radioName = fieldName;
		   	   }
	   	   }
	   	   
	   	   
	      });
	   
	json = json + '"OBJECTO_PRINCIPAL#rows":"3",';
	   json = json.substr(0,json.length-1);
	   json = json+ '}';
	   
	   $("#data").val(json);
	   $("#main").submit();
	     
	  });
	
});

function renderSubFormRadio(field, name) {
	var options = $('input[name="'+name+'"]');
	if(options) {
		options.each(function(index) {
			$('#'+this.value).css("display", "none");
		});
	}
	var subGroup = $('#'+field.value);
	if(subGroup) 
		subGroup.css("display", "block");
}

function renderSubFormSelect(field) {
	var options = $(field.options);

	if(options) {
		options.each(function(index) {
			if(this.value) {
				if(this.selected) {
					$('#'+this.value).css("display", "block");
				} else {
					$('#'+this.value).css("display", "none");
				}
			}
		});
	}
}

var rowGroupIdx = 0;
var countFieldElements = 0;
function addNewElement(element, maxFieldElements) {
	if (validateMaxFieldElement(element, countFieldElements, maxFieldElements)) {
		rowGroupIdx ++;
		var newElement = $('#'+element.id).clone(true);
		$(newElement).attr('id',element.id+"_"+rowGroupIdx);
		$(newElement).find('input:text, input:radio, input:password,  select, textarea, password')
	    .each(function() {
	    	$(this).attr('id',this.id+"_"+rowGroupIdx);
	    });
		if(newElement[0].tagName === "DIV") {
			$(newElement).append('<a href="#'+element.id+'" id="'+element.id+"_"+rowGroupIdx+'_link" class="remove_field" onclick="removeFieldElement('+element.id+"_"+rowGroupIdx+')">Remove</a>');
			$($('#'+element.id+"_span")).append(newElement);
		} else {
			$($('#'+element.id+"_span")).append(newElement);
			$($('#'+element.id+"_span")).append('<a href="#'+element.id+'" id="'+element.id+"_"+rowGroupIdx+'_link"  class="remove_field" onclick="removeFieldElement('+element.id+"_"+rowGroupIdx+')">Remove</a><br id="'+element.id+"_"+rowGroupIdx+'_div"/>');
		}
		countFieldElements ++;
	}
}

function removeFieldElement(field) {
	field.remove();
	$($('#'+field.id+"_link")).remove();
	$($('#'+field.id+"_div")).remove();
	countFieldElements --;
}

function validateMaxFieldElement(element, countFieldElements, maxFieldElements) {
	if (maxFieldElements) {
		if(countFieldElements >= maxFieldElements) {
			alert("O campo múltiplo "+element.id+" suporta no máximo "+maxFieldElements+" entradas.");
			return false;
		}
	}
	return true;
}