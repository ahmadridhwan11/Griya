<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.kimia.farma.model.Obat"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GriyaSehat Natura Depok</title>

</head>
<div id="view" >
	<ol class="breadcrumb bread"
		style="color: orange; font-family: monospace;">
		<li><i class="glyphicon glyphicon-cog"></i> Master</li>
		<li class="active"><i class="glyphicon glyphicon-pencil-square-o"></i>
			Input Data Obat</li>
	</ol>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-primary">
				<div class="panel-heading" >
					<h3 class="panel-title">
						<i class="glyphicon glyphicon-list"></i>Form Input Data Obat
					</h3>
				</div>
				<div style="float: left; width: 100%;">
					<b style="color: orange;"><h4>
							<s:actionmessage /></b>
				</div>
				<form namespace="obat" action="simpanObat" method="post"
					onsubmit="return validasi()" enctype="multipart/form-data"
					class="form-horizontal"
					style="font-size: medium; text-decoration: blink; color: black;">
					<div class="panel-body">

						<div class="form-group">
							<label class="col-sm-2 control-label">KD.Obat :</label>
							<div class="row">
								<div class="col-sm-3">
									<s:hidden id="kode"></s:hidden>
									<s:textfield name="obat.kd_obat" id="kd_obat"
										cssClass="form-control" placeholder="kode obat"
										readonly="true" required="true"></s:textfield>
								</div>
								<div class="col-sm2">
									<input type="checkbox" id="barcode" value="false"
										onchange="gantiBarcode()"> barcode</input>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Nama :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="obat.nama_obat" id="nama_obat"
										cssClass="form-control" placeholder="Nama Obat"
										required="true" />
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Merk:</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="obat.merk" id="merk" cssClass="form-control"
										placeholder="Merk Obat" />
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Keuntungan :</label>
							<div class="row">
								<div class="col-sm-1">
									<s:textfield id="Keuntungan" cssClass="form-control"
										placeholder="0" maxlength="4" onInput="untung('Keuntungan')" />
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Harga Rp.</label>
							<div class="row">
								<div class="col-sm-2">
									<s:textfield id="harga" cssClass="form-control" placeholder="0"
										onInput="onlyNumber('harga')" maxlength="8" />
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Harga Jual Rp.</label>
							<div class="row">
								<div class="col-sm-2">
									<s:textfield name="obat.harga" id="harga2"
										cssClass="form-control" placeholder="0" readonly="true" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Jenis :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="obat.jenis" id="jenis"
										cssClass="form-control" placeholder="jenis obat" />
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">Type :</label>
							<div class="row">
								<div class="col-sm-4">
									<s:textfield name="obat.type" id="type" cssClass="form-control"
										placeholder="Type Obat" />
								</div>
							</div>
						</div>
						<%-- <div class="form-group ">
							<label class="col-sm-2 control-label">Stok :</label>
							<div class="row">
								<div class="col-sm-1">
									<s:textfield name="obat.stok" id="stock"
										cssClass="form-control" placeholder="0" onInput="onlyNumber('stock')" maxlength="4"/>
								</div>
							</div>
						</div> --%>
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
			var b = document.getElementById("kd_obat").value;
			var c = document.getElementById("nama_obat").value;
			if (b.length < 10) {
				document.getElementById("kd_obat").focus();
				return false;
			}

			if (c.length < 5) {
				document.getElementById("nama_obat").focus();
				return false;
			}
		}
		function gantiBarcode() {
			var barcode = document.getElementById("barcode").value;
			if (barcode == "false") {
				document.getElementById("kode").value = document
						.getElementById("kd_obat").value;
				document.getElementById("kd_obat").value = "";
				document.getElementById("kd_obat").readOnly = false;
				document.getElementById("barcode").value = "true";
			} else {
				document.getElementById("barcode").value = "false";
				document.getElementById("kd_obat").value = document
						.getElementById("kode").value;
				document.getElementById("kd_obat").readOnly = true;
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

		function untung(id) {
			var x = document.getElementById(id).value;
			var harga = document.getElementById("harga").value;
			if (x.length >= 1 && harga.length >= 1) {
				var y = ((parseInt(harga) * parseFloat(x)) / 100);

				document.getElementById("harga2").value = (parseFloat(y) + parseInt(harga));
			} else {
				document.getElementById("harga2").value = harga;
			}

		}

		function onlyNumber(id) {
			var number = document.getElementById(id).value;
			var x = document.getElementById("Keuntungan").value;
			if (number.length > 1) {
				document.getElementById(id).value = number.replace(/[^0-9]+/g,
						'');
				if (x.length >= 1) {
					var y = ((parseFloat(number) * parseFloat(x)) / 100);
					document.getElementById("harga2").value = (parseFloat(y) + parseFloat(number));
				}
			} else {
				document.getElementById(id).value = number.replace(/[^1-9]+/g,
						'');

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
						<td>Kd.Obat</td>
						<td>Nama</td>
						<td>Merk</td>
						<td>Jenis</td>
						<td>Type</td>
						<td>Stock</td>
						<td>harga</td>
						<td>Action</td>
					</tr>
				</thead>
				<tbody>
 					<s:iterator value="listObat" id="list_obat" status="test">

					<tr>
						<td><s:property value="%{#list_obat.kd_obat}" /></td>
						<td><s:property value="%{#list_obat.nama_obat}" /></td>
						<td><s:property value="%{#list_obat.merk}" /></td>
						<td><s:property value="%{#list_obat.jenis}" /></td>
						<td><s:property value="%{#list_obat.type}" /></td>
						<td><s:property value="%{#list_obat.stok}" /></td>
						<td><s:property value="%{#list_obat.harga}" /></td>
						<td>
						<s:url id="update" namespace="/obat" action="initUpdate.action">
						<s:param name="obat.kd_obat" value="%{#list_obat.kd_obat}" />
					</s:url>
					<s:a href="%{update}" cssClass="btn btn-sm btn-warning">Update</s:a>

					<s:url id="delete" namespace="/obat" action="delete">
						<s:param name="obat.kd_obat" value="%%{#list_obat.kd_obat}" />
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
