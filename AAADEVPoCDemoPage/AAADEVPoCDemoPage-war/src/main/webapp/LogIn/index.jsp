<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:masterpage title="Home">
	<jsp:attribute name="content">	
		<!--<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">-->
		<link rel="stylesheet" href="LogIn/css/login.css">
		<!-- <link rel="stylesheet" href="LogIn/css/css-loader.css">  -->
		
		<div>
		    <div class="loader loader-default" data-blink="" id="loaderDisplay"></div>
		    <div class="mainLogin">
		        <div class="container">
		
		        </div>
		
		        <div class="containerLogin">
		            <div class="row">
		                <!-- <div class="col-lg-12 col-centered text-center">
		                    <h1 style="font-size: 45px; color: #ffffff !important;">America’s International PoC Development Team</h1>
		                </div> -->
		                <div class="col-lg-12 col-centered">
		
		                    <div class="col-md-8" id="login">
		
		                        <form action="javascript:void(0);" method="get">
		
		                            <fieldset class="clearfix">
		                                <br>
		
		                                <p>
													<span class="fa fas fa-globe"></span><input id="country"
														type="text" placeholder="Country" required="">
												</p>
		                                <br>
		                                <p>
													<span class="fa fa-user"></span><input id="cliente"
														type="text" placeholder="Customer" required="">
												</p>
		                                <br>
		                                <p>
													<span class="fa fas fa-envelope"></span><input id="email"
														type="text" placeholder="Email" required="">
												</p>
		                                <!-- JS because of IE support; better: placeholder="Username" -->
		                                <br>
		                                <p>
													<span class="fa fa-lock"></span><input id="pass"
														type="password" placeholder="Password" required="">
												</p>
		                                <!-- JS because of IE support; better: placeholder="Password" -->
		                                <br>
		                                <div>
		                                    <span
														style="width: 48%; text-align: left; display: inline-block;"><a
														id="forgotten" class="small-text">Forgot
		                                            password?</a></span>
		                                    <span
														style="width: 50%; text-align: right; display: inline-block;"><input
														id="submitbtn" type="submit" value="Sign In"></span>
		                                </div>
		
		                            </fieldset>
		                            <div class="clearfix"></div>
		                        </form>
		
		                        <div class="clearfix"></div>
		
		                    </div> <!-- end login -->
		
		
		                    <div class="col-centered text-center logo">
		                        <img src="images/logo.png" width="200px"
											alt="AvayaIMG">
		                        <div style="font-size: 20px">AVAYA</div>
		                    </div>
		                </div>
		
		
		
		            </div>
		        </div>
		
		    </div>
		    <script src="LogIn/js/aes.js"></script>
		    <script src="LogIn/js/sweetAlertmin.js"></script>
		    <script src="LogIn/js/login.js"></script>
		
	
	</jsp:attribute>
</mt:masterpage>