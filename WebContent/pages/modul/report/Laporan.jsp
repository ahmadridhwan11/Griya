<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.kimia.farma.model.Reporting"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="col-lg-12">
			<div class="panel panel-primary" >
	<div class="panel-body"		style="table-layout: fixed;">
		<form namespace="/trans" action="reporting.action" method="post"
			enctype="multipart/form-data">
			<div class="form-group">
				<label class="col-sm-2 control-label">Data Laporan :</label>
				<div class="row">
					<div class="col-sm-3">
						<s:select list="#{'TR':'Transaksi','BM':'Barang Masuk','MT':'Master-Transaksi','MD':'Gaji Dokter'}"
							name="jenisLaporan" id="jenisLaporan" cssClass="form-control"
							onchange="query()" labelSeparator=":" />
					</div>
					
				</div>
			</div>
			<div class="form-group" id="dokter">
						<div class="form-group ">
			                <label class="col-sm-2 control-label">Nama Dokter</label>
			                <div class="row">
				                <div class="col-sm-3">
				                
				                    <input list="list_j" onchange="setKdJasa()" class="form-control reset" placeholder="Nama dokter"  id="jasa" autocomplete="off"/>
				                        <datalist id="list_j">
				                            <s:iterator value="listJ" id="list_j" status="test">
				                                <option  value="<s:property value='%{#list_j.dokter.nama}' /> : <s:property value='%{#list_j.dokter.kd_dokter}' />">
				                                </option>
				                        	</s:iterator>
				                        </datalist>
				                </div>
				               
				           </div>
            			</div>
					</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Dari Tanggal :</label>
				<div class="row">
					<div class="col-sm-4">
						<div class="input-group datetimepicker">
							<div class="input-group datetimepicker">
								<s:textfield name="tgl_awal" id="tgl2"
									cssClass="form-control input-sm datetimepicker"
									placeholder="dari tanggal" />
								<div class="input-group-addon input-sm">
									<i class="glyphicon glyphicon-calendar"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">sampai Tanggal :</label>
				<div class="row">
					<div class="col-sm-4">
						<div class="input-group datetimepicker">
							<div class="input-group datetimepicker">
								<s:textfield name="tgl_akhir" id="tgl"
									cssClass="form-control input-sm datetimepicker"
									placeholder="sampai tanggal" />
								<div class="input-group-addon input-sm">
									<i class="glyphicon glyphicon-calendar"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group ">
				<div class="col-sm-2">
					<s:hidden name="page" value="lap"></s:hidden>
					<s:hidden  id="kd_dokk" name="kd_dokter1" value=""></s:hidden>
					<s:hidden name="reporting.table" value="TR" id="table"></s:hidden>
					<s:submit cssClass="btn btn-success" value="CETAK" />
				</div>
			</div>

		</form>
	</div>
	</div>
	</div>
	<script type="text/javascript">
		function query() {
			switch (document.getElementById("jenisLaporan").value) {
			case "BM":
				document.getElementById("table").value = "BM";
				document.getElementById("dokter").style.display="none";
				break;
			case "MT":
				document.getElementById("table").value = "MT";
				document.getElementById("dokter").style.display="none";
				break;
			case "TR":
				document.getElementById("table").value = "TR";
				document.getElementById("dokter").style.display="none";
				break;
			case "MD":
				document.getElementById("table").value = "MD";
	           document.getElementById("dokter").style.display="block";

				break;
			}
		}
		
		function setKdJasa() {
			var id = document.getElementById("jasa").value;
		       var data = id.split(':');
		       if(data.length>0){
			       document.getElementById("jasa").value="0";
			       document.getElementById("jasa").value=data[0];
			       document.getElementById("kd_dokk").value=data[1];
	
		       }
		}

		$(function() {
			document.getElementById("dokter").style.display="none";
			$("#tgl").datepicker({
			/* yearRange : "1980:2000",
			changeMonth : true,
			changeYear : true */
			});
			$("#tgl2").datepicker({
			/* 	yearRange : "1980:2000",
				changeMonth : true,
				changeYear : true */
			});
		});
	</script>
</body>
</html>