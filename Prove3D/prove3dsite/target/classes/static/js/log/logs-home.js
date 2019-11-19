$(document).ready(function() {

    var dt = new Date();

    dtInicio.value = dt.getFullYear() + "-" + (dt.getMonth()+ 1) +"-" + dt.getDate();
    dtFim.value = dt.getFullYear() + "-" + (dt.getMonth()+ 1) +"-" + dt.getDate();

    hrInicio.value = "00:00";
    hrFim.value = "23:59";
});

function chamaAjax() {

    if(dtInicio.value == "" || dtFim.value == ""){
        swal("Atenção", "Preencha os campos de data corretamente");
        return;
    } else if(dtInicio.value > dtFim.value){
        swal("Atenção", "Data Inicial não pode ser maior que a Data Final");
        return;
    } else if(hrInicio.value > hrFim.value){
        swal("Atenção", "Hora Inicial não pode ser maior que a Hora Final");
        return;
    } else if(hrInicio.value == "" || hrFim.value == ""){
        swal("Atenção", "Preencha os campos de hora corretamente");
    }

    var parametros="dtInicio="+dtInicio.value + " " + hrInicio.value + "&dtFim="+ dtFim.value +  " " + hrFim.value +
        "&tipo="+cmbFiltro.value+"&comp="+cmbComp.value+"&id="+idAux.value;

    console.log(parametros);

    $.ajax({
        url: "/gerarLog",
        method: 'GET',
        data: parametros,
        error: function (data){
            console.log("foi nada");
        },
        success: function(data){
            geraLog(data);
        }
    });

}

function troca(){
    secTable.style.display = "none";
    secLog.style.display = "block";
}

function geraLog(dados){

    bodyTable.innerHTML = "";

    if(dados == null || dados.length == 0){
        swal("Ops!", "Não foram encontrados dados")
    } else{

            secTable.style.display = "block";
            secLog.style.display = "none";

        for(i = 0; i < dados.length; i++){

            bodyTable.innerHTML += `
            <tr>
              <td>${i + 1}</td>
              <td>${dados[i].tipo}</td>
              <td>${dados[i].descricao}</td>
              <td>${dados[i].dtHora.split("T")[0]}</td>
              <td>${dados[i].dtHora.split("T")[1].split(".")[0]}</td>
            </tr>`;

        }

    }



}
