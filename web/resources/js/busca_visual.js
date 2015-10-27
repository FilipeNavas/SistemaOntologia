/* 
* Este arquivo contem o JavaScript para a Busca Visual.
*/
/* global vis, layoutMethod */

var network;
var layoutMethod = "hubsize";

$(document).ready(function() { 
    
    //Esconde o bloco de detalhes
    $('#blockDetails').hide();
    
    //Esconde a msg de salvar o no caso ela esteja visivel
    $("#msgSalvarNo").hide();
    
    //Quando o modal esconde (o user fecha ele), vamos esconder a msg do Salvar No
    $('#myModal').on('hidden.bs.modal', function (e) {
        //Esconde a msg de salvar o no caso ela esteja visivel
        $("#msgSalvarNo").hide();
    });
    
    //############## BUSCA ###############
    
    $("#btnEnviar").click(function() {

        conceito = $("#conceito").val();
        //selecao = $("#selecao").val();

          //Se o conceito ou a selcao for vazio, mostra uma mensagem de erro.
          if(conceito === ""){

              //Mostra a msg
              $("#divMensagemCampoRequerido").fadeIn();

              //Esvazia a div atualizar
              $("#mynetwork").html("");

          }else{

              //Esconde a msg (caso ela esteja visivel)
              $("#divMensagemCampoRequerido").fadeOut(500);

              //Adiciona a classe CSS de carregando na div
              $("#btnEnviar").addClass("carregando");
              
                
            $.ajax({
                type : "POST",

                url : "http://localhost:8080/SistemaOntologia/ServletJson",
                //Esse comando abaixo pega o servidor(IP local) e a porta dinamicamente
                //url : "http://<%= request.getServerName() + ":" + request.getServerPort() %>/SistemaOntologia/ServletPercorrerOntologia",
                data : "conceito=" + conceito + "&selecao=busca" ,                    
                success : function(data) {
                    
                    console.log(data);
                    
                    //noFinal: "tipo: Linguagem de programacao. "
                    //noInicial: "Java"
                    //tipoRelacionamento: "e_uma"

                    //######### GRAFO ##########
                    
                    //NOS - NODES
                    var options = {};
                    var nodes = new vis.DataSet(options);
                    
                    //console.log("FOR");
                    $.each( data, function( key, val ) {
                        console.log("Id: " + val.noFinal.id + " - Val:" + val.noFinal.nome);
                      
                        //Adiciona os nos no dataset
                        //Aqui ele faz um try/catch pq em alguns momentos o mesmo no pode estar em noFInal (e tbm noFinal), dando erro 
                        //do id. Sendo assim, qdo cai no catch, ele faz o inverso, pegando o noInicial primeiro e depois o final.
                        //Assim fica garantido que todos os nos dessa busca serao retornados.
                        try {
                            //Adiciona os nos no DataSet
                            nodes.add([
                                //{id: key, label: "'" + val.categoria.tipo + "'"}
                                {id: val.noFinal.id, label: "'" + val.noFinal.nome + "'"}
                            ]);
                            
                            //Adiciona os nos no DataSet
                            nodes.add([
                                //{id: key, label: "'" + val.categoria.tipo + "'"}
                                {id: val.noInicial.id, label: "'" + val.noInicial.nome + "'"}
                            ]);
                        }
                        catch(err) {
                            console.log(err);
                            
                            try {

                                //Adiciona os nos no DataSet
                                nodes.add([
                                    //{id: key, label: "'" + val.categoria.tipo + "'"}
                                    {id: val.noInicial.id, label: "'" + val.noInicial.nome + "'"}
                                ]);
                                
                                //Adiciona os nos no DataSet
                                nodes.add([
                                    //{id: key, label: "'" + val.categoria.tipo + "'"}
                                    {id: val.noFinal.id, label: "'" + val.noFinal.nome + "'"}
                                ]);

                            }
                            catch(err) {
                                console.log(err);
                            }
                            
                            
                        }
                        
                        
                        
                        
                    });
                    
                    //Adiciona o conceito, ou seja, o no principal procurado
                    //nodes.add([
                    //        {id: 4, label: 'Classe'}
                    //    ]);
                    //FIM NOS - NODES                    
                    
                    

                    // RELACIONAMENTOS - EDGES
                    // create an array with edges var options = {};
                    var edges = new vis.DataSet(options);
 
                    $.each( data, function( key, val ) {
                        console.log("Inicial Id: " + val.noInicial.id + " - Final:" + val.noFinal.id + + " - Rel:" + val.relacionamento);
                        
                        //Adiciona os edges no DataSet
                        edges.add([
                            //{id: key, label: "'" + val.noFinal + "'"}
                            {id: key , from: val.noInicial.id, to: val.noFinal.id, arrows: 'to', label: "'" + val.relacionamento + "'", font: {align: 'bottom', size: '10'}}
                        ]);
                        
                    });
                    //FIM RELACIONAMENTOS - EDGES

                    // create a network
                    createGraph(nodes, edges);
                }
                
                
             }); 
             
            //Remove a classe CSS de carregando na div
            //Chama a funcao que tira a classe CSS, passando um delay de meio segundo.
            setTimeout( removeCarregandoDelay("#btnEnviar"), 500 );

           }
    
    
    });
    
    

    
    //################ TODOS #######################3
    $("#btnTodos").click(function() {

            pegarTodosNos();
    
            //Adiciona a classe CSS de carregando na div
            $("#mynetwork").addClass("carregando");
            
            $.ajax({
                type : "POST",

                url : "http://localhost:8080/SistemaOntologia/ServletJson",
                //Esse comando abaixo pega o servidor(IP local) e a porta dinamicamente
                //url : "http://<%= request.getServerName() + ":" + request.getServerPort() %>/SistemaOntologia/ServletPercorrerOntologia",
                //data : "&selecao=todos",       
                data : "selecao=" + 'todos',     
                success : function(data) {
                    
                    //Esconde a msg (caso ela esteja visivel)
                    $("#divMensagemCampoRequerido").fadeOut(500);
                    
                    console.log("TODOS");
                    console.log(data);
                    
                    //noFinal: "tipo: Linguagem de programacao. "
                    //noInicial: "Java"
                    //tipoRelacionamento: "e_uma"

                    //######### GRAFO ##########
                    
                    //NOS - NODES
                    var options = {};
                    var nodes = new vis.DataSet(options);
                    
                    //console.log("FOR");
                    $.each( data, function( key, val ) {
                        console.log("Id: " + val.noFinal.id + " - Val:" + val.noFinal.nome);
                      
                        
                        //Adiciona os nos no DataSet
                        //Aqui ele faz um try/catch pq em alguns momentos o mesmo no pode estar em noFInal (e tbm noFinal), dando erro 
                        //do id. Sendo assim, qdo cai no catch, ele faz o inverso, pegando o noInicial primeiro e depois o final.
                        //Assim fica garantido que todos os nos dessa busca serao retornados.
                        try {
                            //Adiciona os nos no DataSet
                            nodes.add([
                                //{id: key, label: "'" + val.categoria.tipo + "'"}
                                {id: val.noFinal.id, label: "'" + val.noFinal.nome + "'"}
                            ]);
                            
                            //Adiciona os nos no DataSet
                            nodes.add([
                                //{id: key, label: "'" + val.categoria.tipo + "'"}
                                {id: val.noInicial.id, label: "'" + val.noInicial.nome + "'"}
                            ]);
                        }
                        catch(err) {
                            console.log(err);
                            
                            try {

                                //Adiciona os nos no DataSet
                                nodes.add([
                                    //{id: key, label: "'" + val.categoria.tipo + "'"}
                                    {id: val.noInicial.id, label: "'" + val.noInicial.nome + "'"}
                                ]);
                                
                                //Adiciona os nos no DataSet
                                nodes.add([
                                    //{id: key, label: "'" + val.categoria.tipo + "'"}
                                    {id: val.noFinal.id, label: "'" + val.noFinal.nome + "'"}
                                ]);

                            }
                            catch(err) {
                                console.log(err);
                            }
                            
                            
                        }
                        
                        
                    });
                    
                    //Adiciona o conceito, ou seja, o no principal procurado
                    //nodes.add([
                    //        {id: 4, label: 'Classe'}
                    //    ]);
                    //FIM NOS - NODES                    
                    
                    

                    // RELACIONAMENTOS - EDGES
                    // create an array with edges var options = {};
                    var edges = new vis.DataSet(options);
 
                    $.each( data, function( key, val ) {
                        console.log("Inicial Id: " + val.noInicial.id + " - Final:" + val.noFinal.id + + " - Rel:" + val.relacionamento);
                        
                        //Adiciona os edges no DataSet
                        edges.add([
                            //{id: key, label: "'" + val.noFinal + "'"}
                            {id: key , from: val.noInicial.id, to: val.noFinal.id, arrows: 'to', label: "'" + val.relacionamento + "'", font: {align: 'bottom', size: '10'}}
                        ]);
                        
                    });
                    //FIM RELACIONAMENTOS - EDGES
                    
                    createGraph(nodes, edges);
                
                }
                    
             });
             
            //Remove a classe CSS de carregando na div
            //Chama a funcao que tira a classe CSS, passando um delay de meio segundo.
            setTimeout( removeCarregandoDelay("#mynetwork"), 500 );
            
    
    });
    
    
    
    //################ CRIAR NO #######################3
    $("#btnSalvarNo").click(function() {
            
            var nomeNo = $("#nomeNo").val();
            var descricaoNo = $("#descricaoNo").val();
                       
            $.ajax({
                type : "POST",

                url : "http://localhost:8080/SistemaOntologia/ServletJson",
                data : "selecao=" + 'criarNo' + "&nomeNo=" + nomeNo + "&descricaoNo=" + descricaoNo,     
                success : function(data) {
                    console.log("CRIAR NO - Sucesso");
                    $("#msgSalvarNo").addClass("alert-success");
                    $("#msgSalvarNo").html("Nó salvo com sucesso!");
                    $("#msgSalvarNo").fadeIn();
                    
                    //Apaga os campos
                    $("#nomeNo").val('');
                    $("#descricaoNo").val('');;
                },
                fail: function() {
                    console.log("CRIAR NO - Erro");
                    $("#msgSalvarNo").addClass("alert-warning");
                    $("#msgSalvarNo").html("Problema ao criar Nó !");
                    $("#msgSalvarNo").fadeIn();
                }
            });
    });
    
    
    //################ RESIZE FOCUS - REFOCAR #######################3
    $("#btnFocar").click(function() {
        resizeFocus();
    });
    
}); 


