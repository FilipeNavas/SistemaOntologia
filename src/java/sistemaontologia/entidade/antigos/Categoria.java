/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaontologia.entidade.antigos;

/**
 *
 * @author Filipe
 */

public class Categoria {
    
    //Tipo eh como o nome da categoria. Usado como tipo pra diferenciar do conceito.
    //Ex: Linguagem de Programacao, Redes
    private String tipo; 


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
