let username
let socket = new WebSocket("ws://"+location.host+":80/ws/chat");
let popOption="width=650px, height=550px, top=300px, left=300px. scrollbars=yes";
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
		let submitArea=document.getElementById('submitArea');
		let chatInvite=document.getElementById('chatInvite');
		let submitMsg=document.createElement('div');
		submitMsg.classList.add('invite');
		submitMsg.innerHTML=`$<p>{msg.sender}님이 대화 신청을 하였습니다.</p>
							<button class="okChat" onclick="accept(${roomId}, '${username}', '${sender}')">승낙</button>
							<button class="noChat" onclick="denine()">거절</button>`
		submitArea.append(submitMsg.cloneNode(true))
		chatInvite.append(submitMsg.cloneNode(true))					
		
	} else if(msg.type=="accept"){
		let roomId = msg.roomId;
		let popOption="width=650px, height=550px, top=300px, left=300px. scrollbars=yes";
//		location.href=`/chat/chatroom?chatroomId=${msg.roomId}`;
		window.open(`/chat/firstchatroom?chatroomId=${roomId}`,'pop', popOption);
		location.href="/chat/chatList"
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
//	location.href=`/chat/chatroom?chatroomId=${roomId}`;
	window.open(`/chat/chatroom?chatroomId=${roomId}`,'pop', popOption);
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
		url:'/chatroom/mylist',
	}).done(function(result){
		const temp=document.createElement("div")
		temp.classList.add("listArray")
		$.each(result, function(index, mylist){
			console.log(mylist)
			const html=document.createElement("div")
			html.classList.add("listEl")
			html.innerHTML=`<div id="rlist">
							<a>${mylist.chatMember}</a>
							<a class="chatroomId">${mylist.title}</a>
							<button type="button" class="chatroomId" onclick="popup(${mylist.roomId})">들어가기</button>
							`
			temp.append(html)
		})
		$('#mylist').replaceWith(temp)
		})
	$.ajax({
		method:'get',
		url:'/chatroom/list',
	}).done(function(result){
		const temp=document.createElement("div")
		temp.classList.add("listArray")
		$.each(result, function(index, chatlist){
			console.log(chatlist)
			const html=document.createElement("div")
			html.classList.add("listEl")
			html.innerHTML=`<div id="rlist">
							<div class="chat-head">
							 <div class="chat-po"><a>${chatlist.position}</a></div>
							 <h1 class="chatTitle" name="chatroomId" value="${chatlist.chatroomId}">${chatlist.title}</h1>
							</div>
							<div class="hostMemo"><p>${chatlist.memo}</p></div>																				
							<div class="chat-footer">
							<div class="hostInfo">
							 <a>${chatlist.userId}</a>|<a>${chatlist.champion}</a>				 
							</div>
							<button type="button" class="chatBtn" onclick="submit(${chatlist.chatroomId})">참가신청</button>
							<div>							
							</div>				
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
		url:'/chatroom/mylist',
	}).done(function(result){
		const temp=document.createElement("div")
		temp.classList.add("listArray")
		$.each(result, function(index, mylist){
			console.log(mylist)
			const html=document.createElement("div")
			html.classList.add("listEl")
			html.innerHTML=`<div id="rlist">
							<a>${mylist.chatMember}</a>
							<a class="chatroomId">${mylist.title}</a>
							<button type="button" class="chatroomId" onclick="popup(${mylist.roomId})">들어가기</button>
							`
			temp.append(html)
		})
		$('#mylist').replaceWith(temp)
		})
	$.ajax({
	method:'get',
	url:'/chatroom/list',
	}).done(function(result){
		const temp=document.createElement("div")
		temp.classList.add("listArray")
		$.each(result, function(index, chatlist){
			console.log(chatlist)
			const html=document.createElement("div")
			html.classList.add("listEl")
			html.innerHTML=`<div id="rlist">
							<div class="chat-head">
							 <div class="chat-po"><a>${chatlist.position}</a></div>
							 <h1 class="chatTitle" name="chatroomId" value="${chatlist.chatroomId}">${chatlist.title}</h1>
							</div>
							<div class="hostMemo"><p>${chatlist.memo}</p></div>																				
							<div class="chat-footer">
							<div class="hostInfo">
							 <a>${chatlist.userId}</a>|<a>${chatlist.champion}</a>
							</div>
							<button type="button" class="chatBtn" onclick="submit(${chatlist.chatroomId})">참가신청</button>
							<div>							
							</div>				
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
		url:'/chatroom/mylist',
	}).done(function(result){
		const temp=document.createElement("div")
		temp.classList.add("listArray")
		$.each(result, function(mylist){
			console.log(mylist.chatroomId)
			console.log(mylist)
			const html=document.createElement("div")
			html.classList.add("listEl")
			html.innerHTML=`<div id="rlist">
							<a>${mylist.chatMember}</a>
							<a class="chatroomId">${mylist.title}</a>
							<button type="button" class="chatroomId" onclick="popup(${mylist.roomId})">들어가기</button>
							`
			temp.append(html)
		})
		$('#mylist').replaceWith(temp)
		})
	$.ajax({
		method:'get',
		url:'/chatroom/list',
	}).done(function(result){
		const temp=document.createElement("div")
		temp.classList.add("listArray")
		$.each(result, function(chatlist){
			console.log(chatlist)
			const html=document.createElement("div")
			html.classList.add("listEl")
			html.innerHTML=`<div id="rlist">
							<div class="chat-head">
							 <div class="chat-po"><a>${chatlist.position}</a></div>
							 <h1 class="chatTitle" name="chatroomId" value="${chatlist.chatroomId}">${chatlist.title}</h1>
							</div>
							<div class="hostMemo"><p>${chatlist.memo}</p></div>																				
							<div class="chat-footer">
							<div class="hostInfo">
							 <a>${chatlist.userId}</a>|<a>${chatlist.champion}</a>
							</div>
							<button type="button" class="chatBtn" onclick="submit(${chatlist.chatroomId})">참가신청</button>
							<div>							
							</div>				
							`
			temp.append(html)
		})
		$('#clist').replaceWith(temp)
	})
}, 300000)
function popup(roomId){
	console.log(roomId);
	window.open(`/chat/chatroom?chatroomId=${roomId}`,'pop', popOption);
}


const checkboxes = document.querySelectorAll('.chatRoomInfo-line input[type="checkbox"]');

// 모든 체크박스에 대한 변경 이벤트를 한 번만 처리
document.querySelector('.chatRoomInfo-line').addEventListener('change', function(event) {
    if (event.target.type === 'checkbox') { // 변경된 요소가 체크박스인지 확인
        let checkedCount = 0;
        checkboxes.forEach(checkbox => {
            if (checkbox.checked) {
                checkedCount++;
            }
        });
        
        if (checkedCount > 2) {
            event.target.checked = false; // 변경된 요소의 체크를 취소
            alert("최대 2개까지 선택할 수 있습니다.");
        }
    }
});


const toastTrigger = document.getElementById('submitArea')
const toastLiveExample = document.getElementById('liveToast')

if (toastTrigger) {
  const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)
  const observer = new MutationObserver(function(mutationsList, observer) {
  mutationsList.forEach(function(mutation) {
    if (mutation.type === 'childList') {
      // 자식 요소가 추가될 때마다 실행
      toastBootstrap.show()
    }
  });
});
const config = { childList: true }; // 자식 요소의 추가와 삭제를 감지
observer.observe(toastTrigger, config);   	
}





  

