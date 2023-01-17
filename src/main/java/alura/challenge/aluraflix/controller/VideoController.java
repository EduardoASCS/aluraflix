package alura.challenge.aluraflix.controller;

import alura.challenge.aluraflix.domain.video.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastrarVideos dados, UriComponentsBuilder uriBuilder) {
        Video video = repository.save(new Video(dados));
        URI uri = uriBuilder
                .path("/videos/{id}")
                .buildAndExpand(video.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoVideo(video));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoVideo>> listar() {
        List<DadosDetalhamentoVideo> listaDeVideos = repository.findAll().stream().map(DadosDetalhamentoVideo::new).toList();

        return ResponseEntity.ok(listaDeVideos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoVideo> buscarPorId(@PathVariable Long id) {
        Video video = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoVideo(video));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarVideo dados) {
        Video video = repository.getReferenceById(dados.id());
        video.atualizar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoVideo(video));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.ok("Vídeo excluído com sucesso");
    }
}
