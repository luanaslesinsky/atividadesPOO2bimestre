# NexusCloud Manager — Atividade 15/10 (POO)

UNICESUMAR – Engenharia de Software / ADSIS — Programação Orientada a Objetos

**Nome:** _preencha_  
**RA:** 25033061-2  
**Turma:** ADSIS / ESOFT

## Descrição
Núcleo de orquestração de servidores virtuais e containers: POJOs com `equals`/`hashCode`,
coleções (`ArrayList`, `HashMap`, `HashSet`), exceções checked e unchecked e Streams API.

## Estrutura
| Arquivo | Função |
|---|---|
| `Cliente.java` | POJO, identidade por CPF |
| `Servidor.java` | POJO, identidade por IP, alocação de containers e RAM em uso |
| `Container.java` | POJO, identidade por ID |
| `CloudManager.java` | Banco de dados (`Map<Cliente, Set<Servidor>>`) e fatura ativa |
| `ServidorOfflineException.java` | Exceção unchecked |
| `CapacidadeExcedidaException.java` | Exceção checked |
| `MainCloud.java` | Roteiro de testes |

## Como executar
```bash
javac -d out *.java
java -cp out MainCloud
```

## Saída esperada
```
Container C-01 alocado com sucesso.
Falha Crítica: Memória RAM insuficiente no servidor 192.168.0.1.
Fatura ativa: R$ 5.5
```
