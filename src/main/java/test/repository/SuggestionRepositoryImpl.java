package test.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import test.model.Suggestion;
import test.model.SuggestionDTO;
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
    public Iterable<SuggestionDTO> toIterable(Iterator<SuggestionDTO> iterator) {
        return () -> iterator;
    }

    /**
     * Получение данных по входным парамертам
     *
     * @param requestParams парамерты запроса
     * @return
     */
    @Override
    public Iterable<SuggestionDTO> findAll(Map<String, String> requestParams) {
        List<Suggestion> list = em.createQuery("""
                                select e from Suggestion e 
                                left outer join fetch e.country 
                                left outer join fetch e.state 
                                where e.name like :q"""
                        , Suggestion.class).
                setParameter("q", requestParams.get("q") + "%").
                getResultList();
        List<Suggestion> result = new ArrayList<>();
        for (Suggestion s : list) {
            Float score = scoreCalculator.getScore(s, requestParams);
            s.setScore(score);
            result.add(s);
        }
        result.sort((a, b) -> b.getScore().compareTo(a.getScore()));
        List<SuggestionDTO> list2 = result.stream().map(s -> new SuggestionDTO(
                s.getName() + ", " + s.getState().getName() + ", " + s.getCountry().getName(),
                s.getLatitude(),
                s.getLongitude(), s.getScore()
        )).collect(Collectors.toList());
        Iterator<SuggestionDTO> iterator = list2.iterator();
        return toIterable(iterator);
    }

}
