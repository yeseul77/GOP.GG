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
		$.each(result, function(index, winlist){
			console.log(winlist)
			const html=document.createElement("div")
			html.innerHTML=`<div id="winlist">${winlist.summonerName}(${winlist.championName}): ${winlist.ranklist}%`
			temp.append(html)
		})
		$('#best-winner').append(temp)
	})
	$.ajax({
		method:'get',
		url:'/rank/gamelist',
	}).done(function(result){
		const temp=document.createElement("div")
		temp.classList.add("listArray")
		$.each(result, function(index, winlist){
			console.log(winlist)
			const html=document.createElement("div")
			html.innerHTML=`<div id="winlist">${winlist.summonerName}(${winlist.championName}): ${winlist.ranklist}`
			temp.append(html)
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
		$.each(result, function(index, winlist){
			console.log(winlist)
			const html=document.createElement("div")
			html.innerHTML=`<div id="winlist">${winlist.summonerName}(${winlist.championName}): ${winlist.ranklist}`
			temp.append(html)
		})
		$('#best-kda').append(temp)
	})
})