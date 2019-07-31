package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test111 {
    public static void main(String[] args) throws Exception {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@127.0.0.1:1521/XE", "HR", "hr");

        /*  Statement가 어떤 sql 문장이든 실행하는 범용적인데 반해서
         *  PreparedStatememt 는 생성시에 준비한 그 문장만 신행 할 수 있다.
         * 대신? 영역을 setString, setInteger등을 이용하여 채울수 있다.
         *  (순서가 1부터 시작하는 것에 주의)
         *  execute 시에 매개변수 없음.
         *
         *  이걸 쓰면 null값을 넣을 때 ''를 붙여야 할지를 결정하기 위해 이럱너런 고민할 필요가 없어진다.
         */
        String sql  = "insert into temp20t values(?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "LEE");
        stmt.executeUpdate();

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


