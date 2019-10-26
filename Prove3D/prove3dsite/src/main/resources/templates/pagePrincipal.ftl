<!DOCTYPE html>

<html class="no-js">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="img/parallax/logo.png" />
    <title>Home</title>
    <link rel="stylesheet" href="css/cards.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  

<head>
</head>

<body>
<!-- TOP DA PÁGINA -->
<nav class="navbar navbar-ligh text-white topPage header">
    <a class="navbar-brand text-white" href="/index">
        <img src="img/parallax/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
        Provë 3D
        <br>
        Bem vindo,
        <#if dadosLog ? has_content>
            <b>${dadosLog.nome}.</b>
        </#if>
    </a>
    <a class="navbar-brand text-white" style="height: 50px;" href="/sair">
        Sair
    </a>
</nav>

<!-- CARD PARA ALERT DE ALERTAS -->
    <div class="card bg-ligth" id="lblAlertas">
        <div class="card-body text-center">
            <p id="lblMsg" class="card-text text-light"></p>
        </div>
    </div>

<!-- CONTAINER DOS CARDS DA PÁGINA -->
<div class="container mgTop">
<center>
    <!-- CARDS PARTE 1 -->
    <div class="row">
        <div class="col-sm-4">
            <a data-toggle="modal" data-target="#modalChamados">
            <div class="card cardSize">
                <img src="img/cards/conversacao.png" class="card-body imgSize"/>
                <div class="card-footer text-white colorCard">Chamados</div>
            </div>
            </a>
        </div>

        <div class="col-sm-4">
            <a href="/dashboard">
            <div class="card cardSize">
                <img src="img/cards/estatisticas.png" class="card-body imgSize"/>
                <div class="card-footer text-white colorCard">Dashboard</div>
            </div>
            </a>
        </div>

        <div class="col-sm-4">
            <a href="#">
            <div class="card cardSize">
                <img src="img/cards/velocimetro.png" class="card-body imgSize"/>
                <div class="card-footer text-white colorCard">Desempenho</div>
            </div>
            </a>
        </div>

    </div>

    <br>
    <!-- CARDS PARTE 2 -->
    <div class="row">
        <div class="col-sm-4">
            <a data-toggle="modal" data-target="#modalEdit">
            <div class="card cardSize">
                <img src="img/cards/curriculo.png" class="card-body imgSize"/>
                <div class="card-footer text-white colorCard">Perfil</div>
            </div>
            </a>
        </div>

        <div class="col-sm-4">
            <a href="/relatorio">
            <div class="card cardSize">
                <img src="img/cards/relatorio.png" class="card-body imgSize"/>
                <div class="card-footer text-white colorCard">Relatórios</div>
            </div>
            </a>
        </div>

        <div class="col-sm-4">
            <a href="#">
            <div class="card cardSize">
                <img src="img/cards/manutencao.png" class="card-body imgSize"/>
                <div class="card-footer text-white colorCard">Em Manutenção</div>
            </div>
            </a>
        </div>
    </div>
</center>
</div>

<!-- MODAL DE EDIÇÃO DE PERFIL -->
<div class="modal fade" id="modalEdit" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h6 class="modal-title" id="exampleModalLabel">
                    <div class="row">
                        <div class="col-sm-2">
                            <img src="img/parallax/logo.png" width="30px" height="30px">
                        </div>
                        <div class="col-sm-8" style="vertical-align: center">
                            Edição de Dados
                        </div>
                        <div class="col-sm-2">
                            <img src="img/users/editUser.png">
                        </div>
                    </div>
                </h6>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Nome:
                <div class="row">
                    <div class="col-sm-10">
                        <input type="text" id="txtName" class="form-control" value="Provë 3D" readonly>
                    </div>
                </div>
                Login:
                <div class="row">
                    <div class="col-sm-10">
                        <input type="text" id="txtLogin" class="form-control" value="Provë Admin" readonly>
                    </div>
                    <div class="col-sm-2">
                        <img src="img/users/edit.png" onclick="btnEdit(txtLogin)">
                    </div>
                </div>
                Senha:
                <div class="row">
                    <div class="col-sm-10">
                        <input type="password" id="txtSenha" class="form-control" value="#palmeirasSemMundial" readonly>
                    </div>
                    <div class="col-sm-2">
                        <img src="img/users/edit.png" onclick="btnEdit(txtSenha)">
                    </div>
                </div>
                Email:
                <div class="row">
                    <div class="col-sm-10">
                        <input type="text" id="txtEmail" class="form-control" value="prove3d@gmail.com" readonly>
                    </div>
                    <div class="col-sm-2">
                        <img src="img/users/edit.png" onclick="btnEdit(txtEmail)">
                    </div>
                </div>
                Telefone:
                <div class="row">
                    <div class="col-sm-10">
                        <input type="text" id="txtTel" class="form-control" maxlength="14" value="(11)97720-2265" readonly>
                    </div>
                    <div class="col-sm-2">
                        <img src="img/users/edit.png" onclick="btnEdit(txtTel)">
                    </div>
                </div>
                CPF:
                <div class="row">
                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="120.569.198-01" readonly>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-primary">Salvar mudanças</button>
                <input type="hidden" id="idUserEdit">
            </div>
        </div>
    </div>
</div>

<!-- MODAL DE CHAMADOS -->
<div class="modal fade" id="modalChamados" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h6 class="modal-title" id="exampleModalLabel">
                    <div class="row">
                        <div class="col-sm-2">
                            <img src="img/parallax/logo.png" width="30px" height="30px">
                        </div>
                        <div class="col-sm-10" style="vertical-align: center">
                            Abrir um chamado
                        </div>
                    </div>
                </h6>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <label for="cmbTipo">Selecione o Tipo:</label>
                    <select id="cmbTipo" class="form-control">
                        <option value="Dúvida">Dúvida</option>
                        <option value="Incidente">Incidente</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="cmbAssunto">Selecione o Assunto:</label>
                    <select id="cmbTipo" class="form-control">
                        <option value="Aplicação">Aplicação</option>
                        <option value="DashBoard">Dashboard</option>
                        <option value="Relatórios">Relatórios</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="comment">Mensagem:</label>
                    <textarea class="form-control" rows="5" id="comment"></textarea>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-primary">Abrir Chamado</button>
                <#if dadosLog ? has_content>
                    <input type="hidden" id="idUserChamado" value="${dadosLog.idUsuario}">
                </#if>
            </div>
        </div>
    </div>
</div>

</body>
</html>


<script>

</script>

<script src="js/pagePrincipal/basic.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>

