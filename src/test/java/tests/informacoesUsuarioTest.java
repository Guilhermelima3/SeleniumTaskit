package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class informacoesUsuarioTest {
    private WebDriver navegador;

    @Before
    public void setUp() {                              
        //open browser
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\guilh\\Drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        //going to the page
        navegador.get("http://www.juliodelima.com.br/taskit");
    }
    @Test
    public void testAdicionarUmaInformacaoDoUsuario() {

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

        //Clicar no botao atraves do xpath "//button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target='addmoredata']")).click();

        //Identificar o popup onde esta o formulario de id "addmoredata"
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        //Na combo de name "type" escolher a opção "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        //No campo de name "contact" digitar "99999-11111"
        popupAddMoreData.findElement(By.name("contact")).sendKeys("99999-11111");

        //Clicar no link de text "SAVE" que esta na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPopup = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPopup.getText();
        assertEquals("Your contact has been added!", mensagem);

    }
    @After
    public void tearDown() {
        //Close browser
        navegador.close();
    }
}
