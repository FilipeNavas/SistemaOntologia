/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaontologia.consultas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.rest.graphdb.GraphDatabaseFactory;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;
import sistemaontologia.dao.conexaobanco.ConexaoBanco;
import sistemaontologia.entidade.Livro;
import sistemaontologia.entidade.PercorreConceito;


/**
 *
 * @author Willian
 */
public class  ConsultasBanco {
    
    RestCypherQueryEngine engine;
    
  public List<Livro> buscarLivro(String conceito) throws ClassNotFoundException { 
    
       //chama a conexão a partir da classe ConexaoBanco 
       engine = new RestCypherQueryEngine(ConexaoBanco.getConnection()); 
                        
       //cria um map chamado params para colocar chave/valor
       Map<String, Object> params = new HashMap<String, Object>();
       
       //coloca o valor palavra na chave nome
       params.put( "conceito", "(?i)" + conceito );
       
       //executa query
       final QueryResult<Map<String,Object>> result = engine.query("MATCH a:Conceito --> b:Livro WHERE "
                                                        + "a.nome =~{conceito} return"
                                                        + " b.autor, "
                                                        + "b.titulo,"
                                                        + "b.edicao, "
                                                        + " b.cidade,"
                                                        + "b.editora, "
                                                        + "b.ano,"
                                                        + "b.p", params);
              
       List<Livro> lista = new ArrayList<Livro>();       

       //percorre o result da query pegando os valores e adicionando na lista
       for (Map<String, Object> row : result) {                   
           
            //a cada interação do for ele cria um novo livro para setar os atributos           
            Livro l = new Livro();
            l.setAutor(row.get("b.autor").toString());    
            l.setTitulo(row.get("b.titulo").toString());    
            l.setEdicao(row.get("b.edicao").toString());
            l.setCidade(row.get("b.cidade").toString());
            l.setEditora(row.get("b.editora").toString());            
            l.setAno(row.get("b.ano").toString());
            l.setPaginas(row.get("b.p").toString());
            
            //adiciona o livro na lista
            lista.add(l);
       }
       
       //retorna a lista, para o servletGrafo
       return(lista);
  } //fim do método buscarLivro
  
  public List<PercorreConceito> bucarConceitos(String conceito) {
      
       //chama a conexão a partir da classe ConexaoBanco 
       engine = new RestCypherQueryEngine(ConexaoBanco.getConnection()); 
                        
       //cria um map chamado params para colocar chave/valor
       Map<String, Object> params = new HashMap<String, Object>();
       
       //coloca o valor conceito na chave conceito
       params.put( "conceito", "(?i)" + conceito);
       
       //executa query
       final QueryResult<Map<String,Object>> result = engine.query(
                 "START n = node(*)"
               + "MATCH (n)-[r]->(x)"
               + "WHERE"
               + " has(n.nome) and n.nome=~{conceito} or"
               + " has(n.tipo) and n.tipo=~{conceito}"
               + "return n, type(r), x", params);
       
      //lista da classe percorrelista 
      List<PercorreConceito> lista = new ArrayList<PercorreConceito>();
       
      //faz a iteração entre os nós com base no result da query, em que percorrerá cada nó
      for (Map<String,Object> row : result) {
           Node x = (Node) row.get("x");
           Node n = (Node) row.get("n");
        
        //percorre os atributos do nó inicial  
        PercorreConceito percorre = new PercorreConceito();   
        
        String noInicial = "";           
        for (String cabecalho : n.getPropertyKeys()) {                       
            noInicial += n.getProperty(cabecalho).toString();                        
        }  
        
        percorre.setNoInicial(noInicial);
        //pega o tipo de relacionamento
        percorre.setTipoRelacionamento(row.get("type(r)").toString());
        
        //percorre os atributos do nó final
        String noFinal ="";
        for (String cabecalho : x.getPropertyKeys()){                              
           noFinal += cabecalho + ": " + x.getProperty(cabecalho).toString() + ". ";
        }        
        
        percorre.setNoFinal(noFinal);
        //cria a lista com base no noInicial, relacionamento e noFinal    
        lista.add(percorre);           
          
      }//fim do for que percorre o result
      
       //retorna a lista
       return(lista);      
   }// fim do método buscarConceitos
}//fim da classe