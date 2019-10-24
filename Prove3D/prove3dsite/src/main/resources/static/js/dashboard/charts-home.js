/*global $, document, Chart, LINECHART, data, options, window*/
function gerarGrafico(cor,texto) {

    'use strict';

    // ------------------------------------------------------- //
    // Line Chart
    // ------------------------------------------------------ //
    var legendState = true;
    if ($(window).outerWidth() < 576) {
        legendState = false;
    }

    var arrayAux = [];
    for ( var i = 0; i <= 7; i++) {

       var  ale = Math.floor(Math.random() * (101 - 1) + 1);
       arrayAux.push(ale)
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
            labels: ["Domingo","Segunda","Terça","Quarta","Quinta","Sexta","Sabádo"],
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
                    data: arrayAux,
                    spanGaps: false

                    
                    
                }
                
            ]
        }
      
    });

}

function tbDisplay(){
    chartDashboard.style.display = 'inline';
}

function filtro(aparecer){
    cpu.style.display = 'none';
    memoria.style.display = 'none';
    disco.style.display = 'none';
    gpu.style.display = 'none';
    chartDashboard.style.display = 'none';
    aparecer.style.display = 'inline';
}
