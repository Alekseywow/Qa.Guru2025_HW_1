package tests;
// 1. Импорт библиотек
import com.codeborne.selenide.Configuration; //com.codeborne.selenide.Configuration: класс для настройки конфигурации Selenide.
import org.junit.jupiter.api.BeforeAll; // org.junit.jupiter.api.BeforeAll и org.junit.jupiter.api.Test: аннотации JUnit для подготовки теста и самого теста.
import org.junit.jupiter.api.Test; // org.junit.jupiter.api.BeforeAll и org.junit.jupiter.api.Test: аннотации JUnit для подготовки теста и самого теста.

import static com.codeborne.selenide.Condition.text; //Статические импорты: упрощают обращение к методам Selenide, позволяя использовать их без указания класса.
import static com.codeborne.selenide.Selectors.byText; //Статические импорты: упрощают обращение к методам Selenide, позволяя использовать их без указания класса.
import static com.codeborne.selenide.Selenide.*; //Статические импорты: упрощают обращение к методам Selenide, позволяя использовать их без указания класса.

public class TestPracticForm {

    // 2. Настройка конфигурации

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080"; // Configuration.browserSize: указывает размер окна браузера для теста. Это помогает эмулировать условия, в которых будет использоваться приложение.
        Configuration.baseUrl = "https://demoqa.com"; //Configuration.baseUrl: базовый URL, который необходим для всех открываемых страниц, упрощая указание адресов.
        Configuration.pageLoadStrategy = "eager"; //Configuration.pageLoadStrategy: устанавливает стратегию загрузки страниц на "eager", что может улучшить производительность теста, так как позволит продолжить выполнение кода, как только основные ресурсы загружены.
        Configuration.holdBrowserOpen = true; // Configuration.holdBrowserOpen: установлено в true, позволяет оставить браузер открытым после завершения теста, что полезно для отладки.
    }


    // 3. Открытие страницы и удаление ненужных элементов
    @Test  //@Test — аннотация JUnit, указывающая, что метод является тестовым. JUnit будет выполнять этот метод как тест.
    void fillFormTest(){

        open("/automation-practice-form"); // open("/automation-practice-form"): открывает страницу с формой, используя относительный путь к базовому URL.
        executeJavaScript("$('#fixedban').remove()"); // executeJavaScript(...): используется для удаления элементов DOM (баннер и футер) с помощью JavaScript. Это позволяет избежать потенциальных проблем с доступом к полям формы, которые могут быть перекрыты.
        executeJavaScript("$('footer').remove()"); // executeJavaScript(...): используется для удаления элементов DOM (баннер и футер) с помощью JavaScript. Это позволяет избежать потенциальных проблем с доступом к полям формы, которые могут быть перекрыты.

        // 4. Заполнение формы

        $("#firstName").setValue("Aleksey");  //$("#firstName").setValue("Aleksey"): находит элемент по ID и вводит в него имя.
        $("#lastName").setValue("Danilov");  //setValue(String value) — метод для заполнения найденного элемента (например, текстового поля) переданным значением. Он имитирует действие ввода текста.
        $("#userEmail").setValue("mail@mail.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("8900909090");

        //Поле Дата Рождения:
        $("#dateOfBirthInput").click(); //Кликает на поле "Дата Рождения": чтобы открыть виджет выбора даты.
        //$(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("September"); //$(".react-datepicker__month-select").selectOption("September"): выбирает месяц.
        $(".react-datepicker__year-select").selectOption("1994"); //$(".react-datepicker__year-select").selectOption("1994"): выбирает год.
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text("26")).click();// (".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text("26")).click(): находит и кликает на 26-й день (егда это не день предыдущего/следующего месяца)

        //Заполнение дополнительных полей
        $("#subjectsInput").setValue("Maths").pressEnter(); //$("#subjectsInput").setValue("Maths").pressEnter(): вводит значение "Maths" и эмулирует нажатие клавиши Enter для подтверждения экскурсии.
        $("#hobbiesWrapper").$(byText("Sports")).click(); //(byText("Sports")).click(): находит хобби "Sports" и кликает по нему.
        $("#uploadPicture").uploadFromClasspath("img/Cat.png"); //$("#uploadPicture").uploadFromClasspath("Cat.png"): загружает изображение изclasspath.

        //Ввод адреса и выбора штата и города
        $("#currentAddress").setValue("INDIA"); //Идут операции выбора штата "Haryana" и города "Karnal": ввод значений и нажатие Enter для подтверждения.
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("8900909090");
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();


        //7. Отправка формы и проверка результатов
        $("#submit").click();



        //8. Проверка результатов
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table").$(byText("Student Name")).sibling(0).shouldHave(text("Aleksey Danilov"));
        $(".table").$(byText("Student Email")).sibling(0).shouldHave(text("mail@mail.ru"));
        $(".table").$(byText("Gender")).sibling(0).shouldHave(text("Male"));
        $(".table").$(byText("Mobile")).sibling(0).shouldHave(text("8900909090"));
        $(".table").$(byText("Date of Birth")).sibling(0).shouldHave(text("26 September,1994"));
        $(".table").$(byText("Subjects")).sibling(0).shouldHave(text("Maths"));
        $(".table").$(byText("Hobbies")).sibling(0).shouldHave(text("Sports"));
        $(".table").$(byText("Picture")).sibling(0).shouldHave(text("img/Cat.png"));
        $(".table").$(byText("Address")).sibling(0).shouldHave(text("INDIA"));
        $(".table").$(byText("State and City")).sibling(0).shouldHave(text("Haryana Karnal"));
    }

}
