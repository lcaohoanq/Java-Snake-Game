package utils;

import java.time.LocalDateTime;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;

public class EmailUtils {


    private final String eFrom = EnvUtils.get("MAIL_HOST");
    private final String ePass = EnvUtils.get("MAIL_PASS");

    // check email
    public boolean isValidEmail(String email) {
        return Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Z|a-z]{2,}$", Pattern.CASE_INSENSITIVE).matcher(email).matches();
    }

    private Authenticator getAuthenticator() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(eFrom, ePass);
            }
        };
    }

    public void sendEmail(String subject, String messgage, String to) {
        // Properties
        Properties props = new Properties();

        //Su dung server nao de gui mail- smtp host
        props.put("mail.smtp.host", "smtp.gmail.com");

        // TLS 587 SSL 465
        props.put("mail.smtp.port", "smtp.gmail.port");

        // dang nhap
        props.put("mail.smtp.auth", "true");

        props.put("mail.smtp.starttls.enable", "true");

        //dang nhap tai khoan
        // phien lam viec
        Session session = Session.getInstance(props, getAuthenticator());

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML, charset=UTF-8");
            msg.setFrom(new InternetAddress("noreply@testsendemail"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // tieu de
            msg.setSubject(subject, "UTF-8");
            // Noi dung
            msg.setContent(messgage, "text/html; charset=UTF-8");
            // Gui email
            Transport.send(msg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String subjectContact(String name) {
        return "Java Snake Game Corporation - Welcome " + name + ", thanks for joining us!";
    }

    public String messageNewOrder(String name, String verifyToken) {
        return "<!DOCTYPE html>\n"
            + "<html>\n"
            + "    <head>\n"
            + "        <title>Welcome to Java Snake Game</title>\n"
            + "        <meta charset=\"UTF-8\">\n"
            + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            + "    </head>\n"
            + "    <body style=\"font-family:arial, helvetica, sans-serif;\n"
            + "          font-size:14px;\n"
            + "          line-height:20px;\n"
            + "          color: #444;\n"
            + "          background:#f2f2f2;\">\n"
            + "        <table width=\"100%\" class=\"wrapper\" style=\" margin:20px 0;\">\n"
            + "            <tr>\n"
            + "                <td class=\"container\"> \n"
            + "                    <div class=\"content\" style=\"display: block!important;\n"
            + "                         max-width: 600px!important;\n"
            + "                         margin: 0 auto!important;\n"
            + "                         clear: both!important;\n"
            + "                         background:white;\">\n"
            + "                        <table cellspacing=\"20\" width=\"100%\">\n"
            + "                            <tr>\n"
            + "                                <td>\n"
            + "                                    <p class=\"brand\" style=\"margin:5px 0 0; font-size:30px;\n"
            + "                                       margin:20px 0;\"><span style=\"color:#19b719;\">Java Snake Game</span> Corporation</p> \n"
            + "                                </td>\n"
            + "                            </tr>\n"
            + "                            <tr>\n"
            + "                                <td class=\"border\" style=\"border-top:2px solid #19b719;\n"
            + "                                    border-bottom:2px solid #19b719;\">\n"
            + "                                    <h1 style=\" font-size:24px;\n"
            + "                                        color:#19b719;\n"
            + "                                        margin:30px 0;\">WELCOME TO JAVA SNAKE GAME!</h1>\n"
            + "                                    <p style=\"margin:5px 0 0\">Dear " + name + ",</p>\n"
            + "                                    <p style=\"margin:5px 0 0\">Thank you for joining Java Snake Game! We're excited to have you on board. Please verify your email address to complete your registration.</p>\n"
            + "                                    <p style=\"margin:5px 0 0\">Your verification token is: <strong style=\"color:#19b719;\">" + verifyToken + "</strong></p>\n"
            + "                                    <p style=\"margin:5px 0 0\">Use this token to verify your account and start playing right away!</p>\n"
            + "                                    <p style=\"margin:5px 0 0\">If you have any questions, feel free to contact our support team at support@example.com.</p>\n"
            + "                                    <p style=\"margin:5px 0 0\">Happy gaming!</p>\n"
            + "                                    <p style=\"margin:5px 0 0\">Best regards,</p>\n"
            + "                                    <p style=\"margin:5px 0 0\">The Java Snake Game Team</p>\n"
            + "                                </td>\n"
            + "                            </tr>\n"
            + "                            <tr class=\"contact\" style=\"font-size:11px; color:#999;\">\n"
            + "                                <td align=\"center\"> \n"
            + "                                    Java Snake Game Corporation - 0123 456 789 - support@example.com\n"
            + "                                </td>\n"
            + "                            </tr>\n"
            + "                        </table>\n"
            + "                    </div> \n"
            + "                </td>\n"
            + "            </tr>\n"
            + "        </table>\n"
            + "    </body>\n"
            + "</html>\n"
            + "";
    }

    public static void main(String[] args) {
        EmailUtils handleEmail = new EmailUtils();
        String email = "hoangclw@gmail.com";
        handleEmail.sendEmail(handleEmail.subjectContact("Hoang"), handleEmail.messageNewOrder("anh luu", "abc123"), email);
    }

}