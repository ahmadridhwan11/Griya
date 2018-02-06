<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.kimia.farma.model.Passien"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GriyaSehat Natura Depok</title>
<link type="text/css"
	href="<s:url value="/content/assets/css/bootstrap.min.css"/>"
	rel="stylesheet" />
	
	<link type="text/css"
	href="<s:url value="/content/assets/css/bootstrap.css"/>"
	rel="stylesheet" />
<%-- <link type="text/css" href="<s:url value="/content/assets/css/bootstraptable.css"/>" rel="stylesheet"/> --%>
<link type="text/css"
	href="<s:url value="/content/assets/css/styles.css"/>" rel="stylesheet" />
<link type="text/css"
	href="<s:url value="/content/assets/css/datepicker.css"/>"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="<s:url value="/content/DataTables-1.10.16/css/jquery.dataTables.css"/>" />


<!--Icons-->
<script type="text/javascript"
	src="<s:url value="/content/assets/js/jquery-2.1.4.js"/>"></script>
	<script type="text/javascript"
	src="<s:url value="/content/assets/js/jquery-2.1.4.min.js"/>"></script>
	<script type="text/javascript"
	src="<s:url value="/content/assets/js/moment.js"/>"></script>
	<script type="text/javascript"
	src="<s:url value="/content/assets/js/bootstrap.min.js"/>"> </script>
 <script type="text/javascript"
	src="<s:url value="/content/assets/js/lumino.glyphs.js"/>"></script>
 
<script type="text/javascript"
	src="<s:url value="/content/assets/js/bootstrap-datepicker.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/content/assets/js/bootstrap-datetimepicker.min.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/content/assets/js/bootstrap-datetimepicker.js"/>"></script>
	<script type="text/javascript"
	src="<s:url value="/content/DataTables-1.10.16/js/jquery.dataTables.js"/>"></script>

</head>
<body>
<div style="float:left; width: 100%;">
					<b style="color: orange;"><h4><s:actionmessage /></b>
</div>
	<form namespace="/" method="post" action="logout.action" enctype="multipart/form-data"
		class="form-horizontal"
		style="font-size: medium; text-decoration: blink; color: black;">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-2 control-label">ID:</label>
				<div class="row">
					<div class="col-sm-4">
						<s:textfield name="passien.kd_passien" id="kd"
							cssClass="form-control" readonly="true" />

					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Password :</label>
				<div class="row">
					<div class="col-sm-4">
						<s:textfield name="password" id="password" cssClass="form-control"
							placeholder="password" readonly="true" />
					</div>
				</div>
			</div>
				<div class="form-group">
							<div class="col-sm-2"></div>
							<div class=" col-sm-2"></div>
							<div class="right col-sm-1"></div>
							<div class="right col-sm-1">
								<button type="submit" class="btn btn-success">OK</button>
							</div>
						</div>
			
	</form>
</body>
</html>