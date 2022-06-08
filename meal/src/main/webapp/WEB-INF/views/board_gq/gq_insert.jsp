<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰쓰기</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	if (document.getElementById("input_check").checked) {
		document.getElementById("input_check_hidden").disabled = true;
	}
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function backToList(obj) {
		obj.action = "${contextPath}/board/listArticles.do";
		obj.submit();
	}
</script>
<title>새글 쓰기 창</title>
<style>
.board-wrap {
	width: 800px;
}

.table-wrap table {
	width: 100%;
}

.board-wrap>tr {
	width: 100%;
}

.td2>input {
	width: 100%;
	padding: 0;
	border-radius: 4px;
}

.board-b-wrap {
	height: 30px;
}

.board-b-wrap>input {
	float: right;
	display: inline-block;
	background-color: #ffc0cf;
	border-radius: 5px;
	border: 4px #cccccc;
	color: black;
	font-size: 15px;
	padding: 0;
	width: 80px;
	height: 40px;
	transition: all 0.5s;
	cursor: pointer;
	margin: 5px;
}

.table-wrap th.td1 {
	border-radius: 4px;
	width: 150px;
	align: center;
	background: #ffc0cf;
}

.table-wrap td.td2 {
	border-radius: 4px;
	border: 1px solid black;
}

.table-wrap textarea {
	width: 100%;
	border-radius: 4px;
}

.filecss {
	align: center;
}

#preview {
	width: 100%;
	height: 100%;
	border: 0;
}

.board-title>h1 {
	border-bottom: 3px solid #ffc0cf;
}

.table-wrap td.td2>.B_Uid {
	padding: 0;
	border-radius: 4px;
	display: inline-block;
	width: 350px;
}

.table-wrap td.td2>.B_Uid1 {
	padding: 0;
	border-radius: 4px;
	display: inline-block;
	float: right;
}

.table-wrap td.td3 {
	border-radius: 4px;
	border: 1px solid black;
}

.B.Uid1, .B.Uid2, .secret-wrap {
	display: inline-block;
}

#myform fieldset {
	display: inline-block;
	direction: rtl;
	border: 0;
}

#myform fieldset legend {
	text-align: right;
}

#myform input[type=radio] {
	display: none;
}

#myform label {
	font-size: 3em;
	color: transparent;
	text-shadow: 0 0 0 #f0f0f0;
}

#myform label:hover {
	text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}

#myform label:hover ~ label {
	text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}

#myform input[type=radio]:checked ~ label {
	text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
}
.inline{
display:inline-block;
font-size: 25px;
}
.span1{
font-size:15px;
}
</style>
</head>
<body>
	<c:if test='${not empty message }'>
		<script>
			window.onload = function() {
				result();
			}

			function result() {
				alert("${message}");
			}
		</script>
	</c:if>
	<div class='board-wrap'>
		<div class='board-title'>
			<h1>상품문의 작성</h1>
		</div>
		<form name="frmArticle" id="myform" method="post"
			action="${contextPath}/boardGq/boardGqinsert.do"
			enctype="multipart/form-data">
			<div class='table-wrap'>
				<table>
					<tr>
						<th class="td1">상품</th>
						<td>
					<img
									src="${contextPath}/download1.do?g_id=${goodsInfo.g_id}&cate=main"
									width="80px;" height="50px" alt = "상품이미지"><div class = "inline">
							<span class="span1">상품</span><br><span class="span2">${goodsInfo.g_name }</span></div>
							<input type="hidden" name="g_id" value="${goodsInfo.g_id}" /> 
							<input type="hidden" name="s_id" value="${goodsInfo.s_id}" />

						</td>
					</tr>
					<tr>
						<th class="td1">작성자 아이디</th>
						<td class="td3"><input class="B_Uid" type=text
							value="${ memberInfo.u_id}" name="_u_id" disabled /> <input
							type="hidden" value="${memberInfo.u_id}" name="u_id"> <input
							type="hidden" value="user" name="reg_id"><input
							type="hidden" value="0" name="parentNo">
							<div class="secret-wrap">
								<input class="B_Uid1" type="checkbox" value="Y" name="secret">비밀글입니다
							</div></td>
					</tr>
					<tr>
						<th class="td1">제목</th>
						<td class="td2"><input type=text value="타이틀필드" name="title"
							id="i_title" /></td>

					</tr>

					<tr>
						<th class="td1">내용</th>
						<td class="td2"><textarea rows="20" cols="70" name="content"
								id="i_content"></textarea></td>
					</tr>
					<tr height="200px">
						<th class="td1">이미지파일 첨부 <input class="filecss" type="file"
							name="imageFileName" onchange="readURL(this);" /></th>
						<td class='td2'><input type="image" id="preview" src="#"
							alt="이미지 출력창 입니다." /></td>
					</tr>
				</table>
			</div>
			<div class="board-b-wrap">
				<input type='submit' value="글쓰기" /> <input type=button value="목록"
					onClick="backToList(this.form)" />
			</div>

		</form>

	</div>
</body>

</html>
</body>