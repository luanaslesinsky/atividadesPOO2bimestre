package br.com.autotech.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class ConexaoFactory {

    private static final String URL = "jdbc:sqlite:autotech.db";

    private ConexaoFactory() {
        throw new UnsupportedOperationException("ConexaoFactory e utilitaria!");
    }

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
