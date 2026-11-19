// Exceção UNCHECKED (herda de RuntimeException) | RA: 25033061-2
//Lançada quando se tenta alocar um container em um servidor desligado | RA: 25033061-2
 
public class ServidorOfflineException extends RuntimeException {

    // construtor que recebe a mensagem de erro e repassa para a superclasse | RA: 25033061-2
    public ServidorOfflineException(String msg) {
        super(msg);
    }
}


