$(document).ready(function() {
	let championNamesJson = $('#championNames').val();
	let championNames = JSON.parse(championNamesJson);
	console.log("kr : ", championNames);

	const championList = $('aside');
	const nav = document.createElement('nav');
	nav.classList.add('championNav')
	const ul = document.createElement('ul');
	ul.classList.add('champList');

	championList.append(nav);
	nav.append(ul);

	function showChampions() {
		for (let i = 0; i < championNames.length; i++) {
			const champion = championNames[i];
			const li = document.createElement('li');
			li.classList.add('champion-item');

			const a = document.createElement('a');
			a.className = 'img-link'
			a.href = '/aram/detail?championName=' + champion.champion_name;
			a.style.width = '50px';

			const div = document.createElement('div');
			div.className = 'champion-img';

			const img = document.createElement('img');
			img.src = `https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${champion.champion_name}.png`;
			img.alt = champion.champion_name;

			div.appendChild(img);
			a.appendChild(div);

			const span = document.createElement('span');
			span.textContent = champion.champion_name_kr;
			li.appendChild(a);
			li.appendChild(span); // span을 li에 추가

			ul.appendChild(li);

			
		}
	}

	showChampions()

	const searchChampion = document.getElementById('searchChampion')

	searchChampion.addEventListener('keydown', function(event) {
		if (event.key === 'Enter') {
			let search = $(this).val();
			console.log(search);

			$.ajax({
				type: 'GET',
				url: "/aram/searchChampionList",
				data: { search: search },
				success: function(data) {
					console.log("data : ", data)

					// 기존에 생성된 ul 요소가 있다면 삭제
					while (ul.firstChild) {
						ul.removeChild(ul.firstChild);
					}

					// 받은 데이터를 이용하여 목록을 구성
					data.forEach(function(champion) {
						console.log("name : ", champion.champion_name_kr);
						const listItem = document.createElement('li');

						// 링크 생성
						const link = document.createElement('a');
						link.href = '/aram/detail?championName=' + champion.champion_name; // 챔피언 페이지로 이동할 URL 설정

						// 챔피언 이미지 생성 및 설정
						const img = document.createElement('img');
						img.src = `https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${champion.champion_name}.png`;
						img.alt = champion.champion_name;
						link.appendChild(img);

						// 챔피언 이름 추가
						const championName = document.createElement('span');
						championName.textContent = champion.champion_name_kr;

						// 이미지를 리스트 아이템에 추가
						listItem.appendChild(link);
						listItem.appendChild(championName); // 챔피언 이름을 이미지 아래에 배치

						ul.appendChild(listItem);
					});

					// nav 요소에 ul 요소를 추가
					nav.appendChild(ul);
				},
				error: function(error) {
					console.logg(error)
				}
			})
		}
	});


	const mainElement = document.querySelector('main');
	const divElement = mainElement.querySelector('div');
	divElement.classList.add('mainBody');
	const table = document.createElement('table')
	table.classList.add('championInfo')
	const colgroup = document.createElement('colgroup')
	const thead = document.createElement('thead')
	const tbody = document.createElement('tbody')
	const theadTr = document.createElement('tr')

	const widths = ['15%', '25%', '15%', '15%', '15%', '15%'];
	const thText = ['티 어', '챔피언', '승 률', '픽 률', 'dpm','펜타킬']
	for (let i = 0; i < 6; i++) {
		const col = document.createElement('col')
		col.style.width = widths[i]
		colgroup.appendChild(col)

		const th = document.createElement('th')
		th.setAttribute('scope', 'col')
		if (i === 0 || i === 1) {
			th.setAttribute('ailgn', 'left')
		}
		th.innerText = thText[i]

		theadTr.appendChild(th)
	}
	thead.appendChild(theadTr)

	table.appendChild(colgroup)
	table.appendChild(thead)
	
	getChampionInfo()

	function getChampionInfo() {
		console.log('start')
		$.ajax({
			type: 'Post',
			url: '/aram/championallinfo',
			data: {},
			success: function(response) {
				// AJAX 요청이 성공했을 때의 처리
				console.log("res : ", response);
				
				response.sort((a, b) => {
	                let avgA = (a.win_rate + a.pickRate) / 2;
	                let avgB = (b.win_rate + b.pickRate) / 2;
	                return avgB - avgA; // 내림차순 정렬
            	});

				response.sort((a, b) => b.avgTierNum - a.avgTierNum);

				const tdCName = ['tier', 'champions', 'win_rate', 'pickRate', 'dpm','pentakills']
				for (let i = 0; i < response.length; i++) {
					let championInfo = response[i]
					const tbodyTr = document.createElement('tr')

					for (let j = 0; j < tdCName.length; j++) {
						const td = document.createElement('td');
						td.className = tdCName[j];

						if (tdCName[j] === 'champions') {
							const championAnchor = document.createElement('a');
							championAnchor.href = '/aram/detail?championName=' + championInfo.championName;
							const chamBox = document.createElement('div')
							chamBox.classList.add('chamBox')
							const championImage = document.createElement('img');
							const championName = championInfo.championName === 'FiddleSticks' ? 'Fiddlesticks' : championInfo.championName; // 피들스틱 S -> s변경
							championImage.src = `https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${championName}.png`; // 이미지 URL을 추가하세요
							championImage.alt = championName; // 이미지에 대한 대체 텍스트
							championImage.style.width = '40px'; // 이미지 크기 설정
							const span = document.createElement('span')
							span.classList.add('chamName')
							chamBox.append(championImage)

							for (let i = 0; i < championNames.length; i++) {
								if (championNames[i].champion_name === championName) {
									span.textContent = championNames[i].champion_name_kr;
									break;
								}
							}

							championAnchor.appendChild(chamBox);
							championAnchor.appendChild(span)
							td.appendChild(championAnchor);
						} else if (tdCName[j] === 'tier') {
							let avgNum = []
							let avg = (championInfo.win_rate + championInfo.pickRate) / 2
							avg = avg.toFixed(1)
							avgNum.push(avg)
							let tier = '';
							
							 if (avg >= 30) {
						        tier = 'OP';
						    } else if (avg >= 28 && avg < 30) {
						        tier = '1티어';
						    } else if (avg >= 26 && avg < 28) {
						        tier = '2티어';
						    } else if (avg >= 24 && avg < 26) {
						        tier = '3티어';
						    } else {
						        tier = '4티어';
						    }

						    td.textContent = tier;;
						}else if (tdCName[j] === 'win_rate') {
							td.textContent = championInfo.win_rate + " %";
						} else if (tdCName[j] === 'pickRate') {
							td.textContent = championInfo.pickRate + " %";;
						} else if (tdCName[j] === 'dpm') {
							td.textContent = championInfo.dpm  ;
						} else if (tdCName[j] === 'pentakills') {
							td.textContent = championInfo.pentakills  ;
						} 
						tbodyTr.appendChild(td);
					}
					tbody.appendChild(tbodyTr);
				}
			},
			error: function(error) {
				// AJAX 요청이 실패했을 때의 처리
				console.log("Error: ", error);
			}
		})
	}




	table.appendChild(tbody)

	
	function clickDelete() {
		const tbody = document.querySelector('tbody');
    	tbody.innerHTML = ''; // 테이블의 tbody 내용을 삭제
	}

	divElement.appendChild(table)
	
});


