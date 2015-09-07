/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaontologia.entidade.antigos;

import sistemaontologia.entidade.Relacionamento;
import sistemaontologia.entidade.antigos.Categoria;

/**
 *
 * @author Willina
 * Modified by Filipe
 */
public class Conceito {
    
    //Nome do conceito. Ex: SQL, Java, PHP
    private String nome;
    
    //Enum do relacionamento (E_UMA, ESTA_CONTIDO, ou EH).
    private Relacionamento relacionamento;
    
    private Categoria categoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Relacionamento getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(Relacionamento relacionamento) {
        this.relacionamento = relacionamento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    
}
