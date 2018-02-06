<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="com.kimia.farma.model.Transaksi"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<div id="view">
	<div class="panel-body table-responsive fortable">
		<display:table id="transaksi2" name="transaksi.transaksi_detail"
			pagesize="10" export="true" requestURI="/trans/pagging.action"
			sort="external"
			class="table table-bordered table-hover table-striped" uid="row">
			<display:column title="kd_obat" property="obat.nama_obat" />
			<display:column title="nama obat " property="obat.nama_obat" />
			<display:column title="jenis " property="obat.jenis" />
			<display:column title="harga obat " property="obat.harga" />
			<display:column title="jml beli " property="jml_beli" />
			<display:column title="kd_jasa " property="jasa.kd_jasa" />
			<display:column title="nama jasa " property="jasa.nama" />
			<display:column title="harga " property="jasa.harga" />
			<display:column title="Action" media="html">
				<s:url id="delete" namespace="/transk" action="delete">
					<s:param name="obat.kd_obat" value="%{#attr.row.kd_obat}" />
				</s:url>||
						<s:a href="%{delete}">Delete</s:a>
			</display:column>
			<display:setProperty name="export.pdf.filename"
				value="ReportObat.pdf" />
			<display:setProperty name="export.excel.filename"
				value="ReportObat.xlsx" />
		</display:table>
	</div>
</div>

<!-- /.row -->
