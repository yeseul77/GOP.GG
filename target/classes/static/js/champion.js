$(document).ready(function() {
	let championNamesJson = $('#championNames').val();
	let championNames = JSON.parse(championNamesJson);
	console.log("kr : ", championNames);

	const championList = $('aside');
	const nav = document.createElement('nav');
	nav.classList.add('championNav')
	const ul = document.createElement('ul');
	ul.classList.add('championList');

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

			// 5개씩 나오도록 추가
			if ((i + 1) % 6 === 0) {
				ul.appendChild(document.createElement('br')); // 줄바꿈 추가
			}
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

	// 버튼 생성 및 추가
	buttonLabels.forEach(label => {
		const button = document.createElement('button');
		button.className = 'line'
		button.textContent = label; // 버튼 텍스트 설정
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
				console.log("data : ", data);
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
						if (championNames[i].champion_name === champion.championName) {
							lineSpan.textContent = championNames[i].champion_name_kr;
							break;
						}
					}

					lineLi.appendChild(lineA);
					lineLi.appendChild(lineSpan); // span을 li에 추가

					ul.appendChild(lineLi);

					if ((index + 1) % 6 === 0) {
						ul.appendChild(document.createElement('br'));
					}
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
	const table = document.createElement('table')
	table.classList.add('championInfo')
	const colgroup = document.createElement('colgroup')
	const thead = document.createElement('thead')
	const tbody = document.createElement('tbody')
	const theadTr = document.createElement('tr')

	const widths = ['auto', '60px', '60px', '94px', '110px', '94px'];
	const thText = ['챔피언', '티어', '포지션', '승률', '픽률', '벤률']
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
	
	const allChampionNames = Object.values(championNames);
	console.log(allChampionNames)
	$.ajax({
		type: 'get',
		url: '/champion/championlineinfo',
		data: {},
		success: function(response) {
			// AJAX 요청이 성공했을 때의 처리
			console.log("res : ", response);

			const tdCName = ['champions', 'tier', 'position', 'winRate', 'pickRate', 'benRate']
			for (let i = 0; i < response.length; i++) {
				let championInfo = response[i]
				const tbodyTr = document.createElement('tr')

				for (let j = 0; j < tdCName.length; j++) {
					const td = document.createElement('td');
					td.className = tdCName[j];

					if (tdCName[j] === 'champions') {
						const championAnchor = document.createElement('a');
						championAnchor.href = '/champion/detail?championName=' + championInfo.championName;
						const championImage = document.createElement('img');
						championImage.src = `https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${championInfo.championName}.png`; // 이미지 URL을 추가하세요
						championImage.alt = championInfo.championName; // 이미지에 대한 대체 텍스트
						championImage.style.width = '40px'; // 이미지 크기 설정
						const strong = document.createElement('strong')
						
						for (let i = 0; i < championNames.length; i++) {
							if (championNames[i].champion_name === championInfo.championName) {
								strong.textContent = championNames[i].champion_name_kr; // 영어 이름 같으면 한글 이름으로 들어감
								break;
							}
						}

						championAnchor.appendChild(championImage);
						championAnchor.appendChild(strong)
						td.appendChild(championAnchor);
					} else if (tdCName[j] === 'tier') {
						td.textContent = championInfo.tier;
					} else if (tdCName[j] === 'position') {
						td.textContent = championInfo.position;
					} else if (tdCName[j] === 'winRate') {
						td.textContent = championInfo.winRate;
					} else if (tdCName[j] === 'pickRate') {
						td.textContent = championInfo.pickRate;
					} else if (tdCName[j] === 'benRate') {
						td.textContent = championInfo.benRate;
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

	table.appendChild(tbody)
	divElement.appendChild(table)


});
