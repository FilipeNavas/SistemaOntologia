<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="Projeto de ferramenta de apoio à indexação de obras em uma acervo.">
        <meta name="author" content="IFSP SBV">
        <link rel="icon" href="../../favicon.ico">
        
        <!-- Bootstrap CSS -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        
        <!-- VIs.JS CSS -->
        <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet">
        
        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/resources/vis.js/vis.css" rel="stylesheet">
        
        
        <!-- JAVASCRIPT -->
    
        <!-- JQUERY -->
        <!-- GOOGLE CDN  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>    -->
        <!-- LOCAL -->      <script src="${pageContext.request.contextPath}/resources/js/jquery/jquery-2.1.4.min.js"></script>
        
        <!-- BOOTSTRAP -->
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
        
        <!-- VIS.JS -->
        <script src="${pageContext.request.contextPath}/resources/vis.js/vis.min.js"></script>
        
        <!-- CUSTOM JS -->
        <script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
        
        <!-- BUSCA VISUAL JS -->
        <script src="${pageContext.request.contextPath}/resources/js/busca_visual.js"></script>
        
        <title>Busca Visual</title>
        
   </head>
   
    <body>       
        
       <!-- Static navbar -->
        <nav class="navbar navbar-default navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="${pageContext.request.contextPath}/">Ontologia Indexador</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/">Ínicio</a></li>
                <li><a href="${pageContext.request.contextPath}/buscaTextual.jsp">Busca Textual</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/buscaVisual.jsp">Busca Visual</a></li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/sobre.jsp">Sobre</a></li>
              </ul>
            </div><!--/.nav-collapse -->
          </div>
        </nav>
    
 
      
        <div class="container">
            
            <div align="center">


                <div id="fomulario" class="row">
                    <div class="col-md-12">

                        <div class="col-md-4 col-md-offset-1">
                            <label class="col-md-12">Conceito</label>
                        </div> 

                        <div class="col-md-4">
                            <label class="col-md-12">Tipos de Busca</label>
                        </div>

                    </div>

                    <div class="row col-md-12">
                        <div class="col-md-4 col-md-offset-1">
                            <input type="text" id ="conceito" name="conceito" class="col-md-12 input-lg" />
                        </div>

                        <div class="col-md-4">

                            <select name="select" id="selecao" class="col-md-12 input-lg">
                                <option name="click" onchange="l" value="l">Busca por Livro</option>
                                <option name="click" onchange="r"  value="r">Busca por Relacionamento</option>
                            </select>

                        </div>

                        <div class="col-md-2">
                            <a href="#" id="btnEnviar"  class="btn btn-success btn-large input-lg centralizado col-md-12" style="font-size: 22px;">
                                Buscar
                            </a>
                        </div>
                    </div>

                </div>  

            </div> <!-- Fim Div Center -->
            
        <!-- DIV MENSAGEM CAMPO REQUERIDO -->
        <div id="divMensagemCampoRequerido" class="alert alert-warning col-md-10 col-md-offset-1" hidden="true" style="margin-top: 10px;">
            Por favor, preencha todos os campos da busca.
        </div>
            
        </div> <!-- Fim Div Container -->
        
        
        <div class="row col-md-12" style="margin-top: 20px;">
            <div class="col-md-10" >
                
                
                <a href="#" class="btn btn-default btn-sm btn-overlay" onclick="resizeFocus();">
                    <span class="glyphicon glyphicon-resize-small" aria-hidden="true"></span>
                    Focar
                </a>

                <div id="mynetwork" class="grafo"></div>
                

            </div>
            <div id="blockDetails" class="col-md-2 grafo no-padding">
                <table class="table table-bordered no-padding">
                    <tbody>
                    <th>
                        Node 1
                    </th>
                    </tbody>
                    <tr>
                        <td>
                            Oi
                        </td>
                    </tr>
                </table>
            </div>
            
        </div>
            
        <footer class="footer">
            <div class="container">
                <div class="col-md-12">
                    <div class="col-md-4">
                        <p class="text-muted">Desenvolvido por  <a href="https://github.com/willcehsar/">Willian César</a>  e <a href="https://github.com/FilipeNavas">Filipe Navas</a></p>
                    </div>
                    <div class="col-md-7">
                        <p class="text-muted">Orientado por Profª Dra. Rosana Ferrareto, Profº Ms. Gustavo Prieto e Ms. Maria Carolina</p>
                    </div>
                    <div class="col-md-1">
                        <p class="text-muted"><a href="https://sbv.ifsp.edu.br/">IFSP</a></p>
                    </div>
                </div>
                
                
            </div>
        </footer>
      

  </body>      
</html>
  
  
 
 