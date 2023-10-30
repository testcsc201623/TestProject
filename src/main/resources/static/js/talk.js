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
	if ($("#statement").val().replace(/\s+/g, '').length > 0) {
		window.sessionStorage.getItem(['user']);
		// /send/messageエンドポイントにメッセージを送信する
		stompClient.send("/send/message", {}, JSON.stringify(
			{ 'userId': $("#userId").val(), 'userName': $("#userName").val(), 'statement': $("#statement").val() }));
		$("#statement").val('');
	}
}

/**
 * 受信したメッセージを表示します。
 */
function showMessage(message) {
	// 受信したメッセージを整形して表示
	$("#message").append(
		"<tr><td class=\"user-name-font\">" + message.userName + "</td></tr><tr><td> " + message.statement.replace(/\n/g, '<br>') + "</td></tr>");
	// 表示後、メッセージエリア、メッセージ入力ボックスのサイズ調整とスクロールの位置調整を実施する
	$("#statement").css({
		'height': `auto`, 
	});
	changeMainAreaHeight();
	scrollBottom();
}

window.addEventListener('DOMContentLoaded', function() {

	// textareaタグを全て取得
	const textareaEls = document.querySelectorAll("textarea");

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

	$(document).ready(function() {
		changeMainAreaHeight();
		scrollBottom();
	});

	textareaEls.forEach((textareaEl) => {
		// デフォルト値としてスタイル属性を付与
		textareaEl.setAttribute("style", `height: ${textareaEl.scrollHeight}px;`);
		// inputイベントが発生するたびに関数呼び出し
		textareaEl.addEventListener("input", setTextareaHeight);
	});

	// textareaの高さを計算して指定する関数
	function setTextareaHeight() {
		this.style.height = "auto";
		this.style.height = `${this.scrollHeight}px`;
		changeMainAreaHeight();
		scrollBottom();
	}

});

setTimeout("connect()", 3000);

/**
 * ページの一番下までスクロールします。
 */
function scrollBottom() {
	let elm = document.documentElement;
	// scrollHeight ページの高さ clientHeight ブラウザの高さ
	let bottom = elm.scrollHeight - elm.clientHeight;
	// 垂直方向へ移動
	window.scroll(0, bottom);
}

/**
 * メインエリアの下マージンをフッターの高さによって変更する
 */
function changeMainAreaHeight() {
	let paddingBottomHeight = $('#footer_area').height() +10;
	$("#messageArea").css({
		'padding-bottom': paddingBottomHeight + `px`,
	});
}