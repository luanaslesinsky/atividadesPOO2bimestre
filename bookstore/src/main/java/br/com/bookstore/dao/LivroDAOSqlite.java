package br.com.bookstore.dao;

import br.com.bookstore.model.Livro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// implementação JDBC do LivroDAO sobre SQLite | RA: 25033061-2 
public class LivroDAOSqlite implements LivroDAO {

    private static final String URL_PADRAO = "jdbc:sqlite:bookstore.db";

    private static final String SQL_CREATE = """
            CREATE TABLE IF NOT EXISTS livros (
                id     INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT NOT NULL,
                autor  TEXT NOT NULL,
                preco  REAL NOT NULL
            )""";
    private static final String SQL_INSERT =
            "INSERT INTO livros (titulo, autor, preco) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_BY_ID =
            "SELECT id, titulo, autor, preco FROM livros WHERE id = ?";
    private static final String SQL_SELECT_ALL =
            "SELECT id, titulo, autor, preco FROM livros ORDER BY id";

    private final String url;

    public LivroDAOSqlite() {
        this(URL_PADRAO);
    }

    public LivroDAOSqlite(String url) {
        this.url = url;
        inicializarTabela();
    }

    // inicialização determinística do schema | RA: 25033061-2 
    private void inicializarTabela() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(SQL_CREATE);
        } catch (SQLException e) {
            throw new DataAccessException("Falha ao criar a tabela 'livros'.", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    @Override
    public void salvar(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("O livro não pode ser nulo.");
        }
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setDouble(3, livro.getPreco());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    livro.setId(keys.getLong(1));
                } else {
                    throw new DataAccessException("Nenhum ID gerado para o livro inserido.", null);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Falha ao salvar o livro: " + livro.getTitulo(), e);
        }
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapearLinha(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Falha ao buscar o livro de id " + id, e);
        }
    }

    @Override
    public List<Livro> listarTodos() {
        List<Livro> livros = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                livros.add(mapearLinha(rs));
            }
            return livros;
        } catch (SQLException e) {
            throw new DataAccessException("Falha ao listar os livros.", e);
        }
    }

    // mapeamento centralizado de uma linha do ResultSet para a entidade | RA: 25033061-2 
    private Livro mapearLinha(ResultSet rs) throws SQLException {
        return new Livro(
                rs.getLong("id"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getDouble("preco"));
    }
}
