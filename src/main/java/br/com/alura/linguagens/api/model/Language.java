package br.com.alura.linguagens.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Document
public class Language {
  @Id private String id;
  @NotBlank private String name;
  @NotBlank private String image;
  @NotNull private int ranking;
}
