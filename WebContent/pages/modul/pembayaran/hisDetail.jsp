<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="com.kimia.farma.model.Transaksi"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="panel-body" style="background: transparent; background-color: transparent; font-family: monospace; font-size: large; font-weight: bold;">
		<form namespace="/trans" action="print.action" method="post">
		<s:hidden name="transaksi.kd_transaksi"></s:hidden>
								<button type="submit" class="btn btn-success">CETAK</button>
		</form>
		<display:table id="transaksi2" name="transaksi" 
			class="table  table-hover"  uid="row"
			style="font-size:medium; background:transparent; color:#a8a7a7; font-family: monospace; font-size: large; font-weight: bold;">
			<display:column title="kd_transaksi" property="kd_transaksi" />
			<display:column title="passien " property="passien.nama" />
			<display:column title="Total " property="total" />
			<display:column title="Tanggal" format="{0,date,MMMM/dd/yyyy}" property="tgl_transaksi" />
			<display:column title="Bayar " property="uang" />
		</display:table>
		<display:table id="transaksi2" name="transaksi.transaksi_detail"
			sort="external"
			class="table table-bordered table-hover" uid="row"
			style="font-size:medium; background:transparent;  color:#a8a7a7; font-family: monospace; font-size: large; font-weight: bold; ">
			<display:column title="Obat" property="obat.nama_obat" />
			<display:column title="Harga" property="obat.harga" />
			<display:column title="jumlah " property="jml_beli" />
			<display:column title="dsc_obat " property="dsc_obat" />
			<display:column title="Jasa" property="jasa.nama" />
			<display:column title="Dokter " property="jasa.dokter.nama" />
			<display:column title="Harga " property="jasa.harga" />
			<display:column title="Sub-Total " property="sub_total" />
		</display:table>
		
</div>

<!-- /.row -->
