/**
 * 
 */
function renderDemos(demoList, target, callback){
    demoList.forEach(element => {
        var strhtml =   '<div class="ant-card responsive-container vertical-spacing  ant-card-bordered">'                                                                                                                                                                                                                                                                                                                                       
                        +'    <div class="ant-card-body">'
                        +'        <div class="ant-row" style="margin-left: -24px; margin-right: -24px;">'
                        +'            <div class="ant-col ant-col-xl-24" style="padding-left: 24px; padding-right: 24px;">'
                        +'                <div class="ant-row">'
                        +'                    <div class="ant-col ant-col-lg-10 ant-col-xl-18">'
                        +'                        <span class="heading right-spacing-3"> '
                        +'                            <span>'
                        +'                                <span class="">'
                        +'                                    <!-- Title -->'
                        + element.title
                        +'                                </span>'
                        +'                            </span>'
                        +'                        </span>'
                        +'                        <span class="ant-tag ant-tag-has-color bottom-spacing-3" style="background-color: rgb(109, 181, 236);">'
                        +'                            <!-- last update time -->'
                        +'                            Updated: ' + element.updatedtime
                        +'                        </span>'
                        
                        +'							<button data-iddemo='+element.id+' type="button" class="btn btn-warning editBtn">'	
						+'							<i class="fa fa-edit"></i>'
						+'						  	</button>'
						+'							<button data-iddemo='+element.id+' type="button" class="btn btn-danger deleteBtn">'	
						+'							<i class="fa fa-trash"></i>'
						+'						  	</button>'
                        
                        
                        +'                    </div>'
                        +'                    <div class="ant-col right-align ant-col-lg-14 ant-col-xl-6">'
                        +'                        <!-- Regiones -->'
                        +'                        <span class="ant-tag '+ renderAvailable(element.available) +'">CALA</span>'
                        +'                    </div>'
                        +'                </div>'
                        +'            </div>'
                        +'        </div>'
                        +'        <div class="ant-tabs ant-tabs-top vertical-spacing ant-tabs-line">'
                        +'            <div role="tablist" class="ant-tabs-bar ant-tabs-top-bar" tabindex="0">'
                        +'                <div class="ant-tabs-extra-content" style="float: right;"><button type="button"'
                        +'                        class="ant-btn primary-action-button-filled" disabled><span>Schedule</span><i aria-label="icon: calendar"'
                        +'                            class="anticon anticon-calendar"><svg viewBox="64 64 896 896" focusable="false" class=""'
                        +'                                data-icon="calendar" width="1em" height="1em" fill="currentColor" aria-hidden="true">'
                        +'                                <path'
                        +'                                    d="M880 184H712v-64c0-4.4-3.6-8-8-8h-56c-4.4 0-8 3.6-8 8v64H384v-64c0-4.4-3.6-8-8-8h-56c-4.4 0-8 3.6-8 8v64H144c-17.7 0-32 14.3-32 32v664c0 17.7 14.3 32 32 32h736c17.7 0 32-14.3 32-32V216c0-17.7-14.3-32-32-32zm-40 656H184V460h656v380zM184 392V256h128v48c0 4.4 3.6 8 8 8h56c4.4 0 8-3.6 8-8v-48h256v48c0 4.4 3.6 8 8 8h56c4.4 0 8-3.6 8-8v-48h128v136H184z">'
                        +'                                </path>'
                        +'                            </svg></i></button>'
                        +'                </div>'
                        +'                <div class="ant-tabs-nav-container"><span unselectable="unselectable"'
                        +'                        class="ant-tabs-tab-prev ant-tabs-tab-btn-disabled"><span class="ant-tabs-tab-prev-icon"><i'
                        +'                                aria-label="icon: left" class="anticon anticon-left ant-tabs-tab-prev-icon-target"><svg'
                        +'                                    viewBox="64 64 896 896" focusable="false" class="" data-icon="left" width="1em"'
                        +'                                    height="1em" fill="currentColor" aria-hidden="true">'
                        +'                                    <path'
                        +'                                        d="M724 218.3V141c0-6.7-7.7-10.4-12.9-6.3L260.3 486.8a31.86 31.86 0 0 0 0 50.3l450.8 352.1c5.3 4.1 12.9.4 12.9-6.3v-77.3c0-4.9-2.3-9.6-6.1-12.6l-360-281 360-281.1c3.8-3 6.1-7.7 6.1-12.6z">'
                        +'                                    </path>'
                        +'                                </svg></i></span></span><span unselectable="unselectable"'
                        +'                        class="ant-tabs-tab-next ant-tabs-tab-btn-disabled"><span class="ant-tabs-tab-next-icon"><i'
                        +'                                aria-label="icon: right"'
                        +'                                class="anticon anticon-right ant-tabs-tab-next-icon-target"><svg viewBox="64 64 896 896"'
                        +'                                    focusable="false" class="" data-icon="right" width="1em" height="1em"'
                        +'                                    fill="currentColor" aria-hidden="true">'
                        +'                                    <path'
                        +'                                        d="M765.7 486.8L314.9 134.7A7.97 7.97 0 0 0 302 141v77.3c0 4.9 2.3 9.6 6.1 12.6l360 281.1-360 281.1c-3.9 3-6.1 7.7-6.1 12.6V883c0 6.7 7.7 10.4 12.9 6.3l450.8-352.1a31.96 31.96 0 0 0 0-50.4z">'
                        +'                                    </path>'
                        +'                                </svg></i></span></span>'
                        +'                    <div class="ant-tabs-nav-wrap">'
                        +'                        <div class="ant-tabs-nav-scroll">'
                        +'                            <div class="ant-tabs-nav ant-tabs-nav-animated">'
                        +'                                <!-- aqui guardo el id del contenedor para los detalles de los tabs id=demoX-->'
                        +'                                <div aria-controls="demo'+ element.id +'">'
                        +'                                    <div role="tab" aria-disabled="false" aria-selected="true" aria-controls="0"'
                        +'                                        class="ant-tabs-tab-active ant-tabs-tab">Description'
                        +'                                    </div>'
                        +'                                    <div role="tab" aria-disabled="false" aria-selected="false" aria-controls="1"'
                        +'                                        class=" ant-tabs-tab">'
                        +'                                        Components</div>'
                        +'                                    <div role="tab" aria-disabled="false" aria-selected="false" aria-controls="2"'
                        +'                                        class=" ant-tabs-tab">'
                        +'                                        Access'
                        +'                                    </div>'
                        +'                                    <div role="tab" aria-disabled="false" aria-selected="false" aria-controls="3"'
                        +'                                        class=" ant-tabs-tab">'
                        +'                                        Downloads</div>'
                        +'                                    <div role="tab" aria-disabled="false" aria-selected="false" aria-controls="4"'
                        +'                                        class=" ant-tabs-tab">'
                        +'                                        Tags</div>'
                        +'                                </div>'
                        +'                                <!-- <div class="ant-tabs-ink-bar ant-tabs-ink-bar-animated"'
                        +'                                    style="display: block; transform: translate3d(0px, 0px, 0px); width: 103px;">'
                        +'                                </div> -->'
                        +'                            </div>'
                        +'                        </div>'
                        +'                    </div>'
                        +'                </div>'
                        +'            </div>'
                        +'            <div tabindex="0" role="presentation"'
                        +'                style="width: 0px; height: 0px; overflow: hidden; position: absolute;">'
                        +'            </div>'
                        //demo1 asi identifico el contenedor de mi demo actual
                        +'            <div id="demo'+ element.id +'" class="ant-tabs-content ant-tabs-content-animated ant-tabs-top-content"'
                        +'                style="margin-left: 0%;">'
                        +'                <div id="contentab0" role="tabpanel" aria-hidden="false"'
                        +'                    class="ant-tabs-tabpane ant-tabs-tabpane-active scrollable-tab-content">'
                        +'                    <div tabindex="0" role="presentation"'
                        +'                        style="width: 0px; height: 0px; overflow: hidden; position: absolute;">'
                        +'                    </div>'
                        +'                    <div class="html-container text-color" style="text-align: justify;">'
                        +'                        <!-- DESCRIPTION -->'
                        + element.description
                        +'                    </div>'
                        +'                    <div tabindex="0" role="presentation"'
                        +'                        style="width: 0px; height: 0px; overflow: hidden; position: absolute;">'
                        +'                    </div>'
                        +'                </div>'
                        +'                <div id="contentab1" role="tabpanel" aria-hidden="true"'
                        +'                    class="ant-tabs-tabpane ant-tabs-tabpane-inactive scrollable-tab-content">'
                        +'                    <div class="html-container text-color">'
                        +'                        <!-- COMPONENTS -->'
                        +'                          <ul class="listaComponents">'
                        + renderComponents(element.componentDetails)
                        +'                          </ul>'
                        +'							<button data-iddemo='+element.id+' data-tipo="component" type="button" class="ml-4 btn btn-success addComponent">'	
						+'							<i class="fa fa-plus"></i>'
						+'						  	</button>'	
                        +'                    </div>'
                        +'                </div>'
                        +'                <div id="contentab2" role="tabpanel" aria-hidden="true"'
                        +'                    class="ant-tabs-tabpane ant-tabs-tabpane-inactive scrollable-tab-content">'
                        +'                    <div class="html-container text-color">'
                        +'                        <!-- ACCESS -->'
                        +'						<a href=' + element.link + ' target="_blank">'+element.link + '</a>'
                        +'                    </div>'
                        +'                </div>'
                        +'                <div id="contentab3" role="tabpanel" aria-hidden="true" class="ant-tabs-tabpane ant-tabs-tabpane-inactive scrollable-tab-content">'
                        +'                    <!-- COLLATERALLS -->'
                        + renderCollaterals(element.collaterals)
                        +'                </div>'
                        +'                <div id="contentab4" role="tabpanel" aria-hidden="true"'
                        +'                    class="ant-tabs-tabpane ant-tabs-tabpane-inactive scrollable-tab-content">'
                        +'                        <!-- Tags -->'
                        +'                          <ul class="listaTags">'
                        + renderTags(element.tags)
                        +'                          </ul>'
                        +'							<button data-iddemo='+element.id+' data-tipo="tag" type="button" class="ml-4 btn btn-success addTag">'	
						+'							<i class="fa fa-plus"></i>'
						+'						  	</button>'	
                        +'                </div>'
                        +'            </div>'
                        +'            <div tabindex="0" role="presentation"'
                        +'                style="width: 0px; height: 0px; overflow: hidden; position: absolute;">'
                        +'            </div>'
                        +'        </div>'
                        +'    </div>'
                        +'</div>';
        
        target.innerHTML += strhtml;
    });

    callback();
}

