<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="com.kimia.farma.model.Transaksi"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <display:table id="transaksi2" name="listTransaksi"
			pagesize="15" export="true" requestURI="/trans/pagging.action"
			sort="external"
			class="table table-hover" uid="row"
			style="font-size:medium; background:transparent; font-family: monospace; font-size: large; font-weight: bold; color:#a8a7a7;">
			<display:column title="kd_transaksi" property="kd_transaksi" />
			<display:column title="passien " property="passien.nama" />
			<display:column title="Total " property="total" />
			<display:column title="Tanggal" format="{0,date,MM/dd/yyyy}" property="tgl_transaksi" />
			<display:column title="Bayar " property="uang" />
			<display:column title="Action" media="html">
				<s:url id="delete" namespace="/trans" action="inputTrans?page=hisDetail">
					<s:param name="transaksi.kd_transaksi" value="%{#attr.row.kd_transaksi}" />
				</s:url>
						<s:a href="%{delete}" cssClass="btn btn-warning btn-success">view</s:a>
			</display:column>
			<display:setProperty name="export.pdf.filename"
				value="ReportTransaksi" />
			<display:setProperty name="export.excel.filename"
				value="ReportTransaksi" />
		</display:table> --%>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-primary">
			<div class="panel-body table-responsive fortable">
				<table id="tbl_tr" class="display" cellspacing="0" width="100%">
					<thead>
						<tr>
							<td>Kd.transaksi</td>
							<td>Pasien</td>
							<td>Total</td>
							<td>Tanggal</td>
							<td>Bayar</td>
							<td>Action</td>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listTransaksi" id="lb" status="test">

							<tr>
								<td><s:property value="%{#lb.kd_transaksi}" /></td>
								<td><s:property value="%{#lb.passien.nama}" /></td>
								<td><s:property value="%{#lb.total}" /></td>
								<td><s:property value="%{#lb.tgl_transaksi}" /></td>
								<td><s:property value="%{#lb.uang}" /></td>
								<td><s:url id="view" 
										namespace="/trans" action="inputTrans?page=hisDetail">
										<s:param name="transaksi.kd_transaksi" value="%{#lb.kd_transaksi}" />
									</s:url> <s:a href="%{view}" cssClass="btn btn-sm btn-warning">detail</s:a>

								</td>
							</tr>
						</s:iterator>

					</tbody>
				</table>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript">
$(document).ready(function(){
	 $('#tbl_tr').DataTable({
			"order" : [ [ 5, "desc" ] ]
		});
});
</script>
<!--

//-->

<!-- /.row -->
