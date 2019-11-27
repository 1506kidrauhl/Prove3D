function btnEntrar(){

    if(txtLogin.value == "" || txtSenha.value == ""){
        swal({
            title: "Ops!",
            text: "Preencha todos os campos para continuar"
        });
        return;
    }

    $.ajax({
        url: "/login",
        method: "GET",
        data: "login=" + txtLogin.value + "&senha=" + txtSenha.value,

        error: function (data) {
            swal({
               title: "Ops",
               text: data.responseText
            });
        },

        success: function (dados) {
            window.location.href = dados;
        }

    })

}