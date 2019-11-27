<!DOCTYPE html>
<html class="no-js lt-ie9 lt-ie8 lt-ie7">
<html class="no-js lt-ie9 lt-ie8">
<html class="no-js lt-ie9">

<html class="no-js" xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<!--<![endif]-->

<head>
  <!-- BASICS -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Provë 3D</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="img/parallax/logo.png" />
  <link rel="stylesheet" type="text/css" href="css/isotope.css" media="screen" />
  <link rel="stylesheet" href="js/fancybox/jquery.fancybox.css" type="text/css" media="screen" />
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/bootstrap-theme.css">
  <link rel="stylesheet" href="css/style.css">

</head>

<body>
  <section id="header" class="appear"></section>
  <div class="navbar navbar-fixed-top" role="navigation" data-0="line-height:70px; height:70px; background-color:rgba(47,79,79,0.2);" data-300="line-height:70px; height:70px; background-color:rgba(25,50,112,1);">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="fa fa-bars color-white"></span>
					</button>
        <h1><a class="navbar-brand" href="index.html" style="margin-left: 5px; vertical-align: middle;" data-0="line-height:90px;" data-300="line-height:90px;">
            Provë 3D
					</a></h1>
      </div>
      <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav" id="homeBar" data-0="margin-top:20px;" data-300="margin-top:20px;">
          <li class="active"><a href="#header">Home</a></li>
          <li><a href="#parallax1">Download</a></li>
          <li><a href="#section-manual">Manual</a></li>
          <li><a href="#testimonialsx1">Sobre</a></li>
          <li><a href="#section-works">Portfolio</a></li>
          <li><a href="#section-contact">Nos contate</a></li>
          <#if dadosUser ? has_content>
             <li><a href="/principal">${dadosLog.login}</a></li>
          <#else>
             <li><a href="#section-signup" type="button"  data-toggle="modal" data-target="#modalExemplo">Cadastro</a></li>
             <li><a href="#section-singin"  type="button"  data-toggle="modal" data-target="#modalExemplo2">Login</a></li>
          </#if>
        </ul>
      </div>
      <!--/.navbar-collapse -->
    </div>
  </div>


  <!-- Modal CADASTRO-->
<form action= "/cadastro" method="POST">
  <div class="modal fade" id="modalExemplo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel"><b>Cadastro</b></h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          </button>
        </div>
        <div class="modal-body">
            <div class="row">
              <div class="col-md-6">
                <label for="inputEmail4">Email</label>
                <input type="email" class="form-control" name="inputEmail" required>
              </div>
              <div class="col-md-6">
                <label for="inputPassword4">Nome</label>
                <input type="text" class="form-control" name="inputName" required>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <label for="inputAddress">Login</label>
                <input type="text" class="form-control" name="inputLogin" required maxlength="20" minlength="6">
              </div>
              </div>
            <div class="row">
              <div class="col-md-12">
                <label for="inputAddress2">Senha</label>
                <input type="password" class="form-control" name="inputPassword" required maxlength="14" minlength="8">
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <label for="inputTel">Telefone</label>
                <input type="text" class="form-control" name="inputTel" id="inputTel" maxlength="14" minlength="14" required>
              </div>
              <div class="col-md-6">
                <label for="inputCpf">CPF</label>
                <input type="text" class="form-control" name="inputCpf" id="inputCpf" maxlength="14" minlength="14" required>
              </div>
            </div>        
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
          <button type="submit" class="btn btn-primary">Salvar mudanças</button>
        </div>
 
      </div>
    </div>
  </div>
