window.addEventListener('DOMContentLoaded', function(){
  /** jQueryの処理 */
  $('#deleteAlert').on('show.bs.modal', function(event) {
		let button = $(event.relatedTarget); //モーダルを呼び出すときに使われたボタンを取得
		let recipient = button.data('whatever'); //data-whatever の値を取得

		let modal = $(this);  //モーダルを取得
		modal.find('.modal-footer input#deleteUserId').val(recipient); //inputタグにも表示
	});
});