// (티어) 랭킹 오름차순 내림차순 

function extractNumber(text) {
	return text.match(/\d+/) ? parseInt(text.match(/\d+/)[0]) : '0';
}

function sortAscending() {

	// 게임 판수를 기준으로 정렬 
	const sortedRows = Array.from(document.querySelectorAll('.championInfo tbody tr')).sort((a, b) => {
		const aGameCount = extractNumber(a.querySelector('.tier').textContent);
		const bGameCount = extractNumber(b.querySelector('.tier').textContent);
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
	const sortedRows = Array.from(document.querySelectorAll('.championInfo tbody tr')).sort((a, b) => {
		const aGameCount = extractNumber(a.querySelector('.tier').textContent);
		const bGameCount = extractNumber(b.querySelector('.tier').textContent);
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



// // (승률) 오름차순 내림차순 버튼
function sortAscending1() {
	// 게임 판수를 기준으로 정렬
	const sortedRows = Array.from(document.querySelectorAll('.championInfo tbody tr')).sort((a, b) => {
		const aGameCount = Number(a.querySelector('.win_rate').textContent.replace('%', ''));
		const bGameCount = Number(b.querySelector('.win_rate').textContent.replace('%', ''));
		return aGameCount - bGameCount;
	});

	// 정렬된 순서대로 테이블 내용 업데이트
	const tbody = document.querySelector('tbody');
	sortedRows.forEach(row => {
		tbody.appendChild(row);
	});
};

// %있는 항목 오름차순 내림차순 버튼
function sortDescending1() {
	// 게임 판수를 기준으로 정렬
	const sortedRows = Array.from(document.querySelectorAll('.championInfo tbody tr')).sort((a, b) => {
		const aGameCount = Number(a.querySelector('.win_rate').textContent.replace('%', ''));
		const bGameCount = Number(b.querySelector('.win_rate').textContent.replace('%', ''));
		return bGameCount - aGameCount;
	});

	// 정렬된 순서대로 테이블 내용 업데이트
	const tbody = document.querySelector('tbody');
	sortedRows.forEach(row => {
		tbody.appendChild(row);
	});
};

// 위쪽 버튼 클릭 시 정렬 함수 호출
document.getElementById('ascendingButton1').addEventListener('click', sortAscending1);

// 아래쪽 버튼 클릭 시 정렬 함수 호출
document.getElementById('descendingButton1').addEventListener('click', sortDescending1);


// // (픽률)  오름차순 내림차순 버튼
function sortAscending2() {
	// 게임 판수를 기준으로 정렬
	const sortedRows = Array.from(document.querySelectorAll('.championInfo tbody tr')).sort((a, b) => {
		const aGameCount = Number(a.querySelector('.pickRate').textContent.replace('%', ''));
		const bGameCount = Number(b.querySelector('.pickRate').textContent.replace('%', ''));
		return aGameCount - bGameCount;
	});

	// 정렬된 순서대로 테이블 내용 업데이트
	const tbody = document.querySelector('tbody');
	sortedRows.forEach(row => {
		tbody.appendChild(row);
	});
};

// %있는 항목 오름차순 내림차순 버튼
function sortDescending2() {
	// 게임 판수를 기준으로 정렬
	const sortedRows = Array.from(document.querySelectorAll('.championInfo tbody tr')).sort((a, b) => {
		const aGameCount = Number(a.querySelector('.pickRate').textContent.replace('%', ''));
		const bGameCount = Number(b.querySelector('.pickRate').textContent.replace('%', ''));
		return bGameCount - aGameCount;
	});

	// 정렬된 순서대로 테이블 내용 업데이트
	const tbody = document.querySelector('tbody');
	sortedRows.forEach(row => {
		tbody.appendChild(row);
	});
};

// 위쪽 버튼 클릭 시 정렬 함수 호출
document.getElementById('ascendingButton2').addEventListener('click', sortAscending2);

// 아래쪽 버튼 클릭 시 정렬 함수 호출
document.getElementById('descendingButton2').addEventListener('click', sortDescending2);


// // (DPM)  오름차순 내림차순 버튼
function sortAscending3() {
	// 게임 판수를 기준으로 정렬
	const sortedRows = Array.from(document.querySelectorAll('.championInfo tbody tr')).sort((a, b) => {
		const aGameCount = Number(a.querySelector('.dpm').textContent);
		const bGameCount = Number(b.querySelector('.dpm').textContent);
		return aGameCount - bGameCount;
	});

	// 정렬된 순서대로 테이블 내용 업데이트
	const tbody = document.querySelector('tbody');
	sortedRows.forEach(row => {
		tbody.appendChild(row);
	});
};

// %있는 항목 오름차순 내림차순 버튼
function sortDescending3() {
	// 게임 판수를 기준으로 정렬
	const sortedRows = Array.from(document.querySelectorAll('.championInfo tbody tr')).sort((a, b) => {
		const aGameCount = Number(a.querySelector('.dpm').textContent);
		const bGameCount = Number(b.querySelector('.dpm').textContent);
		return bGameCount - aGameCount;
	});

	// 정렬된 순서대로 테이블 내용 업데이트
	const tbody = document.querySelector('tbody');
	sortedRows.forEach(row => {
		tbody.appendChild(row);
	});
};

// 위쪽 버튼 클릭 시 정렬 함수 호출
document.getElementById('ascendingButton3').addEventListener('click', sortAscending3);

// 아래쪽 버튼 클릭 시 정렬 함수 호출
document.getElementById('descendingButton3').addEventListener('click', sortDescending3);


// // (펜타킬)  오름차순 내림차순 버튼
function sortAscending4() {
	// 게임 판수를 기준으로 정렬
	const sortedRows = Array.from(document.querySelectorAll('.championInfo tbody tr')).sort((a, b) => {
		const aGameCount = Number(a.querySelector('.pentakills').textContent);
		const bGameCount = Number(b.querySelector('.pentakills').textContent);
		return aGameCount - bGameCount;
	});

	// 정렬된 순서대로 테이블 내용 업데이트
	const tbody = document.querySelector('tbody');
	sortedRows.forEach(row => {
		tbody.appendChild(row);
	});
};

// %있는 항목 오름차순 내림차순 버튼
function sortDescending4() {
	// 게임 판수를 기준으로 정렬
	const sortedRows = Array.from(document.querySelectorAll('.championInfo tbody tr')).sort((a, b) => {
		const aGameCount = Number(a.querySelector('.pentakills').textContent);
		const bGameCount = Number(b.querySelector('.pentakills').textContent);
		return bGameCount - aGameCount;
	});

	// 정렬된 순서대로 테이블 내용 업데이트
	const tbody = document.querySelector('tbody');
	sortedRows.forEach(row => {
		tbody.appendChild(row);
	});
};

// 위쪽 버튼 클릭 시 정렬 함수 호출
document.getElementById('ascendingButton4').addEventListener('click', sortAscending4);

// 아래쪽 버튼 클릭 시 정렬 함수 호출
document.getElementById('descendingButton4').addEventListener('click', sortDescending4);

