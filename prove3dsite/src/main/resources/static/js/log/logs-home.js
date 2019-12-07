$(document).ready(function() {

    var data = new Date(),
        dia  = data.getDate().toString(),
        diaF = (dia.length == 1) ? '0'+dia : dia,
        mes  = (data.getMonth()+1).toString(), //+1 pois no getMonth Janeiro começa com zero.
        mesF = (mes.length == 1) ? '0'+mes : mes,
        anoF = data.getFullYear();

    var dateOk = anoF+"-"+mesF+"-"+diaF;

    dtInicio.value = dateOk;
    dtFim.value = dateOk;

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

    document.getElementById("btnLog").disabled = true;

    $.ajax({
        url: "/gerarLog",
        method: 'GET',
        data: parametros,
        error: function (data){
            document.getElementById("btnLog").disabled = false;
        },
        success: function(data){
            geraLog(data);
            document.getElementById("btnLog").disabled = false;
        }
    });

}

function troca(){
    bodyTable.innerHTML = "";
    secTable.style.display = "none";
    secLog.style.display = "block";
}

function geraLog(dados){

    bodyTable.innerHTML = "";

    if(dados == null || dados.length == 0){
        swal("Ops!", "Não foram encontrados dados");
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

        $("#tbTeste").DataTable();

    }



}
