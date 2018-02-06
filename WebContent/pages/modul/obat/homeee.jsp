/* function addMorePassengerRow(tableID,pil) {
		 var table = document.getElementById(tableID);
		 var rowCount = table.rows.length;
		 if(pil=="obat"){
			 var rowObat = document.getElementById("rowObat").value;
				 rowObat=rowCount-1;
			 if(rowObat==(rowCount-1)){
				 var row = table.insertRow(rowCount); //to insert blank row
				 
				 var cell1 = row.insertCell(0);   //to insert first column
				 //===========================
				 var kd_obat = document.createElement("input");
				 kd_obat.type = "s:textfield";
				 kd_obat.disabled="true";
				 kd_obat.name="transaksi.transaksi_detail["+(rowCount-1)+"].obat.kd_obat";
				 kd_obat.value=document.getElementById("kd_obat").value;
				 kd_obat.title=kd_obat.value;
				 cell1.appendChild(kd_obat);
				 //============================
				 var cell2 = row.insertCell(1); //to insert second column
				 var nama_obat = document.createElement("input");
				 nama_obat.type = "s:textfield";
				 nama_obat.disabled="true";
				 nama_obat.name="transaksi.transaksi_detail["+(rowCount-1)+"].obat.nama_obat";
				 nama_obat.value=document.getElementById("obat").value;
				 nama_obat.title=nama_obat.value;
				 cell2.appendChild(nama_obat);
				 //=================================
				var cell3 = row.insertCell(2); // to insert 3rd column
				 var harga = document.createElement("input");
				 harga.type = "s:textfield";
				 harga.disabled="true";
				 harga.name="transaksi.transaksi_detail["+(rowCount-1)+"].obat.harga";
				 harga.value=document.getElementById("harga_obat").value;
				 harga.style="width:80px";
				 harga.title=harga.value;
				 cell3.appendChild(harga);
				 //=====================================
				 var cell4 = row.insertCell(3);  //to insert 4th column
				 var jenis = document.createElement("input");
				 jenis.type = "s:textfield";
				 jenis.disabled="true";
				 jenis.name="transaksi.transaksi_detail["+(rowCount-1)+"].jenis";
				 jenis.value=document.getElementById("jenis_obat").value;
				 jenis.style="width:80px";
				 jenis.title=jenis.value;
				 cell4.appendChild(jenis);
				 //=====================================
				 var cell5 = row.insertCell(4);  //to insert 4th column
				 var jmlBeli = document.createElement("input");
				 jmlBeli.type = "s:textfield";
				 jmlBeli.disabled="true";
				 jmlBeli.name="transaksi.transaksi_detail["+(rowCount-1)+"].jml_beli";
				 jmlBeli.value=document.getElementById("jml_beli").value;
				 jmlBeli.style="width:30px";
				 cell5.appendChild(jmlBeli);
				 //======================================
				 var cell6 = row.insertCell(5);  //to insert 4th column
				 var sparated = document.createElement("input");
				 sparated.disabled="true";
				 sparated.style="width:20px";
				 cell6.appendChild(sparated);
				 //=======================================
				 var cell7 = row.insertCell(6);  //to insert 4th column
				 var kd_jasa = document.createElement("input");
				 kd_jasa.type = "s:textfield";
				 kd_jasa.disabled="true";
				 kd_jasa.name="transaksi.transaksi_detail["+(rowCount-1)+"].jasa.kd_jasa";
				 kd_jasa.value="z";
				 cell7.appendChild(kd_jasa);
				 //==========================================
				 var cell8 = row.insertCell(7);  //to insert 4th column
				 var nama_jasa = document.createElement("input");
				 nama_jasa.type = "s:textfield";
				 nama_jasa.disabled="true";
				 nama_jasa.name="transaksi.transaksi_detail["+(rowCount-1)+"].jasa.nama";
				 nama_jasa.value="x";
				 nama_jasa.title=nama_jasa.value;
				 cell8.appendChild(nama_jasa);
				 //=========================================
				 var cell9 = row.insertCell(8);  //to insert 4th column
				 var harga_jasa = document.createElement("input");
				 harga_jasa.type = "s:textfield";
				 harga_jasa.disabled="true";
				 harga_jasa.name="transaksi.transaksi_detail["+(rowCount-1)+"].jasa.harga";
				 harga_jasa.value="1";
				 harga_jasa.style="width:80px";
				 harga_jasa.title=harga_jasa.value;
				 cell9.appendChild(harga_jasa);
				 //====================
				 var cell10 = row.insertCell(9);  //to insert 4th column
				 var sub_total = document.createElement("input");
				 sub_total.type = "s:textfield";
				 sub_total.disabled="true";
				 sub_total.name="transaksi.transaksi_detail["+(rowCount-1)+"].sub_total";
				 sub_total.value=document.getElementById("sub_total").value;
				 sub_total.style="width:80px";
				 sub_total.title=sub_total.value;
				 cell10.appendChild(sub_total);
				 //=============================
				 var cell11 = row.insertCell(10);   // to insert 5th column
				 var rowRemoveCol = document.createElement("a");
				 var text = document.createTextNode("Delete");
				 rowRemoveCol.appendChild(text);
				 rowRemoveCol.setAttribute("href","javascript:goSubmit("+(rowCount-1)+")");
				 rowRemoveCol.name="reqlink[]";
				 cell11.appendChild(rowRemoveCol);
			 }else{
				 var cell1 = row.insertCell(0);   //to insert first column
				 var snoCol = document.createElement("input");
				 snoCol.type = "text";
				 snoCol.name="ticket.passengerDetail["+(rowCount-1)+"].sno";
				 snoCol.value=rowCount;
				 cell1.appendChild(snoCol);
			 }
			 var gg = document.getElementById("transaksi_lis");
			 
			 alert()
			 
			 
		}
		 
		 return false;
		 
		} */
