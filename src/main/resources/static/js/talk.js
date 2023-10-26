let stompClient = null;

/**
 * 接続状況を設定します。
 */
function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	} else {
		$("#conversation").hide();
	}
	$("#message").html("");
}

/**
 * 通信を接続します。
 */
function connect() {
	var socket = new SockJS('/websocket'); // WebSocket通信開始
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		// /receive/messageエンドポイントでメッセージを受信し、表示する
		stompClient.subscribe('/receive/message', function(response) {
			showMessage(JSON.parse(response.body));
		});
	});
}

/**
 * 通信を切断します。
 */
function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

/**
 * メッセージをバック側に送信します。
 */
function sendMessage() {
	window.sessionStorage.getItem(['user']);
	// /send/messageエンドポイントにメッセージを送信する
	stompClient.send("/send/message", {}, JSON.stringify(
		{ 'id': $("#userId").val(), 'name': $("#userName").val(), 'statement': $("#statement").val() }));
	$("#statement").val('');
}

/**
 * 受信したメッセージを表示します。
 */
function showMessage(message) {
	// 受信したメッセージを整形して表示
	$("#message").append(
		"<tr><td>" + message.name + ": " + message.statement + "</td></tr>");
	// 表示後、スクロールバーを一番下に下げる
	scrollBottom();
}

window.addEventListener('DOMContentLoaded', function() {
	$("#sendMessage").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() {
		connect();
	});
	$("#disconnect").click(function() {
		disconnect();
	});
	$("#send").click(function() {
		sendMessage();
	});
	
	$(document).ready( function(){
		scrollBottom();
	});
});

setTimeout("connect()", 3000);

/**
 * ページの一番下までスクロールします。
 */
function scrollBottom(){
  let elm = document.documentElement;
  // scrollHeight ページの高さ clientHeight ブラウザの高さ
  let bottom = elm.scrollHeight - elm.clientHeight;
  // 垂直方向へ移動
  window.scroll(0, bottom);

}