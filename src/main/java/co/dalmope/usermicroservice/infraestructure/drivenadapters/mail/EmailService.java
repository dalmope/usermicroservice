package co.dalmope.usermicroservice.infraestructure.drivenadapters.mail;

import co.dalmope.usermicroservice.domain.exceptions.EmailNotSendException;
import co.dalmope.usermicroservice.domain.spi.ISendEmailPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Slf4j

@RequiredArgsConstructor
public class EmailService implements ISendEmailPort {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(String to, String subject, String template, Map<String, String> values) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            String htmlContent = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/" + template + ".html")));
            for (Map.Entry<String, String> entry : values.entrySet()) {
                htmlContent = htmlContent.replace("[[" + entry.getKey() + "]]", entry.getValue());
            }
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            javaMailSender.send(message);
        } catch (MessagingException | IOException e) {
            log.error(String.valueOf(e));
            throw new EmailNotSendException();
        }
    }
}
