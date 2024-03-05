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
		const temp = document.createElement("tbody")
			
		$.each(result, function(index, match){
			
			const championName = match.championName === 'FiddleSticks' ? 'Fiddlesticks' : match.championName;
			i++
			console.log(match)
			const html=document.createElement("tr")
			html.classList.add("result-body")
			html.innerHTML=`<td class="body-text number">${i}</td>
			<td class="body-text"><a href="" class="champ"><div class="champ-box"><img src='https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${championName}.png'></div><strong>${championName}</strong></a></td>
			<td class="body-text chart"><div class="chart-mother"><div class="chart-child" style="width:0"></div></div><strong class="numStrong">0<strong></td>
			<td class="body-text">${match.total_count}</td>`
			temp.append(html)
			
			let t = 0;
            let totalMinwon = match.win_ratio;
            const animationWidth = html.querySelector(".chart-child");
            const animationNum = html.querySelector(".numStrong");
            
            const barAnimation = setInterval(() => {
              animationWidth.style.width =  t + "%";
              t++ >= totalMinwon && clearInterval(barAnimation)
            }, 40);
            
            const numAnimation = setInterval(() => {
              animationNum.textContent =  t + " %";
              t++ >= totalMinwon && clearInterval(numAnimation)
            }, 40);		
		})
		
		
		$('#result').next().remove();
        $('#result').after(temp);
        
	})
}


//시너지 챔피언 포지션 검색 버튼
function handleClick(event) {
  const target = event.target.closest("button[id='myteamPosition']");
  if (target) {
    target.click();
  }
}

const positionBtns = document.querySelectorAll("#myteamPosition");
positionBtns.forEach(positionBtn => {
	positionBtn.addEventListener("click", function(event){
		
		
		positionBtns.forEach(btn => {
			btn.classList.remove("show")
		})
		event.target.classList.add("show")
	}) 
})





