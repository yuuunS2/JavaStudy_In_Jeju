
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<!--
    <input type="file"> 은 파일을 서버로 업로드할때 사용한다.
    이 때는 반드시 enctype="multipart/form-data" 를 사용한다.

    file up에 해당하는 서블릿은 cos.jar 파일의 MultipartRequest를 이용하여
        업로드를 처리하는 것이 일반적이다.

-->
<body>
    <form method ="POST" action="fileup" enctype="multipart/form-data">
        <input type="text" name="title" size="20"/>
        <input type="file" name="apple"/>
        <input type="submit" />
    </form>
</body>
</html><%-- Test122.jsp --%>
