package br.gov.servicos.foundation.http;

import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class CookiesTest {

    @Mock
    HttpServletRequest httpServletRequest;
    Cookies cookies;

    @Before
    public void setUp() throws Exception {
        cookies = new Cookies(httpServletRequest);
    }

    @Test
    public void deveResponderTrueQuandoUmCookieTemStatusON() {
        given(httpServletRequest.getCookies()).willReturn(cookies(new Cookie("ativado", "on")));

        assertThat(cookies.isOn("ativado"), is(true));
    }

    @Test
    public void deveResponderFalseQuandoUmCookieTemStatusOFF() {
        given(httpServletRequest.getCookies()).willReturn(cookies(new Cookie("ativado", "off")));

        assertThat(cookies.isOn("ativado"), is(false));
    }

    @Test
    public void deveResponderFalseQuandoOCookieNaoEstaPresente() {
        given(httpServletRequest.getCookies()).willReturn(cookies());

        assertThat(cookies.isOn("ativado"), is(false));
    }

    @Test
    public void deveResponderFalseComCookiesNulos() {
        given(httpServletRequest.getCookies()).willReturn(null);

        assertThat(cookies.isOn("ativado"), is(false));
    }

    @Test
    public void deveConterCookie() {
        given(httpServletRequest.getCookies()).willReturn(cookies(new Cookie("ativado", "on")));

        assertThat(cookies.contem("ativado"), is(true));
    }

    @Test
    public void deveConterCookieMesmoQueNaoTenhaValor() {
        given(httpServletRequest.getCookies()).willReturn(cookies(new Cookie("ativado", null)));

        assertThat(cookies.contem("ativado"), is(true));
    }

    @Test
    public void naoDeveConterQuandoOCookieNaoEstaPresente() {
        given(httpServletRequest.getCookies()).willReturn(cookies());

        assertThat(cookies.contem("ativado"), is(false));
    }

    @Test
    public void naoDeveConterComCookiesNulos() {
        given(httpServletRequest.getCookies()).willReturn(null);

        assertThat(cookies.isOn("ativado"), is(false));
    }

    private Cookie[] cookies(Cookie... cookies) {
        return cookies;
    }
}