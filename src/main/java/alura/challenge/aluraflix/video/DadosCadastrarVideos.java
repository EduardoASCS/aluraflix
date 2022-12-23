package alura.challenge.aluraflix.video;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastrarVideos(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotBlank
        String url) {
}
