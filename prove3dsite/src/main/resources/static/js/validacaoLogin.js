$(document).ready(function () {

    if(localStorage.getItem("id") == null || localStorage.getItem("id") == "undefined"){
        window.location.href = "/index";
    }

    nmUser.innerHTML = localStorage.getItem("nome");
    idAux.value = localStorage.getItem("id");
    txtNmUser.value = localStorage.getItem("nome");
    txtEmailC.value = localStorage.getItem("email");

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
