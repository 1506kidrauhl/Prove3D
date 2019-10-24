$(document).ready(function() {

    var dt = new Date();

    dtInicio.value = dt.getFullYear() + "-" + (dt.getMonth()+ 1) +"-" + dt.getDate();
    dtFim.value = dt.getFullYear() + "-" + (dt.getMonth()+ 1) +"-" + dt.getDate();

    var hora = dt.getHours();
    var min = dt.getMinutes();

    var horaFim = dt.getHours();
    var minFim = dt.getMinutes();

    if(hora < 10){
        hora = "0" + dt.getHours();
    }

    if(min < 10){
        min = "0" + dt.getMinutes();
    }

    if(horaFim < 10){
        horaFim = "0" + dt.getHours();
    }

    if(minFim < 10){
        minFim = "0" + dt.getMinutes();
    }

    minFim += 5;

    if(minFim > 60){

        minFim = "0" + ((minFim + 5) - 60);
        horaFim += 1;

        if(horaFim < 10){
            horaFim = "0" + horaFim;
        }

    }

    hrInicio.value = hora + ":" + min;
    hrFim.value = horaFim + ":" + minFim;

});

function troca(aparecer){

    secTable.style.display = "none";
    secLog.style.display = "none";
    aparecer.style.display = "block";

}
