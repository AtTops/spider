/**
 * <p>pakage: PACKAGE_NAME</p>
 * <p>
 * descirption:
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/14 下午4:29</pre>
 */
public class Test {
    public static void main(String[] args) {
        String s = "a,b,c,v,,,";
        String s1 = "a,b,c,v,";
        String s2 = "a,b,c,v";
        while (s.endsWith(",")){
            s = s.substring(0,s.length() -1);
        }
        System.out.println(s);
        System.out.println(s1.length());
        System.out.println(s2.length());
    }
}
