var comp = "CPU";

$(document).ready(function() {
    ajaxLog();
    setInterval(ajaxLog, 3000);
});

function ajaxLog(){

    if(comp == "CPU"){
        comp = "GPU";
    } else if(comp == "GPU"){
        comp = "CPU";
    }

    var filtro = ['Alerta', 'Erro'];
    var random = parseInt(Math.random() * filtro.length);

    var parametros = "comp="+comp+"&id=" + idUser.value + "&filtro=" + filtro[random];

    $.ajax({
        url: "/lastLog",
        method: 'GET',
        data: parametros,
        error: function (data){
            console.log("foi nada");
        },
        success: function(data){

            if(data.descricao != null){
                gerarCard(data);
            }

        }
    });

}

function gerarCard(data) {

    lblAlertas.style.display = "block";

    if(data.tipo == "Erro"){
        document.getElementById('lblAlertas').classList.remove('bg-danger');
        document.getElementById('lblAlertas').classList.remove('bg-primary');
        document.getElementById('lblAlertas').classList.remove('bg-warning');
        document.getElementById('lblAlertas').classList.add('bg-danger');
    } else if(data.tipo == "OK"){
        document.getElementById('lblAlertas').classList.remove('bg-danger');
        document.getElementById('lblAlertas').classList.remove('bg-primary');
        document.getElementById('lblAlertas').classList.remove('bg-warning');
        document.getElementById('lblAlertas').classList.add('bg-primary');
    } else{
        document.getElementById('lblAlertas').classList.remove('bg-danger');
        document.getElementById('lblAlertas').classList.remove('bg-primary');
        document.getElementById('lblAlertas').classList.remove('bg-warning');
        document.getElementById('lblAlertas').classList.add('bg-warning');
    }

    lblMsg.innerHTML = data.descricao;

}