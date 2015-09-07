/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaontologia.dao.interfaces;

import java.util.List;
import sistemaontologia.entidade.PercorreNo;

/**
 *
 * @author Filipe
 */
public interface PercorreNoInterface {
    
    /**
    * Este metodo busca tudo o que existe no BD.
    * @return List<PercorreNo>
    */
   public List<PercorreNo> bucarTodos();
   
   /**
    * Este metodo faz a busca de nos e relacionamentos pela busca do usuario.
    * @param busca
    * @return 
    */
   public List<PercorreNo> bucarNos(String busca);
    
}
