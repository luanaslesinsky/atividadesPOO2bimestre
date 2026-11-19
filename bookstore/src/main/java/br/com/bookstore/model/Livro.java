package br.com.bookstore.model;

import java.util.Locale;


 // entidade de domínio que representa um livro do catálogo
 // as invariantes são validadas no construtor: um Livro inválido nunca existe | RA: 25033061-2
 
public class Livro {

    private Long id;
    private final String titulo;
    private final String autor;
    private final double preco;

    /** Construtor completo: usado para hidratar tuplas vindas do banco. | RA: 25033061-2 */
    public Livro(Long id, String titulo, String autor, double preco) {
        validarTitulo(titulo);
        validarAutor(autor);
        validarPreco(preco);
        this.id = id;
        this.titulo = titulo.trim();
        this.autor = autor.trim();
        this.preco = preco;
    }

    // construtor de negócio: o id ainda não existe (será gerado pelo banco) | RA: 25033061-2 
    public Livro(String titulo, String autor, double preco) {
        this(null, titulo, autor, preco);
    }

    private static void validarTitulo(String titulo) {
        if (titulo == null || titulo.trim().length() < 2) {
            throw new IllegalArgumentException(
                    "O título é obrigatório e deve ter ao menos 2 caracteres.");
        }
    }

    private static void validarAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("O autor é obrigatório.");
        }
    }

    private static void validarPreco(double preco) {
        if (preco <= 0.0) {
            throw new IllegalArgumentException("O preço de venda deve ser estritamente positivo");
        }
    }

    public Long getId() {
        return id;
    }

    // único setter permitido: sincroniza a PK gerada pelo banco | RA: 25033061-2 
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return String.format(Locale.forLanguageTag("pt-BR"),
                "Livro [id=%d, título='%s', autor='%s', preço=R$ %.2f]",
                id, titulo, autor, preco);
    }
}
