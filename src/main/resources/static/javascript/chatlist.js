let username
let socket = new WebSocket("ws://"+location.host+":80/ws/chat");

$(document).ready(function(){
	username=document.getElementById('username').value
})
function enterRoom(socket) {
	var enterMsg = { "type": "ENTER", "roomId": 0, "sender": JSON.stringify(username), "msg": "" };
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
	var msg=JSON.parse(e.data);
	if(msg.type=="submit"){
		console.log("submit")
		let sender=msg.sender;
		console.log(sender)
		let submitArea=document.querySelector ('.submitArea');
		let submitMsg=document.querySelector('div');
		submitMsg.innerHTML=`${msg.sender}님이 입장신청을 하였습니다</br>
							<button onclick="accept( ${msg.roomId}, ${username}, ${sender} )">승낙</button>
							<button onclick="denine()">거절</buttton>`
	}
	else if(msg.type=="accept"){
		location.href=`/chat/chatroom?chatroomId=${msg.roomId}`;
	}
	else if(msg.type=="denied"){
		alert("신청이 거부되었습니다");
	}
	let msgArea = document.querySelector('.msgArea');
	let newMsg = document.createElement('div');
	newMsg.innerText = e.data;
	/*msgArea.append(newMsg);*/
}

function submit(roomId){
	var subMsg={"type":"submit", "roomId":JSON.stringify(roomId), "sender":JSON.stringify(username),"msg":"submit"};
	console.log(roomId);
	socket.send(JSON.stringify(subMsg))
}

function accept(roomId, master, sender){
//	let content=document.querySelector('.accept').value;	
	var accMsg={"type":"accept", "roomId":JSON.stringify(roomId), "sender":JSON.stringify(master),"msg":JSON.stringify(sender)};
	socket.send(JSON.stringify(accMsg));
	location.href=`/chat/chatroom?chatroomId=${roomId}`;
}

function denied(){
	var deniedMsg={"type":"denied", "roomId":JSON.stringify(-1), "sender":JSON.stringify(master),"msg":"denied"};
	socket.send(JSON.stringify(deniedMsg));	
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
	location.href = "/chat/out";
}
$(document).ready(function(){
	console.log("ajax start");
	$.ajax({
		method:'get',
		url:'/chatroom/list',
	}).done(function(result){
		const temp=document.createElement("div")
		$.each(result, function(index, chatlist){
			console.log(chatlist)
			const html=document.createElement("div")
			html.innerHTML=`<div id="rlist">
							<a href="/chat/chatroom?chatroomId=${chatlist.chatroomId}" class="chatroomId" name="chatroomId" value="${chatlist.chatroomId}">${chatlist.title}</a>
							</br>
							<a>${chatlist.userId}</a></br>
							<button type="button" class="chatroomId" value="${chatlist.chatroomId}" onclick="submit(${chatlist.chatroomId})">참가신청</button>
							</div>
							</br>
							`
			temp.append(html)
		})
		$('#clist').replaceWith(temp)
		
	})
})
$(document).on('click','#update',(function(){
	console.log("ajax start");
	$.ajax({
		method:'get',
		url:'/chatroom/list',
	}).done(function(result){
		const temp=document.createElement("div")
		$.each(result, function(index, chatlist){
			console.log(chatlist)
			const html=document.createElement("div")
			html.innerHTML=`<div id="rlist">
							<a href="/chat/chatroom?chatroomId=${chatlist.chatroomId}" class="chatroomId" name="chatroomId" value="${chatlist.chatroomId}">${chatlist.title}</a>
							</br>
							<a>${chatlist.userId}</a></br>
							<button type="button" class="chatroomId" value="${chatlist.chatroomId}" onclick="submit(${chatlist.chatroomId})">참가신청</button>
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
		const temp=document.createElement("div")
		$.each(result, function(index, chatlist){
			console.log(chatlist)
			const html=document.createElement("div")
			html.innerHTML=`<div id="rlist">
							<a href="/chat/chatroom?chatroomId=${chatlist.chatroomId}" name="chatroomId" class="chatroomId" value="${chatlist.chatroomId}" onclick="submit()">${chatlist.title}</a>
							</br>
							<a>${chatlist.userId}</a></br>
							<button type="button" class="chatroomId" value="${chatlist.chatroomId}" onclick="submit(${chatlist.chatroomId})">참가신청</button>
							</div>
							</br>
							`
			temp.append(html)
		})
		$('#clist').replaceWith(temp)
	})
}, 300000)