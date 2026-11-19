package br.com.bookstore;

import br.com.bookstore.dao.LivroDAO;
import br.com.bookstore.dao.LivroDAOSqlite;
import br.com.bookstore.model.Livro;

import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        // 1. DIP: depende da abstração, não da implementação | RA: 25033061-2
        LivroDAO dao = new LivroDAOSqlite();

        // 2. persistência de 3 obras | RA: 25033061-2
        System.out.println("=== 1. Cadastrando livros ===");
        Livro[] novos = {
                new Livro("Clean Code", "Robert C. Martin", 129.90),
                new Livro("Design Patterns", "Erich Gamma et al.", 189.50),
                new Livro("Refactoring", "Martin Fowler", 154.00)
        };
        for (Livro livro : novos) {
            dao.salvar(livro);
            System.out.println("Salvo com ID " + livro.getId() + " -> " + livro.getTitulo());
        }

        // 3. busca do ID 2 | RA: 25033061-2
        System.out.println("\n=== 2. Buscando livro de ID 2 ===");
        dao.buscarPorId(2L).ifPresentOrElse(
                livro -> System.out.println("Encontrado: " + livro),
                () -> System.out.println("Aviso: nenhum livro encontrado com ID 2."));

        // 4. busca de ID inexistente: sem NullPointerException | RA: 25033061-2
        System.out.println("\n=== 3. Buscando livro de ID 999 ===");
        dao.buscarPorId(999L).ifPresentOrElse(
                livro -> System.out.println("Encontrado: " + livro),
                () -> System.out.println("Aviso: nenhum livro encontrado com ID 999."));

        // demonstração das invariantes de domínio | RA: 25033061-2
        System.out.println("\n=== 4. Validando invariantes de domínio ===");
        tentarCriar("", "Autor X", 10.0);
        tentarCriar("Título válido", "  ", 10.0);
        tentarCriar("Título válido", "Autor X", 0.0);

        // 5. listagem tabular | RA: 25033061-2
        System.out.println("\n=== 5. Catálogo completo ===");
        imprimirTabela(dao.listarTodos());
    }

    private static void tentarCriar(String titulo, String autor, double preco) {
        try {
            new Livro(titulo, autor, preco);
            System.out.println("Criado (inesperado!)");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejeitado: " + e.getMessage());
        }
    }

    private static void imprimirTabela(List<Livro> livros) {
        String formato = "| %-4s | %-25s | %-22s | %12s |%n";
        String linha = "+------+---------------------------+------------------------+--------------+";
        System.out.println(linha);
        System.out.printf(formato, "ID", "TÍTULO", "AUTOR", "PREÇO");
        System.out.println(linha);
        for (Livro l : livros) {
            System.out.printf(formato, l.getId(), l.getTitulo(), l.getAutor(),
                    String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", l.getPreco()));
        }
        System.out.println(linha);
        System.out.println("Total de registros: " + livros.size());
    }
}
