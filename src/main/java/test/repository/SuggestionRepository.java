package test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.model.Suggestion;

/**
 * Стандартная реализация hibernate-репозитория с кастом методом
 */
public interface SuggestionRepository extends JpaRepository<Suggestion, String>, SuggestionRepositoryCustom {
}
