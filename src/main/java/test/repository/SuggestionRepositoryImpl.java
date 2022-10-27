package test.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import test.model.Suggestion;
import test.model.TestData;
import test.service.ScoreCalculator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class SuggestionRepositoryImpl extends SimpleJpaRepository implements SuggestionRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    /**
     * Ceрвис для реализации бизнес логики
     */
    @Autowired
    private ScoreCalculator scoreCalculator;

    @Autowired
    public SuggestionRepositoryImpl(EntityManager em) {
        super(Suggestion.class, em);
    }

    /**
     * Получение тестовых данных
     * @todo этот метод можно будет заменить на чтение данных из базы, когда будет добавлен Liquibase
     */
    private List<Suggestion> getTestData() {
        List<Suggestion> result = new ArrayList<>();
        result.add(TestData.London_ON_Canada);
        result.add(TestData.London_OH_USA);
        result.add(TestData.London_KY_USA);
        result.add(TestData.Londontowne_MD_USA);
        return result;
    }

    /**
     * Преобразует iterator в Iterable.
     * @param iterator входной параметр
     * @return
     */
    public Iterable<Suggestion> toIterable(Iterator<Suggestion>  iterator) {
        return () -> iterator;
    }

    /**
     * Получение данных по входным парамертам
     * @param requestParams парамерты запроса
     * @return
     */
    @Override
    public Iterable<Suggestion> findAll(Map<String, String> requestParams) {
        List<Suggestion> list0 = getTestData();
        for(Suggestion s: list0) {
            Float score = scoreCalculator.getScore(s, requestParams);
            s.setScore(score);

        }
         list0 = list0.stream().sorted((s1,s2)->s2.getScore().compareTo(s1.getScore())).collect(Collectors.toList());

        Iterator<Suggestion> iterator = list0.iterator();

        /* Лучше данные хранить в БД и читать их так.
        List list = em.createQuery("select e from Suggestion e").getResultList();
        или так
        Stream stream = em.createQuery("select e from Suggestion e").getResultStream();
         */
        return toIterable(iterator) ;
    }

}
