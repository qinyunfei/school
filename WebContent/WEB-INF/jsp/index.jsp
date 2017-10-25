<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
  <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <!--
BeyondAdmin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.2.0
Version: 1.0.0
Purchase: http://wrapbootstrap.com
-->

<html xmlns="http://www.w3.org/1999/xhtml">
<!-- Head -->
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title>首页</title>

<meta name="description" content="Dashboard" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="assets/img/favicon.png"
	type="image/x-icon">


<!--Basic Styles-->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link id="bootstrap-rtl-link" href="" rel="stylesheet" />
<link href="assets/css/font-awesome.min.css" rel="stylesheet" />
<link href="assets/css/weather-icons.min.css" rel="stylesheet" />

<!--Fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300"
	rel="stylesheet" type="text/css">

<!--Beyond styles-->
<link id="beyond-link" href="assets/css/beyond.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/demo.min.css" rel="stylesheet" />
<link href="assets/css/typicons.min.css" rel="stylesheet" />
<link href="assets/css/animate.min.css" rel="stylesheet" />
<link id="skin-link" href="" rel="stylesheet" type="text/css" />
<!-- 引入JQuery -->
<script type="text/javascript" src="resource/easyui/jquery.min.js"></script>
<!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
<script src="assets/js/skins.min.js"></script>

