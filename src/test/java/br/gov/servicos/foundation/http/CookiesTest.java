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
import static org.mockito.Mockito.doReturn;

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
        doReturn(cookies(new Cookie("ativado", "on")))
                .when(httpServletRequest)
                .getCookies();

        assertThat(cookies.isOn("ativado"), is(true));
    }

    @Test
    public void deveResponderFalseQuandoUmCookieTemStatusOFF() {
        doReturn(cookies(new Cookie("ativado", "off")))
                .when(httpServletRequest)
                .getCookies();

        assertThat(cookies.isOn("ativado"), is(false));
    }

    @Test
    public void deveResponderFalseQuandoOCookieNaoEstaPresente() {
        doReturn(cookies())
                .when(httpServletRequest)
                .getCookies();

        assertThat(cookies.isOn("ativado"), is(false));
    }

    @Test
    public void deveResponderFalseComCookiesNulos() {
        doReturn(null)
                .when(httpServletRequest)
                .getCookies();

        assertThat(cookies.isOn("ativado"), is(false));
    }

    private Cookie[] cookies(Cookie... cookies) {
        return cookies;
    }
}