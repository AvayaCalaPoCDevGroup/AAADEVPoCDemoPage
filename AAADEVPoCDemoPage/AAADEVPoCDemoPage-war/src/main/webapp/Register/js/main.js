/**
 * 
 */
var _textToCopy = "";

$('#btnRegisterUser').click(function(){
	var formRegisterUser = document.getElementById('formRegisterUser');
	$('#alertMsg').hide();
	if (formRegisterUser.checkValidity() === false) {
        event.preventDefault();
        event.stopPropagation();
        console.log('Form nok')
	} else {
			var resgiterUser = $('#formRegisterUser').serializeArray();
			
			$("#btnRegisterUser").prop("disabled",true);
			$('#spnrRegisterUser').show();
			$.ajax({
	          url: "RegisterController",
	          type: "POST",
	              data: pairToJsonEncrypt(resgiterUser),
	              dataType: "json",
	          success: function (result) {
	          	
	          	$('#spnrRegisterUser').hide();
	          	$("#btnRegisterUser").prop("disabled",false);
	              if(result.status == "ok" && result.code==201){
	              	showToast("Information", '<span class="text-success">Created correctly!</span>', 3000);	                    	
	              	//agregar el nuevo componente a la vista
	              	$('#alertMsg').show();
	              	$('#cardFooter').hide();
	              	$('#alertContent').html(
	              			'<p><b>User:</b> '+result.user.username+'</p>'+
	              			'<p><b>Password:</b> '+result.user.password+'</p>'
	              	);
	              	_textToCopy="User: "+result.user.username+"\nPassword: "+result.user.password;
	              } else {
	              	showToast("Error", '<span class="text-danger">Error: '+result.message+'</span>', 3000);
	              }
	          },
	          error: function (xhr, ajaxOptions, thrownError) {
	          	$('#spnrRegisterUser').hide();
	          	$("#btnRegisterUser").prop("disabled",false);
	          	showToast("Error", '<span class="text-danger">Your request could not be sent at this time, please try again later.</span>', 3000);
	          }
	      });
	}
	formRegisterUser.classList.add('was-validated');
});

$('#cpyData').click(function(){
	putToClipBoard(_textToCopy);
});

function pairToJsonEncrypt(pair){
	var json = {};
	pair.forEach(
		p => {
			json[[p.name]]= encrytpText(p.value)
		}
	);
	
	//quito el id si es cero por que es un entity nuevo
	if(json.id != undefined && json.id == 0){
		delete json.id;
	}
	return JSON.stringify(json);
}

function putToClipBoard(text){
	const el = document.createElement('textarea');
	  el.value = text;
	  document.body.appendChild(el);
	  el.select();
	  document.execCommand('copy');
	  document.body.removeChild(el);
	  showToast("Information", '<span class="text-success">Copied to clipboard!</span>', 3000);
}

function encrytpText(text){
	var resp = CryptoJS.AES.encrypt(text, "AAADEVPocDemoPag").toString();
	return resp;
}

//dev
function decryptText(text){
	var encryptedBase64Key = 'QUFBREVWUG9jRGVtb1BhZw==';
	var parsedBase64Key = CryptoJS.enc.Base64.parse(encryptedBase64Key);
	var encryptedCipherText = text;
	var decryptedData = CryptoJS.AES.decrypt( encryptedCipherText, parsedBase64Key, {
	mode: CryptoJS.mode.ECB,
	padding: CryptoJS.pad.Pkcs7
	} );
	var decryptedText = decryptedData.toString( CryptoJS.enc.Utf8 );
	
	return decryptedText;
}