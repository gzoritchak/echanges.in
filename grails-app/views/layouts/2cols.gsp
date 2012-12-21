<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Echanges In"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'echangesin.css')}" type="text/css">
    %{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">--}%
    <g:layoutHead/>
    <r:layoutResources/>
</head>
<body>
<div class="navbar-static-top navbar-inverse">
    <div class="navbar-inner">
        <div class="container">
            <div class="row">
                <div class="navbar-text span7">
                    <a href="${createLink(controller:'home',action:'index')}" class="header-title">Echanges In</a>
                    <span class="sel-name">${communaute?.name}</span>
                    <h4 class="moto">Moteur de communautés d'échanges</h4>
                </div>
                <div id="user-box" class="span5">
                    <ul class="nav navbar pull-right">
                        <shiro:isNotLoggedIn><a >Se connecter</a></shiro:isNotLoggedIn>
                        <shiro:isLoggedIn>
                            <li class="dropdown">
                                <a href="#" id="drop3" role="button" class="dropdown-toggle"
                                   data-toggle="dropdown">Gaetan Z<b class="caret"></b></a>
                                <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="drop3">
                                    <li><a tabindex="-1" href="#">Profil</a></li>
                                    <li class="divider"></li>
                                    <li><a tabindex="-1" href="#">se déconnecter</a></li>
                                </ul>
                            </li>
                        </shiro:isLoggedIn>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


<g:layoutBody/>
<div class="footer" role="contentinfo"></div>

<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
<g:javascript library="application"/>
<script src="${resource(dir: 'js', file: 'jquery-1.8.0.min.js')}"></script>
<script src="${resource(dir: 'css/bootstrap/js', file: 'bootstrap-transition.js')}"></script>
<script src="${resource(dir: 'css/bootstrap/js', file: 'bootstrap-dropdown.js')}"></script>

<r:layoutResources/>
</body>
</html>
