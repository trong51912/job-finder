<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><dec:title default="Admin" /></title>
	<meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="manifest" href="site.webmanifest">
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='./template/web/img/favicon.ico' />">

	<!-- CSS here -->
    <link rel="stylesheet" href="<c:url value='/template/admin/css/nucleo-icons.css' />">
    <link rel="stylesheet" href="<c:url value='/template/admin/css/nucleo-svg.css' />">
        

	<!--     Fonts and icons     -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet" />
	  
	<!-- Nucleo Icons -->
	<link rel="stylesheet" href="<c:url value='/template/admin/css/soft-ui-dashboard.css?v=1.0.7' />">
	  
	<!-- Nepcha is a easy-to-use web analytics. No cookies and fully compliant with GDPR, CCPA and PECR. -->
	<script defer data-site="YOUR_DOMAIN_HERE" src="https://api.nepcha.com/js/nepcha-analytics.js"></script>
</head>
<body>
	
	<dec:body/>
	
	<!-- All JS Custom Plugins Link Here here -->
	<script src="./template/admin/js/core/popper.min.js"></script>
	<script src="./template/admin/js/core/bootstrap.min.js"></script>
	<script src="./template/admin/js/plugins/perfect-scrollbar.min.js"></script>
	<script src="./template/admin/js/plugins/smooth-scrollbar.min.js"></script>
	<script src="./template/admin/js/plugins/chartjs.min.js"></script>
	
	<!-- Control Center for Soft Dashboard: parallax effects, scripts for the example pages etc -->
    <script src="./template/admin/js/soft-ui-dashboard.min.js?v=1.0.7"></script>
    
  	<!-- Github buttons -->
  	<script async src="https://buttons.github.io/buttons.js"></script>
</body>
</html>