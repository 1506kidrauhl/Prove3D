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

            var user = dados.split(",");
            var data = JSON.parse(user);

            localStorage.setItem("id", data.id);
            localStorage.setItem("nome", data.nome);
            localStorage.setItem("login", data.login);
            localStorage.setItem("senha", data.senha);
            localStorage.setItem("email", data.email);
            localStorage.setItem("telefone", data.telefone);
            localStorage.setItem("cpf", data.cpf);

            window.location.href = "/principal";
        }

    })

}