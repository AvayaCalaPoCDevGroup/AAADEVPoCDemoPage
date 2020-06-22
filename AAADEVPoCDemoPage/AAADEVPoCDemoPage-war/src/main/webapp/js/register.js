/*
 * VARIABLES
 */
let absolutePath = getAbsolutePath();
console.log(absolutePath);
// The video stream
var cameraStream = null;
var persona = {
    nombres: "",
    apellidos: "",
    correo: "",
    ciudad: "",
    telefonoMovil: "",
};
const sleep = (milliseconds) => {
	  return new Promise(resolve => setTimeout(resolve, milliseconds))
	}


/*
 * EVENTOS
 */

window.onload = function() {
	//Movi esto a donde se termina de cargar la info del evento
	//document.getElementById("loaderDisplay").classList.remove("is-active");
	//document.getElementById("loaderDisplay").setAttribute("data-text", "");
};



//$(".validationInput").focus(function() {	
//}).blur(function() {
//    var id = $(this).attr('id');
//    var valor = document.getElementById(id).value;
//    var elemento = document.getElementById(id);
//	var secondCol = elemento.parentNode.parentNode;
//	 var firstCol = elemento.parentNode;
//    if(valor === "" || valor === undefined || valor === "-666"){
//       secondCol.classList.add("has-error");
//       firstCol.children[1].style.display = "block";
//       firstCol.children[2].style.display = "block";
//    }else{
//    	// LABEL
//    	if(secondCol.classList.contains("has-error")){
//    		secondCol.classList.remove("has-error");
//    		firstCol.children[1].style.display = "none";
//    	}
//    	firstCol.children[2].style.display = "block";
//    }
//});


(function () {
    'use strict';
    window.addEventListener('load', function () {
    	
        // Fetch all the forms we want to apply custom Bootstrap validation
		// styles to
        var forms = document.getElementsByClassName('needs-validation');
        
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function (form) {

            document.getElementById('stepButton').addEventListener('click', function (event) {
            	var stepbutton = this;
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                    console.log("Por favor llene todos los campos")
                } else {
                    if (validateInputs()) {
                    	stepbutton.disabled = true;
                    	$('#stepSpinner').show();
                        console.log("OK");    
                        $.ajax({
                            url: "SignUp",
                            type: "POST",
                            data: JSON.stringify(persona),
                            dataType: "json",
                            success: function (result) {
                            	$('#stepSpinner').hide();
                            	$('#modalRegistro').modal('hide');
                                if(result.status == "ok"){
                                	showToast("Information", '<span class="text-success">Your request was sent correctly!</span>', 3000);
                                	clearFormRegistro();
                                	stepbutton.disabled = false;
                                }
                            },
                            error: function (xhr, ajaxOptions, thrownError) {
                            	$('#stepSpinner').hide();
                            	stepbutton.disabled = false;
                            	$('#modalRegistro').modal('hide');
                            	showToast("Error", '<span class="text-danger">Your registration could not be sent at this time, please try again later.</span>', 3000);
                            }
                        });                        
                    } else {
                        console.log("No OK");
                    }
                }

                form.classList.add('was-validated');
            }, false);
        });


    }, false);
})();

function clearFormRegistro(){
	document.getElementById('inputName').value = "";
	document.getElementById('inputApellido').value = "";
	document.getElementById('inputEmail').value = "";
	document.getElementById('phoneMovil').value = "";
	document.getElementById('inputCiudad').value = "";
}



function buttonCaptureStayleText(valor){
    return valor ? style='"display: none;"' : '"display: inline;"'
}

function validateInputs() {
    if (document.getElementById('inputName').value === "" || document.getElementById('inputName').value === undefined) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Favor de ingresar su nombre'
        });
        return false;
    } else {
        persona.nombres = document.getElementById('inputName').value;
    }
    if (document.getElementById('inputApellido').value === "" || document.getElementById('inputApellido').value === undefined) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Favor de ingresar su Apellido'
        });
        return false;
    } else {
        persona.apellidos = document.getElementById('inputApellido').value;
    }
    if (document.getElementById('inputEmail').value === "" || document.getElementById('inputEmail').value === undefined) {
    	var email = validateEmail(document.getElementById('inputEmail').value);
    	if (email !== true) {
    		Swal({
                type: 'error',
                title: 'Error',
                text: 'Please enter the email correctly'
            });
    		return false;
    	}   
        return false;
    } else {
        persona.correo = document.getElementById('inputEmail').value;
    }
    if (document.getElementById('phoneMovil').value === "" || document.getElementById('phoneMovil').value === undefined) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Favor de ingresar su teléfono móvil'
        });
        return false;
    } else {
        persona.telefonoMovil = document.getElementById('phoneMovil').value;
    }
    if (document.getElementById('inputCiudad').value === "" || document.getElementById('inputCiudad').value === undefined) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Favor de ingresar su Ciudad'
        });
        return false;
    } else {
        persona.ciudad = document.getElementById('inputCiudad').value;
    }
    
    return true;
}

function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

$(document).ready(function () {
    
});


function getAbsolutePath() {
    var loc = window.location;
    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
    return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length))
}