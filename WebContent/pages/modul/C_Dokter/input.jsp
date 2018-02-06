<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.kimia.farma.model.Dokter"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GriyaSehat Natura Depok</title>
</head>
<div id="view"  style=" background-color: transparent;">
	<ol class="breadcrumb bread" style=" color: orange; font-family: monospace;">
		<li><i class="glyphicon glyphicon-cog"></i> Master</li>
		<li class="active"><i class="glyphicon glyphicon-pencil-square-o"></i>
			Input Data Dokter</li>
	</ol>
			<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-primary"  >
				<div class="panel-heading" >
					<h3 class="panel-title">
						<i class="glyphicon glyphicon-list"></i>Form Update Data Dokter
					</h3>
				</div>
				<form  namespace="/drg" method="post"
					enctype="multipart/form-data" class="form-horizontal"
					style="font-size:medium;   text-decoration: blink; color: black;">
					<div class="panel-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">KD.Dokter:</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="dokter.kd_dokter" id="nama"
										cssClass="form-control" placeholder="Nama Dokter"  disabled="true"/>
								</div>
							</div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label">Password :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="pass" id="password"
										cssClass="form-control" required="true" placeholder="password" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Nama :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="dokter.nama" id="nama"
										cssClass="form-control" required="true" placeholder="Nama Dokter" />
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Spesialis :</label>
							<div class="row">
								<div class="col-sm-2">
									<s:select list="#{'MA':'Mata','KL':'Kulit','GG':'Gigi','UM':'Umum','PD':'Penyakit Dalam','NA':'N/A'}" name="dokter.spesialis" cssClass="form-control"  labelSeparator=":"/>
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Kelamin :</label>
							<div class="row">
								<div class="col-sm-2">
									<s:radio name="dokter.kelamin"  list="#{'Laki-laki':'Laki-laki','Wanita ':'Wanita'}"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Alamat :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textarea name="dokter.alamat" id="alamat"
										cssClass="form-control" placeholder="Alamat"  required="true"></s:textarea>
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Kontak :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="dokter.nomer_tlp" id="tlp" cssClass="form-control"
									maxLength="13"	placeholder="0821xxxx"  required="true" onInput="onlyNumber('tlp')"/>
								</div>
							</div>
						</div>
						<div class="form-group ">
			                <label class="col-sm-2 control-label">Tanggal.Lahir</label>
			                <div class="row">
			                    <div class="col-sm-3">
			                        <div class="input-group datetimepicker">
			                            <div class="input-group datetimepicker">
			                            <s:textfield name="dokter.tgl_lahir" id="tgl" cssClass="form-control input-sm datetimepicker" />
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
									<s:select list="#{'Lajang':'Lajang','Bercerai':'Bercerai','Menikah':'Menikah'}" name="dokter.status_perkawinan" cssClass="form-control" labelSeparator=":"/>
								</div>
							</div>
						</div>
						<div class="form-group">
						
							<div class="col-sm-2"></div>
							<div class=" col-sm-2"></div>
							
							<div class="right col-sm-1"><s:submit action="inputDokter" cssClass="btn btn-warning" value="Cancel"></s:submit></div>
							<div class="right col-sm-1">
								<s:submit action="update" cssClass="btn btn-success" value="Update"></s:submit>
							</div>
						</div>
					</div>
				</form>
				</div>
				</div>
	<script>


	 $(document).ready(function(){	
			 $('#tbl_dok').DataTable({
					"order" : [ [ 8, "desc" ] ]
				});
   	  $(".del").click(function(){
   	    if (!confirm("Apakah anda yakin ingin hapus file tersebut")){
   	      return false;
   	    }
   	  }); 
	 });
	 $(function() {
			$("#tgl").datepicker({
				/* yearRange : "1980:2000",
				changeMonth : true,
				changeYear : true */
			});
		});
		
	 function onlyNumber(id) {
			var number = document.getElementById(id).value;
			if(number.length>1){
				document.getElementById(id).value = number.replace(/[^0-9]+/g,'');
			}else{
			 document.getElementById(id).value = number.replace(/[^1-9]+/g,'');
		 }
		}
			
	</script>
				<%-- <div class="panel-body" style="background: transparent; background-color: transparent; ">
	
				<display:table id="listDokterT" name="listDokter" pagesize="10"
					export="true" requestURI="/dokter/pagging.action" sort="external"
					class="table  table-hover" uid="row"
					 style="background: transparent; background-color: transparent; font-family: monospace; font-size: medium; color:#a8a7a7">
					<display:column title="KD.Dokter " property="kd_dokter"/>
					<display:column title="Nama" property="nama" />
					<display:column title="Spesialis " property="spesialis" />
					<display:column title="J.Kelamin" property="kelamin" />
					<display:column title="Alamat" property="alamat" />
					<display:column title="Kontak" property="nomer_tlp" />
					<display:column title="Tgl.Lahir " property="tgl_lahir" format="{0,date,MMMM/dd/yyyy}"/>
					<display:column title="Status" property="status_perkawinan"/>
					<display:column title="Action"  media="html">
						<s:url id="update" namespace="/dokter" action="initUpdate.action">
							<s:param name="dokter.kd_dokter" value="%{#attr.row.kd_dokter}"/>
						</s:url>
						<s:a href="%{update}" >Update</s:a>
						
						<s:url id="delete" namespace="/dokter" action="delete">
							<s:param name="dokter.kd_dokter" value="%{#attr.row.kd_dokter}" />
						</s:url>||
						<s:a href="%{delete}"  onclick="del(row.kd_dokter)">Delete</s:a>
					</display:column>
					<display:setProperty name="export.pdf.filename"
						value="ReportDokter" />
					<display:setProperty name="export.excel.filename"
						value="ReportDokter" />
				</display:table>
			</div>
</div> --%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-body table-responsive fortable">
             <table id="tbl_dok" class="display" cellspacing="0" width="100%" >
				<thead>
					<tr>
						<td>Kd.Dokter</td>
						<td>Nama</td>
						<td>Spesialis</td>
						<td>Kelamin</td>
						<td>Alamat</td>
						<td>Kontak</td>
						<td>Tgl.Lahir</td>
						<td>Status.P</td>
						<td>Action</td>
					</tr>
				</thead>
				<tbody>
 					<s:iterator value="listDokter" id="list_dokter" status="test">

					<tr>
						<td><s:property value="%{#list_dokter.kd_dokter}" /></td>
						<td><s:property value="%{#list_dokter.nama}" /></td>
						<td><s:property value="%{#list_dokter.spesialis}" /></td>
						<td><s:property value="%{#list_dokter.kelamin}" /></td>
						<td><s:property value="%{#list_dokter.alamat}" /></td>
						<td><s:property value="%{#list_dokter.nomer_tlp}" /></td>
						<td><s:property value="%{#list_dokter.tgl_lahir}" /></td>
						<td><s:property value="%{#list_dokter.status_perkawinan}" /></td>
						<td>
						<s:url id="update" namespace="/drg" action="initUpdate.action">
						<s:param name="dokter.kd_dokter" value="%{#list_dokter.kd_dokter}" />
					</s:url>
					<s:a href="%{update}" cssClass="btn btn-sm btn-warning">Update</s:a>

					
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