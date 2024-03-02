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
}, 300000)