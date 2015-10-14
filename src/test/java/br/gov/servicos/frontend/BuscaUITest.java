package br.gov.servicos.frontend;

import br.gov.servicos.Main;
import br.gov.servicos.setup.SetupTestesIntegracao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@IntegrationTest({
        "server.port:0",
        "flags.importar.automaticamente=false"
})
public class BuscaUITest {

    @Autowired
    SetupTestesIntegracao setupTestesIntegracao;

    WebDriver driver;

    @Value("${local.server.port}")
    int port;

    String baseUrl;

    @Before
    public void setUp() throws Exception {
        setupTestesIntegracao.setupComDados();

        baseUrl = "http://localhost:" + port + "/";

        driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }

    @Test
    public void buscaSimplesPorDarf() throws Exception {
        driver.get(baseUrl);
        assertThat(driver.getTitle(), is("Portal de Serviços - Página Inicial"));

        driver.findElement(By.id("buscar")).sendKeys("darf");
        driver.findElement(By.className("searchButton")).click();

        assertThat(driver.getTitle(), is("Portal de Serviços - Busca por darf"));

        assertThat(driver.findElements(By.cssSelector("#resultados-busca li")).size(), is(20));
        assertThat(driver.findElements(By.cssSelector("#resultados-busca li h3")).get(0).getText(), is("Departamento de Polícia Rodoviária Federal (DPRF)"));

        assertThat(driver.findElement(By.id("buscar")).getAttribute("value"), is("darf"));

        driver.findElement(By.id("buscar")).clear();
        driver.findElement(By.id("buscar")).sendKeys("prouni");
        driver.findElement(By.className("searchButton")).click();

        assertThat(driver.getTitle(), is("Portal de Serviços - Busca por prouni"));

        assertThat(driver.findElements(By.cssSelector("#resultados-busca li")).size(), is(10));
        assertThat(driver.findElements(By.cssSelector("#resultados-busca li h3")).get(0).getText(), is("Unidade de Pronto Atendimento (UPA 24h)"));
        driver.findElement(By.cssSelector("#resultados-busca li a")).click();

        assertThat(driver.getTitle(), is("Portal de Serviços - Unidade de Pronto Atendimento (UPA 24h)"));
    }

}
