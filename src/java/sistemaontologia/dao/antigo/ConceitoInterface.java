/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaontologia.dao.antigo;

import java.util.List;
import sistemaontologia.entidade.antigos.Conceito;
import sistemaontologia.entidade.PercorreNo;

/**
 *
 * @author Filipe
 */
public interface ConceitoInterface {
    
   public List<Conceito> bucarConceitos(String nomeConceitoBusca);
   
   /**
    * Este metodo busca tudo de CONCEITOS que tem no BD.
     * @return List<Conceito>
   */
   public List<Conceito> bucarTodosConceitos();
   
    
}
