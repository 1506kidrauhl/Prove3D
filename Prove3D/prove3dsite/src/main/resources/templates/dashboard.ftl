<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>DashBoard</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="robots" content="all,follow">

    <link rel="shortcut icon" href="img/parallax/logo.png" />
    <link rel="stylesheet" href="css/custom.css"/>
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css"> -->
    <!-- Font Awesome CSS-->
    <link href="https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    <!-- <link rel="stylesheet" href="vendor/font-awesome/css/font-awesome.min.css"> -->
    <!-- Fontastic Custom icon font-->
    <link rel="stylesheet" href="css/fontastic.css">
    <!-- Google fonts - Poppins -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">


    <!-- gustavo gay -->

    <link rel="stylesheet" href="js/fancybox/jquery.fancybox.css" type="text/css" media="screen" />
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
</head>
<body>
<div id="cho">
<div id="dashboard">
<div class="page">
    <!-- Main Navbar-->
    <header class="header" style="vertical-align: middle">
        <nav class="navbar">
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
                        <li class="nav-item"><a href="/sair" class="nav-link logout"> <span class="d-flex d-sm-inline">Sair</span><i class="fa fa-sign-out"></i></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <div id="mySidenav" class="sidenav">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
        <a href="/principal">Home</a>
        <a href="/dashboard" data-toggle="modal" data-target="#modalChamados">Chamados</a>
        <a style="color:#796aee" href="/dashboard">Dashboard</a>
        <a href="">Desempenho de processos</a>
        <a href="/relatorio">Relatórios</a>
    </div>


    <script>
        function openNav() {
            document.getElementById("mySidenav").style.width = "250px";
        }

        function closeNav() {
            document.getElementById("mySidenav").style.width = "0";
        }
    </script>


    <div class="page-content d-flex align-items-stretch" >
        <!-- Side Navbar -->
        <nav class="side-navbar" >
            <!-- Sidebar Header-->
            <div class="sidebar-header d-flex align-items-center">
                <div class="avatar"><img src="img/dashboard/user.png" alt="..." class="img-fluid rounded-circle"></div>
                <div class="title">
                    <#if dadosLog ? has_content>
                        <h1 class="h4">${dadosLog.nome}</h1>
                    </#if>
                </div>
            </div>

            <ul class="list-unstyled">
                <li><a href="/principal"><i></i>Home</a></li>
                <li><a data-toggle="modal" data-target="#modalChamados"><i></i>Chamados</a></li>
                <li class="active"><a href="/dashboard"><i></i>Dashboard</a></li>
                <li><a><i></i>Desempenho de processos</a></li>
                <li><a href="/relatorio"><i></i>Relatórios</a></li>
            </ul>

        </nav>

        <div class="content-inner">
            <!-- Page Header-->
            <header class="page-header">
                <div class="container-fluid">
                    <div class="container demo">
                    <h2 class="no-margin-bottom">
                        <div class="col-lg-12">
                            <span class="side-Dash" style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
                            Dashboard
                        </div>
                    </h2>
                </div>
            </header>
            <!-- Dashboard Counts Section-->
            <section class="dashboard-counts no-padding-bottom">
                <div class="container-fluid">
                    <div class="row bg-white has-shadow">
                        <!-- Item -->
                        <div class="col-xl-3 col-sm-6" onclick="filtro(cpu)">
                            <div class="teste1">
                            <div class="item d-flex align-items-center">
                                <div class="img-position" ><img src="img/dashboard/cpu.png"></div>
                                <div class="title"><span>CPU</span>
                                    <div class="progress">
                                        <div role="progressbar" style="width: 25%; height: 4px;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-violet"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </div>

                        <!-- Item -->
                        <div class="col-xl-3 col-sm-6" onclick="filtro(disco)">
                            <div class="teste1">
                            <div class="item d-flex align-items-center">
                                <div class="img-position" ><div><img src="img/dashboard/disco.png"></div></div>
                                <div class="title"><span>Disco</span>
                                    <div class="progress">
                                        <div role="progressbar" style="width: 70%; height: 4px;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </div>

                        <!-- Item -->
                        <div class="col-xl-3 col-sm-6" onclick="filtro(gpu)">
                            <div class="teste1">
                            <div class="item d-flex align-items-center">
                                <div class="img-position"><img src="img/dashboard/gpu.png"></div>
                                <div class="title"><span>GPU</span>
                                    <div class="progress">
                                        <div role="progressbar" style="width: 40%; height: 4px;" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-green"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </div>
                            <!-- Item -->
                        <div class="col-xl-3 col-sm-6" onclick="filtro(memoria)">
                            <div class="teste1">
                            <div class="item d-flex align-items-center">
                                <div class="img-positionMemoria"><img src="img/dashboard/memoria.png"></div>
                                <div class="title"><span>Memória</span>
                                    <div class="progress">
                                        <div role="progressbar" style="width: 50%; height: 4px;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-orange"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </section>
            <!--Dashboard Header Section-->
            <section class="dashboard-header">
                <div class="container-fluid">
                    <div class="row" >
                        <!--Statistics-->
                        <div class="statistics col-lg-3 col-12" style="display: none;" id="cpu" onclick="tbDisplay()">
                            <div class="statistic d-flex align-items-center bg-white has-shadow" onclick="gerarGrafico('#796AED','Temperatura(°C)')">
                                <div class="icon bg-red"><i class="fa fa-tasks"></i></div>
                                <div class="text"><small>Temperatura</small></div>
                            </div>

                            <div class="statistic d-flex align-items-center bg-white has-shadow" onclick="gerarGrafico('#4dafff','Voltagem(V)')">
                                <div class="icon bg-green"><i class="fa fa-calendar-o"></i></div>
                                <div class="text"><small>Voltagem</small></div>
                            </div>
                        </div>

                        <div class="statistics col-lg-3 col-12" style="display: none;" id="memoria" onclick="tbDisplay()">
                            <div class="statistic d-flex align-items-center bg-white has-shadow" onclick="gerarGrafico('#796AED','Leitura')">
                                <div class="icon bg-red"><i class="fa fa-tasks"></i></div>
                                <div class="text"><small>Leitura</small></div>
                            </div>

                            <div class="statistic d-flex align-items-center bg-white has-shadow" onclick="gerarGrafico('#4dafff','Gravação')">
                                <div class="icon bg-green"><i class="fa fa-calendar-o"></i></div>
                                <div class="text"><small>Gravação</small></div>
                            </div>
                        </div>

                        <div class="statistics col-lg-3 col-12" style="display: none;" id="disco" onclick="tbDisplay()">
                            <div class="statistic d-flex align-items-center bg-white has-shadow" onclick="gerarGrafico('#796AED','Utilização(%)')" >
                                <div class="icon bg-red"><i class="fa fa-tasks"></i></div>
                                <div class="text"><small>Utilização</small></div>
                            </div>
                        </div>

                        <div class="statistics col-lg-3 col-12" style="display: none;" id="gpu" onclick="tbDisplay()">
                            <div class="statistic d-flex align-items-center bg-white has-shadow" onclick="gerarGrafico('#796AED','Em uso(%)')">
                                <div class="icon bg-red"><i class="fa fa-tasks"></i></div>
                                <div class="text"><small>Em uso</small></div>
                            </div>

                            <div class="statistic d-flex align-items-center bg-white has-shadow" onclick="gerarGrafico('#4dafff','Cache(%)')">
                                <div class="icon bg-green"><i class="fa fa-calendar-o"></i></div>
                                <div class="text"><small>Cache</small></div>
                            </div>
                        </div>

                        <!--Line Chart-->
                        <div class="chart col-lg-9 col-12" style="display: none;" id="chartDashboard">
                            <div class="line-chart bg-white d-flex align-items-center justify-content-center has-shadow">
                                <canvas id="lineCahrt"></canvas>
                            </div>
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
                        <option value="1">Dúvida</option>
                        <option value="2">Incidente</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="cmbAssunto">Selecione o Assunto:</label>
                    <select id="cmbTipo" class="form-control">
                        <option value="1">Aplicação</option>
                        <option value="2">Dashboard</option>
                        <option value="3">Relatórios</option>
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
            </div>
        </div>
    </div>
</div>
</div>
</div>
</body>
</html>
</html>

<!-- Projects Section-->
<script src="js/dashboard/charts-home.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.js"> </script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"> </script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.js"></script>
<!-- Main File-->
<!-- Tweaks for older IEs--><!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->

<!-- Main File-->
<script src="js/dashboard/front.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
