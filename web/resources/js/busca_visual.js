/* 
* Este arquivo contem o JavaScript para a Busca Visual.
*/
/* global vis, layoutMethod */

var network;
var layoutMethod = "hubsize";

$(document).ready(function() { 
    
    
    //############## BUSCA ###############
    
    $("#btnEnviar").click(function() {

        console.log("Oi");
      
        conceito = $("#conceito").val();
        selecao = $("#selecao").val();

          //Se o conceito ou a selcao for vazio, mostra uma mensagem de erro.
          if(conceito === "" || selecao === null){

              //Mostra a msg
              $("#divMensagemCampoRequerido").fadeIn();

              //Esvazia a div atualizar
              $("#mynetwork").html("");

          }else{

              //Esconde a msg (caso ela esteja visivel)
              $("#divMensagemCampoRequerido").fadeOut(500);

              //Adiciona a classe CSS de carregando na div
              $("#btnEnviar").addClass("carregando");
              
              
//              $.getJSON({
//                url: 'http://localhost:8080/SistemaOntologia/ServletJson',
//                data : "conceito=" + conceito + "&selecao=" + selecao,  
//                //data: {conceito:conceito,selecao:selecao},
//                success: function(data){ console.log(data);}
//              });
              
             
                
            $.ajax({
                type : "POST",

                url : "http://localhost:8080/SistemaOntologia/ServletJson",
                //Esse comando abaixo pega o servidor(IP local) e a porta dinamicamente
                //url : "http://<%= request.getServerName() + ":" + request.getServerPort() %>/SistemaOntologia/ServletPercorrerOntologia",
                data : "conceito=" + conceito + "&selecao=" + selecao,                    
                success : function(data) {
                    
                    console.log(data);
                    
                    //noFinal: "tipo: Linguagem de programacao. "
                    //noInicial: "Java"
                    //tipoRelacionamento: "e_uma"

                    //######### GRAFO ##########
                    
                    //NOS - NODES
                    var options = {};
                    var nodes = new vis.DataSet(options);

                    var nodes = new vis.DataSet([]);
                                        
                    //console.log("FOR");
                    $.each( data, function( key, val ) {
                        //console.log("Key: " + key + " - Val:" + val.noFinal);
                        
                        //Adiciona os nos no DataSet
                        nodes.add([
                            {id: key, label: "'" + val.noFinal + "'"}
                        ]);
                        
                    });
                    
                    //Adiciona o conceito, ou seja, o no principal procurado
                    nodes.add([
                            {id: conceito, label: "'" + conceito + "'"}
                        ]);
                    //FIM NOS - NODES                    
                    
                    

                    // RELACIONAMENTOS - EDGES
                    // create an array with edges var options = {};
                    var edges = new vis.DataSet(options);
 
                    $.each( data, function( key, val ) {
                        
                        //Adiciona os edges no DataSet
                        edges.add([
                            //{id: key, label: "'" + val.noFinal + "'"}
                            {id: key , from: conceito, to: key, arrows: 'to', label: "'" + val.tipoRelacionamento + "'", font: {align: 'bottom', size: '10'}}
                        ]);
                        
                    });
                    //FIM RELACIONAMENTOS - EDGES

                    
                    // create a network
                    var container = document.getElementById('mynetwork');
                    var dataGraph = {
                        nodes: nodes,
                        edges: edges
                    };


                    var options = {
                        autoResize: true,
                        height: '100%',
                        width: '100%',
                        locale: 'en',
                        interaction:{
                            hover: true
                        },
                        layout:{
                            randomSeed: 1,
                            improvedLayout: true

                           
                            /*hierarchical: {
                                //sortMethod: 'directed'
                                direction: 'UD'
                            } */
                        }
                    };

                    network = new vis.Network(container, dataGraph, options);

                    //Pega o evento de clique
                    network.on("click", function (params) {
                        params.event = "[original event]";
                        document.getElementById('blockDetails').innerHTML = '<h2>Click event:</h2>' + JSON.stringify(params, null, 3);
                    });
       

                }
             }); 
             
            //Remove a classe CSS de carregando na div
            //Chama a funcao que tira a classe CSS, passando um delay de meio segundo.
            setTimeout( delay, 500 );

           }
    
    
    });
    
    
    
    
}); 

//Funcao que foca o grafo no meio da tela (de sua div)
function resizeFocus(){
        
    //network.stabilize();
    
    //Esse eh mais suave do que o stabilize
    network.fit({
        nodes:'',
        animation: { // -------------------> can be a boolean too!
          duration: 200,
          easingFunction: 'easeInCubic'
        }
    });
        
}

//Funcao que tira o carregando do botao Buscar
function delay() 
  {
    $("#btnEnviar").removeClass("carregando");
  };