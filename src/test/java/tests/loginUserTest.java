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

public class loginUserTest {
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
    public void testLoginSucess() {

        navegador.findElement(By.linkText("Sign in")).click();

        WebElement formularioSignInbox = navegador.findElement(By.id("signinbox"));

        formularioSignInbox.findElement(By.name("login")).sendKeys("julio0001");

        formularioSignInbox.findElement(By.name("password")).sendKeys("1234561");

        navegador.findElement(By.linkText("SIGN IN")).click();

        WebElement toast = navegador.findElement(By.id("toast-container"));
        String textoNoElementoToast = toast.getText();
        assertEquals("Maybe you brain dropped the password or login in some place!", textoNoElementoToast);

    }
    @Test
    public void testLoginComDadosErrados() {


        navegador.findElement(By.linkText("Sign in")).click();

        WebElement formularioSignInbox = navegador.findElement(By.id("signinbox"));

        formularioSignInbox.findElement(By.name("login")).sendKeys("julioteste02");

        formularioSignInbox.findElement(By.name("password")).sendKeys("1234561");

        navegador.findElement(By.linkText("SIGN IN")).click();

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
