package test.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import test.Application;
import test.model.Suggestion;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SuggestionRepositoryImplTest {

    @Autowired
    SuggestionRepositoryImpl repository;

    /**
     * Проверка метода поиска
     */
    @Test()
    public void testFindAll() {
        Map<String, String> requestParams = Map.of("q", "Londo", "latitude", "43.70011", "longitude", "79.4163");

        Iterable<Suggestion> iterable = repository.findAll(requestParams);

        List<Suggestion> result =
                StreamSupport.stream(iterable.spliterator(), false)
                        .collect(Collectors.toList());

        assertEquals(
                "[Suggestion{name='London, ON, Canada', latitude=42.98339, longitude=-81.23304, score=0.0}, " +
                        "Suggestion{name='London, OH, USA', latitude=39.88645, longitude=-83.44825, score=0.0}, " +
                        "Suggestion{name='London, KY, USA', latitude=37.12898, longitude=-84.0832, score=0.0}, " +
                        "Suggestion{name='Londontowne, MD, USA', latitude=38.93345, longitude=-76.54941, score=0.0}]", result.toString());
    }

}
