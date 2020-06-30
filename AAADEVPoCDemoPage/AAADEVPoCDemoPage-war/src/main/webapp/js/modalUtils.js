/**
 * Alberto Martinez 
 */

//Modal
let generalModal = document.getElementById("generalModal");
let modalTitle = document.getElementById("modalTitle");
let modalBody = document.getElementById("modalBody");
let modalFooter = document.getElementById("modalFooter");

//TOast
let titleToast = document.getElementById('titleToast');
let contentToast = document.getElementById('contentToast');

function clearModal(){
	modalTitle.innerHTML = "";
	modalBody.innerHTML ="";
	modalFooter.style.display = "none";
}

function setCustomModal(title, content, callback){
	clearModal();
	modalTitle.innerHTML += title;
	modalBody.innerHTML += content;
	callback();	
}

function showModal(){
	$('#generalModal').modal('show');
}

function hideModal(){
	$('#generalModal').modal('hide');
}

function showToast(title, content, duration){
	titleToast.innerHTML = "";
	contentToast.innerHTML = "";
	
	titleToast.innerHTML = title;
	contentToast.innerHTML = content;
	
	$('.toast').css('z-index', 3000);
	$('.toast').toast({animation:true, autohide : true, delay : duration});
	$('.toast').toast('show');
}