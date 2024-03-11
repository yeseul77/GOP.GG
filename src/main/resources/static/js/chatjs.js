let socket = new WebSocket("ws://"+location.host+"/ws/chat");


let username
let chatroomId

$('document').ready(function(){
	username=document.getElementById('username').value
	chatroomId=document.getElementById('chatroomId').value
	console.log(chatroomId);
	console.log(document.title);
	document.title=chatroomId
})

function enterRoom(socket) {
	var enterMsg = { "type": "ENTER", "roomId": chatroomId, "sender": username, "msg": "" };
//	socket.send(JSON.stringify(enterMsg));
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
	let msg=JSON.parse(e.data);
	if(msg.roomId==chatroomId){
		console.log(msg.message);
		let msgArea = document.querySelector('.msgArea');
		let newMsg = document.createElement('div');
		newMsg.innerText =msg.sender+": ";
		newMsg.innerText += msg.message;
		console.log(newMsg)
		msgArea.append(newMsg);
	}
}

function sendMsg() {
	let content = document.querySelector('.content').value;
	var talkMsg = {"type": "TALK", "roomId": chatroomId, "sender": username, "message": content};
	socket.send(JSON.stringify(talkMsg))
	$('.content').val('')
}

function outing(){
	window.close();
}

function quit() {
	location.href = "/chat/out?chatroomId="+chatroomId;
 	var quitMsg = { "type": "QUIT", "roomId": chatroomId, "sender": username, "message": "" }
	socket.send(JSON.stringify(quitMsg));
	socket.close();
	window.close();
}
$(document).ready(function(){
	$('.content').focus();
	$.ajax({
		method:'GET',
		url:'/chatroom/chatlist',
		data:{"chatroomId" : chatroomId},
	}).done(function(result){
		console.log(result)
		const temp=document.createElement("div")
		temp.classList.add("listArray")
		$.each(result, function(index, msg){
			console.log(msg.sender,":",msg.message)
			const html=document.createElement("div")
			html.classList.add("listEl")
			html.innerHTML=`${msg.sender}: ${msg.message}`
			console.log(html)
			temp.append(html)
		})
		console.log(temp)
		$('.msgArea').append(temp)
		})
})
$(document).keyup(function(event){
	if(event.which===13){
		sendMsg()
	}
	else if(event.which===27){
		outing()
	}
	else{
		$('.content').focus();
	}
})
