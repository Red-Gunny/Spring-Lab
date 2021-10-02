<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/jsp/members/save.jsp" method="post">     <!-- 전송버튼 누르면 /jsp/members/save.jsp 로 이동해라 post 메소드 req하면서 -->
    username: <input type="text" name="username" />        <!-- 입력칸 정보는 username이라는 key값 -->
    age: <input type="text" name="age" />                  <!-- 여기도 마찬가지 -->
    <button type="submit">전송</button>                      <!-- button 누르면 숑 -->
</form>
</body>
</html>