function renderAvailable(available){
	var resp = "";
	if(available == "true")
		resp = "ant-tag-green";
	else
		resp = "ant-tag-red";
	return resp;
}


function renderComponents(componentList){
    var resp = "";
    componentList.forEach(element => {
        resp += 
        	 '<li class="my-1">' + element.name
	        +'	<button data-id='+element.id+' data-iddemo='+element.iddemo+' type="button" class="ml-2 btn btn-danger deleteComponent">'	
			+'	<i class="fa fa-trash"></i>'
			+'	</button>'	
	        +'</li>'
    });
    return resp;
}

function renderTags(tagList){
    var resp = "";
    tagList.forEach(element => {
        resp += 
        	 '<li class="my-1">' + element.name
	        +'	<button data-id='+element.id+' data-iddemo='+element.iddemo+' type="button" class="ml-2 btn btn-danger deleteTag">'	
			+'	<i class="fa fa-trash"></i>'
			+'	</button>'	
	        +'</li>'
    });
    return resp;
}

function renderCollaterals(collaterallList){
    var resp = "";
    collaterallList.forEach(element => {
        resp += '<div class="ant-card vertical-spacing ant-card-bordered ant-card-type-inner">'
                +'    <div class="ant-card-head">'
                +'        <div class="ant-card-head-wrapper">'
                +'            <div class="ant-card-head-title">'+element.type+'</div>'
                +'        </div>'
                +'    </div>'
                +'    <div class="ant-card-body">'
                +'        <div class="ant-list ant-list-sm ant-list-split">'
                +'            <div class="ant-spin-nested-loading">'
                +'                <div class="ant-spin-container">'
                +'                    <ul class="ant-list-items">'
                +'                        <li class="ant-list-item">'
                +'                            <div class="ant-list-item-meta">'
                +'                                <div class="ant-list-item-meta-content">'
                +'                                    <h4 class="ant-list-item-meta-title">'
                +'                                        <a class="link"'
                +'                                            href="'+element.link+'"'
                +'                                            rel="noopener noreferrer" target="_blank">'
                +'                                            '+element.title+'</a>'
                +'                                        <span class="ant-tag ant-tag-has-color float-right"'
                +'                                            style="background-color: rgb(109, 181, 236);">'
                +'                                            Updated: '+element.updatedtime+'</span>'
                +'                                    </h4>'
                +'                                </div>'
                +'                            </div>'
                +'                        </li>'
                +'                    </ul>'
                +'                </div>'
                +'            </div>'
                +'        </div>'
                +'    </div>'
                +'</div>'
    });
    return resp;
}