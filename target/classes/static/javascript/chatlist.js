let username
let socket = new WebSocket("ws://"+location.host+":80/ws/chat");

$(document).ready(function(){
	username=document.getElementById('username').value
	console.log(username)
})
function enterRoom(socket) {
//	var enterMsg = { "type": "ENTER", "roomId": 0, "sender": JSON.stringify(username), "msg": "" };
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
	var msg=JSON.parse(e.data);
	if(msg.type=="submit"){
		console.log("submit")
		let roomId = msg.roomId;
		let sender = msg.sender.replace(/"/g, '');
		console.log("sender : ", sender)
		console.log("username : ", username)
		let submitArea=document.querySelector ('.submitArea');
		let submitMsg=document.createElement('div');
		submitMsg.innerHTML=`${msg.sender}님이 입장신청을 하였습니다</br>
							<button onclick="accept(${roomId}, '${username}', '${sender}')">승낙</button>
							<button onclick="denine()">거절</buttton>`
		submitArea.append(submitMsg)
	} else if(msg.type=="accept"){
		location.href=`/chat/chatroom?chatroomId=${msg.roomId}`;
	} else if(msg.type=="denied"){
		alert("신청이 거부되었습니다");
		location.href="/chat/chatList"
	}
	let msgArea = document.querySelector('.msgArea');
	let newMsg = document.createElement('div');
	newMsg.innerText = e.data;
	/*msgArea.append(newMsg);*/
}
function submit(roomId){
	var subMsg={"type":"submit", "roomId":JSON.stringify(roomId), "sender":JSON.stringify(username),"msg":"championname, position"};
	console.log(roomId);
	socket.send(JSON.stringify(subMsg))
}

function accept(roomId, master, sender){
	console.log(sender);
	var accMsg={"type":"accept", "roomId":JSON.stringify(roomId), "sender":JSON.stringify(sender),"msg":JSON.stringify(master)};
	console.log(accMsg);
	socket.send(JSON.stringify(accMsg));
	location.href=`/chat/chatroom?chatroomId=${roomId}`;
}

function denine(){
	var deniedMsg={"type":"denied", "roomId":JSON.stringify(-1), "sender":JSON.stringify(username),"msg":"denied"};
	socket.send(JSON.stringify(deniedMsg));	
	location.href="/chat/chatList"
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
							<a>${chatlist.champion}</a></br>
							<a>${chatlist.position}</a></br>
							<button type="button" class="chatroomId" onclick="submit(${chatlist.chatroomId})">참가신청</button>
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
							<button type="button" class="chatroomId" onclick="submit(${chatlist.chatroomId})">참가신청</button>
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
							<button type="button" class="chatroomId" onclick="submit(${chatlist.chatroomId})">참가신청</button>
							</div>
							</br>
							`
			temp.append(html)
		})
		$('#clist').replaceWith(temp)
	})
}, 300000)