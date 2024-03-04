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
			const html=document.createElement("div")
			html.innerHTML=`<div id="winlist">${i}${winlist.summonerName}(${winlist.championName}): ${winlist.ranklist}%`
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
			const html=document.createElement("div")
			html.innerHTML=`<div id="winlist">${i}${winlist.summonerName}(${winlist.championName}): ${winlist.ranklist}`
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
			const html=document.createElement("div")
			html.innerHTML=`<div id="winlist">${i}${winlist.summonerName}(${winlist.championName}): ${winlist.ranklist}`
			temp.append(html)
			i++
		})
		$('#best-kda').append(temp)
	})
})