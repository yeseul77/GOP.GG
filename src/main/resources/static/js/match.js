function search(position){
	console.log("ajax start")
	let championName=$("#championName").val()
	let teamPosition=$("#teamPosition").val()
//	let myteamPosition=$("#myteamPosition").val()
	console.log(championName)
	console.log(teamPosition)
	console.log(myteamPosition)
	var data={"championName":championName,
			  "teamPosition":teamPosition,
			  "myteamPosition":position}
	$.ajax({
		method:'get',
		url:'getData',
		data:data
	}).done(function(result){
		let i =0;
		console.log(result)
		const temp=document.createElement("div")
		$.each(result, function(index, match){
			
			const championName = match.championName === 'FiddleSticks' ? 'Fiddlesticks' : match.championName;
			i++
			console.log(match)
			const html=document.createElement("div")
			html.innerHTML=`<a> <img src='https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${championName}.png' >${i}.${match.championName} 승률:${match.win_ratio} 전체판수:${match.total_count}</a>`
			temp.append(html)
		})
		$('#result').empty().append(temp)
	})
}