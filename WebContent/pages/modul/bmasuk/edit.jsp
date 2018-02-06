<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.kimia.farma.model.BarangMasuk"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GriyaSehat Natura Depok</title>
</head>


<div id="view" >
	<ol class="breadcrumb bread" style="color: orange; font-family: monospace;">
		<li><i class="glyphicon glyphicon-cog"></i> Master</li>
		<li class="active"><i class="glyphicon glyphicon-pencil-square-o"></i>
			Edit Barang Masuk</li>
	</ol>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-primary"  >
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="glyphicon glyphicon-list"></i>Form Edit Barang Masuk
					</h3>
				</div>
				<div style="float:left; width: 100%;">
					<b style="color: orange;"><h4><s:actionmessage /></b>
				</div>
				<form  method="post" namespace="/bmasuk"
					enctype="multipart/form-data" class="form-horizontal"
					style="font-size:medium; background:transparent;  text-decoration: blink; color: black;">
					<s:hidden name="barangMasuk.supplier.kd_supplier" id="sup_old"/>
				                <s:hidden name="barangMasuk.qty" id="qty_old"/>
				                <s:hidden name="barangMasuk.obat.kd_obat" id="kd_obat_old"/>
				                 <s:hidden name="barangMasuk.obat.nama_obat" id="nama_obat_old"/>
				                 <s:hidden name="barangMasuk.supplier.nama" id="nama_sup_old"/>
				                 <s:hidden name="bm.obat.kd_obat" id="kd_obat_new"/>
				                 <s:hidden name="barangMasuk.obat.stok" id="stok_obat_old"/>
				                 <s:hidden name="bm.obat.stok" id="stok_obat_new"/>
				                 <s:hidden name="bm.supplier.kd_supplier" id="kd_new"/>
					<div class="panel-body">

						<div class="form-group">
							<label class="col-sm-2 control-label">Kd.Masuk</label>
							<div class="row">
								<div class="col-sm-3">
									<s:textfield name="barangMasuk.kd_masuk" id="kd_masuk" cssClass="form-control"
										readonly="true"/>
								</div>
							</div>
							</div>
					<div class="form-group ">
			                <label class="col-sm-2 control-label">Nama Obat</label>
			                <div class="row">
				                <div class="col-sm-3">
				                
				                    <input list="list_obat" onchange="setKdObat()" class="form-control reset"  placeholder="Nama obat"  id="obat" autocomplete="off"/>
				                        <datalist id="list_obat">
				                            <s:iterator value="listObat" id="list_obat" status="test">
				                                <option value="<s:property value="%{#list_obat.nama_obat}" /> : <s:property value='%{#list_obat.kd_obat}'/> : <s:property value='%{#list_obat.jenis}' /> : <s:property value='%{#list_obat.stok}' />">
				                            
				                                </option>
				                        	</s:iterator>
				                        </datalist>
				                </div>
			            	</div>            
            			</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Qty:</label>
							<div class="row">
								<div class="col-sm-1">
									<s:textfield name="bm.qty" id="qty2" cssClass="form-control" onInput="onlyNumber('qty')"
										 maxlength="3"/>
								</div>
							</div>
						</div>
						<div class="form-group ">
			                <label class="col-sm-2 control-label">Tanggal.Masuk</label>
			                <div class="row">
			                    <div class="col-sm-3">
			                        <div class="input-group datetimepicker">
			                            <div class="input-group datetimepicker">
			                            <s:textfield name="barangMasuk.tgl_masuk" cssClass="form-control input-sm datepicker" readonly="true" />
			                            <div class="input-group-addon input-sm"><i class="glyphicon glyphicon-calendar"></i></div>
			                        </div>
			                        </div>
			                    </div>
			                </div>                            
			            </div> 
						<div class="form-group ">
			                <label class="col-sm-2 control-label">Tanggal.Expired</label>
			                <div class="row">
			                    <div class="col-sm-3">
			                        <div class="input-group datetimepicker">
			                            <div class="input-group datetimepicker">
			                            <s:textfield name="barangMasuk.tgl_expired" id="tgle" cssClass="form-control input-sm datepicker" />
			                            <div class="input-group-addon input-sm"><i class="glyphicon glyphicon-calendar"></i></div>
			                        </div>
			                        </div>
			                    </div>
			                </div>                            
			            </div>
			            <div class="form-group ">
							<label class="col-sm-2 control-label">Supplier</label>
							<div class="col-sm-3">
				                 
				                    <input list="list_sup" onchange="setKdSup()" class="form-control reset" placeholder="Nama Supplier"  id="sup" autocomplete="off"/>
				                        <datalist id="list_sup">
				                            <s:iterator value="listSup" id="list_sup" status="test">
				                                <option value="<s:property value="%{#list_sup.nama}" /> :<s:property value='%{#list_sup.kd_supplier}'/>">
				                                </option>
				                        	</s:iterator>
				                        </datalist>
				                </div>
						</div>
						<div class="form-group">
						
							<div class="col-sm-2"></div>
							<div class=" col-sm-2"></div>
							<div class="right col-sm-1"><s:submit action="inputBMasuk" cssClass="btn btn-success" value="Cancel"></s:submit></div>
							<div class="right col-sm-1">
								<s:submit action="update" cssClass="btn btn-success" value="Update"></s:submit>
							</div>
						</div>
					</div>
					
				</form>
			</div>
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
	
	function setKdObat() {
		 var id = document.getElementById("obat").value;
	       var data = id.split(':');
	       document.getElementById("obat").value="";
	       document.getElementById("obat").value=data[0];
	       document.getElementById("kd_obat_new").value=data[1].trim();
	       document.getElementById("stok_obat_new").value=data[3].trim();
	}
	function setKdSup() {
		 var id = document.getElementById("sup").value;
		var data = id.split(':');
	       document.getElementById("sup").value="";
	       document.getElementById("sup").value=data[0];
	       document.getElementById("sup_old").value=data[1].trim();
	}
	
	 $(document).ready(function(){
		 $('#tbl_bm').DataTable({
				"order" : [ [ 6, "desc" ] ]
			});
		 document.getElementById("obat").value=document.getElementById("nama_obat_old").value;
		 document.getElementById("sup").value=document.getElementById("nama_sup_old").value;
		 document.getElementById("kd_new").value=document.getElementById("nama_sup_old").value;
		document.getElementById("qty2").value=document.getElementById("qty_old").value;
		document.getElementById("kd_obat_new").value=document.getElementById("kd_obat_old").value;
		document.getElementById("stok_obat_new").value=document.getElementById("stok_obat_new").value;
   	  $(".del").click(function(){
   	    if (!confirm("Apakah anda yakin ingin hapus file tersebut")){
   	      return false;
   	    }
   	  });
   	$(function(){
   	$("#tgle").datepicker({
   		changeMonth : true,
		changeYear : true,
		showButtonPanel: true,
   		yearRange : '2017:2018',
		
	});
   	});
   	});
	</script>

		<%-- <div class="panel-body" style="background: transparent; background-color: transparent;">

				<display:table id="listBmasuk" name="listBarang" pagesize="10"
					export="true" requestURI="/bmasuk/pagging.action" sort="external"
					class="table table-hover" uid="row"
					 style="background: transparent; background-color: transparent; font-family: monospace; font-size: medium; color:#a8a7a7">
					<display:column title="Kd.Masuk " property="kd_masuk" />
					<display:column title="Obat" property="obat.nama_obat" />
					<display:column title="Qty " property="qty" />
					<display:column title="Tgl.Masuk " property="tgl_masuk"  format="{0,date,MMMM/dd/yyyy   HH:mm}" />
					<display:column title="Tgl.Expired " property="tgl_expired"  format="{0,date,MMMM/dd/yyyy   HH:mm}" />
					<display:column title="Supplier " property="supplier.nama" />
					<display:column title="Action" media="html">
						<s:url id="update" namespace="/bmasuk" action="initUpdate.action">
							<s:param name="barangMasuk.kd_masuk" value="%{#attr.row.kd_masuk}"/>
						</s:url>
						<s:a href="%{update}">Update</s:a>
						
						<s:url id="delete" namespace="/bmasuk" action="delete">
							<s:param name="obat.kd_obat" value="%{#attr.row.kd_masuk}" />
						</s:url>||
						<s:a href="%{delete}">Delete</s:a>
					</display:column>
					<display:setProperty name="export.pdf.filename"
						value="ReportObat.pdf" />
					<display:setProperty name="export.excel.filename"
						value="ReportObat.xlsx" />
				</display:table>
				</div>
</div> --%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-body table-responsive fortable">
             <table id="tbl_bm" class="display" cellspacing="0" width="100%" >
				<thead>
					<tr>
						<td>Kd.Masuk</td>
						<td>Obat</td>
						<td>Qty</td>
						<td>Tgl.Masuk</td>
						<td>Tgl.Expired</td>
						<td>Supplier</td>
						<td>Action</td>
					</tr>
				</thead>
				<tbody>
 					<s:iterator value="listBarang" id="lb" status="test">

					<tr>
						<td><s:property value="%{#lb.kd_masuk}" /></td>
						<td><s:property value="%{#lb.obat.nama_obat}" /></td>
						<td><s:property value="%{#lb.qty}" /></td>
						<td><s:property value="%{#lb.tgl_masuk}" /></td>
						<td><s:property value="%{#lb.tgl_expired}" /></td>
						<td><s:property value="%{#lb.supplier.nama}" /></td>

						<td>
						<s:url id="update" namespace="/bmasuk" action="initUpdate.action">
						<s:param name="barangMasuk.kd_masuk" value="%{#lb.kd_masuk}" />
					</s:url>
					<s:a href="%{update}" cssClass="btn btn-sm btn-warning">Update</s:a>

					<s:url id="delete" namespace="/bmasuk" action="delete">
						<s:param name="barangMasuk.kd_masuk" value="%%{#lb.kd_masuk}" />
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
