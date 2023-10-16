window.addEventListener('DOMContentLoaded', function(){
  /** jQueryの処理 */
  $('#updatePasswordAlert').on('show.bs.modal', function(event) {
		let modal = $(this);  //モーダルを取得
		modal.find('.modal-footer input#hiddenOldPassword').val($('#oldPassword').val());
		modal.find('.modal-footer input#hiddenNewPassword').val($('#newPassword').val());
		modal.find('.modal-footer input#hiddenNewPassword2').val($('#newPassword2').val());
	});
});