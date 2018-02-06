<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.kimia.farma.model.Pengeluaran"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GriyaSehat Natura Depok</title>

</head>
<div id="view" >
	<ol class="breadcrumb bread"
		style="color: orange; font-family: monospace;">
		<li><i class="glyphicon glyphicon-cog"></i> Master</li>
		<li class="active"><i class="glyphicon glyphicon-pencil-square-o"></i>
			Input Data Pengeluaran</li>
	</ol>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-primary">
				<div class="panel-heading" >
					<h3 class="panel-title">
						<i class="glyphicon glyphicon-list"></i>Form Input Data Pengeluaran
					</h3>
				</div>
				<div style="float: left; width: 100%;">
					<b style="color: orange;"><h4>
							<s:actionmessage /></b>
				</div>
				<form namespace="pengeluaran" action="simpanPengeluaran" method="post"
					onsubmit="return validasi()" enctype="multipart/form-data"
					class="form-horizontal"
					style="font-size: medium; text-decoration: blink; color: black;">
					<div class="panel-body">

						<div class="form-group">
							<label class="col-sm-2 control-label">KD.Pengeluaran :</label>
							<div class="row">
								<div class="col-sm-3">
									<s:hidden id="kode"></s:hidden>
									<s:textfield name="pengeluaran.kd_pengeluaran" id="kd_pengeluaran"
										cssClass="form-control" placeholder="kode obat"
										readonly="true" required="true"></s:textfield>
								</div>
								
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Detail :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textarea name="pengeluaran.detail" id="nama"
										cssClass="form-control" placeholder="Nama Pengeluaran"
										required="true" />
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Keseluruhan Harga Rp.</label>
							<div class="row">
								<div class="col-sm-2">
									<s:textfield id="harga" cssClass="form-control" placeholder="0"
										onInput="onlyNumber('harga')" maxlength="8" name="pengeluaran.total"/>
								</div>
							</div>
						</div>
						<div class="form-group ">
			                <label class="col-sm-2 control-label">Tanggal.Pengeluaran</label>
			                <div class="row">
			                    <div class="col-sm-3">
			                        <div class="input-group datetimepicker">
			                            <div class="input-group datetimepicker">
			                            <s:textfield name="pengeluaran.tgl" cssClass="form-control input-sm datepicker" readonly="true" />
			                            <div class="input-group-addon input-sm"><i class="glyphicon glyphicon-calendar"></i></div>
			                        </div>
			                        </div>
			                    </div>
			                </div>                            
			            </div> 
						<div class="form-group">
							<div class="col-sm-2"></div>
							<div class=" col-sm-2"></div>
							<div class="right col-sm-1"></div>
							<div class="right col-sm-1">
								<button type="submit" class="btn btn-success">SIMPAN</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		function validasi() {
			var b = document.getElementById("nama").value;
			var c = document.getElementById("harga").value;
			if (b.length < 10) {
				document.getElementById("nama").focus();
				return false;
			}

			if (c.length <3) {
				document.getElementById("harga").focus();
				return false;
			}
		}
		
		$(document).ready(function() {
			
			
			
			$('#tbl_obt').DataTable({
				"order" : [ [ 1, "desc" ] ]
			});

			$(".del").click(function() {
				if (!confirm("Apakah anda yakin ingin hapus file tersebut")) {
					return false;
				}
			});
			$("#listTableObat  > thead tr th").css({
				"text-align" : "center",
				"vertical-align" : "middle"
			});
			$("#listTableObat  > tbody tr td").css({
				"text-align" : "center",
				"vertical-align" : "middle"
			});
		});


		function onlyNumber(id) {
			var number = document.getElementById(id).value;
			if (number.length >=1) {
				document.getElementById(id).value = number.replace(/[^0-9]+/g,'');
			}
		}
	</script>

<%-- 
	<div class="panel-body">
		<div class="panel-body table-responsive fortable">
			
 --%>
			<%-- <display:table id="listObt" name="listObat" pagesize="10"
				export="true" requestURI="/obat/pagging.action" sort="external"
				class="table table-hover" uid="row"
				style="background: transparent; background-color: transparent; font-family: monospace; font-size: medium; color:#a8a7a7">
				<display:column title="KD.Obat " property="kd_obat" />
				<display:column title="Nama" property="nama_obat" />
				<display:column title="Merk " property="merk" />
				<display:column title="Jenis " property="jenis" />
				<display:column title="Type " property="type" />
				<display:column title="Stock " property="stok" />
				<display:column title="Harga " property="harga" />
				<display:column title="Action" media="html">
					<s:url id="update" namespace="/obat" action="initUpdate.action">
						<s:param name="obat.kd_obat" value="%{#attr.row.kd_obat}" />
					</s:url>
					<s:a href="%{update}">Update</s:a>

					<s:url id="delete" namespace="/obat" action="delete">
						<s:param name="obat.kd_obat" value="%{#attr.row.kd_obat}" />
					</s:url>||
						<s:a href="%{delete}">Delete</s:a>
				</display:column>
				<display:setProperty name="export.pdf.filename"
					value="ReportObat.pdf" />
				<display:setProperty name="export.excel.filename"
					value="ReportObat.xlsx" />
			</display:table> --%>
		
		<!-- </div>
	</div>
</div> -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-body table-responsive fortable">
             <table id="tbl_obt" class="display" cellspacing="0" width="100%" >
				<thead>
					<tr>
						<td>Kd.Pengeluaran</td>
						<td>Detail</td>
						<td>Total</td>
						<td>Tgl.Pengeluaran</td>
						<td>Action</td>
					</tr>
				</thead>
				<tbody>
 					<s:iterator value="listPengeluaran" id="list" status="test">

					<tr>
						<td><s:property value="%{#list.kd_pengeluaran}" /></td>
						<td><s:property value="%{#list.detail}" /></td>
						<td><s:property value="%{#list.total}" /></td>
						<td><s:property value="%{#list.tgl}" /></td>
						<td>
						<s:url id="update" namespace="/pengeluaran" action="initUpdate.action">
						<s:param name="pengeluaran.kd_pengeluaran" value="%{#list.kd_pengeluaran}" />
					</s:url>
					<s:a href="%{update}" cssClass="btn btn-sm btn-warning">Update</s:a>

					<s:url id="delete" namespace="/pengeluaran" action="delete">
						<s:param name="pengeluaran.kd_pengeluaran" value="%{#list.kd_pengeluaran}" />
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

</div><!-- /.row -->
<!-- /.row -->
</html>
