package study3;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class FileUpServlet extends HttpServlet {

    private ServletContext application = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        application = config.getServletContext();
    }

    @Override
    public void service(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        String l = process2(request);
        System.out.println(l);
    }

    /*  request 는 요청, 브라우저에서 서버로 전달하는 개념
    *   request.getInputStream() 을 이용해 브라우저에서 서버로 전달되는
    *   내용을 볼 수 있다.
    *   (이런 저런 내용들이 막 모여있다. )
    *
    *   이 내용을 재구성하는 기능이 cos.jar 파일의 MultipartRequest 이다.
    *  */
    public String process( HttpServletRequest request) throws IOException{
        byte[] buf = new byte[1024];
        int len = 0;

        StringBuffer sb = new StringBuffer();

        InputStream in = request.getInputStream();
        while( (len = in.read(buf)) != -1){
            sb.append( new String( buf, 0, len) );
        }
        in.close();

        return sb.toString();
    }

    /*  cos.jar 파일 클라우드에서 다운받아서 WEB-INF/lib 안에 넣어주기
     *  WEB-INF/fileup 폴더 생성
     *
     *  DefaultFileRenamePolicy 는 이름이 겹칠때 이름을 바꿔서 올려준다.
     *      - 올릴 때 이름과 서버에 올려진 이름이 다를 수 있다.
     *      - 이거 대신 null 을 넣어주면 이름이 같은 파일을 업로드하게 되면 덮어쓰기 된다.
     *  */
    public String process2( HttpServletRequest request) throws IOException{
        String path = application.getRealPath("/WEB-INF/fileup");
        System.out.println(path);

        //20MB 이상은 업로드 불가능
        MultipartRequest mpr = new MultipartRequest(
                request, path, 1024*1024*20, "UTF-8", new DefaultFileRenamePolicy());

        //업로드한 원래 파일 이름
        String ofn = mpr.getOriginalFileName("apple");

        //중첩되는 경우에 이름을 바꾸어 저장하는 이름
        String fsn = mpr.getFilesystemName("apple");
        System.out.println(ofn + "," + fsn);

        // MultipartRequest 쓰면 request.getParameter 못 쓴다.
        // 대신 MultipartRequest 안의 getParameter 써야한다.
        //      - 한글 처리도 알아서 다 해준다.
        String title = mpr.getParameter("title");
        System.out.println(title);

        return null;
    }

}
