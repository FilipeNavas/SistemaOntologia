/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaontologia.dao.impl;

import com.sun.xml.ws.security.impl.policy.Constants;
import java.util.HashMap;
import java.util.Map;
import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.graphdb.Node;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.services.RequestType;
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
    public void createNo(){
        
        //chama a conexão a partir da classe ConexaoBanco 
        //RestAPI connection = ConexaoBanco.getConnection();
        
        engine = new RestCypherQueryEngine(ConexaoBanco.getConnection()); 
        
        //cria um map chamado params para colocar chave/valor
        Map<String, Object> params = new HashMap<>();
        params.put( "id", 5);
        params.put( "nome", "PHP");
        params.put( "descricao", "JAVA > PHP");
        
        //connection.createNode(params);
        
        
        
        QueryResult<Map<String,Object>> result = null;
        
        
//        result = engine.query(
//               "START n = node(*)"
//               + " MATCH (n)"
//               + " WHERE"
//               + " has(n.id) and n.id=~{busca}"
//               + " return n", params);

        try{
         //executa query
         result = engine.query(
                  " CREATE n VALUES {id :{id}, nome :{nome}, descricao :{descricao}}"
                  , params);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //Executa a query Cypher criando um nó com valores e passando
        //para uma String result
        //String result = connection.execute( RequestType.POST , "CREATE n VALUES {id :'5', nome :'PHP', descricao : 'PHP < Java'}", null).toString();
        
    }
    
    
    
}
