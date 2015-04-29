package br.gov.servicos.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MailConfig {

    @Value("${mail.protocol}")
    String protocol;

    @Value("${mail.host}")
    String host;

    @Value("${mail.port}")
    int port;

    @Value("${mail.timeout}")
    int timeout;

    @Value("${mail.smtp.auth}")
    boolean auth;

    @Value("${mail.smtp.starttls}")
    boolean starttls;

    @Value("${mail.username}")
    String username;

    @Value("${mail.password}")
    String password;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.timeout", timeout);
        properties.put("mail.smtp.connectiontimeout", timeout);
        properties.put("mail.smtp.starttls.enable", starttls);

        sender.setJavaMailProperties(properties);
        sender.setHost(host);
        sender.setPort(port);
        sender.setProtocol(protocol);
        sender.setUsername(username);
        sender.setPassword(password);

        return sender;
    }
}