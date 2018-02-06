<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.kimia.farma.model.User"%>
<link type="text/css"
	href="<s:url value="/content/assets/css/bootstrap.min.css"/>"
	rel="stylesheet" />
<link type="text/css"
	href="<s:url value="/content/assets/css/bootstrap.css"/>"
	rel="stylesheet" />
<%--         <link type="text/css" href="<s:url value="/content/assets/css/styles.css"/>" rel="stylesheet"/>    	
 --%>
<%--         <link type="text/css" href="<s:url value="/content/assets/font-awesome/css/font-awesome.min.css"/>" >
 --%>
<link type="text/css" rel="stylesheet"
	href="<s:url value="/content/assets/style.css"/>">
<link type="text/css" rel="stylesheet"
	href="<s:url value="/content/assets/form-elements.css"/>">
<link rel="shortcut icon"
	href="<s:url value="/content/assets/ico/favicon.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<s:url value="/content/assets/ico/apple-touch-icon-144-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<s:url value="/content/assets/ico/apple-touch-icon-114-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<s:url value="/content/assets/ico/apple-touch-icon-72-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed"
	href="<s:url value="/content/assets/ico/apple-touch-icon-57-precomposed.png"/>">

<script type="text/javascript"
	src="<s:url value="/content/assets/includes/js_admin/jquery.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/content/assets/js/bootstrap.min.js"/>"></script>
<!-- Javascript -->
<script src="<s:url value="/content/assets/js/jquery-1.11.1.min.js"/>"></script>
<script src="<s:url value="/content/assets/js/scripts.js"/>"></script>

<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

<title>GriyaSehat Natura Depok</title>
</head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<body>
	<style type="text/css">
body {
	background: url('${pageContext.request.contextPath}/content/BG/3.jpg')
		no-repeat fixed center center;
	background-size: cover;
}
</style>
	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>Klinik Griya Sehat Natura - Depok</h1>
						<div class="description">
							<p>Welcome Back. Please Login First</p>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3 form-box">
							<div class="form-top">
								<div class="form-top-left">
									<h3>Login Disini</h3>
									<p>Masukkan Username Dan Password Anda:</p>
								</div>
								<div class="form-top-right">
									<i class="fa fa-lock"></i>
								</div>
							</div>
							<div style="float: left; width: 100%; background: transparent;">
								<b style="color: orange;"><h4>
										<s:actionmessage /></b>
							</div>
							<div class="form-bottom">
								<form namespace="/user" action="login.action" method="post"
									class="login-form">
									<div class="form-group">
										<label class="sr-only" for="form-username">Login-Sebagai</label>
										<s:select
											list="#{'3':'dokter','2':'admin Masuk','1':'pasien','4':'passien baru'}"
											name="user.level" id="jenisLevel" cssClass="form-control"
											onchange="query()" labelSeparator=":" />
									</div>
									<div class="form-group">
										<label class="sr-only" for="form-username">Kd-User</label>
										<s:textfield name="user.kd_user" placeholder="User-Name..."
											cssClass="form-username form-control" id="form-username"
											required="true" />
									</div>

									<div class="form-group">
										<label class="sr-only" for="form-password">Password</label>
										<s:password name="user.password" placeholder="Password..."
											cssClass="form-password form-control" id="form-password"
											required="true" />
											<s:hidden name="user.level" value="1"
											cssClass="form-password form-control" id="level"
											/>
									</div>
									<div class="form-group">
										<button type="submit" class="btn" for=form-1>Login</button>
									</div>
									
								</form>
							</div>
						</div>
					</div>

				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
	
		function query() {
			switch (document.getElementById("level").value) {
			case 4:
				username
				document.getElementById("form-username").value ="pasien-baru";
				document.getElementById("form-password").value ="pasien-baru";
				document.getElementById("table").value = 4;
				
				break;
			
			case 3:
				document.getElementById("table").value = 3;
				
				break;
			case 2:
				document.getElementById("table").value = 2;
				break;
			case 1:
				document.getElementById("table").value =1;
				break;
			}
		}
	</script>
</body>
</html>