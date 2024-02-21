let username
let socket = new WebSocket("ws://192.168.0.153:80/ws/chat");

$(document).ready(function(){
	username=document.getElementById('username').value
})

function enterRoom(socket) {
	var enterMsg = { "type": "ENTER", /*"roomId": JSON.stringify(chatroomId),*/ "sender": JSON.stringify(username), "msg": "" };
	socket.send(JSON.stringify(enterMsg));
}


socket.onopen = function(e) {
	console.log('open server')
	enterRoom(socket);
};

socket.onclose = (e) => {
	console.log("close");
	console.log(e.code, e.wasClean);
}

socket.onerror = function(e) {
	console.log(e);
}

socket.onmessage = function(e) {
	console.log("e.data", e.data);
	let msgArea = document.querySelector('.msgArea');
	let newMsg = document.createElement('div');
	newMsg.innerText = e.data;
	/*msgArea.append(newMsg);*/
}

function sendMsg() {
	let content = document.querySelector('.content').value;
	var talkMsg = {"type": "TALK", "roomId": JSON.stringify(chatroomId), "sender": JSON.stringify(username), "msg": content};
	socket.send(JSON.stringify(talkMsg))
}

function quit() {
	var quitMsg = { "type": "QUIT", "roomId": JSON.stringify(chatroomId), "sender": JSON.stringify(username), "msg": "" }
	socket.send(JSON.stringify(quitMsg));
	socket.close();
	location.href = "/duo_matching/chatList";
}
$(document).on('click','#update',(function(){
	console.log("ajax start");
	$.ajax({
		method:'get',
		url:'/chatroom/list',
	}).done(function(result){
//		console.log(result)
//		let clist=
//		$('#clist').val(result);
//		console.log(result)
		const temp=document.createElement("div")
		$.each(result, function(index, chatlist){
			console.log(chatlist)
			const html=document.createElement("div")
			html.innerHTML=`<div id="rlist">
							<a href="/chat/chatroom?chatroomId=${chatlist.chatroomId}" name="chatroomId" value="${chatlist.chatroomId}">${chatlist.title}</a>
							</br>
							<a>${chatlist.userId}</a>
							</div>
							</br>
							`
			temp.append(html)
		})
		$('#clist').replaceWith(temp)
		
	})
}))
list=setInterval(function(){
	console.log("ajax start");
	$.ajax({
		method:'get',
		url:'/chatroom/list',
	}).done(function(result){
//		console.log(result)
//		let clist=
//		$('#clist').val(result);
//		console.log(result)
		const temp=document.createElement("div")
		$.each(result, function(index, chatlist){
			console.log(chatlist)
			const html=document.createElement("div")
			html.innerHTML=`<div id="rlist">
							<a href="/chat/chatroom?chatroomId=${chatlist.chatroomId}" name="chatroomId" value="${chatlist.chatroomId}">${chatlist.title}</a>
							</br>
							<a>${chatlist.userId}</a>
							</div>
							</br>
							`
			temp.append(html)
		})
		$('#clist').replaceWith(temp)
	})
>>>>>>> YS:GOP.GG-main/src/main/resources/static/javascript/chatlist.js
}, 300000)