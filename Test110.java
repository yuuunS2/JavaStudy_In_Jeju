package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


 //null 값 사용법
public class Test110 {
    public static void main(String[] args) throws Exception {
        //String data = "xyz";
        String data = null;

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@127.0.0.1:1521/XE","HR","hr");

        //String sql = "insert into temp20t values('abc')";
        //sql문 이어붙일 땐 ''에 주의해야한다.
        //data = null 일 때는 에러난다. sql 문에서 널 값을 null이라는 4자리 문자로 인식한다.
        //String sql = "insert into temp20t values('"+ data +"')";
        //해결방안 : 나름 해결책이지만 null가는 필드가 2개면 경우의 수 4개, 3개면 경우의 수 8개.
        //  ㄴ 이런 문제 때문에 현업에서는 PreparedStatement 를 더 선호한다.
        //      : 아예 char는 무조건 4자리 이상을 잡게 하는 경우도 있다.
        String sql = ( data != null ) ? "insert into temp20t values('"+ data +"')" :
                "insert into temp20t values(null)";
        Statement stmt = conn.createStatement();

        stmt.executeUpdate(sql);

        stmt.close();
        conn.close();

    }
}


/*

create table temp20t(
    data char(3) null;
)

insert into temp20t values('abc');
insert into temp20t values(null);
 // null 은 4글자가 아닌 빈 값을 의미.
 // 'null'과 null은 구분하자.
 // select * from temp20t where data = null; 이건 에러.
 // select * from temp20t where data is null; 이게 맞음 //맞지 않는 것은 is not null

 */


