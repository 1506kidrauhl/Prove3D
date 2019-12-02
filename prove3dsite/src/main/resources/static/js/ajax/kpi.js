$(document).ready(function () {
    chamaAjax();
    setInterval(chamaAjax, 5000);
});
function chamaAjax(){

    if(idPcAux.value == ""){
        return;
    }

    var parametros = `idUser=${idAux.value}&idPc=${idPcAux.value}`;
    
    $.ajax({
       url: "/maxProcesso",
       method: "GET", 
        data: parametros,
        
        error: function () {
            console.log("Deu ruim");
        },
        
        success: function (data) {
            var processo = retornaProcesso(data, data[data.length - 1]);
            var utilizacao = retornaUtilzacao(processo);
            var tempAtividade = retornaTempo(processo, data[data.length - 1]);

            data.sort(function (a, b) {
                if (a.tempoAtividade < b.tempoAtividade) {
                    return 1;
                } else{
                    return -1;
                }
                return 0;
            });

            var dadosTable = filtraDados(data, processo);

            dadosTable.sort(function (a, b) {
                if (a.usoCpu < b.usoCpu) {
                    return 1;
                } else{
                    return -1;
                }
                return 0;
            });

            montaChartMaior(processo, utilizacao, tempAtividade);
            montaTabela(dadosTable);
        }
        
    });

}

function retornaProcesso(dados, maiorProcesso) {

    var processos = [];

    for(i = 0; i < dados.length; i++){

        var atual = dados[i];

        if(maiorProcesso.processo == atual.processo){
            processos.push(atual);
        }

    }

    return processos;

}

function retornaTempo(processo, maiorProcesso) {
    var tempo = [];

    for(i = 0; i < processo.length; i++){

        var atual = processo[i];

        if(maiorProcesso.processo == atual.processo){
            tempo.push(atual.tempoAtividade);
        }

    }

    return tempo;
}

function retornaUtilzacao(util) {

    var uso = [];

    for(i =0; i < util.length; i++){
        uso.push(util[i].usoCpu);
    }

    return uso;
}

function montaChartMaior(processos, uso, tempo) {

    var ctx = document.getElementById('myChart1').getContext('2d');
    ctx.canvas.width = 1000;
    ctx.canvas.height = 200;
    var chart = new Chart(ctx, {
        type: 'line',
        data:{
            datasets:[{
                label: processos[0].processo,
                borderColor: "hsla(230,100%,57%,0.6)",
                backgroundColor: "transparent"
            }]

        }

    });

    for(i = 0; i < uso.length; i++){

        var atualUso = uso[i];
        var atualTemp = tempo[i];
        addData(chart, atualTemp, atualUso)
    }

}

function addData(chart, label, data) {

    chart.data.labels.push(label);

    chart.data.datasets.forEach((dataset) => {

        dataset.data.push(data);

    });

    dados = chart.data.datasets[0].data;

    if (dados.length > 5) {
        chart.data.labels.splice(0, 1);
        dados.splice(0, 1);

    }

    chart.update();

}

function montaTabela(processos) {

    bodyProcessos.innerHTML = "";

    for(i = 0; i < processos.length; i++){

        var atual = processos[i];

        bodyProcessos.innerHTML +=
            `<tr>
                  <td>${atual.pid}</td>
                  <td>${atual.processo}</td>
                  <td>${atual.usoCpu}</td>
                  <td>${atual.usoMemoria}</td>
                  <td>${atual.tempoAtividade}</td>
            </tr>`;

    }

}

function filtraDados(processos, maior) {

    var proc = [];
    var comp = "";
    for(i = 0; i < processos.length; i++){

        var atual = processos[i];

        if(atual.processo != comp && atual.processo != maior[0].processo){
            comp = atual.processo;

            proc.push(atual);

        }

    }

    console.log(proc);
    return proc;
}