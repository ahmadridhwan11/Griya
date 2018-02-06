$("#form-contact").ajaxForm({
	beforeSerialize: function(form, options) { 
		$("#contactsubmit").button("loading");
	},
	beforeSubmit: function(){
		return $("#form-contact").valid();
	},
	success: function(data){
		prcHandler(data);
	},
	dataType: "json"
});

function prcHandler(data){
	
	$("#postresult").empty();
	$("#postresult").append(data['message']);
	if(data['status'] == 1){
		$("#form-contact")[0].reset();
		$("#contactsubmit").button("reset");
		$("#postresult").removeClass("text-danger");
		$("#postresult").addClass("text-success");
		$("#contactModal").modal();
	}else{
		$("#contactsubmit").button("reset");
		$("#postresult").removeClass("text-success");
		$("#postresult").addClass("text-danger");
		$("#contactModal").modal();
	}
	//$("#act-progress").addClass("hidden");
	
}