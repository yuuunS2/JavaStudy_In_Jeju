
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<!--
    <input type="file"> �� ������ ������ ���ε��Ҷ� ����Ѵ�.
    �� ���� �ݵ�� enctype="multipart/form-data" �� ����Ѵ�.

    file up�� �ش��ϴ� ������ cos.jar ������ MultipartRequest�� �̿��Ͽ�
        ���ε带 ó���ϴ� ���� �Ϲ����̴�.

-->
<body>
    <form method ="POST" action="fileup" enctype="multipart/form-data">
        <input type="text" name="title" size="20"/>
        <input type="file" name="apple"/>
        <input type="submit" />
    </form>
</body>
</html><%-- Test122.jsp --%>
