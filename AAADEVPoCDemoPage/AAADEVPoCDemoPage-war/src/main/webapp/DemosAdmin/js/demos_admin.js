/**
 * 
 */
const formNewDemo = document.getElementById('formNewDemo');

(function() {	
	window.addEventListener('load', function() {
	    // Fetch all the forms we want to apply custom Bootstrap validation styles to
		//var formNewDemo = document.getElementById('formNewDemo');
	    // Loop over them and prevent submission
	    
    	document.getElementById('btnFormDemo').addEventListener('click', function(event) {
    		var btnFormDemo = this;
	        if (formNewDemo.checkValidity() === false) {
	          event.preventDefault();
	          event.stopPropagation();
	          console.log('Form nok')
	        } else {
	        	console.log('Form ok')
	        	btnFormDemo.disabled = true;
	        	$('#btnFormSpinner').show();
	        	var demo = $('#formNewDemo').serializeArray();
	        	console.log(demo);
	        	if(demo[4] == undefined){
	        		demo.push({name : 'available', value : "false"});
	        	} else {
	        		demo[4].value = "true";
	        	}
	        	//Hacemos el crate
	        	var demoData = pairToJson(demo);
	        	
	        	console.log(demoData);
	        	$.ajax({
	                url: "Demos?action=demo",
	                type: "POST",
	                data: demoData,
	                dataType: "json",
	                success: function (result) {
	                	$('#btnFormSpinner').hide();
	                	$('#modalNewDemo').modal('hide');
	                	btnFormDemo.disabled = false;
	                    if(result.status == "ok"){
	                    	showToast("Information", '<span class="text-success">Demo was created / updated correctly!</span>', 3000);
	                    	formNewDemo.reset();
	                    	cargarDemos();
	                    } else{
	                    	showToast("Information", '<span class="text-danger">Error al crear demo, '+result.message+'</span>', 3000);
	                    }
	                },
	                error: function (xhr, ajaxOptions, thrownError) {
	                	$('#btnFormSpinner').hide();
	                	btnFormDemo.disabled = false;
	                	$('#modalNewDemo').modal('hide');
	                	showToast("Error", '<span class="text-danger">Your demo could not be sent at this time, please try again later.</span>', 3000);
	                }
	            });           
	        }
	        formNewDemo.classList.add('was-validated');
    	}, false);
	}, false);
})();

function pairToJson(pair){
	var json = {};
	pair.forEach(
		p => {
			json[[p.name]]= p.value;
		}
	);
	
	//quito el id si es cero por que es un entity nuevo
	if(json.id != undefined && json.id){
		delete json.id;
	}
	return JSON.stringify(json);
}

function editDemoListener(e){
	var iddemo = 0; 
	if(e.target.children.length == 0){ //Esta validacion es por que a veces el target es el icono dentro del boton
		iddemo =e.target.parentNode.dataset.iddemo;		
	} else {
		iddemo =e.target.dataset.iddemo;
	}
	console.log('iddemo = ' + iddemo);	
	
	var demo = _demosList.find(d => d.id == iddemo);
	llenarForm(demo)
	$('#modalNewDemo').modal('show');
}


function llenarForm(demo){
	document.getElementById('formPropId').value = demo.id;
	document.getElementById('formPropTitle').value = demo.title;
	document.getElementById('formPropDescription').value = demo.description;
	document.getElementById('formPropLink').value = demo.link;
	document.getElementById('formPropAvailable').checked = demo.available;
}

