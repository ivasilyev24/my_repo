package test.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import test.model.Suggestion;
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
        List<Suggestion> list0 = em.createQuery("select e from Suggestion e", Suggestion.class).getResultList();
        for(Suggestion s: list0) {
            Float score = scoreCalculator.getScore(s, requestParams);
            s.setScore(score);

        }
         list0 = list0.stream().sorted((s1,s2)->s2.getScore().compareTo(s1.getScore())).collect(Collectors.toList());

        Iterator<Suggestion> iterator = list0.iterator();
         return toIterable(iterator) ;
    }

}
