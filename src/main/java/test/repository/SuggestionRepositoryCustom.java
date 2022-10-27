package test.repository;

import test.model.Suggestion;

import java.util.Map;

public interface SuggestionRepositoryCustom {

    Iterable<Suggestion> findAll(Map<String,String> requestParams);

}
