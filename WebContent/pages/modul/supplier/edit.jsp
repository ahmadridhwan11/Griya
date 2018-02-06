<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.kimia.farma.model.Supplier"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GriyaSehat Natura Depok</title>
</head>
<div id="view"  style=" background-color: transparent;">
	<ol class="breadcrumb bread"  style=" color: orange; font-family: monospace;">
		<li><i class="glyphicon glyphicon-cog"></i> Master</li>
		<li class="active"><i class="glyphicon glyphicon-pencil-square-o"></i>
			Edit Data Supplier</li>
	</ol>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-primary" >
				<div class="panel-heading" >
					<h3 class="panel-title">
						<i class="glyphicon glyphicon-list"></i>Form Edit Data Supplier
					</h3>
				</div>
				<form  namespace="/supplier" method="post"
					enctype="multipart/form-data" class="form-horizontal">
					<div class="panel-body"
					style="font-size:medium;  text-decoration: blink; color: black;">
						<div class="form-group">
							<label class="col-sm-2 control-label">KD.Supplier:</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="supplier.kd_supplier" id="nama"
										cssClass="form-control"  disabled="true"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Nama :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="supplier.nama" id="nama"
										cssClass="form-control" required="true" placeholder="Nama supplier" />
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">Alamat :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textarea name="supplier.alamat" id="alamat"
										cssClass="form-control" placeholder="Alamat"  required="true"></s:textarea>
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Kontak :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="supplier.nomer_tlp" id="tlp" cssClass="form-control" onInput="onlyNumber('tlp')"
									maxlength="13"	placeholder="0821xxxx"  required="true"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
						
							<div class="col-sm-2"></div>
							<div class=" col-sm-2"></div>
							
							<div class="right col-sm-1"><s:submit action="inputSupplier" cssClass="btn btn-success" value="Cancel"></s:submit></div>
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
	function onlyNumber(id) {
		var number = document.getElementById(id).value;
		if(number.length>1){
			document.getElementById(id).value = number.replace(/[^0-9]+/g,'');
		}else{
		 document.getElementById(id).value = number.replace(/[^1-9]+/g,'');
	 }
	}

	 $(document).ready(function(){		
		$('#tbl_sp').DataTable({
				"order" : [ [ 4, "desc" ] ]
			});
   	  $("#del").click(function(){
   	    if (!confirm("Apakah anda yakin ingin hapus file tersebut")){
   	      return false;
   	    }
   	  }); 
	 });
			
	</script>

	
	
	<%-- <div class="panel-body" style="background: transparent; background-color: transparent; ">
				<display:table id="listSup1" name="listSup" pagesize="10"
					export="true" requestURI="/supplier/pagging.action" sort="external"
					class="table  table-hover" uid="row"
					style="background: transparent; background-color: transparent; font-family: monospace; font-size: medium; color:#a8a7a7">
					<display:column title="KD.Supplier " property="kd_supplier"/>
					<display:column title="Nama" property="nama" />
					<display:column title="Alamat" property="alamat" />
					<display:column title="Kontak" property="nomer_tlp" />
					<display:column title="Action" media="html">
						<s:url id="update" namespace="/supplier" action="initUpdate.action">
							<s:param name="supplier.kd_supplier" value="%{#attr.row.kd_supplier}"/>
						</s:url>
						<s:a href="%{update}"  >Update</s:a>
						
						<s:url id="delete" namespace="/supplier" action="delete">
							<s:param name="supplier.kd_supplier" value="%{#attr.row.kd_supplier}" />
						</s:url>||
						<s:a href="%{delete}">Delete</s:a>
					</display:column>
					<display:setProperty name="export.pdf.filename"
						value="ReportSUp" />
					<display:setProperty name="export.excel.filename"
						value="ReportSUp" />
				</display:table>
			</div> --%>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-body table-responsive fortable">
             <table id="tbl_sp" class="display" cellspacing="0" width="100%" >
				<thead>
					<tr>
						<td>Kd.Supplier</td>
						<td>Nama</td>
						<td>Alamat</td>
						<td>Kontak</td>
						<td>Action</td>
					</tr>
				</thead>
				<tbody>
 					<s:iterator value="listSup" id="lb" status="test">

					<tr>
						<td><s:property value="%{#lb.kd_supplier}" /></td>
						<td><s:property value="%{#lb.nama}" /></td>
						<td><s:property value="%{#lb.alamat}" /></td>
						<td><s:property value="%{#lb.nomer_tlp}" /></td>
						<td>
						<s:url id="update" namespace="/supplier" action="initUpdate.action">
						<s:param name="supplier.kd_supplier" value="%{#lb.kd_supplier}" />
					</s:url>
					<s:a href="%{update}" cssClass="btn btn-sm btn-warning">Update</s:a>

					<s:url id="delete" namespace="/supplier" action="delete">
						<s:param name="supplier.kd_supplier" value="%%{#lb.kd_supplier}" />
					</s:url>||
						<s:a href="%{delete}" cssClass="btn btn-sm btn-primary">Delete</s:a>
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