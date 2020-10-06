package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuarioTestDados.csv")
public class InformacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {

        //Clicar no link que possui o texto "SingIn"
        navegador.findElement(By.linkText("Sign in")).click();

        //Identificando o formulário de login
        WebElement formularioSignInbox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com name "login" que está dentro do formulário de id "signinbox" o text "julio0001"
        formularioSignInbox.findElement(By.name("login")).sendKeys("julio0001");

        //Digitar no campo com name "password" que está dentro do formulário de id "signinbox" o text "123456"
        formularioSignInbox.findElement(By.name("password")).sendKeys("123456");

        //Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        //Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }
    @Test
    public void testAdicionarUmaInformacaoDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada) {

        //Clicar no botao atraves do xpath "//button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target='addmoredata']")).click();

        //Identificar o popup onde esta o formulario de id "addmoredata"
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        //Na combo de name "type" escolher a opção "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        //No campo de name "contact" digitar "99999-11111"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        //Clicar no link de text "SAVE" que esta na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPopup = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPopup.getText();
        assertEquals(mensagemEsperada, mensagem);
    }
   @Test
    public void testeRemoverContatoUsuario(){
        // Clicar no elemento pelo xpath "//span[text()="99999-11111"]/following-sibling::a"
       navegador.findElement(By.xpath("//span[text()=\"99999-11111\"]/following-sibling::a")).click();

       // Confirma a janela javascript
       navegador.switchTo().alert().accept();

       //Validar que a msg apresentada foi  "Rest in peace, dear phone!"
       WebElement mensagemPopup = navegador.findElement(By.id("toast-container"));
       String mensagem = mensagemPopup.getText();
       assertEquals("Rest in peace, dear phone!", mensagem);

       //Screenshot
       String screenshotArquivo = "\\Users\\guilh\\test-screenshot\\TaskIt\\" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
       Screenshot.tirar(navegador,screenshotArquivo);

       //Aguardar ate 10 segundos
       WebDriverWait aguardar = new WebDriverWait(navegador,10);
       aguardar.until(ExpectedConditions.stalenessOf(mensagemPopup));

       //Fazer Logout
       navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() {
        //Close browser
        navegador.close();
    }
}
