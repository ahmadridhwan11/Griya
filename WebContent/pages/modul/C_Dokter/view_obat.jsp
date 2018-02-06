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

	<script>
	
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

		
	
	</script>



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