</head>
<!-- /Head -->
<!-- Body -->
<body height="100%">


	<!-- Loading Container -->
	<div class="loading-container">
		<div class="loading-progress">
			<div class="rotator">
				<div class="rotator">
					<div class="rotator colored">
						<div class="rotator">
							<div class="rotator colored">
								<div class="rotator colored"></div>
								<div class="rotator"></div>
							</div>
							<div class="rotator colored"></div>
						</div>
						<div class="rotator"></div>
					</div>
					<div class="rotator"></div>
				</div>
				<div class="rotator"></div>
			</div>
			<div class="rotator"></div>
		</div>
	</div>
	<!--  /Loading Container -->
	<!-- Navbar -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="navbar-container">
				<!-- Navbar Barnd -->
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand"> <small> <img
							src="assets/img/logo.png" alt="" />
					</small>
					</a>
				</div>
				<!-- /Navbar Barnd -->

				<!-- Sidebar Collapse -->
				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="collapse-icon fa fa-bars"></i>
				</div>
				<!-- /Sidebar Collapse -->


				<!-- Account Area and Settings --->
				<div class="navbar-header pull-right">
					<div class="navbar-account">
						<ul class="account-area">
							<li><a class="login-area dropdown-toggle"
								data-toggle="dropdown">
									<div class="avatar" title="View your public profile">
										<img src="assets/img/avatars/adam-jansen.jpg">
									</div>
									<section>
										<h2>
											<span class="profile"><span><shiro:principal/></span></span>
										</h2>
									</section>
							</a> <!--Login Area Dropdown-->
								<ul
									class="pull-right dropdown-menu dropdown-arrow dropdown-login-area">
									<li class="username"><a>David Stevenson</a></li>
									<li class="email"><a>David.Stevenson@live.com</a></li>
									<!--Avatar Area-->
									<li>
										<div class="avatar-area">
											<img src="assets/img/avatars/adam-jansen.jpg" class="avatar">
											<span class="caption">Change Photo</span>
										</div>
									</li>
									<!--Avatar Area-->
									<li class="edit"><a href="profile.html" class="pull-left">Profile</a>
										<a href="#" class="pull-right">Setting</a></li>
									<!--Theme Selector Area-->
									<li class="theme-area">
										<ul class="colorpicker" id="skin-changer">
											<li><a class="colorpick-btn" href="#"
												style="background-color: #5DB2FF;"
												rel="assets/css/skins/blue.min.css"></a></li>
											<li><a class="colorpick-btn" href="#"
												style="background-color: #2dc3e8;"
												rel="assets/css/skins/azure.min.css"></a></li>
											<li><a class="colorpick-btn" href="#"
												style="background-color: #03B3B2;"
												rel="assets/css/skins/teal.min.css"></a></li>
											<li><a class="colorpick-btn" href="#"
												style="background-color: #53a93f;"
												rel="assets/css/skins/green.min.css"></a></li>
											<li><a class="colorpick-btn" href="#"
												style="background-color: #FF8F32;"
												rel="assets/css/skins/orange.min.css"></a></li>
											<li><a class="colorpick-btn" href="#"
												style="background-color: #cc324b;"
												rel="assets/css/skins/pink.min.css"></a></li>
											<li><a class="colorpick-btn" href="#"
												style="background-color: #AC193D;"
												rel="assets/css/skins/darkred.min.css"></a></li>
											<li><a class="colorpick-btn" href="#"
												style="background-color: #8C0095;"
												rel="assets/css/skins/purple.min.css"></a></li>
											<li><a class="colorpick-btn" href="#"
												style="background-color: #0072C6;"
												rel="assets/css/skins/darkblue.min.css"></a></li>
											<li><a class="colorpick-btn" href="#"
												style="background-color: #585858;"
												rel="assets/css/skins/gray.min.css"></a></li>
											<li><a class="colorpick-btn" href="#"
												style="background-color: #474544;"
												rel="assets/css/skins/black.min.css"></a></li>
											<li><a class="colorpick-btn" href="#"
												style="background-color: #001940;"
												rel="assets/css/skins/deepblue.min.css"></a></li>
										</ul>
									</li>
									<!--/Theme Selector Area-->
									<li class="dropdown-footer"><a href="${pageContext.request.contextPath}/logout"> Sign
											out </a></li>
								</ul> <!--/Login Area Dropdown--></li>
							<!-- /Account Area -->
							<!--Note: notice that setting div must start right after account area list.
                            no space must be between these elements-->
							<!-- Settings -->
						</ul>
						<div class="setting">
							<a id="btn-setting" title="Setting" href="#"> <i
								class="icon glyphicon glyphicon-cog"></i>
							</a>
						</div>
						<div class="setting-container">
							<label> <input type="checkbox" id="checkbox_fixednavbar">
								<span class="text">Fixed Navbar</span>
							</label> <label> <input type="checkbox"
								id="checkbox_fixedsidebar"> <span class="text">Fixed
									SideBar</span>
							</label> <label> <input type="checkbox"
								id="checkbox_fixedbreadcrumbs"> <span class="text">Fixed
									BreadCrumbs</span>
							</label> <label> <input type="checkbox" id="checkbox_fixedheader">
								<span class="text">Fixed Header</span>
							</label>
						</div>
						<!-- Settings -->
					</div>
				</div>
				<!-- /Account Area and Settings -->
			</div>
		</div>
	</div>
	<!-- /Navbar -->
	<!-- Main Container -->
	<div class="main-container container-fluid">
		<!-- Page Container -->
		<div class="page-container">
			<!-- Page Sidebar -->
			<div class="page-sidebar" id="sidebar">
				<!-- Page Sidebar Header-->
				<div class="sidebar-header-wrapper">
					<input type="text" class="searchinput" /> <i
						class="searchicon fa fa-search"></i>
					<div class="searchhelper">Search Reports, Charts, Emails or
						Notifications</div>
				</div>
				<!-- /Page Sidebar Header -->

				<ul id='mycd' class="nav sidebar-menu">			
				</ul>


			</div>
			<!-- /Page Sidebar -->



			<!-- Page Content -->
			<div class="page-content">
				<!-- Page Breadcrumb -->
				<div class="page-breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="fa fa-home"></i> <a href="#">Home</a></li>
						<li class="active">Dashboard</li>
					</ul>
				</div>
				<!-- /Page Breadcrumb -->
				<!-- Page Header -->
				<div class="page-header position-relative">
					<div class="header-title">
						<h1>Dashboard</h1>
					</div>
					<!--Header Buttons-->
					<div class="header-buttons">
						<a class="sidebar-toggler" href="#"> <i class="fa fa-arrows-h"></i>
						</a> <a class="refresh" id="refresh-toggler" href=""> <i
							class="glyphicon glyphicon-refresh"></i>
						</a> <a class="fullscreen" id="fullscreen-toggler" href="#"> <i
							class="glyphicon glyphicon-fullscreen"></i>
						</a>
					</div>
					<!--Header Buttons End-->
				</div>
				<!-- /Page Header -->
				<div >
			 		<iframe name="content" src="" id="iframepage" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"  width="100%"></iframe>
				</div>

				<script type="text/javascript" language="javascript">
			
			 
					var li_id="wc";
					//动态更改高度
					  function reinitIframe() {    
					     var iframe = document.getElementById("iframepage");           
					     iframe.height = $(window).height()-125;       
					}       
					window.setInterval("reinitIframe()", 200); //定时去检查iframe的高度,这样保证时时都是自动高了 
				  
					//点击菜单更改选择样式
					function gbclass(id,parentId){
						var submenus=$("#li_"+id+"").attr("submes");
						if(submenus=="1"){
							$("#li_"+id+"").toggleClass("active");
						}else{
							if (li_id!="wc"&&li_id!=id) {
								$("#li_"+li_id+"").removeClass("active");
								$("#li_"+id+"").addClass("active");
							}else{
								$("#li_"+id+"").addClass("active");
							}
							li_id=id;  
						}
					}
					
					//写入菜单 递归处理
					function xhcd(data){
						var neiron="";
						if (data==undefined) {
							return "";
						}
						for (ibz in data) {
							var url=data[ibz].url;
							var a_class="class=' '";
							var i="";
							var icon=data[ibz].icon;
							var submenu="";
							var submenujies="";
							var s_name=data[ibz].name;
							if (data[ibz].children.length>0) {
								a_class='class="menu-dropdown"';
								i='<i class="menu-expand"></i>';
								submenu='<ul class="submenu">';
								submenujies='</ul>';
								neiron+='<li  id="li_'+data[ibz].id+'" submes="1" >'
										+'<a onclick="gbclass('+data[ibz].id+','+data[ibz].parentId+')"  id="a_'+data[ibz].id+'" target="content" href="'+url +'" '+a_class+'>'
									    +'<i class="'+icon+'"></i>'
									    +'<span class="menu-text">'
									    +s_name
									    +'</span>'
									    +i
									    +'</a>'
									    +submenu
									    +xhcd(data[ibz].children)
									    +submenujies
									    +'</li>';
							}else{
								neiron+='<li id="li_'+data[ibz].id+'" submes="0">'
									+'<a onclick="gbclass('+data[ibz].id+','+data[ibz].parentId+')"  id="a_'+data[ibz].id+'" target="content" href="'+url +'" '+a_class+'>'
									+'<i class="'+icon+'"></i>'
								    +'<span class="menu-text">'
								    +s_name
								    +'</span>'
								    +i
								    +'</a>'
								    +'</li>';
							}

						
						}
						 return neiron;   
					}
					
					
					$(function(){
						
						$.post("${pageContext.request.contextPath}/resource/menus",
						          function(data){
							 			var neiron=xhcd(data);
										$("#mycd").html(neiron)
						          }, "json");
						}); 
					
					
					
					
					
				</script>

			</div>
			<!-- /Page Content -->















		</div>
		<!-- /Page Container -->
		<!-- Main Container -->

	</div>

	<!--Basic Scripts-->
	<script src="assets/js/jquery-2.0.3.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>

	<!--Beyond Scripts-->
	<script src="assets/js/beyond.min.js"></script>


	<!--Page Related Scripts-->
	<!--Sparkline Charts Needed Scripts-->
	<script src="assets/js/charts/sparkline/jquery.sparkline.js"></script>
	<script src="assets/js/charts/sparkline/sparkline-init.js"></script>

	<!--Easy Pie Charts Needed Scripts-->
	<script src="assets/js/charts/easypiechart/jquery.easypiechart.js"></script>
	<script src="assets/js/charts/easypiechart/easypiechart-init.js"></script>

	<!--Flot Charts Needed Scripts-->
	<script src="assets/js/charts/flot/jquery.flot.js"></script>
	<script src="assets/js/charts/flot/jquery.flot.resize.js"></script>
	<script src="assets/js/charts/flot/jquery.flot.pie.js"></script>
	<script src="assets/js/charts/flot/jquery.flot.tooltip.js"></script>
	<script src="assets/js/charts/flot/jquery.flot.orderBars.js"></script>

	<script>
		// If you want to draw your charts with Theme colors you must run initiating charts after that current skin is loaded
		$(window)
				.bind(
						"load",
						function() {

							/*Sets Themed Colors Based on Themes*/
							themeprimary = getThemeColorFromCss('themeprimary');
							themesecondary = getThemeColorFromCss('themesecondary');
							themethirdcolor = getThemeColorFromCss('themethirdcolor');
							themefourthcolor = getThemeColorFromCss('themefourthcolor');
							themefifthcolor = getThemeColorFromCss('themefifthcolor');

							//Sets The Hidden Chart Width
							$('#dashboard-bandwidth-chart').data('width',
									$('.box-tabbs').width() - 20);

							//-------------------------Visitor Sources Pie Chart----------------------------------------//
							var data = [ {
								data : [ [ 1, 21 ] ],
								color : '#fb6e52'
							}, {
								data : [ [ 1, 12 ] ],
								color : '#e75b8d'
							}, {
								data : [ [ 1, 11 ] ],
								color : '#a0d468'
							}, {
								data : [ [ 1, 10 ] ],
								color : '#ffce55'
							}, {
								data : [ [ 1, 46 ] ],
								color : '#5db2ff'
							} ];
							var placeholder = $("#dashboard-pie-chart-sources");
							placeholder.unbind();

							$.plot(placeholder, data, {
								series : {
									pie : {
										innerRadius : 0.45,
										show : true,
										stroke : {
											width : 4
										}
									}
								}
							});

							//------------------------------Visit Chart------------------------------------------------//
							var data2 = [
									{
										color : themesecondary,
										label : "Direct Visits",
										data : [ [ 3, 2 ], [ 4, 5 ], [ 5, 4 ],
												[ 6, 11 ], [ 7, 12 ],
												[ 8, 11 ], [ 9, 8 ],
												[ 10, 14 ], [ 11, 12 ],
												[ 12, 16 ], [ 13, 9 ],
												[ 14, 10 ], [ 15, 14 ],
												[ 16, 15 ], [ 17, 9 ] ],

										lines : {
											show : true,
											fill : true,
											lineWidth : .1,
											fillColor : {
												colors : [ {
													opacity : 0
												}, {
													opacity : 0.4
												} ]
											}
										},
										points : {
											show : false
										},
										shadowSize : 0
									},
									{
										color : themeprimary,
										label : "Referral Visits",
										data : [ [ 3, 10 ], [ 4, 13 ],
												[ 5, 12 ], [ 6, 16 ],
												[ 7, 19 ], [ 8, 19 ],
												[ 9, 24 ], [ 10, 19 ],
												[ 11, 18 ], [ 12, 21 ],
												[ 13, 17 ], [ 14, 14 ],
												[ 15, 12 ], [ 16, 14 ],
												[ 17, 15 ] ],
										bars : {
											order : 1,
											show : true,
											borderWidth : 0,
											barWidth : 0.4,
											lineWidth : .5,
											fillColor : {
												colors : [ {
													opacity : 0.4
												}, {
													opacity : 1
												} ]
											}
										}
									},
									{
										color : themethirdcolor,
										label : "Search Engines",
										data : [ [ 3, 14 ], [ 4, 11 ],
												[ 5, 10 ], [ 6, 9 ], [ 7, 5 ],
												[ 8, 8 ], [ 9, 5 ], [ 10, 6 ],
												[ 11, 4 ], [ 12, 7 ],
												[ 13, 4 ], [ 14, 3 ],
												[ 15, 4 ], [ 16, 6 ], [ 17, 4 ] ],
										lines : {
											show : true,
											fill : false,
											fillColor : {
												colors : [ {
													opacity : 0.3
												}, {
													opacity : 0
												} ]
											}
										},
										points : {
											show : true
										}
									} ];
							var options = {
								legend : {
									show : false
								},
								xaxis : {
									tickDecimals : 0,
									color : '#f3f3f3'
								},
								yaxis : {
									min : 0,
									color : '#f3f3f3',
									tickFormatter : function(val, axis) {
										return "";
									},
								},
								grid : {
									hoverable : true,
									clickable : false,
									borderWidth : 0,
									aboveData : false,
									color : '#fbfbfb'

								},
								tooltip : true,
								tooltipOpts : {
									defaultTheme : false,
									content : " <b>%x May</b> , <b>%s</b> : <span>%y</span>",
								}
							};
							var placeholder = $("#dashboard-chart-visits");
							var plot = $.plot(placeholder, data2, options);

							//------------------------------Real-Time Chart-------------------------------------------//
							var data = [], totalPoints = 300;

							function getRandomData() {

								if (data.length > 0)
									data = data.slice(1);

								// Do a random walk

								while (data.length < totalPoints) {

									var prev = data.length > 0 ? data[data.length - 1]
											: 50, y = prev + Math.random() * 10
											- 5;

									if (y < 0) {
										y = 0;
									} else if (y > 100) {
										y = 100;
									}

									data.push(y);
								}

								// Zip the generated y values with the x values

								var res = [];
								for (var i = 0; i < data.length; ++i) {
									res.push([ i, data[i] ]);
								}

								return res;
							}
							// Set up the control widget
							var updateInterval = 100;
							var plot = $
									.plot("#dashboard-chart-realtime",
											[ getRandomData() ], {
												yaxis : {
													color : '#f3f3f3',
													min : 0,
													max : 100,
													tickFormatter : function(
															val, axis) {
														return "";
													}
												},
												xaxis : {
													color : '#f3f3f3',
													min : 0,
													max : 100,
													tickFormatter : function(
															val, axis) {
														return "";
													}
												},
												colors : [ themeprimary ],
												series : {
													lines : {
														lineWidth : 0,
														fill : true,
														fillColor : {
															colors : [ {
																opacity : 0.5
															}, {
																opacity : 0
															} ]
														},
														steps : false
													},
													shadowSize : 0
												},
												grid : {
													hoverable : true,
													clickable : false,
													borderWidth : 0,
													aboveData : false
												}
											});

							function update() {

								plot.setData([ getRandomData() ]);

								plot.draw();
								setTimeout(update, updateInterval);
							}
							update();

							//-------------------------Initiates Easy Pie Chart instances in page--------------------//
							InitiateEasyPieChart.init();

							//-------------------------Initiates Sparkline Chart instances in page------------------//
							InitiateSparklineCharts.init();
						});
	</script>
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
		})(window, document, 'script',
				'http://www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-52103994-1', 'auto');
		ga('send', 'pageview');
	</script>


</body>
<!--  /Body -->
</html>
