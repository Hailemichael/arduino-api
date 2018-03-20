<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
  content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="hailemichael">
<title>Home Appliance Control</title>
<link rel="icon"
  href="<c:url value='/resources/images/smart-home-icon.png' />">

<!-- bootstrap -->
<link rel="stylesheet"
  href="<c:url value='/resources/bootstrap/css/bootstrap.min.css' />">
<!-- link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" -->
<link rel="stylesheet"
  href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">

<!-- Bootstrap Form Helpers -->
<link rel="stylesheet" media="screen"
  href="<c:url value='/resources/BootstrapFormHelpers/dist/css/bootstrap-formhelpers.css' />">

<!-- Custom style -->
<link rel="stylesheet"
  href="<c:url value='/resources/css/navbar.css' />">
<link rel="stylesheet"
  href="<c:url value='/resources/css/left-sidebar.css' />">
<link rel="stylesheet"
  href="<c:url value='/resources/css/carousel.css' />">

</head>
<body>

  <!-- ######## Header ######## -->
  <%@include file="/resources/divs/header.jsp"%>
  <!-- ######## END Header ######## -->

  <!-- ######### body ######### -->

  <!-- Other Home page content -->
  <div class="container">
    <div class="row main-content-text">
      <div class="col-md-4">
        <h2>Water Plants</h2>
        <p>Water plants in one click.</p>
        <button class="btn btn-success"
          onclick="location.href='<c:url value="/waterplant" />'">Water</button>
        <div id="alert_placeholder_plant"></div>
      </div>
      <div class="col-md-4">
        <h2>Control Garage Door</h2>
        <p>Open and close your garage door</p>
        <button class="btn btn-success"
          onclick="location.href='<c:url value="/opengarage" />'">Open</button>
        <button class="btn btn-danger"
          onclick="location.href='<c:url value="/closegarage" />'">Close</button>
        <div id="alert_placeholder_garage"></div>
      </div>
      <div class="col-md-4">
        <h2>Control Yourself</h2>
        <p>That is a little bit scary ¯\_(ツ)_/¯.</p>
        <button class="btn btn-info"
          onclick="location.href='<c:url value="/" />'">Think?</button>
        <button class="btn btn-primary"
          onclick="location.href='<c:url value="/" />'">Ready</button>
        <button class="btn btn-success"
          onclick="location.href='<c:url value="/" />'">Work</button>
        <button class="btn btn-danger"
          onclick="location.href='<c:url value="/" />'">Sleep</button>
        <div id="alert_placeholder_other"></div>
      </div>
    </div>
  </div>

  <!-- End other Home page content -->
  <!-- ######### END body ######### -->

  <!-- ###### footer ####### -->
  <%@include file="/resources/divs/footer.jsp"%>
  <!-- ###### END footer ####### -->

  <!-- ##### Scripts ###### -->

  <script type="text/javascript"
    src="<c:url value='/resources/jquery/jquery-3.2.0.min.js' />"></script>
  <script type="text/javascript"
    src="<c:url value='/resources/bootstrap/js/bootstrap.min.js' />"></script>
  <!-- script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script -->

  <!-- Bootstrap Form Helpers -->
  <script type="text/javascript"
    src="<c:url value='/resources/BootstrapFormHelpers/dist/js/bootstrap-formhelpers.min.js' />"></script>

  <!-- Alert JS -->
  <script type="text/javascript">
			var receivedWP = '${waterplant}';
			var receivedOG = '${opengarage}';
			var receivedCG = '${closegarage}';

			if (receivedWP == 65) {
				showalert("Success: Thanks for watering your plants.",
						"alert-success", "plant");
			} else if (receivedWP == 0) {
				showalert("Failure: Problem occured while watering plants.",
						"alert-success", "plant");
			} else if (receivedWP == 90) {
				showalert(
						"Info: Click the <Strong>Water</Strong> button to water your palnts.",
						"alert-info", "plant");
			}

			if ((receivedOG == 66) && (receivedCG == 90)) {
				showalert("Success: Opening your garage door.",
						"alert-success", "garage");
			} else if ((receivedOG == 90) && (receivedCG == 67)) {
				showalert("Success: Closing your garage door.",
						"alert-success", "garage");
			} else if ((receivedOG == 0) && (receivedCG == 90)) {
				showalert("Failure: Problem opening your garage door.",
						"alert-success", "garage");
			} else if ((receivedOG == 90) && (receivedCG == 0)) {
				showalert("Failure: Problem closing your garage door.",
						"alert-success", "garage");
			} else if ((receivedOG == 90) && (receivedCG == 90)) {
				showalert(
						"Info: Click the <Strong>Open</Strong> or <Strong>Close</Strong> buttons to control your garage door.",
						"alert-info", "garage");
			}

			function showalert(message, alerttype, idextension) {
				$('#alert_placeholder_' + idextension)
						.append(
								'<div id="alertdiv" class="alert ' +  alerttype + ' fade in"><a class="close" data-dismiss="alert">×</a><span>'
										+ message + '</span></div>')
				setTimeout(function() { // this will automatically close the alert and remove this if the users doesnt close it in 5 secs
					$("#alertdiv").remove();
				}, 10000);
			}
		</script>

  <!-- Carousel JS -->
  <script>
			$('.carousel').carousel({
				interval : 3000
			})
		</script>

</body>
</html>