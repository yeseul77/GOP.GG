

let socket = new WebSocket("ws://"+location.host+"/ws/chat");


let username
let chatroomId

$('document').ready(function(){
	username=document.getElementById('username').value
	chatroomId=document.getElementById('chatroomId').value
})

function enterRoom(socket) {
	var enterMsg = { "type": "ENTER", "roomId": chatroomId, "sender": username, "msg": "" };
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
	let msg=JSON.parse(e.data);
	console.log(msg.message);
	let msgArea = document.querySelector('.msgArea');
	let newMsg = document.createElement('div');
	newMsg.innerText =msg.sender+": ";
	newMsg.innerText += msg.message;
	console.log(newMsg)
	msgArea.append(newMsg);
}

function sendMsg() {
	let content = document.querySelector('.content').value;
	var talkMsg = {"type": "TALK", "roomId": chatroomId, "sender": username, "message": content};
	socket.send(JSON.stringify(talkMsg))
}

function quit() {
	var quitMsg = { "type": "QUIT", "roomId": chatroomId, "sender": username, "message": "" }
	socket.send(JSON.stringify(quitMsg));
	socket.close();
	location.href = "/chat/out?chatroomId="+chatroomId;
}
