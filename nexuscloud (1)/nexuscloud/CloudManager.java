import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// banco de dados principal da NexusCloud | RA: 25033061-2
 
 
public class CloudManager {

    // Map (interface) instanciado com HashMap | RA: 25033061-2
    // Chave = Cliente (usa equals/hashCode por CPF); Valor = Set<Servidor> (HashSet, usa IP) | RA: 25033061-2
    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    // construtor padrão | RA: 25033061-2
    public CloudManager() {
    }

    // registra um cliente com um conjunto vazio de servidores | RA: 25033061-2
    // putIfAbsent evita apagar os servidores de um cliente já cadastrado | RA: 25033061-2
    public void registrarCliente(Cliente c) {
        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    // associa um servidor a um cliente já registrado | RA: 25033061-2
    public void provisionarServidor(Cliente c, Servidor s) {
        Set<Servidor> servidores = bancoDeDados.get(c);   // busca pelo CPF
        if (servidores != null) {
            servidores.add(s);                            // Set ignora IP duplicado
        } else {
            System.out.println("Cliente não registrado: " + c.getNome());
        }
    }

   
      // cliente inexistente retorna 0.0 | RA: 25033061-2
     
    public double calcularFaturaAtiva(Cliente c) {
        return bancoDeDados.getOrDefault(c, new HashSet<>())  // conjunto de servidores do cliente 
                .stream()
                .filter(Servidor::isStatusLigado)             // só os que estão ligados 
                .mapToDouble(Servidor::getPrecoPorHora)       // extrai o preço por hora 
                .sum();                                       // soma final 
    }
}


