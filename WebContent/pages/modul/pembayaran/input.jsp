<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.kimia.farma.model.Transaksi"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GriyaSehat Natura Depok</title>

</head>
<body>

<div id="view">
	<ol class="breadcrumb bread" style="color: orange; font-family: monospace; ">
		<li><i class="glyphicon glyphicon-cog"></i> Master</li>
		<li class="active" style="color: orange; "><i class="glyphicon glyphicon-pencil-square-o"/>
			Input Transaksi</li>
	</ol>
	<div class="row" >
		<div class="col-lg-12">
			<div class="panel panel-primary" >
				<div class="panel-heading" >
					<h3 class="panel-title">
						<i class="glyphicon glyphicon-list"></i>Form Input Data Transaksi
					</h3>
				</div>
				<div class="alert alert-dismissable alert-success" style="float: left; width: 100%; background: transparent; border: threedshadow;">
					<b><h4><s:actionmessage /></b>
				</div>
				<form style="font-size:medium;   text-decoration: blink; color: black;"  namespace="/trans" action="simpanTR.action" method="POST" enctype="multipart/form-data" class="form-horizontal">
					<div class="panel-body">
						<div class="form-group ">
							<label class="col-sm-2 control-label">KD Transaksi</label>
							<div class="row">
								<div class="col-sm-3">
									<s:textfield name="transaksi.kd_transaksi" cssClass="form-control"
										readonly="true"/>
								</div>
								<div class="right col-sm-1"></div>
								<div class="col-sm-2">
									<s:textfield name="transaksi.tgl_transaksi" cssClass="form-control input-sm datepicker" readonly="true" />
								</div>
								<div class="col-sm-3">
				                 <s:hidden name="transaksi.passien.kd_passien" id="kd_passien"></s:hidden>
				                    <input list="list_passien" onchange="setKdPas()" name="transaksi.passien.nama" class="form-control reset" placeholder="Nama Passien"  id="passien" autocomplete="off"/>
				                        <datalist id="list_passien">
				                            <s:iterator value="listPassien" id="list_passien" status="test">
				                                <option value="<s:property value="%{#list_passien.nama}" /> : <s:property value='%{#list_passien.kd_passien}'/>">
				                                </option>
				                        	</s:iterator>
				                        </datalist>
				                </div>
							</div>
							</div>
						<div class="form-group ">
			                <label class="col-sm-2 control-label">Nama Obat</label>
			                <div class="row">
				                <div class="col-sm-4">
				                <s:hidden name="obat.kd_obat" id="kd_obat"></s:hidden>
				                 <s:hidden id="stok"></s:hidden>
				                    <input list="list_obat" onchange="setKdObat()" class="form-control reset" placeholder="Nama obat"  id="obat" autocomplete="off"/>
				                        <datalist id="list_obat">
				                            <s:iterator value="listObat" id="list_obat" status="test">
				                                <option value="<s:property value="%{#list_obat.nama_obat}" /> : <s:property value='%{#list_obat.kd_obat}'/> : <s:property value='%{#list_obat.jenis}' /> : <s:property value='%{#list_obat.stok}' /> : <s:property value='%{#list_obat.harga}'/>">
				                                </option>
				                        	</s:iterator>
				                        </datalist>
				                </div>
				                <div class="col-sm-2">
									<s:textfield id="harga_obat"
												cssClass="form-control" placeholder="Rp.0" disabled="true" />
								</div>
								<div class="col-sm-2">
									<s:textfield  id="jenis_obat"
												cssClass="form-control" placeholder="Jenis" disabled="true" />
								</div>
								<div class="col-sm-1">
									<s:textfield  id="stok_obat"
												cssClass="form-control" placeholder="0" disabled="true" />
								</div>
				           </div>
            			</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Jml.Beli :</label>
							<div class="row">
								<div class="col-sm-1">
									<s:textfield id="jml_beli" onInput="hitungSub(event.keyCode)" maxlength="4"
										cssClass="form-control" required="true"  placeholder="0"/>
								</div>
								<div class="col-sm-2"><s:textfield id="sub_total"
										cssClass="form-control" placeholder="Rp. 0" disabled="true"/></div>
								<div class="col-sm-1">
									<s:textfield  id="dsc_obat"
										cssClass="form-control" placeholder="0%" onInput="dscObat('dsc_obat')" value="0" maxLength="2"/>
								</div>
								<div class="right col-sm-2">
								<input type="button" onclick="addMorePassengerRow('obat')" class="btn btn-warning btn-success" value="add Obat" id="addObt"/>
							</div>
							</div>
						</div>
<!-- 						"addMorePassengerRow('pdTable','obat')
 -->						<hr  width="100%">
							</div>
						<div class="form-group ">
			                <label class="col-sm-2 control-label">Nama Jasa</label>
			                <div class="row">
				                <div class="col-sm-4">
				                <s:hidden  id="kd_jasa"></s:hidden>
				                 <s:hidden id="kd_dokter"></s:hidden>
				                    <input list="list_j" onchange="setKdJasa()" class="form-control reset" placeholder="Nama jasa"  id="jasa" autocomplete="off"/>
				                        <datalist id="list_j">
				                            <s:iterator value="listJ" id="list_j" status="test">
				                                <option value="<s:property value="%{#list_j.nama}" /> : <s:property value='%{#list_j.kd_jasa}'/> : <s:property value='%{#list_j.harga}' /> : <s:property value='%{#list_j.dokter.nama}' /> : <s:property value='%{#list_j.dokter.kd_dokter}' />">
				                                </option>
				                        	</s:iterator>
				                        </datalist>
				                </div>
				                <div class="col-sm-2">
									<s:textfield  id="harga_jasa"
												cssClass="form-control" placeholder="Rp.0" disabled="true"/>
								</div>
								<div class="col-sm-2">
									<s:textfield  id="nama_dokter"
												cssClass="form-control" placeholder="nama dokter" disabled="true" />
								</div>
								<div class="col-sm-1">
								<input type="button" class="btn btn-warning btn-danger" onclick="addJasaRow('jasa')" value="add Jasa" id="addJasa"/>
								</div>
				           </div>
            			</div>
            			<hr  width="100%">
						<div class="form-group ">
							<label class="col-sm-2 control-label">Harus di Bayar :</label>
							<div class="row">
								<div class="col-sm-2">
									<s:textfield name="transaksi.total" id="total_bayar" cssStyle="background:#0044cc; font-size: 2em; color:black"
										cssClass="form-control" placeholder="0" readonly="true" />
								</div>
								<div class="col-sm-0">
									<s:textfield id="copy_bayar"
										hidden="true" readonly="true" />
								</div>
								<div class="col-sm-2">
									<s:textfield  id="diBayar"
										cssClass="form-control" placeholder="0" name="transaksi.uang" onInput="pembayaranFinal()" />
								</div>
								<div class="col-sm-1">
									<s:textfield  id="dsc"
										cssClass="form-control" placeholder="0%" name="transaksi.dsc" value="0" maxLength="2"/>
								</div>
								<div class="right col-sm-1">
									<input type="submit" class="btn btn-success" onclick="bayarTotal()" id="bayar"  value="Bayar"/>
								</div>
								
							</div>
						</div>
						<div class="form-group ">
							<div class="col-sm-1">
									
							</div>	
						</div>
				
				<s:hidden name="rowindex"/>
				 <TABLE id="pdTable" class="table table-hover table-striped" style="background-color:#aed5f5; font-family: monospace;" >
				<TR>
				<TD>kode Obat</TD>
					<TD >Nama Obat</TD>
					<TD>Harga</TD>
					<TD>Jenis</TD>
					<TD>Jml</TD>
					<TD>dsc</TD>
					<TD style="display: none; width: 0%; background-color: transparent;">---</TD>
					<TD>kode jasa</TD>
					<TD>Nama Jasa</TD>
					<TD>harga</TD>
					<TD>Sub-Total</TD>
					<TD>Aksi</TD>
					<td style="display: none; width: 0%; background-color: transparent;"></td>
					<td style="display: none; width: 0%;  background-color: transparent;"></td>
				</TR>

				<s:iterator value="transaksi_detail" status="cnt"
					var="LTD" id="listTransaksi2">
					
					
					<tr id="">
						<TD><s:textfield name="transaksi_detail[%{#cnt.index}].obat.kd_obat" readonly="true" value="abc"/></TD>
						<TD><s:textfield
								name="transaksi_detail[%{#cnt.index}].obat.nama_obat" readonly="true" value="abc"/></TD>
						<TD><s:textfield
								name="transaksi_detail[%{#cnt.index}].obat.harga" readonly="true" value="3"/></TD>
						<TD><s:textfield
								name="transaksi_detail[%{#cnt.index}].obat.jenis" readonly="true" value="abc"/></TD>
						<TD><s:textfield
								name="transaksi_detail[%{#cnt.index}].jml_beli" readonly="true" value="9"/></TD>
						<TD><s:textfield
								name="transaksi_detail[%{#cnt.index}].dsc_obat" readonly="true" value="0"/></TD>
						<TD style="display: none;"><s:hidden name="transaksi_detail[%{#cnt.index}].obat.stok" readonly="true" value="abc" style="width: 0px;"/></TD>
						<TD ><s:textfield name="transaksi_detail[%{#cnt.index}].jasa.kd_jasa" readonly="true" value="abc" /></TD>
						<TD ><s:textfield
								name="transaksi_detail[%{#cnt.index}].jasa.nama" readonly="true" value="abc"/></TD>
						<TD><s:textfield
								name="transaksi_detail[%{#cnt.index}].jasa.harga" readonly="true" value="9"/></TD>
						<TD><s:textfield
								name="transaksi_detail[%{#cnt.index}].sub_total" readonly="true"  value="9"/></TD>
						<td><input type="button" value="hapus" onclick="hapus('{#cnt.index}')"/></td>
						<td style="display: none;"><s:hidden style="width: 0px;" name="transaksi_detail[%{#cnt.index}].jasa.dokter.kd_dokter" readonly="true" value="abc"/></td>
						<td style="display: none;"><s:hidden name="transaksi_detail[%{#cnt.index}].jasa.dokter.nama" readonly="true" value="abc"  style="width: 0px;"/></td>
					</tr>
				</s:iterator>

			</TABLE> 
			</form>
			</div>
			<s:hidden id="rowObat" value="1"/>
			<s:hidden id="rowJasa" value="1"/>
		</div>
		</div>
		</div>
		
	<script>
	
	function dscObat(id) {
		onlyNumber(id);
		var dsc_obat = parseInt(document.getElementById(id).value);
		var b = parseInt(document.getElementById("jml_beli").value);
		var c= parseInt(document.getElementById("stok_obat").value);
		if(dsc_obat>0 && b>0 && b<=c){
			var sub_total = parseInt(document.getElementById("harga_obat").value)*b;
			var hasil = parseInt((sub_total*dsc_obat)/100);
			hasil=sub_total-hasil;
			document.getElementById("sub_total").value=hasil;
		}else{
			b = parseInt(document.getElementById("jml_beli").value);
			dsc_obat=parseInt(document.getElementById("harga_obat").value);
			document.getElementById("sub_total").value=dsc_obat*b;
		}
		
	}
	function onlyNumber(id) {
		var number = document.getElementById(id).value;
		if(number.length>1){
			document.getElementById(id).value = number.replace(/[^0-9]+/g,'');
		}else{
		 document.getElementById(id).value = number.replace(/[^1-9]+/g,'');
	 }
	}
	
	function discounnt() {
		var number = document.getElementById("dsc").value;
		if(number.length>1){
			document.getElementById("dsc").value = number.replace(/[^0-9]+/g,'');
		}else{
		 document.getElementById("dsc").value = number.replace(/[^1-9]+/g,'');
	 }
		var n= document.getElementById("dsc").value;
		var b =  document.getElementById("total_bayar").value;
		var tot=document.getElementById("copy_bayar").value;
		if(n!="" && parseInt(b)>0){
			
			var t = parseInt((tot/100)*n);
			 document.getElementById("total_bayar").value=parseInt(bm-t);
		}else{

			document.getElementById("total_bayar").value=document.getElementById("copy_bayar").value;
		}
	}
	
	function pembayaranFinal() {
		onlyNumber('diBayar');
		var total = document.getElementById("total_bayar").value;
		var bayar = document.getElementById("diBayar").value;
		if(parseInt(total)<=parseInt(bayar)){
			 document.getElementById("bayar").disabled="";
		}else{
			 document.getElementById("bayar").disabled="true";
		}
	}
	
	function bayarTotal() {
		if (!confirm("lanjutkan pembayaran ?")){
	   	      return false;
	   	    }
		if(parseInt(document.getElementById("diBayar").value)>50000){
			setTimeout(function() {
				window.location.reload(1);
			},3000);
		}else{
			setTimeout(function() {
				window.location.reload(1);
			},2000);
		}
		
	}
	
	function hapus(i) {
		if (!confirm("yakin menghapus item  ?")){
	   	      return false;
	   	    }
		 var tabel = document.getElementById("pdTable");
		 ++i;
		 if(tabel.rows[(i)].cells[0].children[0].value.length>4){
			var a= document.getElementById("rowObat").value;
			--a;
			document.getElementById("rowObat").value=a;
		 }
		if(tabel.rows[(i)].cells[6].children[0].value.length>4){
			var a= document.getElementById("rowJasa").value;
			--a;
			document.getElementById("rowJasa").value=a;
		 }
		alert(tabel.rows[(i)].cells[9].children[0].value);
		document.getElementById("total_bayar").value=kurang(document.getElementById("total_bayar").value,tabel.rows[(i)].cells[9].children[0].value);
		document.getElementById("copy_bayar").value=document.getElementById("total_bayar").value;
		tabel.deleteRow(i);
	}
	/* function useAjaxObat() {
		 
		 var context = "${pageContext.request.contextPath}";
			$.ajax({
	        	  url: context+"/trans/ajaxObat.action",
	        	  type: 'POST',
	        	  data:"obat.kd_obat="+document.getElementById("kd_obat").value+"&obat.nama_obat="+document.getElementById("obat").value,
	              async: true,
	        	  success: function( res ) {
                       document.getElementById("transaksi_list").value="";
                       document.getElementById("transaksi_list").value=res;
                       test();
                       
                       
	        	},
	        	  error: function(arg){
	        		  alert("gagal");
	        	  }
	        	});
	} */
	function tambah(a,b){
		var c=parseInt(a)+parseInt(b);
		return c;
	}
	function kurang(a,b){
		var c=parseInt(a)-parseInt(b);
		return c;
	}
	function hitungTotal(sub) {
		document.getElementById("total_bayar").value=tambah(sub,document.getElementById("total_bayar").value);
	}
	
	 function addMorePassengerRow(pil) {
		 if (!confirm("tambahkan obat ini ke keranjang ?")){
	   	      return false;
	   	    }
		 var tabel = document.getElementById("pdTable");
		 var rowObat = document.getElementById("rowObat").value;
		 var rowCount = tabel.rows.length;
		 var kd = document.getElementById("kd_obat").value;
		 var jml=0;
		 var ada=false;
		 document.getElementById("total_bayar").value=tambah(document.getElementById("total_bayar").value,document.getElementById("sub_total").value);
		 
		 if(rowCount>1){
			 for(var i=1; i<rowCount; i++){
				 if(tabel.rows[i].cells[0].children[0].value==kd){
					 jml=tabel.rows[i].cells[4].children[0].value;
					 jml=tambah(jml,document.getElementById("jml_beli").value);
					 var jasahrg=0;
					 if(tabel.rows[i].cells[9].children[0].value.length>3){
						 jasahrg=parseInt(tabel.rows[i].cells[9].children[0].value);
					 }
					 tabel.rows[i].cells[10].children[0].value=(parseInt(tabel.rows[i].cells[2].children[0].value)*jml)+jasahrg;
					 tabel.rows[i].cells[4].children[0].value=jml;
					 ada=true;
					 break;
				 }
			 }
		 }
		 //disini cek dulu barang sudah ada atau belum , kalo sudah tambahkan jumlah dan sub total nya ,kalau belum buat row ba
		 if(pil=="obat" && ada==false){
			 if(rowObat==rowCount){
				 var row = tabel.insertRow(rowCount); //to insert blank row
				 var cell1 = row.insertCell(0);   //to insert first column
				 //===========================
				 var kd_obat = document.createElement("input");
				 kd_obat.type = "s:textfield";
				 kd_obat.readOnly="true";
				 kd_obat.name="transaksi_detail["+(rowCount-1)+"].obat.kd_obat";
				 kd_obat.value=document.getElementById("kd_obat").value;
				 kd_obat.title=kd_obat.value;
				 kd_obat.style.backgroundColor="yellow";
				 kd_obat.style="width:90px";
				 kd_obat.cssClass="form-control";
				 cell1.appendChild(kd_obat);
				 //============================
				 var cell2 = row.insertCell(1); //to insert second column
				 var nama_obat = document.createElement("input");
				 nama_obat.type = "s:textfield";
				 nama_obat.readOnly="true";
				 nama_obat.name="transaksi_detail["+(rowCount-1)+"].obat.nama_obat";
				 nama_obat.value=document.getElementById("obat").value;
				 nama_obat.title=nama_obat.value;
				 nama_obat.style="width:98px";
				 cell2.appendChild(nama_obat);
				 //=================================
				var cell3 = row.insertCell(2); // to insert 3rd column
				 var harga = document.createElement("input");
				 harga.type = "s:textfield";
				 harga.readOnly="true";
				 harga.name="transaksi_detail["+(rowCount-1)+"].obat.harga";
				 harga.value=parseInt(document.getElementById("harga_obat").value);
				 harga.style="width:78px";
				 harga.title=harga.value;
				 cell3.appendChild(harga);
				 //=====================================
				 var cell4 = row.insertCell(3);  //to insert 4th column
				 var jenis = document.createElement("input");
				 jenis.type = "s:textfield";
				 jenis.readOnly="true";
				 jenis.name="transaksi_detail["+(rowCount-1)+"].obat.jenis";
				 jenis.value=document.getElementById("jenis_obat").value;
				 jenis.style="width:80px";
				 jenis.title=jenis.value;
				 cell4.appendChild(jenis);
				 //=====================================
				 var cell5 = row.insertCell(4);  //to insert 4th column
				 var jmlBeli = document.createElement("input");
				 jmlBeli.type = "s:textfield";
				 jmlBeli.readOnly="true";
				 jmlBeli.name="transaksi_detail["+(rowCount-1)+"].jml_beli";
				 jmlBeli.value=document.getElementById("jml_beli").value;
				 jmlBeli.style="width:30px";
				 cell5.appendChild(jmlBeli);
				 //======================================
				 var cell6 = row.insertCell(5);  //to insert 4th column
				 var stock = document.createElement("input");
				 stock.type="s:textfield";
				 stock.name="transaksi_detail["+(rowCount-1)+"].dsc_obat";
				 stock.value=document.getElementById("dsc_obat").value;
				 stock.style="width:30px";
				 
				 cell6.appendChild(stock);
				 //======================================
					 
				var cell7 = row.insertCell(6);  //to insert 4th column
				 var stock = document.createElement("input");
				 stock.type="s:textfield";
				 stock.name="transaksi_detail["+(rowCount-1)+"].obat.stok";
				 stock.value=document.getElementById("stok").value;
				 stock.style="display: none; width: 0%;";
				 
				 cell7.appendChild(stock);
				 cell7.style="display: none; width: 0%;";
				 
				 //=======================================
				 var cell8 = row.insertCell(7);  //to insert 4th column
				 var kd_jasa = document.createElement("input");
				 kd_jasa.type = "s:textfield";
				 kd_jasa.readOnly="true";
				 kd_jasa.name="transaksi_detail["+(rowCount-1)+"].jasa.kd_jasa";
				 kd_jasa.value="";
				 kd_jasa.style="width:90px";
				 cell8.appendChild(kd_jasa);
				 //==========================================
				 var cell9 = row.insertCell(8);  //to insert 4th column
				 var nama_jasa = document.createElement("input");
				 nama_jasa.type = "s:textfield";
				 nama_jasa.readOnly="true";
				 nama_jasa.name="transaksi_detail["+(rowCount-1)+"].jasa.nama";
				 nama_jasa.value="";
				 nama_jasa.style="width:98px";
				 nama_jasa.title=nama_jasa.value;
				 cell9.appendChild(nama_jasa);
				 //=========================================
				 var cell10 = row.insertCell(9);  //to insert 4th column
				 var harga_jasa = document.createElement("input");
				 harga_jasa.type = "s:textfield";
				 harga_jasa.readOnly="true";
				 harga_jasa.name="transaksi_detail["+(rowCount-1)+"].jasa.harga";
				 harga_jasa.value="";
				 harga_jasa.style="width:78px";
				 harga_jasa.title=harga_jasa.value;
				 cell10.appendChild(harga_jasa);
				 //====================
				 var cell11 = row.insertCell(10);  //to insert 4th column
				 var sub_total = document.createElement("input");
				 sub_total.type = "s:textfield";
				 sub_total.readOnly="true";
				 sub_total.name="transaksi_detail["+(rowCount-1)+"].sub_total";
				 sub_total.value=parseInt(document.getElementById("sub_total").value);				 
				 sub_total.style="width:78px";
				 sub_total.title=sub_total.value;
				 cell11.appendChild(sub_total);
				 //=============================
				 var cell12 = row.insertCell(11);   // to insert 5th column
				 var rowRemoveCol = document.createElement("input");
				 rowRemoveCol.type = "button";
				 rowRemoveCol.value="hapus";
				 rowRemoveCol.setAttribute("onclick","hapus('"+(rowCount-1)+"')");
				 rowRemoveCol.name="reqlink[]";
				 cell12.appendChild(rowRemoveCol);
				 
				 var cel13 = row.insertCell(12);
				 var kd_dokter= document.createElement("input");
				 kd_dokter.type="s:textfield";
				 kd_dokter.name="transaksi_detail["+(rowCount-1)+"].jasa.dokter.kd_dokter";
				 kd_dokter.value="";
				 kd_dokter.style="display:none; width: 0%;";
				 cel13.appendChild(kd_dokter);
				 cel13.style="display:none; width: 0%;";
				 
				 var cel14 = row.insertCell(13);
				 var nama_dok = document.createElement("input");
				 nama_dok.type="s:textfield";
				 nama_dok.name="transaksi_detail["+(rowCount-1)+"].jasa.dokter.nama";
				 nama_dok.value="";
				 nama_dok.style="display:none; width: 0%;";
				 cel14.appendChild(nama_dok);
				 cel14.style="display:none; width: 0%;";
				 
			 }else{
				 tabel.rows[rowObat].cells[0].children[0].value=document.getElementById("kd_obat").value;
				 tabel.rows[rowObat].cells[1].children[0].value=document.getElementById("obat").value;
				 tabel.rows[rowObat].cells[2].children[0].value=parseInt(document.getElementById("harga_obat").value);
				 tabel.rows[rowObat].cells[3].children[0].value=document.getElementById("jenis_obat").value;
				 tabel.rows[rowObat].cells[4].children[0].value=document.getElementById("jml_beli").value;
				 tabel.rows[rowObat].cells[6].children[0].value=document.getElementById("stok").value;
				 var total=tambah(document.getElementById("harga_obat").value,document.getElementById("jml_beli").value);
				 total = tambah(total,tabel.rows[rowObat].cells[10].children[0].value);
				 tabel.rows[rowObat].cells[10].children[0].value=total;
				/*  document.getElementById("total_bayar").value=tambah( document.getElementById("total_bayar").value,total); */
			 }
			 rowObat++;
			 document.getElementById("rowObat").value=rowObat;
		}
		 document.getElementById("addObt").disabled="true";
		 clearObat();
		 return false;
	} 
		 
		function goSubmit(rowindex)
		{
		 document.ticketForm.rowindex.value=rowindex;
		 document.ticketForm.action="delete";
		 document.ticketForm.submit();
		 
		 }
	
	
	
	
	function hitungSub(str) {
		onlyNumber('jml_beli');
		 var id = document.getElementById("jml_beli").value.trim();
		 var stok = document.getElementById("stok").value.trim();
		 if(parseInt(id)<=parseInt(stok)){
			var harga = document.getElementById("harga_obat").value;
			if(harga!=null && harga !="" && id!="" && id!=0){
		 		document.getElementById("sub_total").value=(harga*id);
		 		document.getElementById("addObt").disabled="";
		 	}else{
		 		document.getElementById("sub_total").value="";	
		 		document.getElementById("addObt").disabled="true";
		 	}
		 }else{
			 document.getElementById("sub_total").value="";	
		 		document.getElementById("addObt").disabled="true";
		 }
			return false;
	}
	
	function setKdJasa() {
		var id = document.getElementById("jasa").value;
	       var data = id.split(':');
	       if(data.length>2){
		       document.getElementById("jasa").value="0";
		       document.getElementById("jasa").value=data[0];
		       document.getElementById("kd_jasa").value=data[1].trim();
		       document.getElementById("harga_jasa").value=data[2].trim();
		       document.getElementById("kd_dokter").value=data[4].trim();
		       document.getElementById("nama_dokter").value=data[3].trim();
		       document.getElementById("addJasa").disabled="";
	       }

	}
	
	
	
	
	
	///////////////////JJJJJJJASAAAAA
	 function addJasaRow(pil) {
		 if (!confirm("tambahkan jasa ini ke keranjang ?")){
	   	      return false;
	   	    }
		 var tabel = document.getElementById("pdTable");
		 var rowJasa = parseInt(document.getElementById("rowJasa").value);
		 var rowCount = tabel.rows.length;
		 //disini cek dulu barang sudah ada atau belum , kalo sudah tambahkan jumlah dan sub total nya ,kalau belum buat row ba
		 if(pil=="jasa"){
			 if(rowJasa==rowCount){
				 var row = tabel.insertRow(rowCount); //to insert blank row
				 var cell1 = row.insertCell(0);   //to insert first column
				 //===========================
				 var kd_obat = document.createElement("input");
				 kd_obat.type = "s:textfield";
				 kd_obat.readOnly="true";
				 kd_obat.name="transaksi_detail["+(rowCount-1)+"].obat.kd_obat";
				 kd_obat.value="";
				 kd_obat.title=kd_obat.value;
				 kd_obat.style.backgroundColor="yellow";
				 kd_obat.style="width:90px";
				 kd_obat.cssClass="form-control";
				 cell1.appendChild(kd_obat);
				 //============================
				 var cell2 = row.insertCell(1); //to insert second column
				 var nama_obat = document.createElement("input");
				 nama_obat.type = "s:textfield";
				 nama_obat.readOnly="true";
				 nama_obat.name="";
				 nama_obat.value="";
				 nama_obat.title=nama_obat.value;
				 nama_obat.style="width:98px";
				 cell2.appendChild(nama_obat);
				 //=================================
				var cell3 = row.insertCell(2); // to insert 3rd column
				 var harga = document.createElement("input");
				 harga.type = "s:textfield";
				 harga.readOnly="true";
				 harga.name="transaksi_detail["+(rowCount-1)+"].obat.harga";
				 harga.value="";
				 harga.style="width:78px";
				 harga.title=harga.value;
				 cell3.appendChild(harga);
				 //=====================================
				 var cell4 = row.insertCell(3);  //to insert 4th column
				 var jenis = document.createElement("input");
				 jenis.type = "s:textfield";
				 jenis.readOnly="true";
				 jenis.name="transaksi_detail["+(rowCount-1)+"].obat.jenis";
				 jenis.value="";
				 jenis.style="width:80px";
				 jenis.title=jenis.value;
				 cell4.appendChild(jenis);
				 //=====================================
				 var cell5 = row.insertCell(4);  //to insert 4th column
				 var jmlBeli = document.createElement("input");
				 jmlBeli.type = "s:textfield";
				 jmlBeli.readOnly="true";
				 jmlBeli.name="transaksi_detail["+(rowCount-1)+"].jml_beli";
				 jmlBeli.value="";
				 jmlBeli.style="width:30px";
				 cell5.appendChild(jmlBeli);
				 //====================================
					  var cell6 = row.insertCell(5);  //to insert 4th column
				 var stock = document.createElement("input");
				 stock.type="s:textfield";
				 stock.hidden="true";
				 stock.name="transaksi_detail["+(rowCount-1)+"].dsc_obat";
				 stock.value="";
				 stock.style="width:30px";
				 cell6.appendChild(stock);
				 //======================================
				  var cell7 = row.insertCell(6);  //to insert 4th column
				 var stock = document.createElement("input");
				 stock.type="s:textfield";
				 stock.hidden="true";
				 stock.name="transaksi_detail["+(rowCount-1)+"].obat.stok";
				 stock.value="";
				 stock.style="display:none; width: 0%;";
				 cell7.appendChild(stock);
				 cell7.style="display:none; width: 0%;";
				 //=======================================
				 var cell8 = row.insertCell(7);  //to insert 4th column
				 var kd_jasa = document.createElement("input");
				 kd_jasa.type = "s:textfield";
				 kd_jasa.readOnly="true";
				 kd_jasa.name="transaksi_detail["+(rowCount-1)+"].jasa.kd_jasa";
				 kd_jasa.value=document.getElementById("kd_jasa").value;
				 kd_jasa.style="width:90px";
				 cell8.appendChild(kd_jasa);
				 //==========================================
				 var cell9 = row.insertCell(8);  //to insert 4th column
				 var nama_jasa = document.createElement("input");
				 nama_jasa.type = "s:textfield";
				 nama_jasa.readOnly="true";
				 nama_jasa.name="transaksi_detail["+(rowCount-1)+"].jasa.nama";
				 nama_jasa.value=document.getElementById("jasa").value;
				 nama_jasa.style="width:98px";
				 nama_jasa.title=nama_jasa.value;
				 cell9.appendChild(nama_jasa);
				 //=========================================
				 var cell10 = row.insertCell(9);  //to insert 4th column
				 var harga_jasa = document.createElement("input");
				 harga_jasa.type = "s:textfield";
				 harga_jasa.readOnly="true";
				 harga_jasa.name="transaksi_detail["+(rowCount-1)+"].jasa.harga";
				 harga_jasa.value=parseInt(document.getElementById("harga_jasa").value);
				 harga_jasa.style="width:78px";
				 harga_jasa.title=harga_jasa.value;
				 cell10.appendChild(harga_jasa);
				 //====================
				 var cell11 = row.insertCell(10);  //to insert 4th column
				 var sub_total = document.createElement("input");
				 sub_total.type = "s:textfield";
				 sub_total.readOnly="true";
				 sub_total.name="transaksi_detail["+(rowCount-1)+"].sub_total";
				 
				 if(rowCount>1 && tabel.rows[rowJasa].cells[0].children[0].value.length>4 ){
				  data11 =tabel.rows[rowJasa].cells[10].children[0].value;
				  data11=tambah(data11,document.getElementById("harga_jasa").value);
				  alert("lebih dari 1");
				 }else{
					 data11=parseInt(document.getElementById("harga_jasa").value);
					 
				 }
				 document.getElementById("total_bayar").value=tambah(document.getElementById("total_bayar").value,data11);
				 sub_total.value=data11;		 
				 sub_total.style="width:78px";
				 sub_total.title=sub_total.value;
				 cell11.appendChild(sub_total);
				 //=============================
				var cell12 = row.insertCell(11);   // to insert 5th column
				 var rowRemoveCol = document.createElement("input");
				 rowRemoveCol.type = "button";
				 rowRemoveCol.value="hapus";
				 rowRemoveCol.setAttribute("onclick","hapus('"+(rowCount-1)+"')");
				 rowRemoveCol.name="reqlink[]";
				 cell12.appendChild(rowRemoveCol);
				 //======================
				 var cel13 = row.insertCell(12);
				 var kd_dokter= document.createElement("input");
				 kd_dokter.type="s:textfield";
				 kd_dokter.name="transaksi_detail["+(rowCount-1)+"].jasa.dokter.kd_dokter";
				 kd_dokter.value=document.getElementById("kd_dokter").value;
				 kd_dokter.style="display:none; width: 0%;";
				 cel13.appendChild(kd_dokter);
				 cel13.style="display:none; width: 0%;";
				 
				 var cel14 = row.insertCell(13);
				 var nama_dok = document.createElement("input");
				 nama_dok.type="s:textfield";
				 nama_dok.name="transaksi_detail["+(rowCount-1)+"].jasa.dokter.nama";
				 nama_dok.value=document.getElementById("nama_dokter").value;
				 nama_dok.style="display:none; width: 0%;";
				 cel14.appendChild(nama_dok);
				 cel14.style="display:none; width: 0%;";
				 alert(document.getElementById("nama_dokter").value);
				 
			 }else{
				 tabel.rows[rowJasa].cells[7].children[0].value=document.getElementById("kd_jasa").value;
				 tabel.rows[rowJasa].cells[8].children[0].value=document.getElementById("jasa").value;
				 tabel.rows[rowJasa].cells[12].children[0].value=document.getElementById("kd_dokter").value;
				 tabel.rows[rowJasa].cells[13].children[0].value=document.getElementById("nama_dokter").value;
				 tabel.rows[rowJasa].cells[9].children[0].value=parseInt(document.getElementById("harga_jasa").value);
				 var total = tambah(document.getElementById("harga_jasa").value,tabel.rows[rowJasa].cells[10].children[0].value);
				 tabel.rows[rowJasa].cells[10].children[0].value=total;
				 document.getElementById("total_bayar").value=tambah( document.getElementById("total_bayar").value,document.getElementById("harga_jasa").value);
			 }
			 rowJasa++;
			 document.getElementById("rowJasa").value=rowJasa;
		}
		 document.getElementById("addJasa").disabled="true";
		 clearJasa();
		 return false;
	}
	
	
	function setKdPas() {
		var id = document.getElementById("passien").value;
	       var data = id.split(':');
	       if(data.length>0){
		       document.getElementById("passien").value="";
		       document.getElementById("passien").value=data[0].trim();
		       document.getElementById("kd_passien").value=data[1].trim();
	
	       }
	}
	 function setKdObat() {
		 var id = document.getElementById("obat").value;
	       var data = id.split(':');
	       if(data.length>2){
		       document.getElementById("obat").value="";
		       document.getElementById("obat").value=data[0];
		       document.getElementById("kd_obat").value=data[1].trim();
		       document.getElementById("stok").value=data[3].trim();
		       document.getElementById("jenis_obat").value=data[2].trim();
		       document.getElementById("harga_obat").value=data[4].trim();
		       document.getElementById("stok_obat").value=data[3].trim();
		       
	       }
	}
	 function  clearObat() {
		 document.getElementById("obat").value="";
	       document.getElementById("kd_obat").value="";
	       document.getElementById("stok_obat").value="";
	       document.getElementById("jenis_obat").value="";
	       document.getElementById("harga_obat").value="";
	       document.getElementById("sub_total").value="";
	       document.getElementById("jml_beli").value="";
	       
	}
	 function clearJasa() {
		 document.getElementById("jasa").value="";
	       document.getElementById("kd_jasa").value="";
	       document.getElementById("harga_jasa").value="";
	       document.getElementById("nama_dokter").value="";
	       
	}
	 
	 $(document).ready(function(){
		 document.getElementById("total_bayar").value=0;
		 document.getElementById("addObt").disabled="true";
		 document.getElementById("bayar").disabled="true";
		 document.getElementById("addJasa").disabled="true";
		 document.getElementById("diBayar").value="";
   	  $(".del").click(function(){
   	    if (!confirm("Apakah anda yakin ingin hapus file tersebut")){
   	      return false;
   	    }
   	  });
   	  $("#listTableObat  > thead tr th").css({"text-align": "center", "vertical-align": "middle"});
   	  $("#listTableObat  > tbody tr td").css({"text-align": "center", "vertical-align": "middle"});
   	});
	</script>	
<!-- /.row -->
</body>
</html>
