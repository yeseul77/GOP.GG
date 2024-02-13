const sse=new EventSource("http://localhost:80/duo_matching/matching");

$(document).ready(function(){
	$.ajax({
		method:'get',
		url:'/start',
	}).done()
})

sse.addEventListener('connect', (e) => { // connect라는 이름을 가진 이벤트를 받는다
	const { data: receivedConnectData } = e;
	console.log('connect event data: ',receivedConnectData);  // "connected
});

sse.addEventListener('delete', (e) => { // count라는 이름을 가진 이벤트를 받는다
	const { data: roomlist} = e;
//	let i=0;
//	console.log('count event data: ',result);  // "connected
//	result.forEach((result, i)=>{
//		console.log(result)
	let result=JSON.parse(roomlist)
//	console.log(result[0])
//	console.log(result[result.length-1].title)
		const temp=document.createElement("div");
		temp.innerHTML=`<div id="rlist">
						<h1 id="roomTitle">${result[0].title}</h1>
						<p> id="roomPosition"${result[0].position}</p>
						<p id="roomMemo">${result[0].memo}</p>
						<p id="roomChampion">${result[0].champion}</p>
						<button id="delete">방 삭제</button>
						`
		document.querySelector(".rlist").prepend(temp);
	});

sse.addEventListener('count', (e) => { // count라는 이름을 가진 이벤트를 받는다
	const { data: roomlist} = e;
//	let i=0;
//	console.log('count event data: ',result);  // "connected
//	result.forEach((result, i)=>{
//		console.log(result)
	let result=JSON.parse(roomlist)
//	console.log(result[0])
//	console.log(result[result.length-1].title)
		const temp=document.createElement("div");
		temp.innerHTML=`<div id="rlist">
						<h1 id="roomTitle">${result[0].title}</h1>
						<p> id="roomPosition"${result[0].position}</p>
						<p id="roomMemo">${result[0].memo}</p>
						<p id="roomChampion">${result[0].champion}</p>
						<button id="delete">방 삭제</button>
						`
		document.querySelector(".rlist").prepend(temp);
	});

$('#test').on("click",function(){
	console.log("create steart")
	let title=$("#title").val()
	let position=$("#position").val()
	let memo=$("#memo").val()
	let champion=$("#champion").val()
	
//	console.log(title)
	
	data={
		"title": title,
		"position": position,
		"memo": memo,
		"champion": champion
	}
	$.ajax({
		method:'post',
		url:'/count',
		data:data,
	}).done(function(count){
//		console.log(count);
//		$("result").html(count)
	})
})
$('#delete').on("click",function(){
	console.log("delete steart")
	let title=$("#roomTitle").val()
	let position=$("#roomPosition").val()
	let memo=$("#roomMemo").val()
	let champion=$("#roomChampion").val()
	
//	console.log(title)
	
	data={
		"title": roomTitle,
		"position": roomPosition,
		"memo": roomMemo,
		"champion": roomChampion
	}
	$.ajax({
		method:'post',
		url:'/delete',
		data:data,
	}).done(function(count){
//		console.log(count);
//		$("result").html(count)
	})
})