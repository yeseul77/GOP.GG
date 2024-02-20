$(document).ready(function() {
	let championNamesJson = $('#championNames').val();
	// console.log(championNamesJson);
	let championNames = JSON.parse(championNamesJson);
	console.log(championNames);

	const championList = $('#championList');
	const nav = document.createElement('nav');
	const ul = document.createElement('ul');
	ul.classList.add('championList');

	championList.append(nav);
	nav.append(ul);

	for (let i = 0; i < championNames.length; i++) {
		const champion = championNames[i];
		const li = document.createElement('li');
		li.classList.add('champion-item');

		const a = document.createElement('a');
		a.href = '#';

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
	
});
