$('#deleteAlert').on('show.bs.modal', function (event) {
  let button = $(event.relatedTarget); //モーダルを呼び出すときに使われたボタンを取得
  let recipient = button.data('whatever'); //data-whatever の値を取得

  //Ajaxの処理はここに

  let modal = $(this);  //モーダルを取得
  modal.find('.modal-footer input#deleteUserId').val(recipient); //inputタグにも表示
})