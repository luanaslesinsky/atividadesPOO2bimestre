import java.util.Objects;


 // POJO que representa um cliente da NexusCloud. | RA: 25033061-2
 // identidade lógica: dois clientes são iguais se tiverem o mesmo CPF | RA: 25033061-2
public class Cliente {

    // atributos privados (encapsulamento rigoroso) | RA: 25033061-2
    private String cpf;   // identificador lógico do cliente | RA: 25033061-2
    private String nome;

    // construtor: recebe os dados e inicializa os atributos | RA: 25033061-2
    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    // getters: acesso controlado de leitura aos atributos | RA: 25033061-2
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

   // equals: compara por dado de negócio (CPF), não por endereço de memória. | RA: 25033061-2

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                          // mesmo objeto na memória
        if (obj == null || getClass() != obj.getClass()) return false; // nulo ou de outra classe 
        Cliente outro = (Cliente) obj;                         // cast seguro após a checagem 
        return Objects.equals(this.cpf, outro.cpf);            // compara só o CPF (null-safe) 
    }

    // hashCode: DEVE ser coerente com equals | RA: 25033061-2
     
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}