</form>
  <!-- MODAL LOGIN -->
  <div class="modal fade" id="modalExemplo2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel"><b>Login</b></h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          </button>
        </div>
        <div class="modal-body">
            <div class="form-group">
              <label for="exampleInputEmail1">Username</label>
              <input type="text" class="form-control" id="txtLogin" aria-describedby="emailHelp" required>
            </div>
            <div class="form-group">
              <label for="exampleInputPassword1">Senha</label>
              <input type="password" class="form-control" id="txtSenha" required>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
              <button type="button" class="btn btn-primary" onclick="btnEntrar()">Enviar</button>
            </div>
        </div>
      </div>
    </div>
  </div>

      <section id="testimonialsx2" class="section" data-stellar-background-ratio="0.5">
    <div class="container">
      <div class="row mar-bot40">
        <div class="col-md-6 col-md-offset-3">

          <div class="align-center">
              <img src="img/parallax/logo.png" alt="logo" width="130px">
            <h2 class="slogan" style="color: white"><b> Provë 3D</b></h2>
            <p style="color: white">
                Software de monitoramento de forma pratica e rápida
            </p>
          </div>
        </div>
      </div>
    </div>
  </section>


  <!-- services -->
  <section id="section-services" class="section pad-bot30 bg-white">
    <div class="container">
      <div class="row mar-bot40">
        <div class="col-lg-4">
          <div class="align-center">
                <img src="https://image.flaticon.com/icons/svg/1373/1373079.svg" width="125" height="224" alt="Modeling free icon" title="Modeling free icon">
            <h4 class="text-bold">Monitore seu hardware</h4>
            <p>
               Consiga ver de forma pratica o desempenho do seu computador
            </p>
          </div>
        </div>

        <div class="col-lg-4">
          <div class="align-center">
              <img src="https://image.flaticon.com/icons/svg/1086/1086452.svg" width="125" height="224" alt="Document free icon" title="Document free icon">
            <h4 class="text-bold">Receba relatórios</h4>
            <p>
             Você receberá relatorios semanais/mensais sobre o desempenho do seu computador, com algumas dicas para otimizar sua performace
            </p>
          </div>
        </div>

        <div class="col-lg-4">
          <div class="align-center">
              <img src="https://image.flaticon.com/icons/svg/884/884475.svg" width="125" height="224" alt="Shield free icon" title="Shield free icon">
            <h4 class="text-bold">Fique tranquilo</h4>
            <p>
               Você tera mais segurança e flexibilodade conseguindo monitorar seu processo via WTS
            </p>
          </div>
        </div>

      </div>

    </div>
  </section>

  <!-- spacer section:testimonial -->
  <section id="testimonialsx1" class="section" data-stellar-background-ratio="0.5">
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <div class="align-center">
            <div class="testimonial">
                <h2 style="color: white">Sobre Nós</h2>
              <h5>
                    Nós temos como objetivo oferecer ao criador uma maneira de conseguir visualizar o desempenho do seu computador durante um processo de renderização e a produção de uma animação 3D/2D,
                     e a possibilidade de monitorar seu processo onde quiser utilizando a WTS, dando a ele uma melhor flexibilidade e segurança durante todo processo, evitando assim as margem de erro e um ganho de tempo.
        			</h5>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

 <!-- services -->
        <section id="section-services" class="section pad-bot30 bg-white">
          <div class="container">
      
            <div class="row mar-bot40">
              <div class="col-lg-4">
                <div class="align-center">
                      <img src="https://image.flaticon.com/icons/svg/1589/1589600.svg" width="125" height="224" alt="Modeling free icon" title="Modeling free icon">
                  <h4 class="text-bold">Missão</h4>
                  <p>
                     Temos como objetivo principal oferecer um software de monitoramento para designers de animação tanto B2C quanto B2B sendo ela 2D ou 3D, 
                      dando a ele a possibilidade de ver o desempenho atual da sua máquina e ao decorrer da semana/ mês.
                  </p>
                </div>
              </div>
      
              <div class="col-lg-4">
                <div class="align-center">
                    <img src="https://image.flaticon.com/icons/svg/1544/1544209.svg" width="125" height="224" alt="Document free icon" title="Document free icon">
                  <h4 class="text-bold">Valores</h4>
                  <p>
                   Nosso foco principal é proporcionar ao usuário uma boa visibilidade do seu desempenho dando a ele mais segurança e autonomia.
                  </p>
                </div>
              </div>
      
              <div class="col-lg-4">
                <div class="align-center">
                    <img src=https://image.flaticon.com/icons/svg/1366/1366474.svg width="125" height="224" alt="Shield free icon" title="Shield free icon">
                  <h4 class="text-bold">Visão</h4>
                  <p>
                     Visamos nos tornar um sinônimo de monitoramento para o ramo de animação, 
                     fazendo assim parcerias com grandes empresas de animação ou até a integração com aplicativos de design.
                  </p>
                </div>
              </div>
      
            </div>
      
          </div>
        </section>

  <!-- spacer section:stats -->
  <section id="parallax1" class="section pad-top40 pad-bot40" data-stellar-background-ratio="0.5">
    <div class="container">
      <div class="align-center pad-top40 pad-bot40">
        <blockquote class="bigquote color-white">Download Provë3D.zip</blockquote>
        <a href="img/portfolio/Amoeba.zip" download>
          <img src="img/portfolio/Download.png" alt="Provë3D" width="100px">
        </a>
      </div>
    </div>
  </section>

  <!-- section works -->
  <section id="section-works" class="section appear clearfix">

    <div class="container">
      <div class="row mar-bot40">
        <div class="col-md-offset-3 col-md-6">
          <div class="section-header">
            <h2 class="section-heading animated" data-animation="bounceInUp">Portfolio</h2>
            <p>Segue abaixo algumas artes e os aplicativos que são compativeis com nosso app.</p>
          </div>
        </div>
      </div>

      <div class="row">
        <nav id="filter" class="col-md-12 text-center">
          <ul>
            <li><a href="#" class="current btn-theme btn-small" data-filter="*">Todos</a></li>
            <li><a href="#" class="btn-theme btn-small" data-filter=".photography">Modelagem 3D</a></li>
            <li><a href="#" class="btn-theme btn-small" data-filter=".print">Print</a></li>
          </ul>
        </nav>
        <div class="col-md-12">
          <div class="row">
            <div class="portfolio-items isotopeWrapper clearfix" id="3">

              <article class="col-md-4 isotopeItem photography">
                <div class="portfolio-item">
                  <img src="img/portfolio/img2.jpg" class="imgPort" alt="" />
                  <div class="align-center">
                    <div class="folio-info">
                      <a href="img/portfolio/img2.jpg" class="fancybox"></a>
                    </div>
                  </div>
                </div>
              </article>


              <article class="col-md-4 isotopeItem photography">
                <div class="portfolio-item">
                  <img src="img/portfolio/img3.jpg" class="imgPort" alt="" />
                  <div class="align-center">
                    <div class="folio-info">                     
                      <a href="img/portfolio/img3.jpg" class="fancybox"></a>
                    </div>
                  </div>
                </div>
              </article>

              <article class="col-md-4 isotopeItem print">
                <div class="portfolio-item">
                  <img src="img/portfolio/img4.jpg" class="imgPort" alt="" />
                  <div class="align-center">
                    <div class="folio-info">         
                      <a href="img/portfolio/img4.jpg" class="fancybox"></a>
                    </div>
                  </div>
                </div>
              </article>

              <article class="col-md-4 isotopeItem photography">
                <div class="portfolio-item">
                  <img src="img/portfolio/img5.jpg" class="imgPort" alt="" />
                  <div class="align-center">
                    <div class="folio-info">
                      <a href="img/portfolio/img5.jpg" class="fancybox" ></a>
                    </div>
                  </div>
                </div>
              </article>

              <article class="col-md-4 isotopeItem print">
                <div class="portfolio-item">
                  <img src="img/portfolio/img7.jpg" class="imgPort" alt="" />
                  <div class="align-center">
                    <div class="folio-info">
                      <a href="img/portfolio/img7.jpg" class="fancybox"></a>
                    </div>
                  </div>
                </div>
              </article>

              <article class="col-md-4 isotopeItem photography">
                <div class="portfolio-item">
                  <img src="img/portfolio/img8.jpg" class="imgPort" alt="" />
                  <div class=" align-center">
                    <div class="folio-info">
                      <a href="img/portfolio/img8.jpg" class="fancybox"></a>
                    </div>
                  </div>
                </div>
              </article>
            </div>

          </div>
        </div>
      </div>
    </div>
  </section>



  <!-- contact -->

  <section id="footer" class="section footer">

    <div class="container">
      <div class="row animated opacity mar-bot20" data-andown="fadeIn" data-animation="animation">
        <div class="col-sm-12 align-center">
          <ul class="social-network social-circle">
            <li><a href="#" title="prove3d@gmail.com"><i><img src="img/portfolio/email.png"></i></a></li>
          </ul>
        </div>
      </div>

      <div class="row align-center copyright">
        <div  class="col-sm-12">
          <p id="copyright">Copyright &copy; Provë3D</p>
        </div>
      </div>
    </div>

  </section>

