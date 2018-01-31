// View Model class definitions using getter pattern

// Form
function Form(name, version, method, action, show, elements) {
	this._name = name;
	this._version = version;
	this._method = method;
	this._action = action;
	this._show = show;
	this._elements = elements;
}

var formProto = {
	name : function() {
		return this._name;
	},
	version : function() {
		return this._version;
	},
	method : function() {
		return this._method;
	},
	action : function() {
		return this._action;
	},
	show : function() {
		return this._show;
	},
	elements : function() {
		return this._elements;
	}
};

Form.prototype = formProto;

//Element
function Element(position, elements, name, type, show, showName, label, value, style, show, required, showLabel, containsSubForm, options) {
	this._position = position;
	this._elements = elements;
	this._name = name;
	this._type = type;
	this._show = show;
	this._showName = showName;
	this._value = value;
	this._style = style;
	this._show = show;
	this._required = required;
	this._showLabel = showLabel;
	this._containsSubForm = containsSubForm;
	this._options = options;
}

var elementProto = {
	position : function() {
		return this._position;
	},
	elements : function() {
		return this._elements;
	},
	name : function() {
		return this._name;
	},
	type : function() {
		return this._type;
	},
	show : function() {
		return this._show;
	},
	showName : function() {
		return this._showName;
	},
	value : function() {
		return this._value;
	},
	style : function() {
		return this._style;
	},
	show : function() {
		return this._show;
	},
	required : function() {
		return this._required;
	},
	showLabel : function() {
		return this._showLabel;
	},
	containsSubForm : function() {
		return this._containsSubForm;
	},
	options : function() {
		return this._options;
	}
};

Element.prototype = elementProto;

// TYPE
function Type(type, subtype) {
	this._type = type;
	this._subtype = subtype;
}

var typeProto = {
	type : function() {
		return this._type;
	},
	subtype : function() {
		return this._subtype;
	}
};

Type.prototype = typeProto;

// Option
function Option(option, subtype) {
	this._option = option;
	this._value = value;
}

var optionProto = {
	option : function() {
		return this._option;
	},
	value : function() {
		return this._value;
	}
};

Option.prototype = optionProto;
