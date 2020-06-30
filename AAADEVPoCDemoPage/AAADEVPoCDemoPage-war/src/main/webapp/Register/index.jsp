<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:masterpage title="Manage Users"  menuid="menuManageUsers">
	<jsp:attribute name="content">	
	
		<style>
		.center-childs {
			display: flex;
			justify-content: center;
			align-items: center;
		}
		</style>
		
		<div class="center-childs" style="height: 95%; width: 95%">
			<div class="card"
				style="box-shadow:0 10px 16px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19) !important;">
			  <div class="card-header center-childs" style="background: #FF0000;"><h4 class="center-childs" style="color: white !important;">Register a new User</h4></div>
			  <div class="card-body">
			    <h5 class="card-title">Enter the email of the new user</h5>
			    <form id="formRegisterUser" class="col-md-12 col-xs-12 needs-validation">
					 <div class="form-group">
					   <label for="name">Email</label>
					   <input type="email" name="email" class="form-control" value="${requestScope.email}"
						id="formPropName" placeholder="Enter Email" required>
				 	 </div>
				</form>
				<div id="alertMsg" class="alert alert-success col-xs-12 col-md-12" role="alert" style="display: none;">
				  <h4 class="alert-heading">User Created</h4>
				  <div id="alertContent">
				  </div>
				  <hr>
				  <div>
					  <p style="display: inline-block;" class="mb-0">Press de button to copy the data.</p>
					  <i id="cpyData" style="display: inline-block; cursor: pointer;" class="fa fa-clipboard" aria-hidden="true"></i>
				  </div>
				  <br/>
				  <a type="button" class="btn btn-secondary col"
								href=RegisterController
								data-dismiss="modal">Continue</a>
				</div>
				<br/>
				<div id="cardFooter" class="col-xs-12 col-md-12 modal-footer">
					<div class="row">
						<div class="ml-auto">
							<a type="button" class="btn btn-secondary ml-auto"
								href="Home"
								data-dismiss="modal">Back</a>
							&nbsp; &nbsp;
					       	<button id="btnRegisterUser" type="button"
												class="btn btn-success ml-auto">
					       	<span id="spnrRegisterUser"
													class="spinner-border spinner-border-sm" role="status"
													aria-hidden="true" style="display: none;"></span>
					       	Create</button>
						</div>
					</div>
				</div>
			  </div>
			</div>
		</div>
		
		<script src="LogIn/js/aes.js"></script>
		<script src="Register/js/main.js"></script>
	</jsp:attribute>
</mt:masterpage>