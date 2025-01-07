package tests;
// 1. Импорт библиотек

       import com.codeborne.selenide.Configuration;
       import org.junit.jupiter.api.BeforeAll;
       import org.junit.jupiter.api.Test;
       import pages.RegistrationPage;

       import static com.codeborne.selenide.Condition.text;
       import static com.codeborne.selenide.Selectors.byText;
       import static com.codeborne.selenide.Selenide.*;
       import static com.codeborne.selenide.Selenide.$;


public class RegistrationWithPageObjectsTests extends TestBase{

            RegistrationPage registrationPage= new RegistrationPage();




           @Test
           void fillFormTest(){
               registrationPage.openPage()
                       .setFirstName("Aleksey")
                       .setLastName("Danilov")
                       .setEmail("mail@mail.ru")
                       .genterWrapper("Male")
                       .setUserNumber("8900909090")
                       .setDateOfBrith("26","September","1994");





               $("#subjectsInput").setValue("Maths").pressEnter();
               $("#hobbiesWrapper").$(byText("Sports")).click();
               $("#uploadPicture").uploadFromClasspath("img/Cat.png");

               $("#currentAddress").setValue("INDIA");
               $("#react-select-3-input").setValue("Haryana").pressEnter();
               $("#react-select-4-input").setValue("Karnal").pressEnter();

               $("#submit").click();

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

               registrationPage.checkResult("Student Name", "Aleksey Danilov")
                       .checkResult("Student Email", "mail@mail.ru")

           }
       }