function deleteDemoListener(e){
	var iddemo = 0; 
	if(e.target.children.length == 0){ //Esta validacion es por que a veces el target es el icono dentro del boton
		iddemo =e.target.parentNode.dataset.iddemo;		
	} else {
		iddemo =e.target.dataset.iddemo;
	}
	
	var demo = _demosList.find(d => d.id == iddemo);
	
	var htmlText = 
		'<div class="container-fluid">'+
		'	<span class="col-xs-12 col-md-12" style="text-align: justify;">'+
		'		<h4>Are you sure to delete demo <b>'+demo.title+'</b>?</h4>'+ 
		'	</span>'+	
		'	<br/>'+
		'	<br/>'+
		'	<div class="container-fluid col-xs-12 col-md-12 modal-footer">'+
		'	<div class="row">'+
		'		<div class="ml-auto">'+
		'			<button type="button" class="btn btn-secondary ml-auto" data-dismiss="modal">Cancel</button>'+
		'			&nbsp; &nbsp;'+
		'       	<button id="deleteButton" type="button" class="btn btn-danger ml-auto">'+
		'       	<span id="deleteSpinner" class="spinner-border spinner-border-sm" role="status" aria-hidden="true" style="display: none;"></span>'+
		'       	Delete</button>'+
		'		</div>'+
		'	</div>'+
		'	</div>'+
		'</div>';
	setCustomModal('Delete Demo', htmlText, function(){
		showModal();
		//cargar los listeners
		$('#deleteButton').click(function(){
			console.log('delete it');
			$("#deleteButton").prop("disabled",true);
			$('#deleteSpinner').show();
			$.ajax({
                url: "Demos?action=demo&d="+iddemo,
                type: "Delete",
//                data: pairToJson(demo),
//                dataType: "json",
                success: function (result) {
                	var resp = JSON.parse(result);
                	$('#deleteSpinner').hide();
                	$("#deleteButton").prop("disabled",false);
                	hideModal();                	
                    if(resp.status == "ok"){
                    	showToast("Information", '<span class="text-success">Demo was deleted correctly!</span>', 3000);
                    	
                    	cargarDemos();
                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                	$('#deleteSpinner').hide();
                	$("#deleteButton").prop("disabled",false);
                	hideModal();  
                	showToast("Error", '<span class="text-danger">Your demo could not be sent at this time, please try again later.</span>', 3000);
                	
                }
            });  
		});
	});
}

function addComponentAndTagsListener(e){
	//demo a la que se agregara el component o tag
	var iddemo = 0
	var elemento;
	var _url = "";
	var _classForNewElement = "";
	var listContainerClass = "";
	if(e.target.children.length == 0){ //Esta validacion es por que a veces el target es el icono dentro del boton
		elemento = e.target.parentNode;
	} else {
		elemento = e.target;
	}
	iddemo = elemento.dataset.iddemo;
	if(elemento.classList.contains('addComponent')){
		_url = "Demos?action=component";
		listContainerClass= "listaComponents";
		_classForNewElement = "deleteComponent";
	} else if (elemento.classList.contains('addTag')){
		_url = "Demos?action=tag";
		listContainerClass= "listaTags";
		_classForNewElement = "deleteTag";
	}
	
	
	var htmlText = 
		'<div class="container-fluid">'+
		'	<form id="formComp" class="col-md-12 col-xs-12 needs-validation">'+
//	    '	 	  <div class="form-group">'+
//		'		    <input type="number" name="id" class="form-control" value = 0'+
//		'						id="formComponentId" placeholder="Enter Title" required style="display: none;">'+
//		'		  </div>'+
		'		  <div class="form-group">'+
		'		    <input type="number" name="iddemo" class="form-control" value = '+ iddemo +
		'						id="formComponentIdDemo" placeholder="Enter Title" required style="display: none;">'+
		'		  </div>'+
		'		  <div class="form-group">'+
		'		    <label for="name">Name</label>'+
		'		    <input type="text" name="name" class="form-control"'+
		'					id="formPropName" placeholder="Enter Title" required>'+
		'	  </div>'+
		'	</form>'+
		'	<br/>'+
		'	<div class="container-fluid col-xs-12 col-md-12 modal-footer">'+
		'	<div class="row">'+
		'		<div class="ml-auto">'+
		'			<button type="button" class="btn btn-secondary ml-auto" data-dismiss="modal">Cancel</button>'+
		'			&nbsp; &nbsp;'+
		'       	<button id="createCompButton" type="button" class="btn btn-success ml-auto">'+
		'       	<span id="createCompSpinner" class="spinner-border spinner-border-sm" role="status" aria-hidden="true" style="display: none;"></span>'+
		'       	Create</button>'+
		'		</div>'+
		'	</div>'+
		'	</div>'+
		'</div>';
	//Obtengo la demo para sacar su nombre
	var demo = _demosList.find(d => d.id == iddemo);
	
	setCustomModal('Add for ' + demo.title, htmlText, function(){
		showModal();
		
		//cargar los listeners
		var formComponent = document.getElementById('formComp');
		$('#createCompButton').click(function(){
			
			if (formComponent.checkValidity() === false) {
		          event.preventDefault();
		          event.stopPropagation();
		          console.log('Form nok')
		    } else {
				var componnent = $('#formComp').serializeArray();
				
				console.log('create Component');
				$("#deleteButton").prop("disabled",true);
				$('#deleteSpinner').show();
				$.ajax({
	                url: _url,
	                type: "POST",
		                data: pairToJson(componnent),
		                dataType: "json",
	                success: function (result) {
	                	
	                	$('#createCompSpinner').hide();
	                	$("#createCompButton").prop("disabled",false);
	                	hideModal();                	
	                    if(result.status == "ok"){
	                    	showToast("Information", '<span class="text-success">Created correctly!</span>', 3000);	                    	
	                    	//agregar el nuevo componente a la vista
	                    	var container = document.getElementById('demo'+iddemo);
	                    	var lista = container.getElementsByClassName(listContainerClass)[0];
	                    	
	                    	lista.innerHTML += 
	                    		'<li class="my-1">' + result.entity.name
		            	        +'	<button data-id='+result.entity.id+' data-iddemo='+result.entity.iddemo+' type="button" class="ml-2 btn btn-danger '+_classForNewElement+'">'	
		            			+'	<i class="fa fa-trash"></i>'
		            			+'	</button>'	
		            	        +'</li>';
	                    	
	                    	//Asigno de nuevo los listeners a los deletes
	                    	setListenersForDeletes(lista);
	                    	//agregamos la entidad a su respectiva demo
	                    	if(elemento.dataset.tipo == "component"){
	                    		demo.componentDetails.push(result.entity);
	                    	} else if(elemento.dataset.tipo == "tag"){
	                    		demo.tags.push(result.entity);
	                    	}
	                    } else {
	                    	showToast("Error", '<span class="text-danger">Your request could not be sent at this time, please try again later. '+result.message+'</span>', 3000);
	                    }
	                },
	                error: function (xhr, ajaxOptions, thrownError) {
	                	$('#createCompSpinner').hide();
	                	$("#createCompButton").prop("disabled",false);
	                	hideModal();  
	                	showToast("Error", '<span class="text-danger">Your request could not be sent at this time, please try again later.</span>', 3000);
	                	
	                }
	            });
		    }
			formComponent.classList.add('was-validated');
		});
	});
}

function deleteComponentOrTagListener(e){
	//demo a la que se agregara el component o tag
	var id = 0
	var elemento;
	var iddemo = 0;
	var _url = "";
	var message ="";
	
	if(e.target.children.length == 0){ //Esta validacion es por que a veces el target es el icono dentro del boton
		elemento = e.target.parentNode;
	} else {
		elemento = e.target;
	}
	id = elemento.dataset.id;
	iddemo = elemento.dataset.iddemo;
	var demo = _demosList.find(d => d.id == iddemo);
	var entidad; 
	if(elemento.classList.contains('deleteComponent')){
		_url = "Demos?action=component&d="+id;
		message = "Component";
		entidad = demo.componentDetails.find(e => e.id == id);
	} else if (elemento.classList.contains('deleteTag')){
		_url = "Demos?action=tag&d="+id;
		message = "Tag";
		entidad = demo.tags.find(e => e.id == id);
	}
	
	
	var htmlText = 
		'<div class="container-fluid">'+
		'	<span class="col-xs-12 col-md-12" style="text-align: justify;">'+
		'		<h4>Are you sure to delete '+message+' <b>'+entidad.name+'</b>?</h4>'+ 
		'	</span>'+	
		'	<br/>'+
		'	<br/>'+
		'	<div class="container-fluid col-xs-12 col-md-12 modal-footer">'+
		'	<div class="row">'+
		'		<div class="ml-auto">'+
		'			<button type="button" class="btn btn-secondary ml-auto" data-dismiss="modal">Cancel</button>'+
		'			&nbsp; &nbsp;'+
		'       	<button id="deleteButton" type="button" class="btn btn-danger ml-auto">'+
		'       	<span id="deleteSpinner" class="spinner-border spinner-border-sm" role="status" aria-hidden="true" style="display: none;"></span>'+
		'       	Delete</button>'+
		'		</div>'+
		'	</div>'+
		'	</div>'+
		'</div>';
	setCustomModal('Delete for '+demo.title, htmlText, function(){
		showModal();
		//cargar los listeners
		$('#deleteButton').click(function(){
			console.log('delete it');
			$("#deleteButton").prop("disabled",true);
			$('#deleteSpinner').show();
			$.ajax({
                url: _url,
                type: "Delete",
//                data: pairToJson(demo),
//                dataType: "json",
                success: function (result) {
                	var resp = JSON.parse(result);
                	$('#deleteSpinner').hide();
                	$("#deleteButton").prop("disabled",false);
                	hideModal();                	
                    if(resp.status == "ok"){
                    	showToast("Information", '<span class="text-success">'+message+' was deleted correctly!</span>', 3000);
                    	elemento.parentNode.remove();
                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                	$('#deleteSpinner').hide();
                	$("#deleteButton").prop("disabled",false);
                	hideModal();  
                	showToast("Error", '<span class="text-danger">Your demo could not be sent at this time, please try again later.</span>', 3000);
                	
                }
            });  
		});
	});
}


function setListenersForDeletes(parent){
	var deleteComponents = parent.querySelectorAll('.deleteComponent');
    deleteComponents.forEach(btn => {
    	btn.addEventListener("click", deleteComponentOrTagListener);
    });
    
    var deleteTags = parent.querySelectorAll('.deleteTag');
    deleteTags.forEach(btn => {
    	btn.addEventListener("click", deleteComponentOrTagListener);
    });
}