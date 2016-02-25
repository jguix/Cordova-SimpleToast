var exec = require('cordova');

function SimpleToast() { 
	console.log("SimpleToast.js: is created");
}

SimpleToast.prototype.show = function(message) {
	console.log("SimpleToast.js: show");

	cordova.exec(function(result){
		/*alert("OK" + result);*/
	},
	function(error){
		/*alert("Error" + error);*/
	},"SimpleToast", "show", [{message: message}]);
}

var simpleToast = new SimpleToast();
module.exports = simpleToast;
