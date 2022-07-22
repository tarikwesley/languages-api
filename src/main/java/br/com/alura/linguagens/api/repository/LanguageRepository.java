package br.com.alura.linguagens.api.repository;

import br.com.alura.linguagens.api.model.Language;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends MongoRepository<Language, String> {}