function buscarNo(idNo){
    
     
    $.ajax({
            type : "POST",

            url : "http://localhost:8080/SistemaOntologia/ServletJson",
            //Esse comando abaixo pega o servidor(IP local) e a porta dinamicamente
            //url : "http://<%= request.getServerName() + ":" + request.getServerPort() %>/SistemaOntologia/ServletPercorrerOntologia",
            //data : "&selecao=todos",       
            data : "idNo=" + idNo + "&selecao=buscaNo" ,                    
            success : function(data) {
                
                //Esconde a msg (caso ela esteja visivel)
                $("#divMensagemCampoRequerido").fadeOut(500);

                
                //Quando o valor for encontrado no BD mostra o box lateral.
                //Senao, esconde ele.
                if( data.id !== undefined ){
                    var html = 
                            "<table class='table table-striped table-bordered'>" 
                         +  "<tr><td><strong>Id do Elemento</strong></td><td><strong>Nome</strong></td></tr>"
                         +  "<tr><td>" + data.id + "</td><td>" + data.nome + "</td></tr>"
                         +  "<tr><td colspan='2'><strong>Descrição</strong></td></tr>"
                         +  "<tr><td colspan='2'>" + data.descricao + "</td></tr>"
                         + " </table>";
                 
                    
                    //Cria o box lateral com as informacoes do no
                    $("#blockDetails").html(html);
                    $('#blockDetails').fadeIn();
                }else{
                    //html = "<p>Selecione um elemento para ver os detalhes.</p>";
                    $('#blockDetails').fadeOut();
                }
                
                
            }
    });
    
    
}


