function btnEdit(campo) {

    if(campo.readOnly){
        campo.readOnly = false;
    } else{
        campo.readOnly = true;
    }

}

function geraRandom(){

    var frases = ["Temperatura GPU está com a temperatura muito alta", "Utilização da CPU está acima dos 80%",
        "Utilização de memória está acima dos 70%", "Memória OK", "GPU OK", "CPU OK"];

    var random = parseInt(Math.random() * frases.length);

    if(random <= 2){
        document.getElementById('lblAlertas').classList.remove('bg-primary');
        document.getElementById('lblAlertas').classList.add('bg-danger');
    } else{
        document.getElementById('lblAlertas').classList.remove('bg-danger');
        document.getElementById('lblAlertas').classList.add('bg-primary');
    }

    lblMsg.innerHTML = frases[random];

}

setInterval(geraRandom, 3000);

$('#modalExemplo').on('shown.bs.modal', function () {
    $('#meuInput').trigger('focus')
})