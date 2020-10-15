package suporte;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {

    public static WebDriver createChrome(){
        //open browser
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\guilh\\IdeaProjects\\ChromeDriver-Selenium\\chromedriver2.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

        //going to the page
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }
}
