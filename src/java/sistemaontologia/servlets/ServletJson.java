/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaontologia.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistemaontologia.dao.impl.NoDao;
import sistemaontologia.dao.impl.PercorreNoDao;
import sistemaontologia.dao.interfaces.NoInterface;
import sistemaontologia.dao.interfaces.PercorreNoInterface;
import sistemaontologia.entidade.No;

/**
 *
 * @author Filipe
 */
public class ServletJson extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
      String conceito = request.getParameter("conceito");
      String acao = request.getParameter("selecao");
      
        try {  

            if(acao.equals("todos")) {

                //Cria um objeto de ConceitoDao
                PercorreNoInterface percorreNoDao = new PercorreNoDao();

                //Chama o metodo de busca
                List lista = percorreNoDao.bucarTodos();

                //Converte para JSON            
                // create a new Gson instance
                Gson gson = new Gson();

                // convert your list to json
                String jsonLivros = gson.toJson(lista);

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                //Outro meio de mandar o Json
                response.getWriter().write(jsonLivros);

            }else if(acao.equals("busca")) {

                //Cria um objeto de ConceitoDao
                PercorreNoInterface percorreNoDao = new PercorreNoDao();

                //Chama o metodo de busca
                List lista = percorreNoDao.bucarNos(conceito);

                //Converte para JSON            
                // create a new Gson instance
                Gson gson = new Gson();

                // convert your list to json
                String jsonLivros = gson.toJson(lista);

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                //Outro meio de mandar o Json
                response.getWriter().write(jsonLivros);

            }else if(acao.equals("buscaNo")) {

                //Pega o Id do no que esta no request
                String idNo = request.getParameter("idNo");
                
                //Cria um objeto de NoInterface
                NoInterface noDao = new NoDao();

                //Chama o metodo de busca
                No no = noDao.bucarNoPorId(idNo);
                
                //noDao.createNo();

                //Converte para JSON            
                // create a new Gson instance
                Gson gson = new Gson();

                // convert your list to json
                String jsonLivros = gson.toJson(no);

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                //Outro meio de mandar o Json
                response.getWriter().write(jsonLivros);

            }//fim do else

        }catch (IOException exc){
        }finally {            
            out.close();
        }//fim do finally
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
