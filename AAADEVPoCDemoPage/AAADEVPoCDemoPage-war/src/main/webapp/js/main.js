/*$('#subPortfolio').click(function() {
    $('#content-container').load("SolutionsTemplate.html", function() {
        document.getElementById('solutions-title').innerHTML = "Solutions - Portfolio";
        LoadDemos();
    });
});

$('#subVertical').click(function() {
    $('#content-container').load("SolutionsTemplate.html", function() {
        document.getElementById('solutions-title').innerHTML = "Solutions - Vertical";
        LoadDemos();
    });
});*/

$(document).ready(function(){
    const menuItems = document.querySelectorAll('[role="menuitem"]');
            //const tabList = document.querySelector('[role="tablist"]');
        
    //Añadimos el mismo evento para los clicks de los tabs
    menuItems.forEach(menu => {
        menu.addEventListener("click", changeMenuItem);
    });

    function changeMenuItem(e){
        menuItems.forEach(element => {
            element.classList.remove("ant-menu-item-selected");
        });
        e.target.parentNode.classList.add("ant-menu-item-selected");
    }
    
    $(window).trigger('resize'); 
});

$('#btnLogOut').click( function() {

    var data = new FormData();
    data.append("action", "LogOut");
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            try {
                var result = JSON.parse(this.responseText);
                if (result.status === "ok") {
                    window.location.reload();
                } else {                    
                    alert("Error: " + result.message);
                }
            } catch (error) {
            	alert("Error: " + error);
            }

        }
    });

    xhr.open("POST", "LogIn");
    xhr.send(data);

});

var sibeBarOpen = true; 
$('#btnToogleMenu').click( function() {
	toogleMenu(sibeBarOpen);
});

function openMenu(){
	$('#sideBarMenu').width(250);
	$('#btnToogleMenu').width(250);
	$('#btnToogleIcon').removeClass('fa-chevron-right');
	$('#btnToogleIcon').addClass('fa-chevron-left');
	$('#menuHeadTitle').show();
	sibeBarOpen = true;
}

function closeMenu(){
	$('#sideBarMenu').width(75);
	$('#btnToogleMenu').width(75);
	$('#btnToogleIcon').removeClass('fa-chevron-left');
	$('#btnToogleIcon').addClass('fa-chevron-right');
	$('#menuHeadTitle').hide();
	sibeBarOpen = false;
}

function toogleMenu(option){
	if(option)
		closeMenu();
	else
		openMenu();
}

window.onresize = function() {
	var h = $(window).width();
    if (h >= 990) { 
    	openMenu();
    } else {
    	closeMenu();
    }
}