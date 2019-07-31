package main;

public class Test112 {
    //가변 파라미터 : 매개변수로 String울 0 ... * 개를 넣어도 에러가 안나는 선언 방식
    //  실은 String...은 String[]과 동일하다.
    public static void test(String... args){
        System.out.println(args.length);
    }
    public static void test2(Object... args){
        for (int i = 0; i <args.length ; i++) {
            if( args[i] == null ){
                System.out.println("null");
            }
            else if( args[i] instanceof Integer ){
                int r = ((Integer)args[i]).intValue();
                System.out.println(r+1);
            }
            else if( args[i] instanceof Double ){
                double r = ((Double)args[i]).doubleValue();
                System.out.println(r+0.1);
            }
            else if( args[i] instanceof String ){
                System.out.println((String)args[i]);
            }

        }
    }

    public static void main(String[] args){
        test("apple");
        test();
        test("apple", "banana");

        // Object arg_1 = 100; -> auto boxing, 100을 new Integer(100)으로 자동변환
        // Object arg_4 = null; -> 당연히 가능
        test2(100, "Hello", 3.14, null);
    }
}

/*
*   Object i = 100; //오토박싱
*   int j = i; //에러.
*
*   Integer i = 100; // Integer i = new Integer(100); 오토박싱
*   int j = i;       // int j = i.intValue(); 언박싱 가능 !
* */
