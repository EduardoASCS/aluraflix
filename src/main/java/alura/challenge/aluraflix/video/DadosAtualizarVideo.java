package alura.challenge.aluraflix.video;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarVideo(
        @NotNull
        Long id,
        String titulo,
        String descricao,
        String url
) {
}
