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
			<td class="body-text"><a href="/champion/detail?championName=${match.championName}" class="champ"><div class="champ-box"><img src='https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${match.championName}.png'></div><strong>${match.championNameKr}</strong></a></td>
			<td class="body-text chart"><div class="chart-mother"><div class="chart-child" style="width:0"></div></div><strong class="numStrong">0<strong></td>
			<td class="body-text sort">${match.total_count}</td>`
			temp.append(html)
			
			let t = 0;
            let totalMinwon = match.win_ratio;
            const animationWidth = html.querySelector(".chart-child");
            const animationNum = html.querySelector(".numStrong");
            
            const barAnimation = setInterval(() => {
              animationWidth.style.width =  t + "%";
              animationNum.textContent =  t + " %";
              t++ >= totalMinwon && clearInterval(barAnimation)
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


// 위쪽 버튼 클릭 시 호출되는 함수
function sortAscending() {
    // 게임 판수를 기준으로 정렬
    const sortedRows = Array.from(document.querySelectorAll('.result-body')).sort((a, b) => {
        const aGameCount = parseInt(a.querySelector('.body-text.sort').textContent);
        const bGameCount = parseInt(b.querySelector('.body-text.sort').textContent);
        return aGameCount - bGameCount;
    });

    // 정렬된 순서대로 테이블 내용 업데이트
    const tbody = document.querySelector('tbody');
    sortedRows.forEach(row => {
        tbody.append(row);
    });
}

// 아래쪽 버튼 클릭 시 호출되는 함수
function sortDescending() {
    // 게임 판수를 기준으로 정렬
    const sortedRows = Array.from(document.querySelectorAll('.result-body')).sort((a, b) => {
        const aGameCount = parseInt(a.querySelector('.body-text.sort').textContent);
        const bGameCount = parseInt(b.querySelector('.body-text.sort').textContent);
        return bGameCount - aGameCount;
    });

    // 정렬된 순서대로 테이블 내용 업데이트
    const tbody = document.querySelector('tbody');
    sortedRows.forEach(row => {
        tbody.append(row);
    });
}

// 위쪽 버튼 클릭 시 정렬 함수 호출
document.getElementById('ascendingButton').addEventListener('click', sortAscending);

// 아래쪽 버튼 클릭 시 정렬 함수 호출
document.getElementById('descendingButton').addEventListener('click', sortDescending);



// // %있는 항목 오름차순 내림차순 버튼
function sortAscending1() {
    // 게임 판수를 기준으로 정렬
    const sortedRows = Array.from(document.querySelectorAll('.result-body')).sort((a, b) => {
        const aGameCount = parseInt(a.querySelector('.body-text.chart .numStrong').textContent.replace('%',''));
        const bGameCount = parseInt(b.querySelector('.body-text.chart .numStrong').textContent.replace('%',''));
        return aGameCount - bGameCount;
    });

    // 정렬된 순서대로 테이블 내용 업데이트
    const tbody = document.querySelector('tbody');
    sortedRows.forEach(row => {
        tbody.append(row);
    });
}

// %있는 항목 오름차순 내림차순 버튼
function sortDescending1() {
    // 게임 판수를 기준으로 정렬
    const sortedRows = Array.from(document.querySelectorAll('.result-body')).sort((a, b) => {
        const aGameCount = parseInt(a.querySelector('.body-text.chart .numStrong').textContent.replace('%',''));
        const bGameCount = parseInt(b.querySelector('.body-text.chart .numStrong').textContent.replace('%',''));
        return bGameCount - aGameCount;
    });

    // 정렬된 순서대로 테이블 내용 업데이트
    const tbody = document.querySelector('tbody');
    sortedRows.forEach(row => {
        tbody.append(row);
    });
}

// 위쪽 버튼 클릭 시 정렬 함수 호출
document.getElementById('ascendingButton1').addEventListener('click', sortAscending1);

// 아래쪽 버튼 클릭 시 정렬 함수 호출
document.getElementById('descendingButton1').addEventListener('click', sortDescending1);

