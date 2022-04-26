package denis.timushev;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OzonTest {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @DisplayName("проверка поиска продукции nokia в магазине ozon")
    @Test
    void ozonSearchTest() {
        Selenide.open("https://www.ozon.ru");
        $("[name=text]").setValue("Nokia");
        $("[type=submit]").click();
        $$(".ri5")
                .find(Condition.text("Nokia"))
                .shouldBe(Condition.visible);
    }
}
