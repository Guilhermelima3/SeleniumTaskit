package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class infoUsersTest{
    private WebDriver navegador;

    @Before
    public void setUp() {
        //open browser
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\guilh\\IdeaProjects\\ChromeDriver-Selenium\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        //going to the page
        navegador.get("http://www.juliodelima.com.br/taskit");
    }
    @Test
    public void testLoginSucess() {

        //Clicar no link que possui o texto "SingIn"
        navegador.findElement(By.linkText("Sign in")).click();

        //Identificando o formulário de login
        WebElement formularioSignInbox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com name "login" que está dentro do formulário de id "signinbox" o text "julio0001"
        formularioSignInbox.findElement(By.name("login")).sendKeys("julio0001");

        //Digitar no campo com name "password" que está dentro do formulário de id "signinbox" o text "123456"
        formularioSignInbox.findElement(By.name("password")).sendKeys("1234561");

        //Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

//        //Validar dentro do elemento com class "me" está o text "Hi, Julio"
//        WebElement me = navegador.findElement(By.className("me"));
//        String textoNoElementoMe = me.getText();
//        assertEquals("Hi, Julio", textoNoElementoMe);

        //Validando o error do login não sucedido
        WebElement toast = navegador.findElement(By.id("toast-container"));
        String textoNoElementoToast = toast.getText();
        assertEquals("Maybe you brain dropped the password or login in some place!", textoNoElementoToast);

    }
    @Test
    public void testLoginComDadosErrados() {

        //Clicar no link que possui o texto "SingIn"
        navegador.findElement(By.linkText("Sign in")).click();

        //Identificando o formulário de login
        WebElement formularioSignInbox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com name "login" que está dentro do formulário de id "signinbox" o text "julio0001"
        formularioSignInbox.findElement(By.name("login")).sendKeys("julioteste02");

        //Digitar no campo com name "password" que está dentro do formulário de id "signinbox" o text "123456"
        formularioSignInbox.findElement(By.name("password")).sendKeys("1234561");

        //Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Validando o error do login não sucedido
        WebElement toast = navegador.findElement(By.id("toast-container"));
        String textoNoElementoToast = toast.getText();
        assertEquals("Maybe you brain dropped the password or login in some place!", textoNoElementoToast);

    }

    @After
    public void tearDown() {
        //Close browser
        navegador.close();
    }
}