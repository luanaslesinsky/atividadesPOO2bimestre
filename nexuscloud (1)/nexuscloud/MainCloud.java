
 // classe executável com o roteiro de testes da atividade. | RA: 25033061-2

public class MainCloud {

    public static void main(String[] args) throws CapacidadeExcedidaException {

        // 1. instancia o gerenciador | RA: 25033061-2
        CloudManager manager = new CloudManager();

        // 2. instancia um cliente | RA: 25033061-2
        Cliente cliente = new Cliente("111.222.333-44", "Wayne Enterprises");

        // 3. instancia um servidor (nasce desligado) | RA: 25033061-2
        Servidor servidor = new Servidor("192.168.0.1", 16.0, 5.50);

        // 4. registra o cliente e associa o servidor a ele | RA: 25033061-2
        manager.registrarCliente(cliente);
        manager.provisionarServidor(cliente, servidor);

        // 5. instancia dois containers | RA: 25033061-2
        Container c1 = new Container("C-01", "App Web", 10.0);
        Container c2 = new Container("C-02", "Banco de Dados", 8.0);

        // 6. teste Unchecked: servidor desligado -> ServidorOfflineException aparece no console. | RA: 25033061-2
        //    Sem try-catch de propósito (unchecked não exige). O "throws" no main existe só 
        //    porque alocarContainer também declara a exceção checked. 
        //    >>> Passo 7: comentado após o teste para o programa prosseguir. <<< 
        // servidor.alocarContainer(c1); | RA: 25033061-2

        // 7. segue em frente: liga o servidor | RA: 25033061-2
        servidor.ligar();

        // 8. aloca o Container 1 com sucesso (10.0 <= 16.0) | RA: 25033061-2
        try {
            servidor.alocarContainer(c1);
            System.out.println("Container " + c1.getId() + " alocado com sucesso.");
        } catch (CapacidadeExcedidaException e) {
            // obrigatório: a exceção é checked (o compilador exige o tratamento)
            System.out.println(e.getMessage());
        }

        // 9. teste Checked: 10.0 + 8.0 = 18.0 > 16.0 -> CapacidadeExcedidaException | RA: 25033061-2
        try {
            servidor.alocarContainer(c2);
            System.out.println("Container " + c2.getId() + " alocado com sucesso.");
        } catch (CapacidadeExcedidaException e) {
            // trata o erro de forma elegante: imprime a mensagem sem derrubar o programa
            System.out.println(e.getMessage());
        }

        // 10. fatura ativa: servidor ligado custa 5.50/h | RA: 25033061-2
        System.out.println("Fatura ativa: R$ " + manager.calcularFaturaAtiva(cliente));
    }
}