<%-- <TABLE id="pdTable">
				<TR>
				<TD>kode Obat</TD>
					<TD>Nama Obat</TD>
					<TD>Harga</TD>
					<TD>Jenis</TD>
					<TD>Jml</TD>
					<TD>---</TD>
					<TD>kode jasa</TD>
					<TD>Nama Jasa</TD>
					<TD>harga</TD>
					<TD>Sub-Total</TD>
					<TD>Aksi</TD>
				</TR>

				<s:iterator value="transaksi.transaksi_detail" status="cnt"
					var="LTD" id="listTransaksi2">
					
					
					<tr>
						<TD><s:textfield name="transaksi.transaksi_detail[%{#cnt.count-1}].obat.kd_obat" disabled="true" /></TD>
						<TD><s:textfield
								name="transaksi.transaksi_detail[%{#cnt.count-1}].obat.nama_obat" disabled="true"/></TD>
						<TD><s:textfield
								name="transaksi.transaksi_detail[%{#cnt.count-1}].obat.harga" disabled="true"/></TD>
						<TD><s:textfield
								name="transaksi.transaksi_detail[%{#cnt.count-1}].obat.jenis" disabled="true"/></TD>
						<TD><s:textfield
								name="transaksi.transaksi_detail[%{#cnt.count-1}].jml_beli" disabled="true"/></TD>
						<TD>---</TD>
						<TD><s:textfield name="transaksi.transaksi_detail[%{#cnt.count-1}].jasa.kd_jasa" disabled="true" /></TD>
						<TD><s:textfield
								name="transaksi.transaksi_detail[%{#cnt.count-1}].jasa.nama" disabled="true"/></TD>
						<TD><s:textfield
								name="transaksi.transaksi_detail[%{#cnt.count-1}].jasa.harga" disabled="true"/></TD>
						<TD><s:textfield
								name="transaksi.transaksi_detail[%{#cnt.count-1}].sub_total" disabled="true" /></TD>
						<td><a
							href="javascript:goSubmit(<s:property value='#cnt.count-1'/>)">delete</a></td>
							
					</tr>
				</s:iterator>

			</TABLE> --%>