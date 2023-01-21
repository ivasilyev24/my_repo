package test.repository;

import test.model.SuggestionDTO;

import java.util.Map;

public interface SuggestionRepositoryCustom {

    Iterable<SuggestionDTO> findAll(Map<String,String> requestParams);

}
