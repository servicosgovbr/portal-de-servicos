package br.gov.servicos.frontend;

import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class LoggingFilterTest {

    MockFilterChain chain = new MockFilterChain();
    ServletRequest request = new MockHttpServletRequest();
    ServletResponse response = new MockHttpServletResponse();
    LoggingFilter filter = new LoggingFilter();

    @Test
    public void sempreContinuaExecucaoDosFiltros() throws Exception {
        filter.doFilter(request, response, chain);

        assertThat(chain.getRequest(), is(request));
        assertThat(chain.getResponse(), is(response));
    }
}
