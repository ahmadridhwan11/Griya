<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.kimia.farma.model.Passien"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GriyaSehat Natura Depok</title>
</head>
<div id="view">
	<ol class="breadcrumb bread" style="color: orange; font-family: monospace;">
		<li><i class="glyphicon glyphicon-cog"></i> Master</li>
		<li class="active"><i class="glyphicon glyphicon-pencil-square-o"></i>
			Edit Data Passien</li>
	</ol>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-primary" >
				<div class="panel-heading" >
					<h3 class="panel-title">
						<i class="glyphicon glyphicon-list"></i>Form Edit Data Passien
					</h3>
				</div>
				<form namespace="/pasien" method="post"
					enctype="multipart/form-data" class="form-horizontal"
					style="font-size:medium;text-decoration: blink; color: black;">
					<div class="panel-body">
					<div class="form-group">
							<label class="col-sm-2 control-label">KD Passien :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="passien.kd_passien" id="kd"
										cssClass="form-control" disabled="true"/>
									
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Nama :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="passien.nama" id="nama"
										cssClass="form-control" placeholder="Nama Dokter" />
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Kelamin :</label>
							<div class="row">
								<div class="col-sm-2">
									<s:radio name="passien.kelamin"  list="#{'Laki-laki':'Laki-laki','Wanita ':'Wanita'}"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Alamat :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textarea name="passien.alamat" id="alamat"
										cssClass="form-control" placeholder="Alamat" ></s:textarea>
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Kontak :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="passien.nomer_tlp" id="tlp" cssClass="form-control" onInput="onlyNumber('tlp')" maxlength="13"
										placeholder="0821xxxx" />
								</div>
							</div>
						</div>
						<div class="form-group ">
			                <label class="col-sm-2 control-label">Tanggal.Lahir</label>
			                <div class="row">
			                    <div class="col-sm-3">
			                        <div class="input-group datetimepicker">
			                            <div class="input-group datetimepicker">
			                            <s:textfield name="passien.tgl_lahir" id="tgl" cssClass="form-control input-sm datetimepicker" />
			                            <div class="input-group-addon input-sm"><i class="glyphicon glyphicon-calendar"></i></div>
			                        </div>
			                        </div>
			                    </div>
			                </div>                            
			            </div>              
						<div class="form-group ">
							<label class="col-sm-2 control-label">Status Perkawinan :</label>
							<div class="row">
								<div class="col-sm-3">
									<s:select list="#{'Lajang':'Lajang','Bercerai':'Bercerai','Menikah':'Menikah'}" name="passien.status_perkawinan" cssClass="form-control" labelSeparator=":"/>
								</div>
							</div>
						</div>
						<div class="form-group">
						
							<div class="col-sm-2"></div>
							<div class=" col-sm-2"></div>
							
							<div class="right col-sm-1"><s:submit action="inputPasien" cssClass="btn btn-success" value="Cancel"></s:submit></div>
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
	$(document).ready(function(){
		 $('#tbl_sp').DataTable({
				"order" : [ [ 6, "desc" ] ]
			});
	});
	function onlyNumber(id) {
		var number = document.getElementById(id).value;
			document.getElementById(id).value = number.replace(/[^0-9]+/g,'');
	}
   	  function del(str) {
   	    if (!confirm("Apakah anda yakin ingin hapus Data "+str)){
   	      return false;
   	    }
   	  } 
	 $(function() {
			$("#tgl").datepicker({
				/* yearRange : "1980:2000",
				changeMonth : true,
				changeYear : true */
			});
		});

			
	</script>
<<%-- div class="panel-body" style="background: transparent; background-color: transparent; ">
				<display:table id="listPasienT" name="listPas" pagesize="10"
					export="true"  requestURI="/pasien/pagging.action" sort="external"
					class="table table-hover " uid="row"
					style="background: transparent; background-color: transparent; font-family: monospace; font-size: medium; color:#a8a7a7">
					<display:column title="KD.Passien " property="kd_passien"/>
					<display:column title="Nama" property="nama" />
					<display:column title="J.Kelamin" property="kelamin" />
					<display:column title="Alamat" property="alamat" />
					<display:column title="Komtak" property="nomer_tlp" />
					<display:column title="Tgl.Lahir " property="tgl_lahir" format="{0,date,MM/dd/yyyy}"/>
					<display:column title="Status" property="status_perkawinan"/>
					<display:column title="Action">
						<s:url id="update" namespace="/pasien" action="initUpdate.action">
							<s:param name="passien.kd_passien" value="%{#attr.row.kd_passien}"/>
						</s:url>
						<s:a href="%{update}"  >Update</s:a>
						
						<s:url id="delete" namespace="/pasien" action="delete">
							<s:param name="passien.kd_passien" value="%{#attr.row.kd_passien}" />
						</s:url>||
						<s:a href="%{delete}"  onclick="del(row.kd_passien)">Delete</s:a>
					</display:column>
					<display:setProperty name="export.pdf.filename"
						value="ReportObat.pdf" />
					<display:setProperty name="export.excel.filename"
						value="ReportObat.xlsx" />
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
						<td>Kd.Passien</td>
						<td>Nama</td>
						<td>Kelamin</td>
						<td>Alamat</td>
						<td>Kontak</td>
						<td>Tgl.Lahir</td>
						<td>Status</td>
						<td>Action</td>
					</tr>
				</thead>
				<tbody>
 					<s:iterator value="listPas" id="lb" status="test">

					<tr>
						<td><s:property value="%{#lb.kd_passien}" /></td>
						<td><s:property value="%{#lb.nama}" /></td>
						<td><s:property value="%{#lb.kelamin}" /></td>
						<td><s:property value="%{#lb.alamat}" /></td>
						<td><s:property value="%{#lb.nomer_tlp}" /></td>
						<td><s:property value="%{#lb.tgl_lahir}" /></td>
						<td><s:property value="%{#lb.status_perkawinan}" /></td>
						<td>
						<s:url id="update" namespace="/pasien" action="initUpdate.action">
						<s:param name="passien.kd_passien" value="%{#lb.kd_passien}" />
					</s:url>
					<s:a href="%{update}" cssClass="btn btn-sm btn-warning">Update</s:a>

					<s:url id="delete" namespace="/pasien" action="delete">
						<s:param name="passien.kd_passien" value="%%{#lb.kd_passien}" />
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
