package mail;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * <p>pakage: mail</p>
 * <p>
 * descirption: 发送尺码试穿统计报告到工程团队
 *
 * @author wanghai
 * @version V1.0
 * @since <pre>2018/8/16 上午11:58</pre>
 */
public class SendEmail {
    // 登录用户名
    private static String account;
    // 登录密码
    private static String pass;
    // 服务器地址（邮件服务器）
    private static String host;
    // 端口
    private static String port;
    // 协议
    private static String protocol;

    static {
        Properties prop = new Properties();
        try {
            prop = PropertiesLoaderUtils.loadAllProperties("email.properties");
        } catch (IOException e) {
            System.out.println("邮箱属性文件加载失败");
        }
        account = prop.getProperty("e.account");
        pass = prop.getProperty("e.pass");
        host = prop.getProperty("e.host");
        port = prop.getProperty("e.port");
        protocol = prop.getProperty("e.protocol");
    }

    static class MyAuthenricator extends Authenticator {
        String user;
        String passw;

        MyAuthenricator(String user, String passw) {
            this.user = user;
            this.passw = passw;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, passw);
        }
    }

    // 收件人
    private String to;
    // 主题
    private String subject;
    // 内容
    private String content;
    // 附件路径
    //private String fileStr;

    public SendEmail(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }


    public void send() {
        Properties prop = new Properties();
        // 协议
        prop.setProperty("mail.transport.protocol", protocol);
        // 服务器
        prop.setProperty("mail.smtp.host", host);
        // 端口
        prop.setProperty("mail.smtp.port", port);
        // 使用smtp身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 使用SSL，企业邮箱必需！
        // 开启安全协议
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        prop.put("mail.smtp.ssl.enable", "true");
        assert sf != null;
        prop.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getDefaultInstance(prop, new MyAuthenricator(account, pass));
        session.setDebug(true);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            // 发件人,可以设置发件人的别名
            mimeMessage.setFrom(new InternetAddress(account, "XXX"));
            // 收件人
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 主题
            mimeMessage.setSubject(subject);
            // 时间
            mimeMessage.setSentDate(new Date());
            // 容器类，可以包含多个MimeBodyPart对象
            Multipart mp = new MimeMultipart();

            // MimeBodyPart可以包装文本，图片，附件
            MimeBodyPart body = new MimeBodyPart();
            // HTML正文
            body.setContent(content, "text/html; charset=UTF-8");
            mp.addBodyPart(body);

            // 添加图片&附件
//            if (fileStr.length() > 0) {
//                body = new MimeBodyPart();
//                body.attachFile(fileStr);
//                mp.addBodyPart(body);
//            }

            // 设置邮件内容
            mimeMessage.setContent(mp);
            // 仅发送文本
//            mimeMessage.setText(content);
            mimeMessage.saveChanges();
            // 发送
            Transport.send(mimeMessage);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}
