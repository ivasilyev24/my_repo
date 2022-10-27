package test.model;

/**
 * Тестовые данные.
 * TODO Убрать, когда они будут перенесены в Liquibase
 */
public class TestData {

    public static Suggestion London_ON_Canada = new Suggestion("London, ON, Canada", 42.98339f, -81.23304f),
            London_OH_USA = new Suggestion("London, OH, USA", 39.88645f, -83.44825f),
            London_KY_USA = new Suggestion("London, KY, USA", 37.12898f, -84.0832f),
            Londontowne_MD_USA = new Suggestion("Londontowne, MD, USA", 38.93345f, -76.54941f);

}
