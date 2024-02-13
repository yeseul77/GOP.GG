

let socket = new WebSocket("ws://192.168.0.153:80/ws/chat");


let username=document.getElementById('username').value
let chatroomId=document.getElementById('chatroomId').value

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
	let data=e.data;
	var msg=JSON.parse(data);
	console.log("e.data",data);
//	console.log(e.data[10]);
	console.log(msg.sender);
	let msgArea = document.querySelector('.msgArea');
	let newMsg = document.createElement('div');
	newMsg.innerText = msg.sender;
	newMsg.innerText += ":";
	newMsg.innerText+=msg.msg;
	console.log(newMsg)
	msgArea.append(newMsg);
}

function sendMsg() {
	let content = document.querySelector('.content').value;
	var talkMsg = {"type": "TALK", "roomId": chatroomId, "sender": username, "msg": content};
	socket.send(JSON.stringify(talkMsg))
}

function quit() {
	var quitMsg = { "type": "QUIT", "roomId": chatroomId, "sender": username, "msg": "" }
	socket.send(JSON.stringify(quitMsg));
	socket.close();
	location.href = "/duo_matching/chatList";
}