//Funca que cria o Grafo na tela. Parametros nodes e edges.
function createGraph(nodes, edges){
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
                //randomSeed: 2,
                //improvedLayout: true

                
                hierarchical: {
                    //sortMethod: 'directed',
                    direction: 'UD', //UD, DU, LR, RL
                    levelSeparation: 150
                    
                } /**/
            }
        };

        network = new vis.Network(container, dataGraph, options);

        //Pega o evento de clique
        network.on("click", function (params) {
            params.event = "[original event]";
            //console.log("PARAMS: " + params);
            //Pega o JSON do elemento clicado
            var obj = jQuery.parseJSON( JSON.stringify(params, null, 3) );
            
            //alert( obj.nodes );
            //document.getElementById('blockDetails').innerHTML = '<h4>Id do Elemento:</h4>' + obj.nodes;
            buscarNo(obj.nodes);
        });

}

//Funcao que foca o grafo no meio da tela (de sua div)
function resizeFocus(){
        
    //network.stabilize();
    
    //Esconde a msg (caso ela esteja visivel)
    $("#divMensagemCampoRequerido").fadeOut(500);
    
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
function removeCarregandoDelay(id) 
  {
    $(id).removeClass("carregando");
  };
 
function pegarTodosNos(){
    
     $.ajax({
            type : "POST",

            url : "http://localhost:8080/SistemaOntologia/ServletJson",
            data : "selecao=" + 'buscaTodosNos',
            success : function(data) {
                
                console.log("TODOS");
                console.log(data);
                
                var options = "";
                
                $.each( data, function( key, val ) {
                    
                    options = options + "<option value='" + val.id + "'>" + val.nome + "</option>";
                
                });
                
                
                $("#selectTodosNos").append(options);
               
            }
        });
    
}
  
  