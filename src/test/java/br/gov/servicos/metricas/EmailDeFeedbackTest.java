package br.gov.servicos.metricas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static br.gov.servicos.fixtures.TestData.FEEDBACK;
import static javax.mail.Message.RecipientType.TO;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailDeFeedbackTest {

    @Mock
    JavaMailSender mail;

    @Mock
    MimeMessage message;

    @Test
    public void deveEnviarEmailFormatadoComSucesso() throws Exception {
        given(mail.createMimeMessage()).willReturn(message);

        EmailDeFeedback email = new EmailDeFeedback(mail, "from@servicos.gov.br", "to@servicos.gov.br");
        email.enviar(FEEDBACK);

        verify(message).setSubject("Novo feedback para /servico/foo");
        verify(message).setFrom("from@servicos.gov.br");
        verify(message).setRecipients(TO, "to@servicos.gov.br");
        verify(message).setText(anyString(), eq("utf8"), eq("html"));

        verify(mail).send(message);
    }

    @Test
    public void deveLidarComErrosNaFormatacao() throws Exception {
        given(mail.createMimeMessage()).willReturn(message);
        doThrow(new MessagingException()).when(message).setText(anyString(), anyString(), anyString());

        EmailDeFeedback email = new EmailDeFeedback(mail, "from@servicos.gov.br", "to@servicos.gov.br");
        email.enviar(FEEDBACK);

        verify(mail, never()).send(message);
    }

    @Test
    public void deveLidarComErrosNoEnvio() throws Exception {
        given(mail.createMimeMessage()).willReturn(message);
        doThrow(new MailSendException("")).when(mail).send(Matchers.<MimeMessage>anyObject());

        EmailDeFeedback email = new EmailDeFeedback(mail, "from@servicos.gov.br", "to@servicos.gov.br");
        email.enviar(FEEDBACK);

        // exceção não propagada
    }

}