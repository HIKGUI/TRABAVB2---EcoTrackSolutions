package br.unipar.programacaoweb.livraria.service;

import br.unipar.programacaoweb.livraria.model.Autor;
import br.unipar.programacaoweb.livraria.model.Livro;
import br.unipar.programacaoweb.livraria.repository.AutorRepository;
import br.unipar.programacaoweb.livraria.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final LivroService livroService;
    private AutorRepository autorRepository;
    private LivroRepository livroRepository;

    public AutorService(AutorRepository autorRepository, LivroRepository livroRepository, LivroService livroService) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
        this.livroService = livroService;
    }

    @Transactional
    public Autor salvar(Autor autor) {
        for (Livro livro : autor.getLivros()) {
            livro.setAutor(autor);
        }
        return autorRepository.save(autor);
    }

    public void excluir(Long id) {
        autorRepository.deleteById(id);
    }

    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Autor editar(Long id, Autor novo) {
        Autor autorEditando = buscarPorId(id);

        if(autorEditando != null) {
            autorEditando.setNome(novo.getNome());
            autorEditando.setNacionalidade(novo.getNacionalidade());
            autorEditando.setDataNascimento(novo.getDataNascimento());
            autorEditando.setEmail(novo.getEmail());

            for (Livro livro : novo.getLivros()) {
                Livro livroEditando = livroService.buscarPorId(livro.getId());

                if(livroEditando != null) {
                    livroEditando.setAutor(autorEditando);
                    livroEditando.setTitulo(livro.getTitulo());
                    livroEditando.setNumeroPaginas(livro.getNumeroPaginas());
                    livroEditando.setGenero(livro.getGenero());

                    autorEditando.getLivros().add(livroEditando);
                }
            }

            autorRepository.save(autorEditando);
            return autorEditando;
        }
        return null;
    }
}