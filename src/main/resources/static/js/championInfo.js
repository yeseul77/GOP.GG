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


})