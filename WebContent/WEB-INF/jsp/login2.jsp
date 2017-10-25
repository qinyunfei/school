﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Beyond Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3
Version: 1.0.0
Purchase: http://wrapbootstrap.com
-->

<html>
<!--Head-->
<head>
<meta charset="utf-8" />
<title>Login Page</title>

<meta name="description" content="login page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="assets/img/favicon.png"
	type="image/x-icon">

<!--Basic Styles-->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link id="bootstrap-rtl-link" href="" rel="stylesheet" />
<link href="assets/css/font-awesome.min.css" rel="stylesheet" />

<!--Fonts-->
<link href="assets/css/zdy1.css" rel="stylesheet">
<!--Beyond styles-->
<link id="beyond-link" href="assets/css/beyond.min.css" rel="stylesheet" />
<link href="assets/css/demo.min.css" rel="stylesheet" />
<link href="assets/css/animate.min.css" rel="stylesheet" />
<link id="skin-link" href="" rel="stylesheet" type="text/css" />

<!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
<script src="assets/js/skins.min.js"></script>
</head>
<!--Head Ends-->
<!--Body-->
<body>
	<div class="login-container animated fadeInDown">
		<div class="loginbox bg-white">
			<div class="loginbox-title">SIGN IN</div>
			<div class="loginbox-social">
				<div class="social-title ">Connect with Your Social Accounts</div>
				<div class="social-buttons">
					<a href="" class="button-facebook"> <i
						class="social-icon fa fa-facebook"></i>
					</a> <a href="" class="button-twitter"> <i
						class="social-icon fa fa-twitter"></i>
					</a> <a href="" class="button-google"> <i
						class="social-icon fa fa-google-plus"></i>
					</a>
				</div>
			</div>
			<div class="loginbox-or">
				<div class="or-line"></div>
				<div class="or">OR</div>
			</div>


			<form  method="post">
				<div class="loginbox-textbox">
					<input type="text" class="form-control" placeholder="用户名" name="username"
						value="<shiro:principal/>">
				</div>
				<div class="loginbox-textbox">
					<input type="password" class="form-control" placeholder="密码" name="password">
					<input type="checkbox" name="rememberMe" value="true">
				</div>

			


		
			<div class="loginbox-forgot">
				<a href="">Forgot Password?</a>
			</div>
			<div class="loginbox-submit">
				<input type="submit" class="btn btn-primary btn-block" value="Login">
			</div>
			</form>
			<div class="loginbox-signup">
				${error}
			</div>
		</div>
		<div class="logobox"></div>
	</div>

	<!--Basic Scripts-->
	<script src="assets/js/jquery-2.0.3.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>

	<!--Beyond Scripts-->
	<script src="assets/js/beyond.js"></script>

	<!--Google Analytics::Demo Only-->
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script', 'assets/js/analytics.js', 'ga');

		ga('create', 'UA-52103994-1', 'auto');
		ga('send', 'pageview');
	</script>
</body>
<!--Body Ends-->
</html>
