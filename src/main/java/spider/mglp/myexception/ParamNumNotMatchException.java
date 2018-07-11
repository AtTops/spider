package spider.mglp.myexception;

/**
 * <p>pakage: spider.mglp.exception</p>
 *
 * descirption:参数个数不匹配时抛出该异常
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/7/9 下午5:55</pre>
 */
public class ParamNumNotMatchException extends Exception {

    private static final long serialVersionUID = 1L;
    // 无参构造方法
    public ParamNumNotMatchException() {
        super();
    }

    // 有参的构造方法（e.getMessage()）
    public ParamNumNotMatchException(String message) {
        super(message);

    }

    // 用指定的详细信息和原因构造一个新的异常
    public ParamNumNotMatchException(String message, Throwable cause) {

        super(message, cause);
    }

    //  用指定原因构造一个新的异常
    public ParamNumNotMatchException(Throwable cause) {

        super(cause);
    }
}
