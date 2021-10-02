<%--
  Created by IntelliJ IDEA.
  User: nehep
  Date: 2021-07-13
  Time: 오후 2:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>   <!-- java에서 import문 같은 역할 -->
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // request, response 사용 가능
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("save.jsp");
    String username = request.getParameter("username"); // 요청에서 빼내고
    int age = Integer.parseInt(request.getParameter("age"));   // 나이 빼내고
    Member member = new Member(username, age);  // Member 인스턴스 생성
    System.out.println("member = " + member);   // request의 member 인스턴스 정보 출력
    memberRepository.save(member);              // 저장소에 저장
%>                                              <!-- yacc 코드 쓰는 거랑 비슷 여기 윗 부분은 입력부분 -->
<html>
<head>      <!-- 입력 후 결과 출력하는 jsp form -->
    <meta charset="UTF-8">
</head>
<body>성공
<ul>    <!-- member.id {$ 내부에 } 이렇게 해도 됨 -->
    <li>id=<%=member.getId()%></li>                 <!-- 요련 식으로 중간에 java 코드 삽입. 앞에만 등호가 더 붙으면 출력 -->
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>