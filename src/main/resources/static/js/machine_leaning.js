    function champExp(championName,champExp){
		var data={ championName: championName, champExp: champExp};
 	   	console.log(data)
		 $.ajax({
	     type: "GET",
	     url: "DamageCheck",
	     data: data,
	     async:false,
	     success: function (data) {
			 console.log(data);
			 var insert='<tr class="win-body">'+data+'</tr>'
			 $("#gameInfoTable").append(insert);
	    	 return data;
	     },
	     error: function (xhr, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
	     }
	})
}