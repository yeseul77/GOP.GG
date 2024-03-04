$("document").ready(function(){
//        <div class="best-winner">승률</div>
//        <div class="best-kda">KDA</div>
//        <div class="best-time">플레잉 타임</div>
	console.log("ajax start");
	$.ajax({
		method:'get',
		url:'/rank/winlist',
	}).done(function(result){
		const temp=document.createElement("div")
		temp.classList.add("listArray")
		let i=1;
		
		$.each(result, function(index, winlist){
			console.log(winlist)
			winlist.championName === 'FiddleSticks' ? 'Fiddlesticks' : winlist.championName;
			const html=document.createElement("div")
			const medal = `<span class="material-symbols-outlined">workspace_premium</span>`
			const num = i>3 ? i : medal;  	 				
			html.classList.add("rankBox")
			html.innerHTML=`
			<div class="rankNum">${num}</div>
			<div class="rankPhoto"><img src='https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${winlist.championName}.png' alt='${winlist.championName}'></div>
			<div class="rankInfo"><div class="summonerName">${winlist.summonerName}</div><div class="championName">${winlist.championName}</div></div></div>
			<div class="rankDate">${winlist.ranklist}&nbsp;%&nbsp;&nbsp;</div>
			`
			temp.append(html)
			i++
		})
		$('#best-winner').append(temp)
	})
	$.ajax({
		method:'get',
		url:'/rank/gamelist',
	}).done(function(result){
		const temp=document.createElement("div")
		temp.classList.add("listArray")
		let i=1;
		$.each(result, function(index, winlist){
			console.log(winlist)
			winlist.championName === 'FiddleSticks' ? 'Fiddlesticks' : winlist.championName;
			const playTime = (winlist.ranklist / 3600).toFixed(1);
			const html=document.createElement("div")
			const medal = `<span class="material-symbols-outlined">workspace_premium</span>`
			const num = i>3 ? i : medal;  
			html.classList.add("rankBox")
			html.innerHTML=`
			<div class="rankNum">${num}</div>
			<div class="rankPhoto"><img src='https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${winlist.championName}.png' alt='${winlist.championName}'></div>
			<div class="rankInfo"><div class="summonerName">${winlist.summonerName}</div><div class="championName">${winlist.championName}</div></div></div>
			<div class="rankDate">${playTime}&nbsp;시간&nbsp;&nbsp;</div>
			`
			temp.append(html)
			i++
		})
		console.log(temp)
		$('#best-time').append(temp)
	})
	$.ajax({
		method:'get',
		url:'/rank/kdalist',
	}).done(function(result){
		const temp=document.createElement("div")
		temp.classList.add("listArray")
		let i=1
		$.each(result, function(index, winlist){
			console.log(winlist)
			winlist.championName === 'FiddleSticks' ? 'Fiddlesticks' : winlist.championName;
			const html=document.createElement("div")
			const medal = `<span class="material-symbols-outlined">workspace_premium</span>`
			const num = i>3 ? i : medal;  
			html.classList.add("rankBox")
			html.innerHTML=`
			<div class="rankNum">${num}</div>
			<div class="rankPhoto"><img src='https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${winlist.championName}.png' alt='${winlist.championName}'></div>
			<div class="rankInfo"><div class="summonerName">${winlist.summonerName}</div><div class="championName">${winlist.championName}</div></div></div>
			<div class="rankDate">${winlist.ranklist}&nbsp;점&nbsp;&nbsp;</div>
			`
			temp.append(html)
			i++
		})
		$('#best-kda').append(temp)
	})
})