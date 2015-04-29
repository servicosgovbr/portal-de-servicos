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
        message.setSubject("Novo feedback para " + url(feedback));
        message.setRecipients(TO, to);
        message.setText(format("<html><body>" +
                        "<h1>Novo feedback enviado</h1>" +
                        "<p>Um novo feedback foi enviado no Guia de Serviços através do formulário embutido na aplicação:</p>" +
                        "<h2>Contexto</h2><dl>" +
                        "<dt>Horário:</dt><dd>%s</dd>" +
                        "<dt>Ticket:</dt><dd><code>%s</code></dd>" +
                        "<dt>URL:</dt><dd><code>%s</code></dd>" +
                        "<dt>Query String:</dt><dd><code>%s</code></dd>" +
                        "<dt>Encontrou o que procurava?</dt><dd>%s</dd>" +
                        "</dl><h2>Mensagem</h2>" +
                        "<p>%s</p>" +
                        "</body></html>",
                horario(feedback),
                ticket(feedback),
                url(feedback),
                queryString(feedback),
                conteudoEncontrado(feedback),
                mensagem(feedback)
        ), "utf8", "html");

        return message;
    }

    private String mensagem(Feedback feedback) {
        return feedback.getFeedback() == null || feedback.getFeedback().trim().isEmpty() ? "(vazia)" : feedback.getFeedback();
    }

    private String queryString(Feedback feedback) {
        return feedback.getQueryString().isEmpty() ? "(vazia)" : feedback.getQueryString();
    }

    private String ticket(Feedback feedback) {
        return feedback.getTicket() == null || feedback.getTicket().isEmpty() ? "(vazio)" : feedback.getTicket();
    }

    private String horario(Feedback feedback) {
        return feedback.getTimestamp() == null ? "(vazio)" : new Date(feedback.getTimestamp()).toString();
    }

    private String url(Feedback feedback) {
        return feedback.getUrl() == null || feedback.getUrl().isEmpty() ? "(vazia)" : feedback.getUrl();
    }

    private String conteudoEncontrado(Feedback feedback) {
        if (feedback.getConteudoEncontrado() == null) {
            return "(vazio)";
        } else {
            if (feedback.getConteudoEncontrado()) {
                return "<strong style=\"color: #008000;\">sim</strong>";
            } else {
                return "<strong style=\"color: #800000;\">não</strong>";
            }
        }
    }
}
