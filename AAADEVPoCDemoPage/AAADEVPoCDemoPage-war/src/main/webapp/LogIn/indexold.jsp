<%-- 
    Document   : index
    Created on : Jan 14, 2020, 1:34:28 PM
    Author     : umansilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="img/Icon-Avaya.png"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/custom.css">
        <link rel="stylesheet" href="css/css-loader.css">

        <script src="js/jquery.slim.js"></script>
        <link rel="stylesheet" href="//unpkg.com/bootstrap@3.3.7/dist/css/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="//unpkg.com/bootstrap-select@1.12.4/dist/css/bootstrap-select.min.css" type="text/css" />
        <link rel="stylesheet" href="//unpkg.com/bootstrap-select-country@4.0.0/dist/css/bootstrap-select-country.min.css" type="text/css" />

        <script src="//unpkg.com/jquery@3.4.1/dist/jquery.min.js"></script>
        <script src="//unpkg.com/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
        <script src="//unpkg.com/bootstrap-select@1.12.4/dist/js/bootstrap-select.min.js"></script>
        <script src="//unpkg.com/bootstrap-select-country@4.0.0/dist/js/bootstrap-select-country.min.js"></script>

        <title>CloudIVR</title>
    </head>
    <body>
        <div class="loader loader-default" data-blink id="loaderDisplay"></div>
        <div class="container-fluid">
            <div class="row no-gutter">
                <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
                <div class="col-md-8 col-lg-6">
                    <div class="login d-flex align-items-center py-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-9 col-lg-8 mx-auto" style="font-size: 20px;">
                                    <h3 class="login-heading mb-4" style="text-align: center;">America’s CALA PoC Development Team</h3>
                                    <h3 class="login-heading mb-4" style="text-align: center; font-weight: bold;">Omnichannel</h3>
                                    <form>
                                        <div class="form-label-group">
                                            <select class="selectpicker countrypicker form-control" data-flag="true" data-live-search="true" data-default="MX" id="country"></select>
                                        </div>
                                        <div class="form-label-group">
                                            <input type="text" id="inputCustomer" class="form-control" placeholder="Customer name"  required autofocus>
                                            <label for="inputCustomer">Customer Name</label>
                                        </div>
                                        <div class="form-label-group">
                                            <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                                            <label for="inputEmail">Email address</label>
                                        </div>

                                        <div class="form-label-group">
                                            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                                            <label for="inputPassword">Password</label>
                                        </div>
                                        <!--
                                        <div class="custom-control custom-checkbox mb-3">
                                            <input type="checkbox" class="custom-control-input" id="customCheck1">
                                            <label class="custom-control-label" for="customCheck1">Remember password</label>
                                        </div> -->
                                        <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" id="submitbtn" type="submit">Sign in</button>
                                        <!-- <div class="text-center">
                                            <a class="small" href="#">Forgot password?</a> 
                                         </div>-->

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>  
        <script>
            $('.countrypicker').countrypicker();
        </script>
        <script type="text/javascript" src="js/aes.js"></script>
        <script type="text/javascript" src="js/sweetAlertmin.js"></script>
        <script type="text/javascript" src="js/login.js"></script>

    </body>
</html>
