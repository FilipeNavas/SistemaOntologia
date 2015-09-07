/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaontologia.dao.antigo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.graphdb.Node;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;
import sistemaontologia.dao.conexaobanco.ConexaoBanco;
import sistemaontologia.entidade.antigos.Categoria;
import sistemaontologia.entidade.antigos.Conceito;
import sistemaontologia.entidade.Relacionamento;

/**
 *
 * @author Filipe
 */
public class ConceitoDao implements ConceitoInterface{
    
    RestCypherQueryEngine engine;
    
    
    @Override
    public List<Conceito> bucarConceitos(String nomeConceitoBusca) {
      
       //chama a conexão a partir da classe ConexaoBanco 
       engine = new RestCypherQueryEngine(ConexaoBanco.getConnection()); 
                        
       //cria um map chamado params para colocar chave/valor
       Map<String, Object> params = new HashMap<>();
       
       //coloca o valor conceito na chave conceito
       params.put( "conceito", "(?i)" + nomeConceitoBusca);
       
       //executa query
       final QueryResult<Map<String,Object>> result = engine.query(
                 "START n = node(*)"
               + "MATCH (n)-[r]->(x)"
               + "WHERE"
               + " has(n.nome) and n.nome=~{conceito} or"
               + " has(n.tipo) and n.tipo=~{conceito}"
               + "return n, type(r), x", params);
       
      //lista da classe Conceito 
      List<Conceito> lista = new ArrayList<>();
       
      //faz a iteração entre os nós com base no result da query, em que percorrerá cada nó
      for (Map<String,Object> row : result) {
           Node x = (Node) row.get("x");
           Node n = (Node) row.get("n");
        
        //Cria um objeto do tipo Conceito
        Conceito conceito = new Conceito();   
        
        //Cria um objeto do tipo Categoria
        Categoria categoria = new Categoria();
        
        //Cria um objeto do tipo Relacionamento
        //Relacionamento relacionamneto = new Relacionamento();
        
        String nomeConceito = "";           
        for (String cabecalho : n.getPropertyKeys()) {                       
            nomeConceito = n.getProperty(cabecalho).toString();                        
        }  
        
        //Seta o nome do conceito
        conceito.setNome(nomeConceito);
        
        //Pega o relacionamento do conceito
        String strRelacionamento = row.get("type(r)").toString();
        
        switch (strRelacionamento) {
            
            case "e_uma":
                    conceito.setRelacionamento(Relacionamento.E_UMA);
                break;
                
            case "esta_contido":
                    conceito.setRelacionamento(Relacionamento.ESTA_CONTIDO);
                break;

            case "e":
                    conceito.setRelacionamento(Relacionamento.EH);
                break;
                
        }
        
        //percorre os atributos do nó final
        String tipoCategoria ="";
        for (String cabecalho : x.getPropertyKeys()){                              
           tipoCategoria = x.getProperty(cabecalho).toString();
        }        
        
        categoria.setTipo(tipoCategoria);
        
        conceito.setCategoria(categoria);
        
        //cria a lista com base no noInicial, relacionamento e noFinal    
        lista.add(conceito);           
          
      }//fim do for que percorre o result
      
       //retorna a lista
       return(lista);      
    }// fim do método buscarConceitos
    
    
    
    
    @Override
    public List<Conceito> bucarTodosConceitos() {
      
       //chama a conexão a partir da classe ConexaoBanco 
       engine = new RestCypherQueryEngine(ConexaoBanco.getConnection()); 
                    
       //cria um map chamado params para colocar chave/valor
       //Map<String, Object> params = new HashMap<>();
       
       //coloca o valor conceito na chave conceito
       //params.put( "", "");
       
         //executa query
       final QueryResult<Map<String,Object>> result = engine.query(
                 "START n = node(*)" 
               + " MATCH (n)-[r]->(x)"
               + " return n, type(r), x", null);
       
      //lista da classe Conceito 
      List<Conceito> lista = new ArrayList<>();
       
      //faz a iteração entre os nós com base no result da query, em que percorrerá cada nó
      for (Map<String,Object> row : result) {
           Node x = (Node) row.get("x");
           Node n = (Node) row.get("n");
        
        //Cria um objeto do tipo Conceito
        Conceito conceito = new Conceito();   
        
        //Cria um objeto do tipo Categoria
        Categoria categoria = new Categoria();
        
        //Cria um objeto do tipo Relacionamento
        //Relacionamento relacionamneto = new Relacionamento();
        
        String nomeConceito = "";           
        for (String cabecalho : n.getPropertyKeys()) {                       
            nomeConceito = n.getProperty(cabecalho).toString();                        
        }  
        
        //Seta o nome do conceito
        conceito.setNome(nomeConceito);
        
        //Pega o relacionamento do conceito
        String strRelacionamento = row.get("type(r)").toString();
        
        switch (strRelacionamento) {
            
            case "e_uma":
                    conceito.setRelacionamento(Relacionamento.E_UMA);
                break;
                
            case "esta_contido":
                    conceito.setRelacionamento(Relacionamento.ESTA_CONTIDO);
                break;

            case "e":
                    conceito.setRelacionamento(Relacionamento.EH);
                break;
                
        }
        
        //percorre os atributos do nó final
        String tipoCategoria ="";
        for (String cabecalho : x.getPropertyKeys()){                              
           tipoCategoria = x.getProperty(cabecalho).toString();
        }        
        
        categoria.setTipo(tipoCategoria);
        
        conceito.setCategoria(categoria);
        
        //cria a lista com base no noInicial, relacionamento e noFinal    
        lista.add(conceito);           
          
      }//fim do for que percorre o result
      
       //retorna a lista
       return(lista);      
   }// fim do método buscarConceitos
    
}
