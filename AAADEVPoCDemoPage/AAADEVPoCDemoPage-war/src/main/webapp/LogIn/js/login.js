/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

console.log('LogIn.js');

var absolutepath = getAbsolutePath();
console.log(absolutepath);
function getAbsolutePath() {
    var loc = window.location;
    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
    return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
}

document.cookie = "JWT= ; expires = Thu, 01 Jan 1970 00:00:00 GMT";



document.getElementById('submitbtn').addEventListener('click', function (e) {

    e.preventDefault();
    var loaderd = document.getElementById('loaderDisplay'); 
    

    console.log("Submit");
    var email = validateEmail(document.getElementById('email').value);

    if (document.getElementById('country').value === "" || document.getElementById('country').value.length === 0) {
        Swal({
            type: 'error',
            title: 'Error',
            text: 'Please establish the country of origin.'

        });
    } else if (document.getElementById('cliente').value === "" || document.getElementById('cliente').value.length === 0 || /^\s+$/.test(document.getElementById('cliente').value) || document.getElementById('cliente').value.match(/Avaya/) || document.getElementById('cliente').value.match(/AVAYA/) || document.getElementById('cliente').value.match(/avaya/)) {
        Swal({
            type: 'error',
            title: 'Error',
            text: 'Please do not enter Avaya as a customer.'

        });
    } else if (email !== true) {
        Swal({
            type: 'error',
            title: 'Error',
            text: 'Please enter the email correctly'
        });
    } else if (document.getElementById('pass').value === "" && email !== true) {
        Swal({
            type: 'error',
            title: 'Error',
            text: 'Please enter the password'
        });
    } else {
    	loaderd.classList.add('is-active');
        loaderd.setAttribute("data-text", "Validando Información");
        var data = new FormData();
        data.append("action", "LogIn");
        data.append("Email", CryptoJS.AES.encrypt(document.getElementById('email').value, "AAADEVPocDemoPag"));
        data.append("Pass", CryptoJS.AES.encrypt(document.getElementById('pass').value, "AAADEVPocDemoPag"));
        data.append("Cliente", CryptoJS.AES.encrypt(document.getElementById('cliente').value, "AAADEVPocDemoPag"));
        data.append("Pais", CryptoJS.AES.encrypt(document.getElementById('country').value, "AAADEVPocDemoPag"));
        var xhr = new XMLHttpRequest();
        xhr.withCredentials = false;
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                document.getElementById("loaderDisplay").classList.remove("is-active");
                document.getElementById("loaderDisplay").setAttribute("data-text", "");
                try {
                    var result = JSON.parse(this.responseText);
                    if (result.status === "ok") {
                    	if(window.location.pathname.includes("LogIn")){
                    		window.location.href = "Home";
                    	} else {                    		
                    		window.location.reload();
                    	}
                    } else {
                        Swal({
                            type: 'error',
                            title: 'Error',
                            text: result.message
                        });
                    }
                } catch (error) {
                    Swal({
                        type: 'error',
                        title: 'Error',
                        text: error
                    });
                }

            }
        });

        xhr.open("POST", absolutepath + "LogIn");
        xhr.send(data);
    }

});

//document.getElementById('forgotten').addEventListener('click', function (e) {
//    e.preventDefault();
//    console.log("Forgotten");
//});
document.getElementById('pass').addEventListener("keyup", function(event) {
	  // Number 13 is the "Enter" key on the keyboard
	  if (event.keyCode === 13) {
	    // Cancel the default action, if needed
	    event.preventDefault();
	    // Trigger the button element with a click
	    document.getElementById("submitbtn").click();
	  }
	});


function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

var delete_cookie = function (name) {
    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
};