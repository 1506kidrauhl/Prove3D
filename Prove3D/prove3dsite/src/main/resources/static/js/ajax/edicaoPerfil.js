
$(document).ready(function () {
    atribuiValor();

    jQuery("#modalEdit").trigger('click');
});

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

    var dadosPerfil = {
        idUsuario : localStorage.getItem("id"),
        nome : localStorage.getItem("nome"),
        login : txtLogin.value,
        senha : txtSenha.value,
        email : txtEmail.value,
        telefone : txtTel.value,
        cpf : localStorage.getItem("cpf")
    };

    $.ajax({
        url: "/editarPerfil",
        method: 'POST',
        contentType : '\'application/x-www-form-urlencoded; charset=UTF-8',
        dataType : 'json',
        data: JSON.stringify(dadosPerfil),
        error: function (data){
            var conteudo = data.responseText.split(".");
            swal(conteudo[0], conteudo[1]);

            if(conteudo[2] != undefined){
                atribuiValor();
            }
            cancelaEdit();
            document.getElementById("btnEdit").disabled = false;
        },
        success: function(data){
            var conteudo = data.responseText.split(".");
            swal(conteudo[0], conteudo[1]);
            if(conteudo[2] != undefined){
                atribuiValor();
            }
            cancelaEdit();
            document.getElementById("btnEdit").disabled = false;
        }
    });


}