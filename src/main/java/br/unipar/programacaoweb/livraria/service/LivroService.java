package br.unipar.programacaoweb.livraria.service;

import br.unipar.programacaoweb.livraria.model.Livro;
import br.unipar.programacaoweb.livraria.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public void excluir(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }


    public void atualizarNumeroPaginasAleatoriamente() {
        List<Livro> livros = listarTodos();
        for (Livro livro : livros) {
            int numeroPaginasAleatorio = (int) (Math.random() * 1000) + 1; // Gera um número aleatório entre 1 e 1000
            livro.setNumeroPaginas(numeroPaginasAleatorio);
            System.out.println("Livro: " + livro.getTitulo() + " - Número de páginas atualizado para: " + numeroPaginasAleatorio);
            salvar(livro);
        }
    }

    public void criarNovaLeituraAleatoria() {
        Livro livro = new Livro();
        livro.setTitulo("Título Aleatório");
        livro.setNumeroPaginas(100);
        livro.setGenero("Gênero Aleatório");

        livroRepository.save(livro);
    }

    public void verificarSensoresOffline() {
        List<Livro> livros = listarTodos();
        for (Livro livro : livros) {
            //verifca se está offline
            if (livro.getNumeroPaginas() == null) {
               //mandar mensagem de aviso
                System.out.println("Livro: " + livro.getTitulo() + " está offline.");
            }
        }
    }
}
