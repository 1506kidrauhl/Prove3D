<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Relatório</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="robots" content="all,follow">

  <link rel="shortcut icon" href="img/parallax/logo.png" />
  <link rel="stylesheet" href="css/custom.css"/>

  <!-- Bootstrap CSS-->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <!-- Font Awesome CSS-->
  <link href="https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
  <!-- Fontastic Custom icon font-->
  <link rel="stylesheet" href="css/fontastic.css">
  <!-- Google fonts - Poppins -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">
  <!-- theme stylesheet-->
  <link rel="stylesheet" href="/css/style.default.css" id="theme-stylesheet">

</head>
<body>
<div class="page">
  <!-- Main Navbar-->
  <header class="header">
    <nav class="navbar">
      <!-- Search Box-->
      <div class="container-fluid">
        <div class="navbar-holder d-flex align-items-center justify-content-between">
          <!-- Navbar Header-->
          <div class="navbar-header">
            <!-- Navbar Brand -->
            <a class="navbar-brand d-flexx d-sm-inline-block" href="/index">
              <div class="brand-text d-flex d-lg-inline-block"><strong>Provë3D</strong>
                <img style="height: 30px; width: 30px;" src="img/logo.png">
              </div>
            </a>
            <!-- Toggle Button-->
          </div>
          <!-- Navbar Menu -->
          <ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
            <!-- Logout    -->
            <li class="nav-item"><a href="/sair" class="nav-link logout"> <span class="d-none d-sm-inline">Sair</span><i class="fa fa-sign-out"></i></a></li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
  <div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="/principal">Home</a>
    <a href="/dashboard" data-toggle="modal" data-target="#modalChamados">Chamados</a>
    <a  href="/dashboard">Dashboard</a>
    <a  href="">Desempenho de processos</a>
    <a style="color:#796aee" href="/relatorio">Relatórios</a>
  </div>


  <script>
    function openNav() {
      document.getElementById("mySidenav").style.width = "250px";
    }
    function closeNav() {
      document.getElementById("mySidenav").style.width = "0";
    }
  </script>

  <div class="page-content d-flex align-items-stretch">
    <!-- Side Navbar -->
    <nav class="side-navbar">
      <!-- Sidebar Header-->
      <div class="sidebar-header d-flex align-items-center">
        <div class="avatar"><img src="img/dashboard/user.png" alt="..." class="img-fluid rounded-circle"></div>
        <div class="title">
            <h1 class="h4">${dadosUser.nome}</h1>
            <input type="hidden" id="idAux" value="${dadosUser.idUsuario}">
        </div>
      </div>

      <ul class="list-unstyled">
        <li><a href="/principal"><i></i>Home</a></li>
        <li><a data-toggle="modal" data-target="#modalChamados"><i></i>Chamados</a></li>
        <li><a href="/dashboard"><i></i>Dashboard</a></li>
        <li><a><i></i>Desempenho de processos</a></li>
        <li class="active"><a href="/relatorio"><i></i>Relatórios</a></li>
      </ul>

    </nav>

    <div class="content-inner">
      <!-- Page Header-->
      <header class="page-header">
        <div class="container-fluid">
          <div class="col-lg-12"> <span class="side-Dash" style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
            Relatórios </div>

        </div>
      </header>

      <!-- Dashboard Counts Section-->
      <section class="dashboard-counts no-padding-bottom" id="secLog">
        <div class="container-fluid">
          <div class="row bg-white has-shadow">
            <!-- Item -->
            <div class="col-xl-12 col-sm-12">
              <!-- FILTRO DATA -->
              <div class="row">
                <div class="col-sm-6">
                  <label for="dtInicio">Data Inicial: </label>
                  <input type="date" class="form-control" id="dtInicio" aria-describedby="emailHelp">
                </div>
                <div class="col-sm-6">
                  <label for="dtFim">Data Final: </label>
                  <input type="date" class="form-control" id="dtFim" aria-describedby="emailHelp">
                </div>
              </div>
              <!--FILTRO HORA-->
              <div class="row">
                <div class="col-sm-6">
                  <label for="hrInicio">Hora Inicial: </label>
                  <input type="time" class="form-control" id="hrInicio" aria-describedby="emailHelp">
                </div>
                <div class="col-sm-6">
                  <label for="hrFim">Hora Final: </label>
                  <input type="time" class="form-control" id="hrFim" aria-describedby="emailHelp">
                </div>
              </div>
              <!--FILTRO TIPO-->
              <div class="row">
                <div class="col-sm-6">
                  <select id="cmbFiltro" class="form-control">
                    <option value="">Todos</option>
                    <option value="Atenção">Atenção</option>
                    <option value="Erro">Erro</option>
                    <option value="OK">OK</option>
                  </select>
                </div>
                <div class="col-sm-6">
                  <select id="cmbComp" class="form-control">
                    <option value="CPU">CPU</option>
                    <option value="Disco">Disco</option>
                    <option value="GPU">GPU</option>
                    <option value="Memória">Memória</option>
                  </select>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-6">
                  <button class="btn btn-primary" onclick="chamaAjax()">Gerar Relatório</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!--SECTION PARA O RELATÓRIO ONLINE-->
      <section class="dashboard-counts no-padding-bottom" style="display: none" id="secTable">
        <div class="container-fluid">
          <div class="row bg-white has-shadow">
            <div class="col-xl-12 col-sm-6">
              <img src="img/back.png" onclick="troca()"/>

              <table class="table table-bordered table-striped" style="margin-top: 5px;">
                <thead>
                <tr>
                  <th>&nbsp;</th>
                  <th>Status</th>
                  <th>Descrição</th>
                  <th>Data</th>
                  <th>Hora</th>
                <tr>
                </thead>
                <tbody id="bodyTable"></tbody>
              </table>

            </div>
          </div>
        </div>
      </section>

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
          <select id="cmbAssunto" class="form-control">
            <option value="Aplicação">Aplicação</option>
            <option value="Dashboard">Dashboard</option>
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
        <button type="button" class="btn btn-primary" id="btnEnviar" onclick="btnEmail()">Abrir Chamado</button>
          <input type="hidden" id="txtNmUser" value="${dadosUser.nome}">
          <input type="hidden" id="txtEmailC" value="${dadosUser.email}">
      </div>
    </div>
  </div>
</div>

</body>
</html>

<!-- JavaScript files-->
<script src="js/ajax/envioEmail.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.js"> </script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"> </script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.js"></script>
<!-- Main File-->
<!-- Tweaks for older IEs--><!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->

<script src="js/log/logs-home.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>