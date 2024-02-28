function search(position){
	console.log("ajax start")
	let championName=$("#championName").val()
	let teamPosition=$("#teamPosition").val()
//	let myteamPosition=$("#myteamPosition").val()
	console.log(championName)
	console.log(teamPosition)
	console.log(myteamPosition)
	var data={"champtionName":championName,
			  "teamPosition":teamPosition,
			  "myteamPosition":position}
	$.ajax({
		method:'get',
		url:'getData',
		data:data
	}).done(function(result){
		console.log(result)
		const temp=document.createElement("div")
		$.each(result, function(index, match){
			console.log(match)
			const html=document.createElement("div")
			html.innerHTML=`<a>${match}</a>`
			temp.append(html)
		})
		$('#result').empty().append(temp)
	})
}