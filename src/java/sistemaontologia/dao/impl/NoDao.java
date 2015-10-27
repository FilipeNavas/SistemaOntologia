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
import sistemaontologia.dao.interfaces.NoInterface;
import sistemaontologia.entidade.No;

/**
 *
 * @author Filipe
 */
public class NoDao implements NoInterface{
    
    RestCypherQueryEngine engine;
    
    @Override
    public No bucarNoPorId(String busca){
      
        //chama a conexão a partir da classe ConexaoBanco 
        engine = new RestCypherQueryEngine(ConexaoBanco.getConnection()); 
        
        //Cria um objeto No
        No no = new No();

        //cria um map chamado params para colocar chave/valor
       Map<String, Object> params = new HashMap<>();
       
       
       //int buscaInt = Integer.parseInt(busca);
       
       //coloca o valor conceito na chave conceito
       params.put( "busca", "(?i)" + busca);
       
       
       
       QueryResult<Map<String,Object>> result = null;
       
       try{
        //executa query
        result = engine.query(
               "START n = node(*)"
               + " MATCH (n)"
               + " WHERE"
               + " has(n.id) and n.id=~{busca}"
               + " return n", params);
       }catch(Exception e){
           e.printStackTrace();
       }
       
       //faz a iteração entre os nós com base no result da query, em que percorrerá cada nó
       for (Map<String,Object> row : result) {

            Node n = (Node) row.get("n");
        
            
        for (String propriedade : n.getPropertyKeys()) {   
            
            //Seta os atributos de NoInicial
            switch (propriedade){
                
                case "id":
                    no.setId(n.getProperty(propriedade).toString());
                break;
                
                case "nome":
                    no.setNome(n.getProperty(propriedade).toString());
                break;
                
                case "descricao":
                    no.setDescricao(n.getProperty(propriedade).toString());
                break;
                
            }
            
        }  
        
          
      }//fim do for que percorre o result
      
       //retorna o no
       return no;  
       
   }// fim do método buscarConceitos
    
    
    @Override
    public void createNo(String nome, String descricao){
        
        //Cria uma engine
        engine = new RestCypherQueryEngine(ConexaoBanco.getConnection()); 
        
        //Pega o ultimo no para incrementar o id
        No ultimoNo = pegarUltimoNo();
        
        String id;
        
        //Se o ultimoNo for vazio quer dizer q nao tem nada no BD.
        if(ultimoNo.getId() == null){
            id = "0";
        }else{
            //Cria o id a ser passado (adiciona 1 ao ultimo valor)
            //Retorna a conta como uma String
            id = String.valueOf(Integer.parseInt(ultimoNo.getId()) + 1);
        }
        
        //cria um map chamado params para colocar chave/valor
        Map<String, Object> params = new HashMap<>();
        params.put( "id", id);
        params.put( "nome", nome);
        params.put( "descricao", descricao);
        
        
        //Executa a query
        try{
            engine.query(
                  " CREATE (n {id : {id}, nome : {nome}, descricao : {descricao}}) RETURN n"
                  , params);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    
    public No pegarUltimoNo(){
        
        engine = new RestCypherQueryEngine(ConexaoBanco.getConnection()); 
         
        /*
        Match (n)
        Return n
        Order by ID(n) desc
        Limit 1;
        1*/
        
        QueryResult<Map<String,Object>> result = null;
        
        No no = new No();
       
        try{
         //executa query
         result = engine.query(
                "Match (n)"
                + " Return n"
                + " Order by ID(n) desc"
                + " Limit 1", null);
        }catch(Exception e){
            e.printStackTrace();
        }
        
       
        //faz a iteração entre os nós com base no result da query, em que percorrerá cada nó
        for (Map<String,Object> row : result) {

             Node n = (Node) row.get("n");


         for (String propriedade : n.getPropertyKeys()) {   

             //Seta os atributos de NoInicial
             switch (propriedade){

                 case "id":
                     no.setId(n.getProperty(propriedade).toString());
                 break;

                 case "nome":
                     no.setNome(n.getProperty(propriedade).toString());
                 break;

                 case "descricao":
                     no.setDescricao(n.getProperty(propriedade).toString());
                 break;

             }

         }  


       }//fim do for que percorre o result
       
        
        //retorna o no
        return no;  

    }
    
    @Override
    public List<No> buscarTodosNos(){
        
        engine = new RestCypherQueryEngine(ConexaoBanco.getConnection()); 
        
        QueryResult<Map<String,Object>> result = null;
        
        List listaDeNos = new ArrayList<>();
        No no = new No();
       
        try{
         //executa query
         result = engine.query(
                " Match (n)"
                + " Return n", null);
        }catch(Exception e){
            e.printStackTrace();
        }
        
       
        //faz a iteração entre os nós com base no result da query, em que percorrerá cada nó
        for (Map<String,Object> row : result) {

             Node n = (Node) row.get("n");


         for (String propriedade : n.getPropertyKeys()) {   

             //Seta os atributos de NoInicial
             switch (propriedade){

                 case "id":
                     no.setId(n.getProperty(propriedade).toString());
                 break;

                 case "nome":
                     no.setNome(n.getProperty(propriedade).toString());
                 break;

                 case "descricao":
                     no.setDescricao(n.getProperty(propriedade).toString());
                 break;

             }

         }  

        listaDeNos.add(no);

       }//fim do for que percorre o result
       
        
        //retorna a lista
        return listaDeNos;  

    }

    
    
}
