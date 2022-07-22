package br.com.alura.linguagens.api.controller;

import br.com.alura.linguagens.api.model.Language;
import br.com.alura.linguagens.api.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("linguagens")
public class LanguageController {

  @Autowired LanguageRepository languageRepository;

  @GetMapping
  public ResponseEntity<Page<Language>> getAllLanguades(
      @PageableDefault(page = 0, size = 10, sort = "ranking", direction = Sort.Direction.DESC)
          Pageable pageable) {
    return ResponseEntity.ok().body(languageRepository.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Language> getOneLanguage(@PathVariable(value = "id") String id) {
    Optional<Language> optionalLinguagem = languageRepository.findById(id);
    if (!optionalLinguagem.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok().body(optionalLinguagem.get());
  }

  @PostMapping
  public ResponseEntity<Language> registerLanguage(@Valid @RequestBody Language language) {
    return ResponseEntity.status(HttpStatus.CREATED).body(languageRepository.save(language));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Language> updateLanguage(
      @Valid @PathVariable(value = "id") String id, @RequestBody Language language) {
    Optional<Language> optionalLinguagem = languageRepository.findById(id);
    if (!optionalLinguagem.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    language.setId(optionalLinguagem.get().getId());
    return ResponseEntity.ok().body(languageRepository.save(language));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Language> deleteLanguage(@PathVariable(value = "id") String id) {
    Optional<Language> optionalLinguagem = languageRepository.findById(id);
    if (!optionalLinguagem.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    languageRepository.delete(optionalLinguagem.get());
    return ResponseEntity.ok().build();
  }
}
