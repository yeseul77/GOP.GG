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
			a.href = '/champion/detail?championName=' + champion.champion_name;
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
				url: "/champion/searchChampionList",
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
						link.href = '/champion/detail?championName=' + champion.champion_name; // 챔피언 페이지로 이동할 URL 설정

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


	const lineButton = document.getElementById('lineButton');
	const buttonLabels = ['All', 'Top', 'Jungle', 'Middle', 'Bottom', 'Utility'];
	const buttonKRLabel = ['전체', '탑', '정글', '미드', '원딜', '서폿']

	// 버튼 생성 및 추가
	buttonLabels.forEach((label, index) => {
		const button = document.createElement('button');
		button.className = 'line'
		const searchIcon = document.createElement('div');
		searchIcon.classList.add('searchIcon');		
		searchIcon.innerHTML = `<img src="/images/${label}.svg" alt=${label}>`
		button.append(searchIcon);
		const searchName = document.createElement('span');
		searchName.classList.add('searchName');
		searchName.textContent = buttonKRLabel[index];
		button.append(searchName);
		button.name = label
		button.id = label
		button.value = label
		lineButton.appendChild(button); // lineButton 요소에 버튼 추가
	});

	const allButton = document.getElementById('All');
	allButton.addEventListener('click', function(event) {
		event.preventDefault()

		// 모든 자식 요소를 제거합니다.
		while (ul.firstChild) {
			ul.removeChild(ul.firstChild);
		}
		searchChampion.value = ''

		showChampions()
	});

	function handleButtonClick(line, championNames) {
		//event.preventDefault();
		console.log("line : ", line)

		// AJAX 요청 보내는 코드
		$.ajax({
			type: 'GET',
			url: '/champion/lineChampionList', // 챔피언 데이터를 가져올 URL
			data: { line: line }, // 선택된 라인 정보 전달
			success: function(data) {
				console.log("datas : ", data);
				console.log(data.length)

				while (ul.firstChild) {
					ul.removeChild(ul.firstChild);
				}

				// 각 챔피언에 대해 반복
				data.forEach(function(champion, index) {
					const lineLi = document.createElement('li')
					lineLi.classList.add('champion-item');

					const lineA = document.createElement('a');
					lineA.className = 'img-link'
					lineA.href = '/champion/detail?championName=' + champion.championName;
					lineA.style.width = '50px';

					const lineDiv = document.createElement('div');
					lineDiv.className = 'champion-img';

					const lineImg = document.createElement('img');
					const championName = champion.championName === 'FiddleSticks' ? 'Fiddlesticks' : champion.championName; // 피들스틱 S -> s변경
					lineImg.src = `https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${championName}.png`;
					lineImg.alt = champion.champion_name;

					lineDiv.appendChild(lineImg);
					lineA.appendChild(lineDiv);

					const lineSpan = document.createElement('span');

					// 챔피언 이름과 매칭되는 한글 이름을 찾아 span 태그에 설정
					for (let i = 0; i < championNames.length; i++) {
						const championName = champion.championName === 'FiddleSticks' ? 'Fiddlesticks' : champion.championName;
						if (championNames[i].champion_name === championName) {
							lineSpan.textContent = championNames[i].champion_name_kr;
							break;
						}
					}

					lineLi.appendChild(lineA);
					lineLi.appendChild(lineSpan); // span을 li에 추가

					ul.appendChild(lineLi);

					
				});
			},
			error: function(error) {
				console.log("Error: ", error);
			}
		});
	}

	const lineButtons = document.querySelectorAll('.line:not(#All)'); // All 버튼은 작동 안함
	lineButtons.forEach(button => {
		const line = button.value; // 버튼의 값 (라인 정보)
		button.onclick = function() {
			handleButtonClick(line, championNames); // 클릭 시 handleButtonClick 함수 호출
		};
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
	const thText = ['티 어', '챔피언', '포지션', '승 률', '픽 률', '벤 률']
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
			url: '/champion/championallinfo',
			data: {},
			success: function(response) {
				// AJAX 요청이 성공했을 때의 처리
				console.log("res : ", response);
				
				response.sort((a, b) => {
	                let avgA = (a.winRate + a.pickRate + a.banRate) / 3;
	                let avgB = (b.winRate + b.pickRate + b.banRate) / 3;
	                return avgB - avgA; // 내림차순 정렬
            	});

				response.sort((a, b) => b.avgTierNum - a.avgTierNum);

				const tdCName = ['tier', 'champions', 'position', 'winRate', 'pickRate', 'banRate']
				for (let i = 0; i < response.length; i++) {
					let championInfo = response[i]
					const tbodyTr = document.createElement('tr')

					for (let j = 0; j < tdCName.length; j++) {
						const td = document.createElement('td');
						td.className = tdCName[j];

						if (tdCName[j] === 'champions') {
							const championAnchor = document.createElement('a');
							championAnchor.href = '/champion/detail?championName=' + championInfo.championName;
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
							console.log(championInfo.banRate)
							let avgNum = []
							let avg = (championInfo.winRate + championInfo.pickRate + championInfo.banRate) / 3
							avg = avg.toFixed(1)
							avgNum.push(avg)
							console.log(avg)
							let tier = '';
							
							 if (avg >= 25) {
						        tier = 'OP';
						    } else if (avg >= 20 && avg < 25) {
						        tier = '1티어';
						    } else if (avg >= 15 && avg < 20) {
						        tier = '2티어';
						    } else if (avg >= 10 && avg < 15) {
						        tier = '3티어';
						    } else if (avg >= 5 && avg < 10) {
						        tier = '4티어';
						    } else {
								tier = '5티어'
							}

						    td.textContent = tier;;
						} else if (tdCName[j] === 'position') {
							td.textContent = championInfo.position;
						} else if (tdCName[j] === 'winRate') {
							td.textContent = championInfo.winRate + "%";
						} else if (tdCName[j] === 'pickRate') {
							td.textContent = championInfo.pickRate + "%";;
						} else if (tdCName[j] === 'banRate') {
							td.textContent = championInfo.banRate + "%";;
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

	function getLineChampionInfo(line, championNames) {
		console.log('aa', line)
		$.ajax({
			type: 'get',
			url: '/champion/championlineinfo',
			data: { "line": line },
			success: function(response) {
				// AJAX 요청이 성공했을 때의 처리
				console.log("r : ", response);
				
				 response.sort((a, b) => {
	                let avgA = (a.winRate + a.pickRate + a.banRate) / 3;
	                let avgB = (b.winRate + b.pickRate + b.banRate) / 3;
	                return avgB - avgA; // 내림차순 정렬
            	});
				
				const tdCName = ['tier', 'champions', 'position', 'winRate', 'pickRate', 'banRate']
				for (let i = 0; i < response.length; i++) {
					let championInfo = response[i]
					const tbodyTr = document.createElement('tr')

					for (let j = 0; j < tdCName.length; j++) {
						const td = document.createElement('td');
						td.className = tdCName[j];

						if (tdCName[j] === 'champions') {
							const championAnchor = document.createElement('a');
							championAnchor.href = '/champion/detail?championName=' + championInfo.championName;
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
							let avg = (championInfo.winRate + championInfo.pickRate + championInfo.banRate) / 3
							avg = avg.toFixed(1)
							avgNum.push(avg)
							let tier = '';
							
							 if (avg >= 25) {
						        tier = 'OP';
						    } else if (avg >= 20 && avg < 25) {
						        tier = '1티어';
						    } else if (avg >= 15 && avg < 20) {
						        tier = '2티어';
						    } else if (avg >= 10 && avg < 15) {
						        tier = '3티어';
						    } else if (avg >= 5 && avg < 10) {
						        tier = '4티어';
						    } else {
								tier = '5티어'
							}

						    td.textContent = tier;
						} else if (tdCName[j] === 'position') {
							td.textContent = championInfo.position;
						} else if (tdCName[j] === 'winRate') {
							td.textContent = championInfo.winRate + "%";
						} else if (tdCName[j] === 'pickRate') {
							td.textContent = championInfo.pickRate + "%";;
						} else if (tdCName[j] === 'banRate') {
							td.textContent = championInfo.banRate + "%";;
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

	const divBtt = document.createElement('div')
	divBtt.classList.add('lineBtt')
	

	let bttline = ['All', 'Top', 'Jungle', 'Middle', 'Bottom', 'Utility']
	let bttline_kr = ['전체', '탑', '정글', '미드', '원딜', '서폿']
	for (let i = 0; i < 6; i++) {
		
		const lineIcon = document.createElement('div')
		lineIcon.classList.add('lineIcon')
		lineIcon.innerHTML = `<img src="/images/${bttline[i]}.svg" alt="${bttline[i]}">`
		const lineName = document.createElement('span')
		lineName.classList.add('lineName')
		lineName.textContent = bttline_kr[i]
		const lineBtt = document.createElement('button')
		lineBtt.className = bttline[i]
		lineBtt.value = bttline[i]
		lineBtt.setAttribute('data-line', bttline[i])
		lineBtt.append(lineIcon)
		lineBtt.append(lineName)
		divBtt.appendChild(lineBtt)
	}

	table.appendChild(tbody)
	divElement.appendChild(divBtt)
	
	function clickDelete() {
		const tbody = document.querySelector('tbody');
    	tbody.innerHTML = ''; // 테이블의 tbody 내용을 삭제
	}

	// 전체 버튼 누르면 전체 표시
	const allBtt = document.querySelector('.All');
	allBtt.addEventListener('click', function(event) {
		event.preventDefault()
		clickDelete()
		getChampionInfo()
	});

	const linebtts = document.querySelectorAll('[data-line]:not(.All)'); // All 버튼은 작동 안함
	linebtts.forEach(button => {
		const line = button.value; // 버튼의 값 (라인 정보)
		button.onclick = function() {
			clickDelete()
			getLineChampionInfo(line, championNames); // 클릭 시 handleButtonClick 함수 호출
		};
	});

	divElement.appendChild(table)
	
});