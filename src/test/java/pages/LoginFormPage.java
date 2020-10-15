package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage {


    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage typeLogin(String login) {
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);

        return this;
    }

    public LoginFormPage typePassowrd(String password) {
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);

        return this;
    }

    public SecretaPage typeSignIn(String signin) {
        navegador.findElement(By.linkText("SIGN IN")).click();

        return new SecretaPage(navegador);
    }

    public SecretaPage typeFazerLogin(String fazerlogin, String senha){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(fazerlogin);
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(senha);
        navegador.findElement(By.linkText("SIGN IN")).click();

        return new SecretaPage(navegador);
    }
}
