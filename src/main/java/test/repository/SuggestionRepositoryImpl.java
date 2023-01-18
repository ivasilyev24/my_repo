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
     *
     * @param iterator входной параметр
     * @return
     */
    public Iterable<Suggestion> toIterable(Iterator<Suggestion> iterator) {
        return () -> iterator;
    }

    /**
     * Получение данных по входным парамертам
     *
     * @param requestParams парамерты запроса
     * @return
     */
    @Override
    public Iterable<Suggestion> findAll(Map<String, String> requestParams) {
        List<Suggestion> list = em.createQuery("select e from Suggestion e where e.name like :q", Suggestion.class).
                setParameter("q", requestParams.get("q") + "%").
                getResultList();
        List<Suggestion> result = new ArrayList<>();
        for (Suggestion s : list) {
            Float score = scoreCalculator.getScore(s, requestParams);
            s.setScore(score);
            result.add(s);
        }
        result.sort((a, b) -> b.getScore().compareTo(a.getScore()));
        Iterator<Suggestion> iterator = result.iterator();
        return toIterable(iterator);
    }

}
