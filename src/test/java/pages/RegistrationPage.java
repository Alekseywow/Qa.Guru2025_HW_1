package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendareComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {

    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genterWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput");



    CalendareComponent calendarComponent = new CalendareComponent();



    public RegistrationPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;

    }

    public RegistrationPage genterWrapper(String value) {
        genterWrapper.$(byText(value)).click();
        return this;

    }

    public RegistrationPage setUserNumber (String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBrith (String day, String month, String year) {

        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage checkResult(String key, String value){
        $(".table-responsive").$(byText(key)).parent()
                .shouldHave(text(value));


        return this;
    }

}
