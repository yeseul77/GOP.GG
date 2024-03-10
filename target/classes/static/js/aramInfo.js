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
	document.title = `${championInfo.champion_name_kr} 분석 GOP.GG`
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
		div_skill.classList.add('imgBox')
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


	function rateData(res) {
		const rates = document.querySelector('.rates')

		rates.innerHTML = ''; // 기존 값 초기화
	
		// 승, 픽, dpm 레이블 추가
		const labels = ['승률', '픽률', 'dpm'];		

		// 두 번째 div 생성
		const div2 = document.createElement('div');
		div2.classList.add('rate-values');

		// 승, 픽, dpm 비율 값 추가
		const values = [res.win_rate, res.pickRate, res.dpm];
		

		// 부모 요소에 자식 요소 추가
		rates.appendChild(div2);
		
		google.charts.load('current', { 'packages': ['corechart'] });
		google.charts.setOnLoadCallback(drawCharts1);
		
		function drawCharts1() {
			var data1 = google.visualization.arrayToDataTable([
				['항목', '퍼센트', { role: 'style' }, { role: 'annotation' }],
				['승률(%)', res.win_rate, 'color: #5d9ceb', `${res.win_rate}`],
				['픽률(%)', res.pickRate, 'color: #00c29f', `${res.pickRate}`]
				]);
						
		    var options1 = {
				width: 300,
				height: 250,
				fontSize: 14,				
				textStyle: { bold: true, fontSize: 14 },
				hAxis: {
					textStyle: { bold: true, fontSize: 14 } // X축 레이블에 대한 스타일 설정
				},
				vAxis: {
					textStyle: { bold: true, fontSize: 14 } // Y축 레이블에 대한 스타일 설정
				},
				legend: {
					position: 'none'
				},
				animation: {
					startup: true,
					duration: 1000,
					easing: 'out'
				}
			};

			var chart1 = new google.visualization.ColumnChart(document.getElementById('myChart'));
			chart1.draw(data1, options1);			
		}
		
		google.charts.setOnLoadCallback(drawCharts2);
		function drawCharts2() {
			var data2 = google.visualization.arrayToDataTable([
				['항목', '퍼센트', { role: 'style' }, { role: 'annotation' }],				
				['DPM', res.dpm, 'color: #e57474', `${res.dpm}`]
			]);
		
		    var options2 = {
				width: 300,
				height: 250,
				fontSize: 14,
				bar: {groupWidth: '30%'},				
				textStyle: { bold: true, fontSize: 14 },
				hAxis: {
					textStyle: { bold: true, fontSize: 14 } // X축 레이블에 대한 스타일 설정
				},
				vAxis: {
					textStyle: { bold: true, fontSize: 14 } // Y축 레이블에 대한 스타일 설정
				},
				legend: {
					position: 'none'
				},
				animation: {
					startup: true,
					duration: 2000,
					easing: 'out'
				}
			};

			var chart2 = new google.visualization.ColumnChart(document.getElementById('myChartTwo'));
			chart2.draw(data2, options2);
		}		
	
	}

	function readRates() {
		// Ajax 요청 실행
		$.ajax({
			type: "get",
			url: "/aram/" + championInfo.champion_name + "/rates",
			success: function(res) {
				// 데이터를 받아온 후에 원하는 작업 수행
				rateData(res);
			},
			error: function(error) {
				console.log(error);
			}
		});
	}


	// 페이지 로드 시 처음 버튼의 라인 값을 사용하여 데이터
	readRates()

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
				const perksBox = document.createElement('div');
				perksBox.classList.add('perksBox');
				const mainPerksImg = document.createElement('img');
				const mainPerksName = mostCommonMainPerks[i];

				mainPerksImg.src = `https://opgg-static.akamaized.net/meta/images/lol/14.4.1/perk/${mainPerksName}.png?image=q_auto,f_webp,w_64,h_64&v=1708681571653`;
				mainPerksImg.alt = mainPerksName;

				perksBox.append(mainPerksImg);
				mainPerks.appendChild(perksBox);
			}

			const subRuneImg = document.createElement('img')
			subRuneImg.src = `https://opgg-static.akamaized.net/meta/images/lol/14.4.1/perkStyle/${mostCommonSubName}.png?image=q_auto,f_webp,w_64,h_64&v=1708681571653`
			subRuneImg.alt = mostCommonSubName

			const mostCommonSubPerks = [mostCommonSubPerks1, mostCommonSubPerks2];
			for (let j = 0; j < mostCommonSubPerks.length; j++) {
				const perksBox = document.createElement('div');
				perksBox.classList.add('perksBox');
				const subPerksImg = document.createElement('img');
				const subPerksName = mostCommonSubPerks[j];

				subPerksImg.src = `https://opgg-static.akamaized.net/meta/images/lol/14.4.1/perk/${subPerksName}.png?image=q_auto,f_webp,w_64,h_64&v=1708681571653`;
				subPerksImg.alt = subPerksName;

				perksBox.append(subPerksImg);
				subPerks.appendChild(perksBox);
			}
            
            const runeMain = document.querySelector('.main');
			const listLineFirst = document.querySelector('.listLine1');

			switch (mostCommonMainName) {
				case '8000':
					runeMain.style.border = '1.5px solid #faca7b';
					runeMain.style.boxShadow = '0 0 6px #faca7b';
					listLineFirst.style.backgroundColor = '#faca7b';
					listLineFirst.style.boxShadow = '0 0 6px #faca7b';
					break;
				case '8100':
					runeMain.style.border = '1.5px solid #eb1950';
					runeMain.style.boxShadow = '0 0 6px #eb1950';
					listLineFirst.style.backgroundColor = '#eb1950';
					listLineFirst.style.boxShadow = '0 0 6px #eb1950';
					break;
				case '8200':
					runeMain.style.border = '1.5px solid #8662f2';
					runeMain.style.boxShadow = '0 0 6px #8662f2';
					listLineFirst.style.backgroundColor = '#8662f2';
					listLineFirst.style.boxShadow = '0 0 6px #8662f2';
					break;
				case '8300':
					runeMain.style.border = '1.5px solid #5aafc2';
					runeMain.style.boxShadow = '0 0 6px #5aafc2';
					listLineFirst.style.backgroundColor = '#5aafc2';
					listLineFirst.style.boxShadow = '0 0 6px #5aafc2';
					break;
				case '8400':
					runeMain.style.border = '1.5px solid #56ce4a';
					runeMain.style.boxShadow = '0 0 6px #56ce4a';
					listLineFirst.style.backgroundColor = '#56ce4a';
					listLineFirst.style.boxShadow = '0 0 6px #56ce4a';
					break;
			}

			const runePerksBoxs = document.querySelectorAll('.mainPerks .perksBox');
			runePerksBoxs.forEach(runePerksBox => {
				switch (mostCommonMainName) {
					case '8000':
						runePerksBox.style.border = '1.5px solid #faca7b';
						runePerksBox.style.boxShadow = '0 0 6px #faca7b';
						break;
					case '8100':
						runePerksBox.style.border = '1.5px solid #eb1950';
						runePerksBox.style.boxShadow = '0 0 6px #eb1950';
						break;
					case '8200':
						runePerksBox.style.border = '1.5px solid #8662f2';
						runePerksBox.style.boxShadow = '0 0 6px #8662f2';
						break;
					case '8300':
						runePerksBox.style.border = '1.5px solid #5aafc2';
						runePerksBox.style.boxShadow = '0 0 6px #5aafc2';
						break;
					case '8400':
						runePerksBox.style.border = '1.5px solid #56ce4a';
						runePerksBox.style.boxShadow = '0 0 6px #56ce4a';
						break;
				}
			})

			const runeSub = document.querySelector('.subRune');
			const listLineSecond = document.querySelector('.listLine2');
			const listLineThird = document.querySelector('.listLine3');
			listLineThird.style.backgroundColor = '#333';
			listLineThird.style.boxShadow = '0 0 6px #333';
			switch (mostCommonSubName) {
				case '8000':
					runeSub.style.border = '1.5px solid #faca7b';
					runeSub.style.boxShadow = '0 0 6px #faca7b';
					listLineSecond.style.backgroundColor = '#faca7b';
					listLineSecond.style.boxShadow = '0 0 6px #faca7b';
					break;
				case '8100':
					runeSub.style.border = '1.5px solid #eb1950';
					runeSub.style.boxShadow = '0 0 6px #eb1950';
					listLineSecond.style.backgroundColor = '#eb1950';
					listLineSecond.style.boxShadow = '0 0 6px #eb1950';
					break;
				case '8200':
					runeSub.style.border = '1.5px solid #8662f2';
					runeSub.style.boxShadow = '0 0 6px #8662f2';
					listLineSecond.style.backgroundColor = '#8662f2';
					listLineSecond.style.boxShadow = '0 0 6px #8662f2';
					break;
				case '8300':
					runeSub.style.border = '1.5px solid #5aafc2';
					runeSub.style.boxShadow = '0 0 6px #5aafc2';
					listLineSecond.style.backgroundColor = '#5aafc2';
					listLineSecond.style.boxShadow = '0 0 6px #5aafc2';
					break;
				case '8400':
					runeSub.style.border = '1.5px solid #56ce4a';
					runeSub.style.boxShadow = '0 0 6px #56ce4a';
					listLineSecond.style.backgroundColor = '#56ce4a';
					listLineSecond.style.boxShadow = '0 0 6px #56ce4a';
					break;
			}

			const runeSubPerksBoxs = document.querySelectorAll('.subPerks .perksBox');
			runeSubPerksBoxs.forEach(runeSubPerksBox => {
				switch (mostCommonSubName) {
					case '8000':
						runeSubPerksBox.style.border = '1.5px solid #faca7b';
						runeSubPerksBox.style.boxShadow = '0 0 6px #faca7b';
						break;
					case '8100':
						runeSubPerksBox.style.border = '1.5px solid #eb1950';
						runeSubPerksBox.style.boxShadow = '0 0 6px #eb1950';
						break;
					case '8200':
						runeSubPerksBox.style.border = '1.5px solid #8662f2';
						runeSubPerksBox.style.boxShadow = '0 0 6px #8662f2';
						break;
					case '8300':
						runeSubPerksBox.style.border = '1.5px solid #5aafc2';
						runeSubPerksBox.style.boxShadow = '0 0 6px #5aafc2';
						break;
					case '8400':
						runeSubPerksBox.style.border = '1.5px solid #56ce4a';
						runeSubPerksBox.style.boxShadow = '0 0 6px #56ce4a';
						break;
				}
			})
            
            
			const mostCommonStatPerks = [mostCommonStatPerks1, mostCommonStatPerks2, mostCommonStatPerks3];
			for (let z = 0; z < mostCommonStatPerks.length; z++) {
				const perksBox = document.createElement('div');
				perksBox.classList.add('perksBox');
				const statPerksImg = document.createElement('img');
				const statPerksName = mostCommonStatPerks[z];

				statPerksImg.src = `https://opgg-static.akamaized.net/meta/images/lol/perkShard/${statPerksName}.png?image=q_auto,f_webp,w_64,h_64&v=1708681571653`;
				statPerksImg.alt = statPerksName;

				perksBox.append(statPerksImg);
				statPerks.appendChild(perksBox);
			}

			mainRune.appendChild(mainRuneImg)
			subRune.appendChild(subRuneImg)
		} else {
			console.log('No data available.');
		}
	}

	function readRune() {
		$.ajax({
			type: "get",
			url: "/aram/" + championInfo.champion_name + "/rune",
			success: function(res) {
				runeData(res)
			},
			error: function(error) {
				console.log(error)
			}
		})
	}

	// 룬
	readRune()

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
		
		const ulOne = document.createElement('ul');
		ulOne.classList.add('ulOne');	
		const ulTwo = document.createElement('ul');
		ulTwo.classList.add('ulTwo');
		firstSpell.append(ulOne);
		secondSpell.append(ulTwo);

		// 첫 번째 객체의 스펠 처리
		for (const key in res[0]) {
			if (res[0].hasOwnProperty(key) && /^[^\uAC00-\uD7A3]*$/.test(res[1][key]) && key !== 'pickRate' && key !== 'winRate') {
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
		const firstTotal = document.createElement('div');
		firstTotal.classList.add('firstTotal');
		const firstPlay = document.createElement('div');
		firstPlay.classList.add('firstData');
		firstPlay.textContent = '총 ' + res[0]['playCount'] + ' 게임';
		firstTotal.append(firstPlay);
		
		const firstPick = document.createElement('div');
		firstPick.classList.add('firstPick');
		firstPick.textContent = '픽률 ' + res[0]['pickRate'] + ' %';
		firstTotal.append(firstPick);
		
		const firstWin = document.createElement('div');
		firstWin.classList.add('firstWin');
		firstWin.textContent = '승률 ' + res[0]['winRate'] + ' %';
		firstTotal.append(firstWin);
		
		firstSpell.append(firstTotal);

		// 두 번째 객체의 스펠 처리
		for (const key in res[1]) {
			if (res[1].hasOwnProperty(key) && /^[^\uAC00-\uD7A3]*$/.test(res[1][key])&& key !== 'pickRate' && key !== 'winRate') {
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
		const secondTotal = document.createElement('div');
		secondTotal.classList.add('secondTota');
		const secondPlay = document.createElement('div');
		secondPlay.classList.add('secondData');
		secondPlay.textContent = '총 ' + res[1]['playCount'] + ' 게임';
		secondTotal.append(secondPlay);
		
		const secondPick = document.createElement('div');
		secondPick.classList.add('secondPick');
		secondPick.textContent = '픽률 ' + res[1]['pickRate'] + ' %';
		secondTotal.append(secondPick);
		
		const secondWin = document.createElement('div');
		secondWin.classList.add('secondWin');
		secondWin.textContent = '승률 ' + res[1]['winRate'] + ' %';
		secondTotal.append(secondWin);
		
		secondSpell.append(secondTotal);

	}

	function readSpell() {
		$.ajax({
			type: "get",
			url: "/aram/" + championInfo.champion_name + "/spell",
			success: function(res) {
				spellData(res)
			},
			error: function(error) {
				console.log(error)
			}
		})
	}

	// 스펠
	readSpell()

	// 코어 아이템
	function itemData(res) {
		const table = document.querySelector('.startItemList')
		table.innerHTML = ''

		const tbody = document.createElement('tbody')
		

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
							div.classList.add('itemBox');

							const itemImg = document.createElement('img');
							itemImg.src = `https://ddragon.leagueoflegends.com/cdn/14.4.1/img/item/${item[`core${k + 1}`]}.png`;

							div.appendChild(itemImg);
							td.appendChild(div);
						}
					} else if (j === 2) {
						td.className = classTd[j - 1];

						const pickSpan = document.createElement('span');

						pickSpan.textContent = '픽률 ' + item.core3_percentage + '%'
						td.appendChild(pickSpan);
					} else {
						td.className = classTd[j - 1];

						const winSpan = document.createElement('span');

						winSpan.textContent = '승률 ' + item.core3_win_percentage + '%'
						td.appendChild(winSpan);
					}
					row.appendChild(td);
				}
				tbody.appendChild(row);
			}
		}
		
		table.appendChild(tbody)
	}

	function readItem() {
		$.ajax({
			type: "get",
			url: "/aram/" + championInfo.champion_name + "/item",
			success: function(res) {
				itemData(res)
			},
			error: function(error) {
				console.log(error)
			}
		})
	}

	// 아이템
	readItem()

})