<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="title" required="true" rtexprvalue="true"%>
<%@ attribute name="content" fragment="true"%>
<html lang="en">

<head>
<meta charset="utf-8">
<link rel="shortcut icon" href="/favicon.ico">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.10.2/css/all.css">

<meta name="viewport" content="width=device-width,initial-scale=1">
<meta name="theme-color" content="#000000">
<link rel="manifest" href="/manifest.json">
<title>CALA PoC Dev Team - ${title }</title>
<link href="css/sb-admin.css" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link href="css/2.5fc38d15.chunk.css" rel="stylesheet">
<link href="css/main.17086f5e.chunk.css" rel="stylesheet">
</head>

<body>
	<script
		src="https://cdn.cookielaw.org/consent/da235a6b-8230-462c-8f13-eccb4ce4537b.js"
		type="text/javascript" charset="UTF-8"></script>
	<noscript>You need to enable JavaScript to run this app.</noscript>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<div id="root">
		<section class="ant-layout ant-layout-has-sider">
			<div>
				<aside
					class="navigation-menu ant-layout-sider ant-layout-sider-light ant-layout-sider-has-trigger"
					style="flex: 0 0 250px; max-width: 250px; min-width: 250px; width: 250px;">
					<div class="ant-layout-sider-children">
						<div>
							<img src="images/logo.png" alt="logo">
							<p class="logo-text bottom-spacing-4">
								<span class="large-text">CALA PoC Dev Team<br>Demo
									Page
								</span>
							</p>
						</div>
						<ul
							class="ant-menu menu ant-menu-light ant-menu-root ant-menu-inline"
							role="menu">
							<li class="ant-menu-item" role="menuitem"
								style="padding-left: 24px;"><a href="Home"> <span>
										<i class="fas fa-home"></i> <i class="anticon"
										style="margin-right: 20px; vertical-align: text-bottom;"><svg
												xmlns="http://www.w3.org/2000/svg" width="25"
												fill="currentColor" viewBox="0 0 149.98 150"></svg></i> <span
										class=" large-text">Home</span>
								</span></a></li>
							<c:if test="${sessionScope.UserBeanSession != null}">
								<!-- <li
									class="ant-menu-submenu ant-menu-submenu-inline ant-menu-submenu-open"
									role="menuitem">
									<div class="ant-menu-submenu-title" aria-expanded="true"
										aria-haspopup="true" style="padding-left: 24px;"
										aria-owns="Solutions$Menu">
										<span> <i class="fas fa-toolbox"></i> <i
											class="anticon"
											style="margin-right: 20px; vertical-align: text-bottom;"><svg
													xmlns="http://www.w3.org/2000/svg" width="25"
													fill="currentColor" viewBox="0 0 155.16 150"></svg></i><span
											class="large-text">Solutions</span></span><i
											class="ant-menu-submenu-arrow"></i>
									</div>
									<ul id="Solutions$Menu"
										class="ant-menu ant-menu-sub ant-menu-inline" role="menu"
										style>
										<li id="subVertical" class="ant-menu-item" role="menuitem"
											style="padding-left: 48px;"><a href="Demos"> <span><i
													class="anticon"
													style="margin-right: 20px; vertical-align: text-bottom;"></i><span
													class="medium-text">Demos</span></span>
										</a></li>
									</ul>
								</li> -->
								<li class="ant-menu-item" role="menuitem"
									style="padding-left: 24px;"><a href="Demos"> <span>
											<i class="fas fa-toolbox"></i> <i class="anticon"
											style="margin-right: 20px; vertical-align: text-bottom;"><svg
													xmlns="http://www.w3.org/2000/svg" width="25"
													fill="currentColor" viewBox="0 0 149.98 150"></svg></i> <span
											class=" large-text">Demos</span>
									</span></a></li>
							</c:if>
							<c:if test="${sessionScope.UserBeanSession.rol == 'SADMIN'}">
								<li class="ant-menu-item" role="menuitem"
									style="padding-left: 24px;"><a href="Demos?p=admin"> <span>
											<i class="fas fa-tools"></i> <i class="anticon"
											style="margin-right: 20px; vertical-align: text-bottom;"><svg
													xmlns="http://www.w3.org/2000/svg" width="25"
													fill="currentColor" viewBox="0 0 149.98 150"></svg></i> <span
											class=" large-text">Admin</span>
									</span></a></li>
							</c:if>
						</ul>
					</div>
					<div class="ant-layout-sider-trigger" style="width: 250px;">
						<i aria-label="icon: left" class="anticon anticon-left"><svg
								viewBox="64 64 896 896" focusable="false" class=""
								data-icon="left" width="1em" height="1em" fill="currentColor"
								aria-hidden="true">
                                <path
									d="M724 218.3V141c0-6.7-7.7-10.4-12.9-6.3L260.3 486.8a31.86 31.86 0 0 0 0 50.3l450.8 352.1c5.3 4.1 12.9.4 12.9-6.3v-77.3c0-4.9-2.3-9.6-6.1-12.6l-360-281 360-281.1c3.8-3 6.1-7.7 6.1-12.6z">
                                </path>
                            </svg></i>
					</div>
				</aside>
			</div>
			<section class="ant-layout">
				<header>
					<div class="ant-row">
						<nav id="nav" class="navbar navbar-expand-lg navbar-light bg-light ml-auto">
							
							<a class="navbar-brand">${title }</a>
							<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                              <span class="navbar-toggler-icon"></span>
                            </button>
                            							
							<div class="collapse navbar-collapse ml-auto" id="navbarText">
                              <ul class="navbar-nav ml-auto">
									<!-- Aqui va el login -->
									<c:choose>
									<c:when test="${sessionScope.UserBeanSession == null}">
										<li class="nav-item active">
											
												<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalRegistro">
												  Sign Up
												</button>
										
										</li>
										<li class="nav-item active">
											
												<a href="LogIn"
												class="btn btn-danger mx-2"> Login
												</a>							
										
										</li>
									</c:when>
									<c:otherwise>
										<li class="nav-item active">
												
														<p class="text-center mx-2 my-2">
																	${sessionScope.UserBeanSession.userName} 
														</p>
										</li>
										<li class="nav-item active">
											
												<button type="button" id="btnLogOut"
												class="btn btn-danger"> Logout
											</button>
										
										
										</li>
									</c:otherwise>
									</c:choose>
								</ul>
                            </div>
						</nav>
					</div>
				</header>
				<main id="content-container" class="content ant-layout-content">
					<jsp:invoke fragment="content"></jsp:invoke>
				</main>
				<!-- contenido -->
				
				<footer class="footer ant-layout-footer">
					<div class="ant-row right-align">
						<span class="paragraph">© 2020 Avaya, Inc.   Ver 3.7.0.0.26</span><span>&nbsp;&nbsp;|&nbsp;&nbsp;</span><a
							class="optanon-toggle-display cookie-settings-button link"
							href="# " title="Cookie Statement" aria-label="Cookie Statement"
							tabindex="1">Cookie Statement</a><span>&nbsp;&nbsp;|&nbsp;&nbsp;</span><span><a
							href="https://www.avaya.com/en/privacy/website/"
							rel="noopener noreferrer" target="_blank" class="link">Privacy
								Statement</a></span>
					</div>
				</footer>
			</section>
		</section>
	</div>
	<div id="modalRegistro" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-md modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header" style="background: red;">
	        <h5 class="modal-title" id="exampleModalLabel" style="color: white !important;">Register Info</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body col-md-12 col-xs-12">
	        <p class="col-md-12 col-xs-12">Please fill out the registration form, we will contact you as soon as we validate the information</p>
	        <form role="form"
							class="form-horizontal reg-form needs-validation col-md-12 col-xs-12"
							novalidate="true">
							<div class="js-fields-container row">
								<!-- Here are going to be placed the registration fields -->
								<div class="has-feedback reg-field col-xs-12 col-md-12"
									style="margin-bottom: 12px;">
									<div>
										<!--<label class ="visually-hidden"><span class="data-label"></span></label>-->
										<input type="text"
											class="form-control input-sm js-input data-input validationInput"
											data-toggle="tooltip" data-placement="bottom"
											data-trigger="manual" maxlength="500" name="inputName"
											id="inputName" aria-label="First Name" required="required"
											style="z-index: 10; background-color: transparent;"
											data-original-title="This is a required field" title="">
										<div class="tooltip fade bottom in"
											style="top: 30px; left: 164px; display: none;">
											<div class="tooltip-arrow"></div>
											<div class="tooltip-inner">This is a required field</div>
										</div>
										<label class="placeholder-label js-label" aria-hidden="true"><span
											class="data-label" style="float: right;">First Name</span><span
											style="float: right;" class="required data-required text-danger">*</span></label>
									</div>
								</div>
								<div class="has-feedback reg-field col-xs-12 col-md-12"
									style="margin-bottom: 12px;">
									<div>

										<!--<label class ="visually-hidden"><span class="data-label"></span></label>-->
										<input type="text"
											class="form-control input-sm js-input data-input validationInput"
											data-toggle="tooltip" data-placement="bottom"
											data-trigger="manual" maxlength="500" name="inputApellido"
											id="inputApellido" aria-label="Last Name" required="required"
											style="z-index: 10; background-color: transparent;"
											data-original-title="This is a required field" title="">
										<div class="tooltip fade bottom in"
											style="top: 30px; left: 164px; display: none;">
											<div class="tooltip-arrow"></div>
											<div class="tooltip-inner">This is a required field</div>
										</div>
										<label class="placeholder-label js-label" aria-hidden="true">
											<span class="data-label" style="float: right;">Last
												Name</span><span style="float: right;"
											class="required data-required text-danger">*</span>
										</label>
									</div>
								</div>
								<div class="has-feedback reg-field col-xs-12 col-md-12"
									style="margin-bottom: 12px;">
									<div>
										<!--<label class ="visually-hidden"><span class="data-label"></span></label>-->
										<input type="email"
											class="form-control input-sm js-input data-input validationInput"
											data-toggle="tooltip" data-placement="bottom"
											data-trigger="manual" maxlength="500" name="inputEmail"
											id="inputEmail" aria-label="Email" required="required"
											style="z-index: 10; background-color: transparent;"
											data-original-title="This is a required field" title="">
										<div class="tooltip fade bottom in"
											style="top: 30px; left: 164px; display: none;">
											<div class="tooltip-arrow"></div>
											<div class="tooltip-inner">This is a required field</div>
										</div>
										<label class="placeholder-label js-label" aria-hidden="true">
											<span class="data-label" style="float: right;">Email</span><span
											style="float: right;" class="required data-required text-danger">*</span>
										</label>
									</div>
								</div>
								
								<div class="has-feedback reg-field col-xs-12 col-md-12"
									aria-live="polite" style="margin-bottom: 12px;">
									<div>

										<!--<label class ="visually-hidden"><span class="data-label"></span></label>-->
										<input type="text"
											class="form-control input-sm js-input data-input validationInput"
											data-toggle="tooltip" data-placement="bottom"
											data-trigger="manual" maxlength="500" name="phoneMovil"
											id="phoneMovil" aria-label="Company" required="required"
											style="z-index: 10; background-color: transparent;"
											data-original-title="This is a required field" title="">
										<div class="tooltip fade bottom in"
											style="top: 30px; left: 109px; display: none;">
											<div class="tooltip-arrow"></div>
											<div class="tooltip-inner">This is a required field</div>
										</div>
										<label class="placeholder-label js-label" aria-hidden="true">
											<span class="data-label" style="float: right;">Mobile
												Phone</span>
											<span style="float: right;"
											class="required data-required text-danger">*</span>
										</label>
									</div>
								</div>
								
								<div class="has-feedback reg-field col-xs-12 col-md-12"
									style="margin-bottom: 12px;">
									<div>

										<!--<label class ="visually-hidden"><span class="data-label"></span></label>-->
										<input type="text"
											class="form-control input-sm js-input data-input validationInput"
											data-toggle="tooltip" data-placement="bottom"
											data-trigger="manual" maxlength="500" name="inputCiudad"
											id="inputCiudad" aria-label="City" required="required"
											style="z-index: 10; background-color: transparent;"
											data-original-title="This is a required field" title="">
										<div class="tooltip fade bottom in"
											style="top: 30px; left: 109px; display: none;">
											<div class="tooltip-arrow"></div>
											<div class="tooltip-inner">This is a required field</div>
										</div>
										<label class="placeholder-label js-label" aria-hidden="true">
											<span style="float: right;"
											class="data-label">City</span>
											<span
											class="required data-required text-danger" style="float: right;">*</span></label>
									</div>
								</div>
								
								
								</div>
								</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
	        <button id="stepButton" type="button" class="btn btn-success">
	        <span id="stepSpinner" class="spinner-border spinner-border-sm" role="status" aria-hidden="true" style="display: none;"></span>
	        Submit</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- Modal de proposito general -->
	<div id="generalModal" class="modal fade" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 id="modalTitle" class="modal-title">Modal title</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div id="modalBody" class="modal-body">
	        
	      </div>
	      <div id="modalFooter" class="modal-footer" style="display: none;">
	        <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
	        <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- Toast de notificaciones -->
	<!-- <div aria-live="polite" aria-atomic="true" style="position: relative; min-height: 200px;"> -->
	  <div class="toast" style="position: absolute; bottom: 30; right: 10; font-size: 30px">
	    <div class="toast-header" style="background-color: #333333">
	      <img src="images/logo.png" width=40px class="rwhiteounded mr-2" alt="Avaya">
	      <strong id="titleToast" class="mr-auto" style="color: white;">Bootstrap</strong>
	      <!-- <small>11 mins ago</small> -->
	      <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
	        <span aria-hidden="true">&times;</span>
	      </button>
	    </div>
	    <div id="contentToast" class="toast-body">
	      Registro creado correctamente
	    </div>
	  </div>
	<!-- </div> -->
	<script>var d = new Date; d.setFullYear(d.getFullYear() + 1, d.getMonth(), d.getDay()); var expires = "expires=" + d.toUTCString(); function addConsentCookies() { document.cookie = "OptanonAlertBoxClosed=true; " + expires }
        setTimeout(function () { document.querySelector(".accept-cookies-button").addEventListener("click", addConsentCookies, !1) }, 1e3)
    </script>
	<script>!function (p) { function e(e) { for (var r, t, n = e[0], o = e[1], u = e[2], l = 0, a = []; l < n.length; l++)t = n[l], Object.prototype.hasOwnProperty.call(i, t) && i[t] && a.push(i[t][0]), i[t] = 0; for (r in o) Object.prototype.hasOwnProperty.call(o, r) && (p[r] = o[r]); for (s && s(e); a.length;)a.shift()(); return c.push.apply(c, u || []), f() } function f() { for (var e, r = 0; r < c.length; r++) { for (var t = c[r], n = !0, o = 1; o < t.length; o++) { var u = t[o]; 0 !== i[u] && (n = !1) } n && (c.splice(r--, 1), e = l(l.s = t[0])) } return e } var t = {}, i = { 1: 0 }, c = []; function l(e) { if (t[e]) return t[e].exports; var r = t[e] = { i: e, l: !1, exports: {} }; return p[e].call(r.exports, r, r.exports, l), r.l = !0, r.exports } l.m = p, l.c = t, l.d = function (e, r, t) { l.o(e, r) || Object.defineProperty(e, r, { enumerable: !0, get: t }) }, l.r = function (e) { "undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(e, Symbol.toStringTag, { value: "Module" }), Object.defineProperty(e, "__esModule", { value: !0 }) }, l.t = function (r, e) { if (1 & e && (r = l(r)), 8 & e) return r; if (4 & e && "object" == typeof r && r && r.__esModule) return r; var t = Object.create(null); if (l.r(t), Object.defineProperty(t, "default", { enumerable: !0, value: r }), 2 & e && "string" != typeof r) for (var n in r) l.d(t, n, function (e) { return r[e] }.bind(null, n)); return t }, l.n = function (e) { var r = e && e.__esModule ? function () { return e.default } : function () { return e }; return l.d(r, "a", r), r }, l.o = function (e, r) { return Object.prototype.hasOwnProperty.call(e, r) }, l.p = "/"; var r = this.webpackJsonpae_portal = this.webpackJsonpae_portal || [], n = r.push.bind(r); r.push = e, r = r.slice(); for (var o = 0; o < r.length; o++)e(r[o]); var s = n; f() }([])</script>
	<!-- <script src="js/2.7e0fb75c.chunk.js"></script>
    <script src="js/main.2229284b.chunk.js"></script> -->
	<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<script src="js/main.js"></script>
	<script src="js/modalUtils.js"></script>
	<script src="js/register.js"></script>
</body>

</html>