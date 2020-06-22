
var _demosList;
var _demosContainer;

$(document).ready( function() {
	cargarDemos(function(){});
});

function cargarDemos(){
	clearDemos();
	_demosContainer = document.getElementById('demosContainer');
    $.getJSON("SnapIns", function(json){
    	_demosList = json;
        renderDemos(json, _demosContainer, () => {
            agregarListeners();
        });
    });
}

function agregarListeners(){
	
	// Añadimos el mismo evento para los clicks de los tabs, esto es para la vista demos
	const tabs = document.querySelectorAll('[role="tab"]');
    tabs.forEach(tab => {
        tab.addEventListener("click", changeTabs);
    });
    
    
    //Añadimos el mismo evento para los clicks de los tabs, esto es para la vista Admin
    const edits = document.querySelectorAll('.editBtn');
    edits.forEach(btn => {
    	btn.addEventListener("click", editDemoListener);
    });
    
    const deletes = document.querySelectorAll('.deleteBtn');
    deletes.forEach(btn => {
    	btn.addEventListener("click", deleteDemoListener);
    });
    
    const addComponents = document.querySelectorAll('.addComponent');
    addComponents.forEach(btn => {
    	btn.addEventListener("click", addComponentAndTagsListener);
    });
    
    const addTags = document.querySelectorAll('.addTag');
    addTags.forEach(btn => {
    	btn.addEventListener("click", addComponentAndTagsListener);
    });
    
    const deleteComponents = document.querySelectorAll('.deleteComponent');
    deleteComponents.forEach(btn => {
    	btn.addEventListener("click", deleteComponentOrTagListener);
    });
    
    const deleteTags = document.querySelectorAll('.deleteTag');
    deleteTags.forEach(btn => {
    	btn.addEventListener("click", deleteComponentOrTagListener);
    });
}


function filterDemos() {
	var txtSearch = $('#text_searchDemo').val();
	if(txtSearch == ""){
		filteredList = _demosList;
	} else {
		var filteredList = _demosList.filter(function(unit){
			return unit.title.toUpperCase().includes(txtSearch.toUpperCase()) || checkTags(unit.tags, txtSearch);
		});
	}
	// Limpiamos la lista de demos
	clearDemos();
	
	renderDemos(filteredList, _demosContainer, () => {
        agregarListeners();
    });
	
	
	// Checa los tags de las demos y retora true si hay match con el txt de
	// busqueda
	function checkTags(jsonTags, txtSearch){
		var resp = false;
		jsonTags.forEach(x => {
				if(x.name.toUpperCase().includes(txtSearch.toUpperCase())){
					resp = true;
				}
			});
		return resp;
	}
}

function clearDemos(){
	// Buscamos todas las demos
	const tabs = document.querySelectorAll('.ant-card');

	// Eliminamos cada una
    tabs.forEach(tab => {
        tab.remove();
    });
}
  
function changeTabs(e) {
    const target = e.target;
    const parent = target.parentNode;

    // quitamos la propiedad activa
    parent
        .querySelectorAll('.ant-tabs-tab')
        .forEach(t => t.setAttribute("aria-selected", false));
    parent
        .querySelectorAll('.ant-tabs-tab-active')
        .forEach(t => t.classList.remove("ant-tabs-tab-active"));

    // Marcamos el elemento actual como activo
    target.setAttribute("aria-selected", true);
    target.classList.add('ant-tabs-tab-active');

    //Taboane tiene el div padre del contenido
    var tabpane = document.getElementById(parent.getAttribute("aria-controls"));

    //Quitamos la visivilidad de todos los tabs
	 var tabs = tabpane.querySelectorAll('[role="tabpanel"]');
	 tabs.forEach(t => {
	     t.classList.remove('ant-tabs-tabpane-active');
	     t.classList.add('ant-tabs-tabpane-inactive');
	     t.setAttribute("aria-hidden", true);
	 });
	 
	 //Pondemos visible el contenido actual
	 tabpane
     .querySelectorAll('#contentab'+target.getAttribute('aria-controls'))
     .forEach(t => {
    	 t.classList.remove('ant-tabs-tabpane-inactive');
	     t.classList.add('ant-tabs-tabpane-active');
	     t.setAttribute("aria-hidden", false);
     });
	 
    
	 var offset = target.getAttribute('aria-controls');

	 //Recorro la vista d edemos con el offset que obtengo del tab clickeado
	 tabpane.style.marginLeft = "-"+ (offset*100) +"%";
}