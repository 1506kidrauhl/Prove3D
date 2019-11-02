function btnEmail() {

    if(comment.value == ""){
        swal("Por favor, digite uma mensagem no chamado");
        return;
    }

    document.getElementById("btnEnviar").disabled = true;

    var dados = {
        nome: txtNmUser.value,
        email: txtEmailC.value,
        assunto: cmbAssunto.value,
        tipo: cmbTipo.value,
        mensagem: comment.value
    };

    $.ajax({
        url: "/enviarEmail",
        method: 'POST',
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        data: JSON.stringify(dados),
        error: function (data){
            var conteudo = data.responseText.split(".");
            swal(conteudo[0], conteudo[1]);
            document.getElementById("btnEnviar").disabled = false;
        },
        success: function(data){
            var conteudo = data.responseText.split(".");
            swal(conteudo[0], conteudo[1]);
            document.getElementById("btnEnviar").disabled = false;
        }
    });


}