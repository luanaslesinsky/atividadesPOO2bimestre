import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


 // POJO que representa um servidor virtual. | RA: 25033061-2
 // identidade lógica: dois servidores são iguais se tiverem o mesmo IP | RA: 25033061-2
 
public class Servidor {

    // atributos privados (encapsulamento) | RA: 25033061-2
    private String ip;              // identificador lógico do servidor 
    private double precoPorHora;    // custo de uso por hora (R$)
    private double ramTotal;        // capacidade total de RAM (GB) 
    private boolean statusLigado;   // true = ligado, false = desligado 

    // lista cronológica de containers, já inicializada na declaração | RA: 25033061-2
    private List<Container> containers = new ArrayList<>();

    // construtor: o servidor "nasce" desligado | RA: 25033061-2
    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = ip;
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
        this.statusLigado = false;
    }

    // getters usados em outras classes | RA: 25033061-2
    public String getIp() {
        return ip;
    }

    public double getPrecoPorHora() {
        return precoPorHora;
    }

    public double getRamTotal() {
        return ramTotal;
    }

    public boolean isStatusLigado() {
        return statusLigado;
    }

    // liga o servidor | RA: 25033061-2
    public void ligar() {
        this.statusLigado = true;
    }

    // desliga o servidor | RA: 25033061-2
    public void desligar() {
        this.statusLigado = false;
    }

    
     //aloca um container no servidor aplicando Fail-Fast | RA: 25033061-2
     // (valida tudo ANTES de alterar o estado interno). | RA: 25033061-2
     @throws CapacidadeExcedidaException 
     @throws ServidorOfflineException    
     
    public void alocarContainer(Container c) throws CapacidadeExcedidaException {

        // servidor desligado não aceita containers | RA: 25033061-2
        if (!statusLigado) {
            throw new ServidorOfflineException("Erro: Não é possível alocar containers em um servidor desligado.");
        }

        // RAM já em uso + RAM do novo container não pode passar do total | RA: 25033061-2
        if (calcularRamEmUso() + c.getRamNecessaria() > ramTotal) {
            throw new CapacidadeExcedidaException("Falha Crítica: Memória RAM insuficiente no servidor " + ip + ".");
        }

        // passou nas validações: só agora o container é adicionado à lista | RA: 25033061-2
        containers.add(c);
    }

   // soma da RAM de todos os containers alocados, usando Streams (sem laço for). | RA: 25033061-2
   
    public double calcularRamEmUso() {
        return containers.stream()
                .mapToDouble(Container::getRamNecessaria)
                .sum();
    }

    // equals por IP: mesmo IP = mesmo servidor | RA: 25033061-2
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                          // mesma referência 
        if (obj == null || getClass() != obj.getClass()) return false; // nulo/classe diferente 
        Servidor outro = (Servidor) obj;                       // cast após validar o tipo 
        return Objects.equals(this.ip, outro.ip);              // compara apenas o IP 
    }

    // hashCode coerente com equals: baseado somente no IP | RA: 25033061-2
    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}

