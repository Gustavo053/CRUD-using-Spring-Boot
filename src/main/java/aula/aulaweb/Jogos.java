package aula.aulaweb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Jogos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank
    String nome;
    @Size(min = 3, message = "A categoria deve conter, no mínimo, 3 dígitos  ")
    @NotBlank(message = "O campo não pode ser vazio")
    String categoria;
    @Size(min = 4, max = 4, message = "A data deve conter 4 algarismos")
    @NotNull(message = "O campo não pode ser vazio")
    String anoLancamento;
}
