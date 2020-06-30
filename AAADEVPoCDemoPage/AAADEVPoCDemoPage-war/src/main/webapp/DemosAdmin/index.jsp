<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<mt:masterpage title="Demos"  menuid="menuAdmin">
	<jsp:attribute name="content">
		<div id="container">
		    <div id="demosContainer">
		
		        <div class="ant-row vertical-spacing">
		            <div class="row col-md-12 col-xs-12 ml-auto">
								<span class="ant-input-search ant-input-search-enter-button"><span
							class="ant-input-wrapper ant-input-group">
									<span
								class="ant-input-search ant-input-search-enter-button ant-input-affix-wrapper">
										<input placeholder="Search Demo" class="ant-input" type="text"
									id="text_searchDemo" value="">
											<span class="ant-input-suffix"></span>
											</span>
											<span class="ant-input-group-addon">
												<button type="button" onclick="filterDemos();"
										class="ant-btn ant-input-search-button ant-btn-primary">
												<i aria-label="icon: search" class="anticon anticon-search">
												<svg viewBox="64 64 896 896" focusable="false" class=""
												data-icon="search" width="1em" height="1em"
												fill="currentColor" aria-hidden="true">
		                                        <path
													d="M909.6 854.5L649.9 594.8C690.2 542.7 712 479 712 412c0-80.2-31.3-155.4-87.9-212.1-56.6-56.7-132-87.9-212.1-87.9s-155.5 31.3-212.1 87.9C143.2 256.5 112 331.8 112 412c0 80.1 31.3 155.5 87.9 212.1C256.5 680.8 331.8 712 412 712c67 0 130.6-21.8 182.7-62l259.7 259.6a8.2 8.2 0 0 0 11.6 0l43.6-43.5a8.2 8.2 0 0 0 0-11.6zM570.4 570.4C528 612.7 471.8 636 412 636s-116-23.3-158.4-65.6C211.3 528 188 471.8 188 412s23.3-116.1 65.6-158.4C296 211.3 352.2 188 412 188s116.1 23.2 158.4 65.6S636 352.2 636 412s-23.3 116.1-65.6 158.4z">
		                                        </path>
		                                    </svg>
										</i>
											</button>
							</span>
								</span></span>
								<div class="ml-auto">
									<button type="button" class="btn btn-success col ml-20"
								data-toggle="modal" data-target="#modalNewDemo">	
										<i class="fa fa-plus"></i>
										Add New Demo
									</button>						
								</div>
					</div>
		        </div>
		
		        <!-- Aqui empieza la lista de demos -->
		
		        <!-- Aqui Termina empieza la lista de demos -->
		
		    </div>
		</div>
		
		<div class="modal fade" id="modalNewDemo" tabindex="-1" role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div id="modalDemoHead" class="modal-header">
		        <h5 id="modalDemoTitle" class="modal-title">New Demo</h5>
		        <button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <br />
		      <form id="formNewDemo"
						class="col-md-12 col-xs-12 needs-validation">
		      	  <div class="form-group">
				    <input type="number" name="id" class="form-control" value = 0
								id="formPropId" placeholder="Enter Title" required style="display: none;">
				    <!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
				  </div>
				  <div class="form-group">
				    <label for="title">Demo Title</label>
				    <input type="text" name="title" class="form-control"
								id="formPropTitle" placeholder="Enter Title" required>
				    <!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
				  </div>
				  
				  <div class="form-group">
				    <label for="description">Description</label>
				    <br>
				         <textarea id="formPropDescription" rows="5" cols="60" name="description"
								form="formNewDemo" style="width: 100%" required></textarea>
				    <br>
				  </div>				  
				  <div class="form-group">
				    <label for="link">Access Link</label>
				    <input type="url" name="link" class="form-control"
								id="formPropLink" placeholder="Enter link" required>
				    <!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
				  </div>
				  <div class="custom-control custom-switch">
				  	<input type="checkbox" name="available"
								class="custom-control-input" id="formPropAvailable" checked>
					<label class="custom-control-label" for="formPropAvailable">Available</label>
				  </div>				  
				  <!-- <button id="submitFormNewDemo" type="submit" class="btn btn-primary" style="display: none;">Submit</button> -->
				</form>
		      <div class="modal-footer">
		        <label id="btnFormDemo" class="btn btn-primary">
		        	<span id="btnFormSpinner"
							class="spinner-border spinner-border-sm" role="status"
							aria-hidden="true" style="display: none;"></span>
		       		Save changes
	       		</label>
		        <button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>

		<script src="Demos/js/mainSolutions.js"></script>
		<script src="DemosAdmin/js/renderDemosAdminUtils.js"></script>
		<script src="DemosAdmin/js/demos_admin.js"></script>
	</jsp:attribute>
</mt:masterpage>