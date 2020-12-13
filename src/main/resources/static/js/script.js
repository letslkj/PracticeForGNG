/*ログインボタンに作動するスクリプト*/
function checkLogin(){
	
	let form = document.getElementById("frm");		
	
	//IDチェック形式
	let loginid = form.loginid.value;
	let idPattern = /^[A-za-z0-9]{4,20}$/; //IDパターン
	let errMsg = ""; 
	if(!idPattern.test(loginid)) {
		errMsg = "英文字と数字のみ (4 ~ 20文字)"
		form.loginid.value = "";
		document.getElementById("errMsg").innerHTML = errMsg; //エラーメッセージ表示
		form.loginid.focus();
		return false;
	}	
	
	//パスワードチェック形式
	let loginpw = form.loginpw.value;
	let pwPattern = /^(?=.*[A-Z])[A-Za-z0-9]{8,32}$/; //パスワードパターン
	if(!pwPattern.test(loginpw)) {
		errMsg = "英文字の大文字は必ず１文字 (8 ~ 32文字)"
		form.loginpw.value = "";
		document.getElementById("errMsg").innerHTML = errMsg; //エラーメッセージ表示
		form.loginpw.focus();
		return false;
	}
	
	form.action = "/login";
	form.method = "POST";

	form.submit(); 	
	
}

function deleteBook(bookId) {
	confirm("削除しますか？")
	let form = document.getElementById("frm");		
	form.bookId.value = bookId;
	form.action = "/deleteBook";
	form.method = "POST";

	form.submit(); 	
	
}

function goUpdatePage(bookId) {
	let form = document.getElementById("frm");
	form.bookId.value = bookId;
	form.action = "/goUpdateForm";
	form.method = "POST";

	form.submit(); 	
	
}


/*登録ボタンに作動するスクリプト*/
function checkForm(form){
	
	//タイトル有効性チェック
	let name = form.itemName.value;
	if(name.length<1) {
		alert("タイトルを入力してください。");
		form.itemName.focus();
		return false;
	} else if(name.length>100) {
		alert("タイトルは最大100文字まで。");
		form.itemName.value="";
		form.itemName.focus();
		return false;
	}
	
	//作家有効性チェック
	let author = form.itemAuthor.value;
	if(author.length<1) {
		alert("著者を入力してください。");
		form.itemAuthor.focus();
		return false;
	} else if(author.length>50) {
		alert("著者は最大50文字まで");
		form.itemAuthor.value="";
		form.itemAuthor.focus();
		return false;
	}
	
	//出版社有效性チェック
	let publisher = form.itemPublisher.value;
	if(publisher.length!==0){
		if(publisher.length>50) {
			alert("出版社は最大50字まで");
			form.itemPublisher.value="";
			form.itemPublisher.focus();
			return false;
		}
	}
	
	//発売日有効性チェック
/*
	let date = form.itemPublicationDate.value;
	let datePattern = /^(19|20)\d{2}\/(0[1-9]|1[012])\/(0[1-9]|[12][0-9]|3[0-1])$/;
	if(date.length!==0){
		if(!datePattern.test(date)) { 
			alert("日付形式に合わせて入力してください。")
			form.itemPublicationDate.value="";
			form.itemPublicationDate.focus();
			return false;
		}
	}
*/	
	let inputDate = form.itemPublicationDate.value;
	let datePattern = /^(19|20)\d{2}\/([1-9]|1[012])\/([1-9]|[12][0-9]|3[0-1])$/;
	if(inputDate.length!==0){
		if(!datePattern.test(inputDate)) { 
			alert("日付形式に合わせて入力してください。")
			form.itemPublicationDate.value="";
			form.itemPublicationDate.focus();
			return false;
		}
		
		let inputDateArr = inputDate.split('/');
		let inputYear = inputDateArr[0];
		let inputMonth = inputDateArr[1];
		let inputDay = inputDateArr[2];
		
		let compareDate = new Date(inputDate);
		let compareYear = compareDate.getFullYear(); 
		let compareMonth = compareDate.getMonth()+1; 
		let compareDay = compareDate.getDate();
		
		if(inputYear!=compareYear || inputMonth!=compareMonth || inputDay!=compareDay) {
			alert("存在しない日付です。");
			form.itemPublicationDate.value="";
			form.itemPublicationDate.focus();
			return false;
		}
	}
	
	
	//価額有効性チェック
	let price = form.itemPrice.value;
	if(price.length<1){
		alert("価額を入力してください。")
		form.itemPrice.focus();
		return false;
	}
	let pricePattern = /^[0-9]+$/;
	if(!pricePattern.test(price)) { 
		alert("数字のみ入力してください。")
		form.itemPrice.value="";
		form.itemPrice.focus();
		return false;
	} 
   	
   	return true;
}

function saveBook() {

	let form = document.getElementById('frm');
	
	if(checkForm(form)){
		form.action = "/saveBook";
		form.method = "POST";
	
		form.submit(); 
	}
}

function updateBook() {

	let form = document.getElementById('frm');

	
	if(checkForm(form)){
		form.action = "/updateBook";
		form.method = "POST";
	
		form.submit(); 
	}
}
