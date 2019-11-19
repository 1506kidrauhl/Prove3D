function btnEdit(campo) {

    if(campo.readOnly){
        campo.readOnly = false;
    } else{
        campo.readOnly = true;
    }

}

$('#modalExemplo').on('shown.bs.modal', function () {
    $('#meuInput').trigger('focus')
})