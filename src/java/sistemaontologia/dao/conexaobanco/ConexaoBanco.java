/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaontologia.dao.conexaobanco;

import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;

/**
 *
 * @author Willian
 */
public class ConexaoBanco {
    public static RestAPI bd;
        
 public static RestAPI getConnection() {
    try {         
        
       //bd = new RestAPIFacade("http://localhost:7474/db/data", "neo4j", "graphs" );               
        bd = new RestAPIFacade("http://localhost:7474/db/data", "neo4j", "graphs" );               
        
   } catch (Exception e) {
       e.printStackTrace();
   }
        
    
    return(bd);
 }
 
}
