<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-header">
    <h4>Login</h4>
</div>
<form class="form-horizontal" action="<spring:url value="/login"/>" method="POST">
    <c:if test="${param.error ne null}">
        <div class="alert alert-danger">Invalid username and password.</div>
    </c:if>
    <c:if test="${param.logout ne null}">
        <div class="alert alert-success">You've logged out successfully.</div>
    </c:if>
    <div class="form-group">
        <label class="col-md-2 control-label">Username</label>
        <div class="col-md-4">
            <input type="text" class="form-control" name="username" value="" />
        </div>
        <span class="col-md-6 help-block"><form:errors path="username"/></span>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label">Password</label>
        <div class="col-md-4">
            <input type="password" class="form-control" name="password" value=""/>
        </div>
        <span class="col-md-6 help-block"><form:errors path="password"/></span>
    </div>
    <div class="form-group">
        <div class="col-md-offset-6 col-md-2">
            <button type="submit" class="btn btn-primary">Login</button>
            <a href="<spring:url value="/register"/>" class="btn btn-default" role="button">Register</a>
        </div>
    </div>
</form>