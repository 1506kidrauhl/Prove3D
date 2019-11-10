/*global $, document, Chart, LINECHART, data, options, window*/
function gerarGrafico(cor,texto,fil,comp) {
    chamaAjax(cor, texto, fil, comp);
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
    clearTimeout(myVar);
}
