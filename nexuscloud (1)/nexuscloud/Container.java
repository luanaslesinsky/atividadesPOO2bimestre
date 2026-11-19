import java.util.Objects;

// POJO que representa uma aplicação (container) rodando em um servidor | RA: 25033061-2
 // identidade lógica: dois containers são iguais se tiverem o mesmo ID | RA: 25033061-2
 
public class Container {

    // atributos privados (encapsulamento rigoroso) | RA: 25033061-2
    private String id;             // identificador lógico do container | RA: 25033061-2
    private String nomeApp;        // nome da aplicação | RA: 25033061-2
    private double ramNecessaria;  // RAM (GB) que o container consome | RA: 25033061-2

    // construtor: inicializa todos os atributos | RA: 25033061-2
    public Container(String id, String nomeApp, double ramNecessaria) {
        this.id = id;
        this.nomeApp = nomeApp;
        this.ramNecessaria = ramNecessaria;
    }

    // getters (conforme o diagrama UML) | RA: 25033061-2
    public String getId() {
        return id;
    }

    public double getRamNecessaria() {
        return ramNecessaria;
    }

    // equals por ID: mesmo ID = mesmo container, independentemente do endereço de memória | RA: 25033061-2
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                          // mesma referência | RA: 25033061-2
        if (obj == null || getClass() != obj.getClass()) return false; // nulo/classe diferente | RA: 25033061-2
        Container outro = (Container) obj;                     // cast após validar o tipo | RA: 25033061-2
        return Objects.equals(this.id, outro.id);              // compara apenas o ID | RA: 25033061-2
    }

    // hashCode coerente com equals: baseado somente no ID | RA: 25033061-2
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

