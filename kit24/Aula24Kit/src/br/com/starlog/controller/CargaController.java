package br.com.starlog.controller;

import br.com.starlog.model.Carga;
import br.com.starlog.view.TelaCadastroCarga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CargaController {

    private final List<Carga> repositorio = new ArrayList<>();

    
    
     @param codigoRaw    
     @param categoriaRaw 
     @param pesoRaw      
     @param valorRaw    
     @param tela       
     
    public void cadastrarCarga(String codigoRaw, String categoriaRaw, String pesoRaw, String valorRaw, TelaCadastroCarga tela) {

            if (pesoRaw == null || pesoRaw.trim().isEmpty()) {
                throw new IllegalArgumentException("O campo Peso é obrigatório.");
            }
            if (valorRaw == null || valorRaw.trim().isEmpty()) {
                throw new IllegalArgumentException("O campo Valor Declarado é obrigatório.");
            }
            double peso = Double.parseDouble(pesoRaw.trim().replace(",", "."));
            double valor = Double.parseDouble(valorRaw.trim().replace(",", "."));

            
            Carga novaCarga = new Carga(codigoRaw, categoriaRaw, peso, valor);

         
            this.repositorio.add(novaCarga);

          
            if (tela != null) {
                tela.exibirMensagemSucesso("Carga cadastrada com sucesso!\n" + novaCarga.toString());
                tela.atualizarRelatorio(gerarTextoRelatorio());
            }

        } catch (NumberFormatException e) {
            
            if (tela != null) {
                tela.exibirMensagemErro("Erro de formato numérico: Digite apenas números válidos para Peso e Valor!");
            }
        } catch (IllegalArgumentException e) {
          
            if (tela != null) {
                tela.exibirMensagemErro("Regra de Negócio Violada: " + e.getMessage());
            }
        }
    }

   
    public String gerarTextoRelatorio() {
        if (repositorio.isEmpty()) {
            return "Nenhuma carga registrada na esteira.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("--- RELATÓRIO OFICIAL DE DESPACHO ---\n");
        double totalSeguro = 0.0;
        double pesoTotal = 0.0;

        for (int i = 0; i < repositorio.size(); i++) {
            Carga c = repositorio.get(i);
            sb.append(String.format("%02d. %s\n", (i + 1), c.toString()));
            totalSeguro += c.calcularSeguro();
            pesoTotal += c.getPeso();
        }

        sb.append("-------------------------------------\n");
        sb.append(String.format("Total de Cargas: %d | Peso Total: %.2f kg | Seguro Acumulado: R$ %.2f\n",
                repositorio.size(), pesoTotal, totalSeguro));

        return sb.toString();
    }

    public List<Carga> getRepositorio() {
        return Collections.unmodifiableList(repositorio);
    }
}
