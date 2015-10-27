/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaontologia.dao.interfaces;

import java.util.List;
import sistemaontologia.entidade.No;

/**
 *
 * @author Filipe
 */
public interface NoInterface {
    
    /**
     * Busca um No dado um id
     * @param busca
     * @return 
     */
    public No bucarNoPorId(String busca);
    
    public void createNo(String nome, String descricao);
    
    /**
     * Busca todos os nos que tem no BD
     */
    public List<No> buscarTodosNos();
    
}
