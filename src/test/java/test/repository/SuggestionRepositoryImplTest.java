package test.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import test.Application;
import test.model.SuggestionDTO;

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
        Map<String, String> requestParams = Map.of("q", "Londo", "latitude", "43.70011", "longitude", "-79.4163");

        Iterable<SuggestionDTO> iterable = repository.findAll(requestParams);

        List<SuggestionDTO> result =
                StreamSupport.stream(iterable.spliterator(), false)
                        .collect(Collectors.toList());

        assertEquals(
                """
                        [\
                        SuggestionDTO[name=London, ON, Canada, latitude=42.98339, longitude=-81.23304, score=0.9], \
                        SuggestionDTO[name=London, KY, USA, latitude=37.12898, longitude=-84.08326, score=0.5], \
                        SuggestionDTO[name=London, OH, USA, latitude=39.88645, longitude=-83.44825, score=0.5], \
                        SuggestionDTO[name=Londontowne, MD, USA, latitude=38.93345, longitude=-76.54941, score=0.3], \
                        SuggestionDTO[name=Londonderry, NH, USA, latitude=42.86509, longitude=-71.37395, score=0.3]\
                        ]"""
                , result.toString());
    }

}
