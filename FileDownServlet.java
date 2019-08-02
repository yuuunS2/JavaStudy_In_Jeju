package study3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileDownServlet extends HttpServlet {

    /* <a href="filedn?fsn=beach.png">fileDown</a> : Test123.jsp
    *
    * */
    @Override
    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        String fsn = request.getParameter("fsn");
        System.out.println(fsn);

        String path = request.getServletContext()
                .getRealPath("/WEB-INF/fileup");


        // System.out.println(path + "/" + fsn);
        // 서버에 보관중인 파일을 읽어서 브라우저로 내보내는 전송 프로그램
        InputStream in = new FileInputStream(path + "/" + fsn);
        OutputStream out = response.getOutputStream();

        byte[] buf = new byte[1024*4];
        int len = 0;

        while( (len = in.read(buf)) != -1 ){
            out.write(buf, 0, len);
            out.flush();
        }
        out.close();
        in.close();
    }
}
