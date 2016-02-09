<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- CSS -->
<link rel="stylesheet" href="<spring:url value="/webjars/bootstrap/3.3.5/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<spring:url value="/webjars/bootstrap/3.3.5/css/bootstrap-theme.min.css"/>">
<link rel="stylesheet" href="<spring:url value="/webjars/bootstrap/3.3.5/css/bootstrap-theme.min.css"/>">
<link rel="stylesheet" href="<spring:url value="/css/base.css"/>">
<link rel="stylesheet" href="<spring:url value="/css/chat.css"/>">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<!-- JS -->
<script type="text/javascript" src="<spring:url value="/webjars/jquery/2.1.4/jquery.min.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/webjars/jquery-tmpl/beta1.0.0/jquery.tmpl.min.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/webjars/bootstrap/3.3.5/js/bootstrap.min.js"/>" ></script>
<script type="text/javascript" src="<spring:url value="/webjars/sockjs-client/1.0.2/sockjs.min.js"/>" ></script>
<script type="text/javascript" src="<spring:url value="/webjars/stomp-websocket/2.3.3/stomp.min.js"/>" ></script>
<script type="text/javascript" src="<spring:url value="/js/base.js"/>" ></script>
<script type="text/javascript">
  sc.contextPath = '<spring:url value="/"/>';
</script>
