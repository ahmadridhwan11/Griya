<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.kimia.farma.model.Jasa"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GriyaSehat Natura Depok</title>

</head>
<div id="view">
	<ol class="breadcrumb bread"  style="color: orange; font-family: monospace;">
		<li><i class="glyphicon glyphicon-cog"></i> Master</li>
		<li class="active"><i class="glyphicon glyphicon-pencil-square-o"></i>
			Input Data Jasa</li>
	</ol>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-primary" >
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="glyphicon glyphicon-list"></i>Form Input Data Jasa
					</h3>
				</div>
				<div style="float:left; width: 100%;">
					<b style="color: orange;"><h4><s:actionmessage /></b>
				</div>
				<form  namespace="/dok_jas" method="post"
					enctype="multipart/form-data" class="form-horizontal"
					style="font-size:medium;font-family: monospace; text-decoration: blink; color: black;">
					<div class="panel-body">

						<div class="form-group">
							<label class="col-sm-2 control-label">Kd.Jasa :</label>
							<div class="row">
								<div class="col-sm-3">
									<s:textfield name="jasa.kd_jasa" id="kd_jasa"
										cssClass="form-control" placeholder="kode jasa" readonly="true"></s:textfield>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Tindakan :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textarea name="jasa.nama" id="tindakan"
										cssClass="form-control" placeholder="tindakan" maxLength="100"/>
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Harga</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="jasa.harga" id="harga" cssClass="form-control" onInput="onlyNumber('harga')" maxLength="8"
										placeholder="Rp.0" />
								</div>
							</div>
						</div>
						
						<div class="form-group">
						
							<div class="col-sm-2"></div>
							<div class=" col-sm-2"></div>
							<div class="right col-sm-1"><s:submit action="inputJasa" cssClass="btn btn-success" value="Cancel"></s:submit></div>
							<div class="right col-sm-1">
								<s:submit action="update" cssClass="btn btn-success" value="Update"></s:submit>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
	function setKdDOkter() {
		 var id = document.getElementById("dokter").value;
	       var data = id.split(':');
	       document.getElementById("dokter").value="";
	       document.getElementById("dokter").value=data[0];
	       document.getElementById("kd_dokter").value=data[1].trim();
	}
	function onlyNumber(id) {
		var number = document.getElementById(id).value;
		if(number.length>1){
			document.getElementById(id).value = number.replace(/[^0-9]+/g,'');
		}else{
		 document.getElementById(id).value = number.replace(/[^1-9]+/g,'');
	 }
	}
	 $(document).ready(function(){
		 $('#tbl_js').DataTable({
				"order" : [ [ 3, "desc" ] ]
			});
		 document.getElementById("dokter").value= document.getElementById("nama_dokter").value;
		 
   	  $(".del").click(function(){
   	    if (!confirm("Apakah anda yakin ingin hapus file tersebut")){
   	      return false;
   	    }
   	  });
   	  $("#listTableObat  > thead tr th").css({"text-align": "center", "vertical-align": "middle"});
   	  $("#listTableObat  > tbody tr td").css({"text-align": "center", "vertical-align": "middle"});
   	});
	</script>

	
	<%-- <div class="panel-body" style="background: transparent; background-color: transparent; ">

				<display:table id="listJ" name="listJasa" pagesize="10"
					export="true"  requestURI="/jas/pagging.action" sort="external"
					class="table table-hover" uid="row"
					style="background: transparent; background-color: transparent; font-family: monospace; font-size: medium; color:#a8a7a7">
					<display:column title="Kd.Jasa " property="kd_jasa" />
					<display:column title="Tindakan" property="nama" />
					<display:column title="harga " property="harga" />
					<display:column title="dokter " property="dokter.nama" />
					
					<display:column title="Action" media="html">
						<s:url id="update" namespace="/jas" action="initUpdate.action">
							<s:param name="jasa.kd_jasa" value="%{#attr.row.kd_jasa}"/>
						</s:url>
						<s:a href="%{update}">Update</s:a>
						
						<s:url id="delete" namespace="/jas" action="delete">
							<s:param name="jasa.kd_jasa" value="%{#attr.row.kd_jasa}" />
						</s:url>||
						<s:a href="%{delete}">Delete</s:a>
					</display:column>
					<display:setProperty name="export.pdf.filename"
						value="ReportJasa.pdf" />
					<display:setProperty name="export.excel.filename"
						value="ReportJasa.xlsx" />
				</display:table>
			</div> --%>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-body table-responsive fortable">
				<table id="tbl_js" class="display" cellspacing="0" width="100%">
					<thead>
						<tr>
							<td>Kd.Jasa</td>
							<td>Tindakan</td>
							<td>Harga</td>
							<td>Action</td>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listJasa" id="lb" status="test">

							<tr>
								<td><s:property value="%{#lb.kd_jasa}" /></td>
								<td><s:property value="%{#lb.harga}" /></td>
								<td><s:property value="%{#lb.dokter.nama}" /></td>
								<td><s:url id="view" 
										namespace="/dok_jas"  action="initUpdate.action">
										<s:param name="jasa.kd_jasa" value="%{#lb.kd_jasa}" />
									</s:url> <s:a href="%{view}" cssClass="btn btn-sm btn-warning">update</s:a>
									<s:url id="view" 
										namespace="/dok_jas" action="delete">
										<s:param name="jasa.kd_jasa" value="%{#lb.kd_jasa}" />
									</s:url> <s:a href="%{view}" cssClass="btn btn-sm btn-primary">delete</s:a>

								</td>
							</tr>
						</s:iterator>

					</tbody>
				</table>
			</div>
		</div>
	</div>

</div>
<!-- /.row -->
</html>
