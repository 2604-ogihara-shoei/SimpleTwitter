<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>編集</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="header">
			<a href="./">ホーム</a>
			<a href="setting">設定</a>
			<a href="logout">ログアウト</a>
	</div>



	<div>
		<div class="form-area">
			<form action="edit" method="post">
            	<textarea name="text" cols="100" rows="5" class="tweet-box">${message.text }</textarea>
				<input type="hidden" name="messageId" id="messageId" value="${message.id}"><br />
            	<input type="submit" value="更新">（140文字まで）
			</form>
		</div>
	</div>

	<div class="copyright">
		Copyright(c)ogihara
	</div>
</body>
</html>