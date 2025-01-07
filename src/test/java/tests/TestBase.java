package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080"; // Configuration.browserSize: указывает размер окна браузера для теста. Это помогает эмулировать условия, в которых будет использоваться приложение.
        Configuration.baseUrl = "https://demoqa.com"; //Configuration.baseUrl: базовый URL, который необходим для всех открываемых страниц, упрощая указание адресов.
        Configuration.pageLoadStrategy = "eager"; //Configuration.pageLoadStrategy: устанавливает стратегию загрузки страниц на "eager", что может улучшить производительность теста, так как позволит продолжить выполнение кода, как только основные ресурсы загружены.
        Configuration.holdBrowserOpen = true; // Configuration.holdBrowserOpen: установлено в true, позволяет оставить браузер открытым после завершения теста, что полезно для отладки.
    }
}
