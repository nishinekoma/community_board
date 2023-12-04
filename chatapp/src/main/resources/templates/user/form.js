// form.js
//query は　問い　やDBに検索書ける意味を持つ
//htmldocument内のclass名　.jsform　から始まるのを取得.その後クリックしたら関数が働く
document.querySelector('.jsform').addEventListener('click', function () {
    // userFormのデータを取得
    var userFormData = new FormData(document.getElementById('userForm'));

    // mailFormのデータを取得
    var mailFormData = new FormData(document.getElementById('mailForm'));

    // userFormのデータを送信
    fetch('@{/user/signup}', {
        method: 'POST',
        body: userFormData,
    });

    // mailFormのデータを送信
    fetch('@{/user/signup}', {
        method: 'POST',     //HTTPメソッドはPOST
        body: mailFormData, //リクエストのボディに送信するデータはmailFormData
    });
});
