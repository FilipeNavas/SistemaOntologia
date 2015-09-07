/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaontologia.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.graphdb.Node;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;
import sistemaontologia.dao.conexaobanco.ConexaoBanco;
import sistemaontologia.dao.interfaces.PercorreNoInterface;
import sistemaontologia.entidade.No;
import sistemaontologia.entidade.PercorreNo;
import sistemaontologia.entidade.Relacionamento;

/**
 *
 * @author Filipe
 */
public class PercorreNoDao implements PercorreNoInterface{
    
    RestCypherQueryEngine engine;
    
    @Override
    public List<PercorreNo> bucarTodos(){
      
        //chama a conexão a partir da classe ConexaoBanco 
        engine = new RestCypherQueryEngine(ConexaoBanco.getConnection()); 

        //executa query
        final QueryResult<Map<String,Object>> result = engine.query(
                  "START n = node(*)"
                + "MATCH (n)-[r]->(x)"
                + "return n, type(r), x", null);

       //lista da classe percorrelista 
       List<PercorreNo> lista = new ArrayList<>();

       //faz a iteração entre os nós com base no result da query, em que percorrerá cada nó
       for (Map<String,Object> row : result) {
            Node x = (Node) row.get("x");
            Node n = (Node) row.get("n");
        
        //Cria um objeto PercorreNo
        PercorreNo percorreNo = new PercorreNo();   
        
        //Cria um objeto No para NoInicial
        No noInicial = new No();
        
        //Cria um objeto No para NoFinal
        No noFinal = new No();
        
                 
        for (String propriedade : n.getPropertyKeys()) {   
            
            //Seta os atributos de NoInicial
            switch (propriedade){
                
                case "id":
                    noInicial.setId(n.getProperty(propriedade).toString());
                break;
                
                case "nome":
                    noInicial.setNome(n.getProperty(propriedade).toString());
                break;
                
                case "descricao":
                    noInicial.setDescricao(n.getProperty(propriedade).toString());
                break;
                
            }
            
        }  
        
        
        //pega o tipo de relacionamento
        String strRelacionamento = (row.get("type(r)").toString());
        
        //Seta o relacionamento
        switch (strRelacionamento) {
            
            case "e_uma":
                    percorreNo.setRelacionamento(Relacionamento.E_UMA);
                break;
                
            case "esta_contido":
                    percorreNo.setRelacionamento(Relacionamento.ESTA_CONTIDO);
                break;

            case "eh":
                    percorreNo.setRelacionamento(Relacionamento.EH);
                break;
                
        }
        
        
        //percorre os atributos do nó final
        for (String propriedade : x.getPropertyKeys()){                              
           
            //Seta os atributos de NoFinal
            switch (propriedade){
                
                case "id":
                    noFinal.setId(x.getProperty(propriedade).toString());
                break;
                
                case "nome":
                    noFinal.setNome(x.getProperty(propriedade).toString());
                break;
                
                case "descricao":
                    noFinal.setDescricao(x.getProperty(propriedade).toString());
                break;
                
            }

        }        
        
        //Seta NoInicial e NoFinal no PercorreNo
        percorreNo.setNoInicial(noInicial);
        percorreNo.setNoFinal(noFinal);
        
        //adiciona na lista o percorreNo
        lista.add(percorreNo);           
          
      }//fim do for que percorre o result
      
       //retorna a lista
       return(lista);  
       
    }// fim do método
    
    
    
    @Override
    public List<PercorreNo> bucarNos(String busca){
      
        //chama a conexão a partir da classe ConexaoBanco 
        engine = new RestCypherQueryEngine(ConexaoBanco.getConnection()); 

        //cria um map chamado params para colocar chave/valor
       Map<String, Object> params = new HashMap<>();
       
       //coloca o valor conceito na chave conceito
       params.put( "busca", "(?i)" + busca);
       
        //executa query
        final QueryResult<Map<String,Object>> result = engine.query(
                 "START n = node(*)"
               + "MATCH (n)-[r]->(x)"
               + "WHERE"
               + " has(n.nome) and n.nome=~{busca} or"
               + " has(x.nome) and x.nome=~{busca}"
               + "return n, type(r), x", params);

       //lista da classe percorrelista 
       List<PercorreNo> lista = new ArrayList<>();

       //faz a iteração entre os nós com base no result da query, em que percorrerá cada nó
       for (Map<String,Object> row : result) {
            Node x = (Node) row.get("x");
            Node n = (Node) row.get("n");
        
        //Cria um objeto PercorreNo
        PercorreNo percorreNo = new PercorreNo();   
        
        //Cria um objeto No para NoInicial
        No noInicial = new No();
        
        //Cria um objeto No para NoFinal
        No noFinal = new No();
        
                 
        for (String propriedade : n.getPropertyKeys()) {   
            
            //Seta os atributos de NoInicial
            switch (propriedade){
                
                case "id":
                    noInicial.setId(n.getProperty(propriedade).toString());
                break;
                
                case "nome":
                    noInicial.setNome(n.getProperty(propriedade).toString());
                break;
                
                case "descricao":
                    noInicial.setDescricao(n.getProperty(propriedade).toString());
                break;
                
            }
            
        }  
        
        
        //pega o tipo de relacionamento
        String strRelacionamento = (row.get("type(r)").toString());
        
        //Seta o relacionamento
        switch (strRelacionamento) {
            
            case "e_uma":
                    percorreNo.setRelacionamento(Relacionamento.E_UMA);
                break;
                
            case "esta_contido":
                    percorreNo.setRelacionamento(Relacionamento.ESTA_CONTIDO);
                break;

            case "eh":
                    percorreNo.setRelacionamento(Relacionamento.EH);
                break;
                
        }
        
        
        //percorre os atributos do nó final
        for (String propriedade : x.getPropertyKeys()){                              
           
            //Seta os atributos de NoFinal
            switch (propriedade){
                
                case "id":
                    noFinal.setId(x.getProperty(propriedade).toString());
                break;
                
                case "nome":
                    noFinal.setNome(x.getProperty(propriedade).toString());
                break;
                
                case "descricao":
                    noFinal.setDescricao(x.getProperty(propriedade).toString());
                break;
                
            }

        }        
        
        //Seta NoInicial e NoFinal no PercorreNo
        percorreNo.setNoInicial(noInicial);
        percorreNo.setNoFinal(noFinal);
        
        //adiciona na lista o percorreNo
        lista.add(percorreNo);           
          
      }//fim do for que percorre o result
      
       //retorna a lista
       return(lista);  
       
   }// fim do método buscarConceitos
    
    
    
    
}
