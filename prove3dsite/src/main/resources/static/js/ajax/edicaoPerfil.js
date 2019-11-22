
$(document).ready(function () {

    if(localStorage.getItem("id") == null){
        window.location.href = "/index";
    }

    pegaValores();

    jQuery("#modalEdit").trigger('click');
});

function btnSair() {

    localStorage.removeItem("id");
    localStorage.removeItem("nome");
    localStorage.removeItem("login");
    localStorage.removeItem("senha");
    localStorage.removeItem("email");
    localStorage.removeItem("telefone");
    localStorage.removeItem("cpf");

    window.location.href = "/index";

}

function pegaValores() {

    idUser.value = localStorage.getItem("id");
    txtName.value = localStorage.getItem("nome");
    txtLogin.value = localStorage.getItem("login");
    txtSenha.value = localStorage.getItem("senha");
    txtEmail.value = localStorage.getItem("email");
    txtTel.value = localStorage.getItem("telefone");
    txtCpf.value = localStorage.getItem("cpf");

    nmUser.innerHTML = localStorage.getItem("nome") + ".";
    txtNmUser.value = localStorage.getItem("nome");
    txtEmailC.value = localStorage.getItem("email");
}

function atribuiValor() {
    localStorage.setItem("id", idUser.value);
    localStorage.setItem("nome", txtName.value);
    localStorage.setItem("login", txtLogin.value);
    localStorage.setItem("senha", txtSenha.value);
    localStorage.setItem("email", txtEmail.value);
    localStorage.setItem("telefone", txtTel.value);
    localStorage.setItem("cpf", txtCpf.value);
}

function cancelaEdit() {

    idUser.value = localStorage.getItem("id");
    txtName.value = localStorage.getItem("nome");
    txtName.readOnly = true;
    txtLogin.value = localStorage.getItem("login");
    txtLogin.readOnly = true;
    txtSenha.value = localStorage.getItem("senha");
    txtSenha.readOnly = true;
    txtEmail.value = localStorage.getItem("email");
    txtEmail.readOnly = true;
    txtTel.value = localStorage.getItem("telefone");
    txtTel.readOnly = true;
    txtCpf.value = localStorage.getItem("cpf");
    txtCpf.readOnly = true;

}


function btnEditaPerfil(){

    document.getElementById("btnEdit").disabled = true;

    var dadosPerfil = "id=" + localStorage.getItem("id") + "&nome=" + localStorage.getItem("nome") +
    "&login=" + txtLogin.value + "&senha=" + txtSenha.value + "&email=" + txtEmail.value + "&telefone=" + txtTel.value +
    "&cpf=" + localStorage.getItem("cpf");

    $.ajax({
        url: "/editarPerfil",
        method: 'POST',
        data: dadosPerfil,
        error: function (data){
            swal({
                title: "Erro!",
                text: data.responseText
            });
            pegaValores();
            cancelaEdit();
            document.getElementById("btnEdit").disabled = false;
        },
        success: function(data){

            swal({
                title: "Beleza!",
                text: data
            });

            atribuiValor();
            cancelaEdit();
            document.getElementById("btnEdit").disabled = false;
        }
    });


}