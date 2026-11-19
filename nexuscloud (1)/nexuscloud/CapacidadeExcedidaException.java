
// exceção checked, o compilador OBRIGA quem chama o método a tratar (try-catch) | RA: 25033061-2
 
public class CapacidadeExcedidaException extends Exception {

    // construtor que recebe a mensagem de erro e repassa para a superclasse | RA: 25033061-2
    public CapacidadeExcedidaException(String msg) {
        super(msg);
    }
}


