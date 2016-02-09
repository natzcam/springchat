<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication var="user" property="principal" />

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sc-navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<spring:url value="/"/>"><p>Spring Chat</p></a>
        </div>
        <div id="sc-navbar" class="collapse navbar-collapse">
            <sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
                <ul class="nav navbar-nav">
                    <li>
                        <p class="navbar-text"><c:out value="${user.username}"/></p>
                    </li>
                </ul>
                <form class="navbar-form navbar-right" action="<spring:url value="/logout"/>" method="POST">
                    <input type="submit" class="btn btn-default" value="Log Out"/>
                </form>
            </sec:authorize>
        </div><!--/.nav-collapse -->
    </div>
</nav>

