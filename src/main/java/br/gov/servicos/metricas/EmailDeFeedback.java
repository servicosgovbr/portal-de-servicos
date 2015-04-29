package br.gov.servicos.metricas;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

import static java.lang.String.format;
import static javax.mail.Message.RecipientType.TO;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class EmailDeFeedback {

    JavaMailSender mail;

    String from;
    String to;

    @Autowired
    public EmailDeFeedback(JavaMailSender mail, @Value("${mail.from}") String from, @Value("${mail.to}") String to) {
        this.mail = mail;
        this.from = from;
        this.to = to;
    }

    public void enviar(Feedback feedback) {
        try {
            MimeMessage message = prepararMensagem(feedback);
            log.debug("Email para {} preparado com sucesso", to);

            mail.send(message);
            log.info("Email para {} enviado com sucesso", to);

        } catch (MessagingException e) {
            log.error("Erro ao preparar o email de feedback", e);

        } catch (MailSendException e) {
            log.error("Erro ao enviar o email de feedback", e);
        }
    }

    private MimeMessage prepararMensagem(Feedback feedback) throws MessagingException {
        MimeMessage message = mail.createMimeMessage();

        message.setFrom(from);
        message.setSubject("Novo feedback para " + feedback.getUrl());
        message.setRecipients(TO, to);
        message.setText(format("<html><body>" +
                        "<h1>Novo feedback enviado</h1>" +
                        "<p>Um novo feedback foi enviado no Guia de Serviços através do formulário embutido na aplicação:</p>" +
                        "<dl>" +
                        "<dt>ID:<dd><code>%s</code></dd>" +
                        "<dt>Horário:</dt><dd>%s</dd>" +
                        "<dt>Ticket:</dt><dd><code>%s</code></dd>" +
                        "<dt>URL:</dt><dd><code>%s</code></dd>" +
                        "<dt>Query String:</dt><dd><code>%s</code></dd>" +
                        "<dt>Encontrou o que procurava?</dt><dd><strong>%s</strong></dd>" +
                        "</dl><p>%s</p>" +
                        "</body></html>",
                feedback.getId(),
                new Date(feedback.getTimestamp()),
                feedback.getTicket(),
                feedback.getUrl(),
                feedback.getQueryString().isEmpty() ? "(vazia)" : feedback.getQueryString(),
                feedback.getConteudoEncontrado() ? "sim" : "não",
                feedback.getFeedback()
        ), "utf8", "html");

        return message;
    }
}
