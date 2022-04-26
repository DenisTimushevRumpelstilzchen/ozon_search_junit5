package denis.timushev;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OzonParameterizedTests {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @ValueSource(strings = {
            "Nokia",
            "Xiaomi"
    })

    @ParameterizedTest(name = "проверка поиска продукции {0} в магазине ozon")
    void ozonSearchTest(String testData) {
        Selenide.open("https://www.ozon.ru");
        $("[name=text]").setValue(testData);
        $("[type=submit]").click();
        $$(".ri5")
                .find(Condition.text(testData))
                .shouldBe(Condition.visible);
    }

    @CsvSource(value = {
            "Nokia, 105 SS",
            "Xiaomi, Xiaomi, Mijia Stainless Steel Nail Clippers (MJZJD002QW)"
    })

    @ParameterizedTest(name = "проверка поиска продукции {0} в магазине ozon, ожидаем {1}")
    void ozonSearchComplexTest(String testData, String expectedResult) {
        Selenide.open("https://www.ozon.ru");
        $("[name=text]").setValue(testData);
        $("[type=submit]").click();
        $$(".ri5")
                .find(Condition.text(expectedResult))
                .shouldBe(Condition.visible);
    }

    static Stream<Arguments> ozonMethodSourceTest() {
        return Stream.of(
                Arguments.of("Nokia", "105 SS"),
                Arguments.of("Xiaomi", "Xiaomi, Mijia Stainless Steel Nail Clippers (MJZJD002QW)")
        );
    }

    @MethodSource("ozonMethodSourceTest")
    @ParameterizedTest(name = "проверка поиска продукции с MethodSource")
    void ozonMethodSourceTest(String testData, String result) {
        Selenide.open("https://www.ozon.ru");
        $("[name=text]").setValue(testData);
        $("[type=submit]").click();
        $$(".ri5")
                .find(Condition.text(testData))
                .shouldBe(Condition.visible);
    }
}
