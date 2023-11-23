package co.dalmope.usermicroservice.domain.spi;

import java.util.Map;

public interface ISendEmailPort {
    void sendEmail(String to, String subject, String content, Map<String, String> values);

}
