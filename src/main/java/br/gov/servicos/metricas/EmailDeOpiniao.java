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
class EmailDeOpiniao {

    JavaMailSender mail;

    String from;
    String to;

    @Autowired
    public EmailDeOpiniao(JavaMailSender mail, @Value("${mail.from}") String from, @Value("${mail.to}") String to) {
        this.mail = mail;
        this.from = from;
        this.to = to;
    }

    public void enviar(Opiniao opiniao) {
        try {
            MimeMessage message = prepararMensagem(opiniao);
            log.debug("Email para {} preparado com sucesso", to);

            mail.send(message);
            log.info("Email para {} enviado com sucesso", to);

        } catch (MessagingException e) {
            log.error("Erro ao preparar o email de opinião", e);

        } catch (MailSendException e) {
            log.error("Erro ao enviar o email de opinião", e);
        }
    }

    private MimeMessage prepararMensagem(Opiniao opiniao) throws MessagingException {
        MimeMessage message = mail.createMimeMessage();

        message.setFrom(from);
        message.setSubject("Nova opinião em " + url(opiniao));
        message.setRecipients(TO, to);
        message.setText(format("<html><body>" +
                        "<h1>Nova opinião enviada</h1>" +
                        "<p>Uma nova opinião foi enviada no Portal de Serviços através do formulário embutido na aplicação:</p>" +
                        "<h2>Contexto</h2><dl>" +
                        "<dt>Horário:</dt><dd>%s</dd>" +
                        "<dt>Ticket:</dt><dd><code>%s</code></dd>" +
                        "<dt>URL:</dt><dd><code>%s</code></dd>" +
                        "<dt>Query String:</dt><dd><code>%s</code></dd>" +
                        "<dt>Encontrou o que procurava?</dt><dd>%s</dd>" +
                        "</dl><h2>Mensagem</h2>" +
                        "<p>%s</p>" +
                        "</body></html>",
                horario(opiniao),
                ticket(opiniao),
                url(opiniao),
                queryString(opiniao),
                conteudoEncontrado(opiniao),
                mensagem(opiniao)
        ), "utf8", "html");

        return message;
    }

    private String mensagem(Opiniao opiniao) {
        return opiniao.getMensagem() == null || opiniao.getMensagem().trim().isEmpty() ? "(vazia)" : opiniao.getMensagem();
    }

    private String queryString(Opiniao opiniao) {
        return opiniao.getQueryString().isEmpty() ? "(vazia)" : opiniao.getQueryString();
    }

    private String ticket(Opiniao opiniao) {
        return opiniao.getTicket() == null || opiniao.getTicket().isEmpty() ? "(vazio)" : opiniao.getTicket();
    }

    private String horario(Opiniao opiniao) {
        return opiniao.getTimestamp() == null ? "(vazio)" : new Date(opiniao.getTimestamp()).toString();
    }

    private String url(Opiniao opiniao) {
        return opiniao.getUrl() == null || opiniao.getUrl().isEmpty() ? "(vazia)" : opiniao.getUrl();
    }

    private String conteudoEncontrado(Opiniao opiniao) {
        if (opiniao.getConteudoEncontrado() == null) {
            return "(vazio)";
        } else {
            if (opiniao.getConteudoEncontrado()) {
                return "<strong style=\"color: #008000;\">sim</strong>";
            } else {
                return "<strong style=\"color: #800000;\">não</strong>";
            }
        }
    }
}
