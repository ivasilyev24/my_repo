package test.service;

import org.junit.jupiter.api.Test;
import test.model.Suggestion;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Тест для проверки основного сервиса приложения
 */
public class ScoreCalculatorTest {

    private ScoreCalculator scoreCalculator = new ScoreCalculator();

    Suggestion London_ON_Canada = new Suggestion("London, ON, Canada", 42.98339f, -81.23304f),
            London_OH_USA = new Suggestion("London, OH, USA", 39.88645f, -83.44825f),
            London_KY_USA = new Suggestion("London, KY, USA", 37.12898f, -84.0832f),
            Londontowne_MD_USA = new Suggestion("Londontowne, MD, USA", 38.93345f, -76.54941f);

    @Test
    public void testShouldBePresented() {
        Map<String, String> requestParams = Map.of("q", "Londo", "latitude", "43.70011", "longitude", "79.4163");
        assertTrue(scoreCalculator.shouldBePresented(London_ON_Canada, requestParams));
        assertTrue(scoreCalculator.shouldBePresented(London_OH_USA, requestParams));
        assertTrue(scoreCalculator.shouldBePresented(London_KY_USA, requestParams));
       assertTrue(scoreCalculator.shouldBePresented(Londontowne_MD_USA, requestParams));

        requestParams = Map.of("q", "SomeRandomCityInTheMiddleOfNowhere");
        assertFalse(scoreCalculator.shouldBePresented(London_ON_Canada, requestParams)
        );

        // Сервис может бросать ошибки в случае отсутствия обязательных параметров (q)
        requestParams = Map.of();
        try {
            scoreCalculator.shouldBePresented(new Suggestion("London, ON, Canada", 42.98339f, -81.23304f),
                    requestParams);
            fail();
        } catch (RuntimeException e) {
            // Ok
        }
   }

    @Test
    public void testGetScore() {
        Map<String, String> requestParams = Map.of("q", "Londo", "latitude", "43.70011", "longitude", "79.4163");
        assertFloatEquals(0.9f, scoreCalculator.getScore(London_ON_Canada, requestParams));
        assertFloatEquals(0.5f, scoreCalculator.getScore(London_OH_USA, requestParams));
        assertFloatEquals(0.5f, scoreCalculator.getScore(London_KY_USA, requestParams));
        // TODO исправить потом
        assertFloatEquals(0.9f, scoreCalculator.getScore(Londontowne_MD_USA, requestParams));
    }

    private void assertFloatEquals(Float f1, Float f2) {
        assertEquals(f1, f2);
    }


}
