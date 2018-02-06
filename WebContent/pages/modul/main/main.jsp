<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.kimia.farma.model.User"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GriyaSehat Natura Depok</title>
<head>

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
 <%-- <script type="text/javascript"
	src="<s:url value="/content/assets/js/lumino.glyphs.js"/>"></script> --%>
 
<script type="text/javascript"
	src="<s:url value="/content/assets/js/bootstrap-datepicker.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/content/assets/js/bootstrap-datetimepicker.min.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/content/assets/js/bootstrap-datetimepicker.js"/>"></script>
	<script type="text/javascript"
	src="<s:url value="/content/DataTables-1.10.16/js/jquery.dataTables.js"/>"></script>


<%-- <script type="text/javascript"  src="<s:url value="/content/assets/js/bootstraptable.js"/>"></script>  --%>
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<!-- <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button> -->
			<a class="navbar-brand" href="#"><span>Klinik Griya Sehat
					Natura</span> - Depok</a>
			<ul class="user-menu">
				<li class="dropdown pull-right"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"><svg
							class="glyph stroked male-user">
						<use xlink:href="#stroked-male-user"></use></svg> Hello,<%if(session.getAttribute("userName")!=null){User u =(User)session.getAttribute("userName"); out.print(u.getNama());} %>
						<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><svg class="glyph stroked male-user">
							<use xlink:href="#stroked-male-user"></use></svg> Profile</li>

						<li><svg class="glyph stroked gear">
							<use xlink:href="#stroked-gear"></use></svg> Settings</li>
						<s:url id="log1234" namespace="/" action="logout.action">
						</s:url>
						<li><s:a href="%{log1234}">
								<svg class="glyph stroked cancel">
								<use xlink:href="#stroked-cancel"></use></svg> Logout</s:a></li>
					</ul></li>
			</ul>
		</div>

	</div>
	<!-- /.container-fluid --> </nav>

	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar"
		style="border-style: inset;">
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>
		<ul class="nav menu"
			style="color: black; font-family: cursive; font-stretch: narrower;">
			<li class="active"><s:url id="home" namespace="/user"
					action="inputHome.action">
				</s:url> <s:a href="%{home}">
					<svg class="glyph stroked dashboard-dial">
					<use xlink:href="#stroked-dashboard-dial"></use></svg>
					<U>HOME</U>
				</s:a></li>
			<li class="parent ">
			<li><s:url id="obat11" namespace="/obat"
					action="inputObat.action">
				</s:url> <s:a href="%{obat11}">
					<svg class="glyph stroked chevron-right">
					<use xlink:href="#stroked-chevron-right"></use></svg>
					<u>Data Obat</u>
				</s:a></li>
			<li><s:url id="dokter11" namespace="/dokter"
					action="inputDokter.action">
				</s:url> <s:a href="%{dokter11}">
					<svg class="glyph stroked chevron-right">
					<use xlink:href="#stroked-chevron-right"></use></svg>
					<u>Data Dokter</u>
				</s:a></li>
			<li><s:url id="barang1" namespace="/bmasuk"
					action="inputBMasuk.action">
				</s:url> <s:a href="%{barang1}">
					<svg class="glyph stroked chevron-right">
					<use xlink:href="#stroked-chevron-right"></use></svg>
					<u>Data Barang Masuk</u>
				</s:a></li>
			<li><s:url id="barangs" namespace="/supplier"
					action="inputSupplier.action">
				</s:url> <s:a href="%{barangs}">
					<svg class="glyph stroked chevron-right">
					<use xlink:href="#stroked-chevron-right"></use></svg>
					<u>Data Supplier</u>
				</s:a></li>
			<li><s:url id="pasien1" namespace="/pasien"
					action="inputPasien.action">
				</s:url> <s:a href="%{pasien1}">
					<svg class="glyph stroked-male-user">
					<use xlink:href="#stroked-male-user"></use></svg>
					<u>Data Passien</u>
				</s:a></li>
			<li><s:url id="tra" namespace="/trans"
					action="inputTrans.action">
				</s:url> <s:a href="%{tra}">
					<svg class="glyph stroked chevron-right">
					<use xlink:href="#stroked-chevron-right"></use></svg>
					<u>Transaksi</u>
				</s:a></li>
			<li><s:url id="ja" namespace="/jas" action="inputJasa.action">
				</s:url> <s:a href="%{ja}">
					<svg class="glyph stroked chevron-right">
					<use xlink:href="#stroked-chevron-right"></use></svg>
					<u>Jasa</u>
				</s:a></li>
			<li><s:url id="hs" namespace="/trans"
					action="inputTrans.action?page=his">
				</s:url> <s:a href="%{hs}">
					<svg class="glyph stroked chevron-right">
					<use xlink:href="#stroked-chevron-right"></use></svg>
					<u>History Transaction</u>
				</s:a></li>
				<li><s:url id="pengeluaran" namespace="/pengeluaran"
					action="inputPengeluaran.action?page=peng">
				</s:url> <s:a href="%{pengeluaran}">
					<svg class="glyph stroked chevron-right">
					<use xlink:href="#stroked-chevron-right"></use></svg>
					<u>Pengeluaran</u>
				</s:a></li>
			<li>
			<s:url id="lap" namespace="/trans"
					action="inputTrans.action?page=lap">
				</s:url> <s:a href="%{lap}">
					<svg class="glyph stroked chevron-right">
					<use xlink:href="#stroked-chevron-right"></use></svg>
					<u>Laporan</u>
				</s:a>
				</li>
				
				<li>
			<s:url id="api" namespace="/trans"
					action="inputTrans.action?page=localhost:8070/mhsall">
				</s:url> <s:a href="%{api}">
					<svg class="glyph stroked chevron-right">
					<use xlink:href="#stroked-chevron-right"></use></svg>
					<u>Laporan</u>
				</s:a>
				</li>
		</ul>

	</div>
	<!--/.sidebar-->

	<div class="col-sm-12 col-sm-offset-2 col-lg-10 col-lg-offset-2 main">


		<div class="containernya cont">
			<s:hidden name="page" id="page">


			</s:hidden>
			<div class="clear" id="view"></div>
		</div>
	</div>
	<!--/.main-->


	<script type="text/javascript">
    
    
    		
    		$(document).ready(function(){
    			var data = document.getElementById("page").value;
    			if(data!=null && data!=""){
    				$("#view").load(data);
        		}
    		 });
    		
    </script>
</body>
</html>