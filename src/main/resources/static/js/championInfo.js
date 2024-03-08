$(document).ready(function() {
	let championNameJson = $('#championInfo').val()
	let championInfo = JSON.parse(championNameJson);
	console.log("championInfo : ", championInfo)

	const championBox = document.querySelector('.champion-box')
	//console.log(championBox)
	const div = document.createElement('div')
	div.classList.add('img-box')

	const img = document.createElement('img')
	img.className = 'champion-img'
	img.alt = championInfo.champion_name_kr;
	img.src = `https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${championInfo.champion_name}.png`;

	div.appendChild(img)
	championBox.appendChild(div)

	const champion = document.querySelector('.champion-info')
	const h1 = document.createElement('h1')
	const strong = document.createElement('strong')
	strong.innerText = championInfo.champion_name_kr
	h1.appendChild(strong)

	champion.appendChild(h1)

	const skills = document.querySelector('.champion-skills')
	const div_skills = document.createElement('div')
	div_skills.classList.add('skill-img')

	// 챔피언의 스킬 수
	const skillCount = 5;

	for (let i = 0; i < skillCount; i++) {
		const div_skill = document.createElement('div')
		const skill_img = document.createElement('img')

		// 스킬 이미지 설정
		if (i === 0) {
			// 패시브 스킬
			skill_img.alt = championInfo.champion_passive_en;
			skill_img.src = `https://ddragon.leagueoflegends.com/cdn/14.3.1/img/passive/${championInfo.champion_passive_en}.png`;
		} else {
			// 기본 스킬
			skill_img.alt = championInfo[`champion_skill_name${i}`]
			skill_img.src = `https://ddragon.leagueoflegends.com/cdn/14.3.1/img/spell/${championInfo[`champion_skill_id${i}`]}.png`
		}

		div_skill.appendChild(skill_img)
		div_skills.appendChild(div_skill)
	}

	skills.appendChild(div_skills)

	// 버튼 생성 후 첫번 째 버튼의 라인 값 가지고 가서 승,픽,벤률 값 가져오기
	const lineButton = document.querySelector('.lineButton')

	$.ajax({
		type: "get",
		url: "/champion/" + championInfo.champion_name + "/teamposition",
		async: false, // 비동기 -> 동기로 변환.(권장하진 않는다.)
		success: function(res) {
			// 데이터를 빈도순으로 정렬
			const sortedPositions = res.sort((a, b) => res.filter(item => item === b).length - res.filter(item => item === a).length);

			// 중복된 값을 제거하고 유일한 값들을 포함한 배열을 얻음
			const uniquePositions = Array.from(new Set(sortedPositions));

			// 유일한 값을 기반으로 버튼 생성
			uniquePositions.forEach(position => {
				if (position.trim() !== '') { // 빈 값이 아닌 경우에만 버튼을 생성
					const button = document.createElement('button');
					button.textContent = position;
					button.classList.add('line');
					button.dataset.position = position; // 버튼에 포지션 정보를 데이터 속성으로 추가
					button.addEventListener('click', lineButton); // 클릭 이벤트 처리기 추가
					lineButton.appendChild(button);
				}
			});
		},
		error: function(error) {
			console.log(error)
		}
	})

	// 첫 번째 버튼의 값
	const buttons = document.querySelectorAll('.line');
	const firstLine = buttons[0].dataset.position;

	function rateData(res) {
		const rates = document.querySelector('.rates')

		rates.innerHTML = ''; // 기존 값 초기화

		// 첫 번째 div 생성
		const div1 = document.createElement('div');
		div1.classList.add('rate-info');

		// 승, 픽, 벤 레이블 추가
		const labels = ['승률', '픽률', '벤률'];
		labels.forEach(label => {
			const span = document.createElement('span');
			span.textContent = label;
			div1.appendChild(span);
		});

		// 두 번째 div 생성
		const div2 = document.createElement('div');
		div2.classList.add('rate-values');

		// 승, 픽, 벤 비율 값 추가
		const values = [res.winRate, res.pickRate, res.banRate];
		values.forEach(value => {
			const span = document.createElement('span');
			span.textContent = value;
			div2.appendChild(span);
		});

		// 부모 요소에 자식 요소 추가
		rates.appendChild(div1);
		rates.appendChild(div2);
	}

	function readRates(position) {
		// Ajax 요청 실행
		$.ajax({
			type: "get",
			url: "/champion/" + championInfo.champion_name + "/" + position + "/rates",
			success: function(res) {
				// 데이터를 받아온 후에 원하는 작업 수행
				rateData(res);
			},
			error: function(error) {
				console.log(error);
			}
		});
	}

	// 버튼 이벤트
	buttons.forEach(button => {
		button.addEventListener('click', function() {
			// 클릭된 버튼의 데이터 속성 값
			const position = this.dataset.position;
			readRates(position); // 승,픽,벤률 AJAX

			readRune(position) // 룬 AJAX

			readSpell(position) // 스펠 AJAX

			readItem(position) // 아이템 AJAX

			readEasyCounter(position) // 카운터 AJAX
			
			readHardCounter(position) // 카운터 AJAX
		});
	});

	// 페이지 로드 시 처음 버튼의 라인 값을 사용하여 데이터
	readRates(firstLine)

	// 룬 데이터
	function runeData(res) {
		const combinationCounts = {};

		const mainRune = document.querySelector('.main')
		mainRune.innerHTML = ''
		const mainPerks = document.querySelector('.mainPerks')
		mainPerks.innerHTML = ''
		const subRune = document.querySelector('.subRune')
		subRune.innerHTML = ''
		const subPerks = document.querySelector('.subPerks')
		subPerks.innerHTML = ''
		const statPerks = document.querySelector('.statPerks')
		statPerks.innerHTML = ''

		// 각 조합별 카운트를 증가시킵니다.
		for (let i = 0; i < res.length; i++) {
			let championRune = res[i];
			let combination = `${championRune.main_name}-${championRune.main_perks1}-${championRune.main_perks2}-${championRune.main_perks3}-${championRune.main_perks4}-${championRune.sub_name}-${championRune.sub_perks1}-${championRune.sub_perks2}-${championRune.stat_perks1}-${championRune.stat_perks2}-${championRune.stat_perks3}`;

			// 해당 조합의 카운트를 증가
			if (!(combination in combinationCounts)) {
				combinationCounts[combination] = 0;
			}
			combinationCounts[combination]++;
		}

		// 가장 많이 나온 조합을 저장할 변수들
		let mostCommonMainName = ''
		let mostCommonMainPerks1 = '';
		let mostCommonMainPerks2 = '';
		let mostCommonMainPerks3 = '';
		let mostCommonMainPerks4 = '';
		let mostCommonSubName = '';
		let mostCommonSubPerks1 = '';
		let mostCommonSubPerks2 = '';
		let mostCommonStatPerks1 = '';
		let mostCommonStatPerks2 = '';
		let mostCommonStatPerks3 = '';
		let maxCount = 0;

		// 가장 많이 나온 조합을 찾습니다.
		for (const combination in combinationCounts) {
			if (combinationCounts.hasOwnProperty(combination)) {
				const count = combinationCounts[combination];
				if (count > maxCount) {
					// 더 큰 카운트를 발견하면 해당 값을 업데이트
					maxCount = count;
					// 각 변수에 해당하는 값을 업데이트
					[mostCommonMainName, mostCommonMainPerks1, mostCommonMainPerks2, mostCommonMainPerks3, mostCommonMainPerks4, mostCommonSubName, mostCommonSubPerks1,
						mostCommonSubPerks2, mostCommonStatPerks1, mostCommonStatPerks2, mostCommonStatPerks3] = combination.split('-');
				}
			}
		}

		// 가장 많이 나온 조합을 출력
		if (maxCount > 0) {
			const mainRuneImg = document.createElement('img')
			mainRuneImg.src = `https://opgg-static.akamaized.net/meta/images/lol/14.4.1/perkStyle/${mostCommonMainName}.png?image=q_auto,f_webp,w_64,h_64&v=1708681571653`
			mainRuneImg.alt = mostCommonMainName

			const mostCommonMainPerks = [mostCommonMainPerks1, mostCommonMainPerks2, mostCommonMainPerks3, mostCommonMainPerks4];

			for (let i = 0; i < mostCommonMainPerks.length; i++) {
				const mainPerksImg = document.createElement('img');
				const mainPerksName = mostCommonMainPerks[i];

				mainPerksImg.src = `https://opgg-static.akamaized.net/meta/images/lol/14.4.1/perk/${mainPerksName}.png?image=q_auto,f_webp,w_64,h_64&v=1708681571653`;
				mainPerksImg.alt = mainPerksName;

				mainPerks.appendChild(mainPerksImg);
			}

			const subRuneImg = document.createElement('img')
			subRuneImg.src = `https://opgg-static.akamaized.net/meta/images/lol/14.4.1/perkStyle/${mostCommonSubName}.png?image=q_auto,f_webp,w_64,h_64&v=1708681571653`
			subRuneImg.alt = mostCommonSubName

			const mostCommonSubPerks = [mostCommonSubPerks1, mostCommonSubPerks2];
			for (let j = 0; j < mostCommonSubPerks.length; j++) {
				const subPerksImg = document.createElement('img');
				const subPerksName = mostCommonSubPerks[j];

				subPerksImg.src = `https://opgg-static.akamaized.net/meta/images/lol/14.4.1/perk/${subPerksName}.png?image=q_auto,f_webp,w_64,h_64&v=1708681571653`;
				subPerksImg.alt = subPerksName;

				subPerks.appendChild(subPerksImg);
			}

			const mostCommonStatPerks = [mostCommonStatPerks1, mostCommonStatPerks2, mostCommonStatPerks3];
			for (let z = 0; z < mostCommonStatPerks.length; z++) {
				const statPerksImg = document.createElement('img');
				const statPerksName = mostCommonStatPerks[z];

				statPerksImg.src = `https://opgg-static.akamaized.net/meta/images/lol/perkShard/${statPerksName}.png?image=q_auto,f_webp,w_64,h_64&v=1708681571653`;
				statPerksImg.alt = statPerksName;

				statPerks.appendChild(statPerksImg);
			}

			mainRune.appendChild(mainRuneImg)
			subRune.appendChild(subRuneImg)
		} else {
			console.log('No data available.');
		}
	}

	function readRune(position) {
		$.ajax({
			type: "get",
			url: "/champion/" + championInfo.champion_name + "/rune",
			data: { position: position },
			success: function(res) {
				runeData(res)
			},
			error: function(error) {
				console.log(error)
			}
		})
	}

	// 룬
	readRune(firstLine)

	// 스펠 데이터
	const spellList = document.querySelector('.spellList')

	function spellData(res) {
		spellList.innerHTML = ''
		const firstSpell = document.createElement('div')
		const secondSpell = document.createElement('div')

		firstSpell.classList.add('firstSpell')
		secondSpell.classList.add('secondSpell')

		spellList.appendChild(firstSpell)
		spellList.appendChild(secondSpell)
		const oneSpell = document.createElement('div')
		const twoSpell = document.createElement('div')

		const ulOne = document.createElement('ul');
		const ulTwo = document.createElement('ul');

		// 첫 번째 객체의 스펠 처리
		for (const key in res[0]) {
			if (res[0].hasOwnProperty(key) && /^[^\uAC00-\uD7A3]*$/.test(res[1][key])) {
				// playCount일 경우 '판' 문자열을 추가하여 처리
				if (key != 'playCount') {
					const spell = document.createElement('li');
					const spellImg = document.createElement('img')
					spellImg.src = `https://ddragon.leagueoflegends.com/cdn/14.4.1/img/spell/${res[0][key]}.png`
					spell.appendChild(spellImg)
					ulOne.appendChild(spell);
				}
			}
		}

		// 첫번 째
		const firstPlay = document.createElement('div');
		firstPlay.textContent = res[0]['playCount'] + '판';
		firstSpell.parentNode.insertBefore(firstPlay, firstSpell.nextSibling);

		// 두 번째 객체의 스펠 처리
		for (const key in res[1]) {
			if (res[1].hasOwnProperty(key) && /^[^\uAC00-\uD7A3]*$/.test(res[1][key])) {
				if (key != 'playCount') {
					const spell = document.createElement('li');
					const spellImg = document.createElement('img')
					spellImg.src = `https://ddragon.leagueoflegends.com/cdn/14.4.1/img/spell/${res[1][key]}.png`
					spell.appendChild(spellImg)
					ulTwo.appendChild(spell);
				}
			}
		}

		// 두번 째
		const secondPlay = document.createElement('div');
		secondPlay.textContent = res[1]['playCount'] + '판';
		firstSpell.parentNode.insertBefore(secondPlay, secondSpell.nextSibling);

		oneSpell.appendChild(ulOne);
		twoSpell.appendChild(ulTwo)
		firstSpell.appendChild(oneSpell)
		secondSpell.appendChild(twoSpell)
	}

	function readSpell(position) {
		$.ajax({
			type: "get",
			url: "/champion/" + championInfo.champion_name + "/spell",
			data: { position: position },
			success: function(res) {
				spellData(res)
			},
			error: function(error) {
				console.log(error)
			}
		})
	}

	// 스펠
	readSpell(firstLine)

	// 코어 아이템
	function itemData(res) {
		const table = document.querySelector('.startItemList')
		table.innerHTML = ''

		const tbody = document.createElement('tbody')
		const thead = document.createElement('thead')

		const headerRow = document.createElement('tr')
		const header1 = document.createElement('th')
		header1.textContent = '아이템'
		const header2 = document.createElement('th')
		header2.textContent = '픽률'
		const header3 = document.createElement('th')
		header3.textContent = '승률'

		headerRow.appendChild(header1)
		headerRow.appendChild(header2)
		headerRow.appendChild(header3)
		thead.appendChild(headerRow)

		for (let i = 0; i < 5; i++) {
			let item = res[i];

			if (item) {
				const row = document.createElement('tr');

				const classTd = ['first', 'second', 'third'];
				for (let j = 1; j <= 3; j++) {
					const td = document.createElement('td');
					if (j === 1) {
						td.className = classTd[j - 1];
						for (let k = 0; k < 3; k++) {
							const div = document.createElement('div');

							const itemImg = document.createElement('img');
							itemImg.src = `https://ddragon.leagueoflegends.com/cdn/14.4.1/img/item/${item[`core${k + 1}`]}.png`;

							div.appendChild(itemImg);
							td.appendChild(div);
						}
					} else if (j === 2) {
						td.className = classTd[j - 1];

						const pickSpan = document.createElement('span');

						pickSpan.textContent = item.core3_percentage + '%'
						td.appendChild(pickSpan);
					} else {
						td.className = classTd[j - 1];

						const winSpan = document.createElement('span');

						winSpan.textContent = item.core3_win_percentage + '%'
						td.appendChild(winSpan);
					}
					row.appendChild(td);
				}
				tbody.appendChild(row);
			}
		}
		table.appendChild(thead)
		table.appendChild(tbody)
	}

	function readItem(position) {
		$.ajax({
			type: "get",
			url: "/champion/" + championInfo.champion_name + "/item",
			data: { position: position },
			success: function(res) {
				itemData(res)
			},
			error: function(error) {
				console.log(error)
			}
		})
	}

	// 아이템
	readItem(firstLine)

	// 챔피언 카운터 TOP 5
	const championCounter = document.querySelector('.championCounter')

	const championCounterEasy = document.createElement('div')
	championCounterEasy.classList.add('championCounterEasy')
	championCounterEasy.textContent = '상대하기 편한 챔피언'
	
	const championCounterHard = document.createElement('div')
	championCounterHard.classList.add('championCounterHard')
	championCounterHard.textContent = '상대하기 어려운 챔피언'
	
	const championEasyCounterUl = document.createElement('ul')
	championEasyCounterUl.classList.add('championEasyCounterUl')
	
	const championHardCounterUl = document.createElement('ul')
	championHardCounterUl.classList.add('championHardCounterUl')

	championCounter.appendChild(championCounterEasy)
	championCounter.appendChild(championEasyCounterUl)
	championCounter.appendChild(championCounterHard)
	championCounter.appendChild(championHardCounterUl)
		
	function counterEasyData(res) {
		championEasyCounterUl.innerHTML = ''

		for (let i = 0; i < 5; i++) {
			let counter = res[i]
			const championCounterIl = document.createElement('li')
			championCounterIl.className = 'counterChmapions'

			const counterImg = document.createElement('img')
			const enemy_championName = counter.enemy_championName === 'FiddleSticks' ? 'Fiddlesticks' : counter.enemy_championName;
			counterImg.src = `https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${enemy_championName}.png`
			counterImg.alt = enemy_championName

			const winDiv = document.createElement('div')
			winDiv.textContent = counter.win_rate + '%'

			const playdiv = document.createElement('div')
			playdiv.innerHTML = counter.games_played.toLocaleString() + '<br>게임'

			championCounterIl.appendChild(counterImg)
			championCounterIl.appendChild(winDiv)
			championCounterIl.appendChild(playdiv)
			championEasyCounterUl.appendChild(championCounterIl)
		}
	}
	
	function counterHardData(res) {
		championHardCounterUl.innerHTML = ''
		
		for (let i = 0; i < 5; i++) {
			let counter = res[i]
			const championCounterIl = document.createElement('li')
			championCounterIl.className = 'counterChmapions'

			const counterImg = document.createElement('img')
			const enemy_championName = counter.enemy_championName === 'FiddleSticks' ? 'Fiddlesticks' : counter.enemy_championName;
			counterImg.src = `https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/${enemy_championName}.png`
			counterImg.alt = enemy_championName

			const winDiv = document.createElement('div')
			winDiv.textContent = counter.win_rate + '%'

			const playdiv = document.createElement('div')
			playdiv.innerHTML = counter.games_played.toLocaleString() + '<br>게임'

			championCounterIl.appendChild(counterImg)
			championCounterIl.appendChild(winDiv)
			championCounterIl.appendChild(playdiv)
			championHardCounterUl.appendChild(championCounterIl)
		}
	}
	
	function readEasyCounter(position) {
		$.ajax({
			type: "get",
			url: "/champion/" + championInfo.champion_name + "/easycounter",
			data: { position: position },
			success: function(res) {
				console.log('counter2 :', res)
				counterEasyData(res)
			},
			error: function(error) {
				console.log(error)
			}
		})
	}

	readEasyCounter(firstLine)
	
	// counter2
	function readHardCounter(position) {
		$.ajax({
			type: "get",
			url: "/champion/" + championInfo.champion_name + "/hardcounter",
			data: { position: position },
			success: function(res) {
				console.log('counter :', res)
				counterHardData(res)
			},
			error: function(error) {
				console.log(error)
			}
		})
	}

	readHardCounter(firstLine)
})