</body>

</html>

<script src="js/ajax/login.js"></script>
<script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script src="js/jquery.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.isotope.min.js"></script>
<script src="js/jquery.nicescroll.min.js"></script>
<script src="js/fancybox/jquery.fancybox.pack.js"></script>
<script src="js/skrollr.min.js"></script>
<script src="js/jquery.scrollTo.js"></script>
<script src="js/jquery.localScroll.js"></script>
<script src="js/stellar.js"></script>
<script src="js/jquery.appear.js"></script>
<script src="js/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<script src="js/jquery.mask.min.js"></script>

<!-- máscara -->
<script>
    $(document).ready(function(){

        $("#inputTel").mask("(00)00000-0000");
        $("#inputCpf").mask("000.000.000-00");

        $('#modalExemplo').on('shown.bs.modal', function () {
            $('#meuInput').trigger('focus')
        });

        if(localStorage.getItem("id") == null) {
          homeBar.innerHTML += `
              <li><a href="#section-signup" type="button"  data-toggle="modal" data-target="#modalExemplo">Cadastro</a></li>
              <li><a href="#section-singin"  type="button"  data-toggle="modal" data-target="#modalExemplo2">Login</a></li>
           `;
        } else{
          homeBar.innerHTML += "<li><a href='/principal'>"+localStorage.getItem("login")+"</a></li>";
        }

    });

</script>