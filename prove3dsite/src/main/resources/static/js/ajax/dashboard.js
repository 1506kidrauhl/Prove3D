

function chamaAjax(cor, texto, fil,comp) {

    var parametros="componente="+comp+"&filtro="+fil+"&id="+idAux.value;
    clearTimeout(myVar);
    $.ajax({
        url: "/pegarDados",
        method: 'GET',
        data: parametros,
        error: function (data){
            chartDashboard.style.display = 'none';
            swal({
               title: "Ops!",
                text: "Ainda não possuimos dados do seu computador"
            });
        },
        success: function(dadosD){

            if(dadosD.length == 2){
                chartDashboard.style.display = 'none';
                swal({
                    title: "Ops!",
                    text: "Este componente não possui dados sendo coletados"
                });
                return;
            }

            var data = JSON.parse(dadosD);
            var  dados=[],dataHora=[];

            for(i=0;i<data.length;i++){
                dados.push(data[i].dados);
                dataHora.push(data[i].dataHora.split(" ")[1].split(".")[0]);
            }

            grafico(dados,dataHora,cor, texto, fil, comp);
        }
    });

}

function grafico(dados,dataHora, cor, texto, fil, comp) {

    'use strict';

    // ------------------------------------------------------- //
    // Line Chart
    // ------------------------------------------------------ //
    var legendState = true;
    if ($(window).outerWidth() < 576) {
        legendState = false;
    }

    var LINECHART = $('#lineCahrt');

    var myLineChart = new Chart(LINECHART, {
        type: 'line',
        options: {
            scales: {
                xAxes: [{
                    display: true,
                    gridLines: {
                        display: false
                    }
                }],
                yAxes: [{
                    display: true,
                    gridLines: {
                        display: false
                    },

                }]
            },
            legend: {
                display: legendState
            },

        },

        data: {
            labels: dataHora,
            datasets: [
                {
                    label: texto,
                    multiTooltipTemplate : "$<%= value %>k",
                    fill: true,
                    lineTension: 0,
                    backgroundColor: "transparent",
                    borderColor: cor,
                    pointBorderColor: cor,
                    pointHoverBackgroundColor: cor,
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'miter',
                    borderWidth: 1,
                    pointBackgroundColor: "#fff",
                    pointBorderWidth: 2,
                    pointHoverRadius: 2,
                    pointHoverBorderColor: "#fff",
                    pointHoverBorderWidth: 2,
                    pointRadius: 4,
                    pointHitRadius: 0,
                    data: dados,
                    spanGaps: false



                }

            ]
        }

    });

    myVar = setTimeout( function() { chamaAjax(cor,texto,fil, comp); }, 5000 );

}

var myVar;