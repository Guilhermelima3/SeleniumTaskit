package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

import static org.junit.Assert.assertEquals;
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuariosPageObjectsTestDados.csv")

public class InformacoesUsuariosPageObjectsTest {
    private WebDriver navegador;

    @Before
    public void setUp() {
        navegador = Web.createChrome();
    }

    @Test
    public void testAdicionarUmaInformacaoAdiconalDoUsuario(
            @Param(name="login")String login,
            @Param(name="senha")String senha,
            @Param(name="tipo")String tipo,
            @Param(name="contato")String contato,
            @Param(name="mensagem")String mensagemEsperada
    ) {
        String textoToast = new LoginPage(navegador)
            .clickSignIn()
            .typeFazerLogin(login, senha)
            .clickMe()
            .clickAbaMoreDataAboutYou()
            .clickButtonMoreDataAboutYou()
            .addContato(tipo,contato)
            .capturarTextoToast();

        assertEquals(mensagemEsperada, textoToast);
    }

    @After
    public void tearDown() {
        //Close browser
        navegador.close();
    }
}
