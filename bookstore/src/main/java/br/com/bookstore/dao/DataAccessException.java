package br.com.bookstore.dao;

// exceção não checada que encapsula falhas de infraestrutura | RA: 25033061-2
public class DataAccessException extends RuntimeException {
    public DataAccessException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
