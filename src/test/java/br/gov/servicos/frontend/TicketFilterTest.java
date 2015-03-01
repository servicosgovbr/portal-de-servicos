package br.gov.servicos.frontend;

import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@FieldDefaults(level = PRIVATE)
public class TicketFilterTest {

    TicketFilter filter;
    MockHttpServletRequest req;
    MockHttpServletResponse res;
    MockFilterChain chain;

    @Before
    public void setUp() throws Exception {
        filter = new TicketFilter();
        req = new MockHttpServletRequest();
        res = new MockHttpServletResponse();
        chain = new MockFilterChain();
    }

    @Test
    public void adicionaTicketAoRequest() throws Exception {
        filter.doFilter(req, res, chain);

        assertThat(req.getAttribute("Ticket"), is(Ticket.atual()));
    }

    @Test
    public void sempreContinuaExecucaoDosFiltros() throws Exception {
        filter.doFilter(req, res, chain);

        assertThat(chain.getRequest(), is(req));
        assertThat(chain.getResponse(), is(res));
    }
}