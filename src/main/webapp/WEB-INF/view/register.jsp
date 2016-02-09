<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-header">
    <h4>Register</h4>
</div>
<form:form modelAttribute="account" class="account-form form-horizontal">
    <div class="alert alert-danger" style="display: none"></div>
    <div class="form-group">
        <label class="col-md-2 control-label">Username</label>
        <div class="col-md-4">
            <form:input class="form-control" path="username" />
        </div>
        <span class="col-md-6 help-block"><form:errors path="username"/></span>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label">Password</label>
        <div class="col-md-4">
            <form:password class="form-control" path="password" />
        </div>
        <span class="col-md-6 help-block"><form:errors path="password"/></span>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label">Repeat Password</label>
        <div class="col-md-4">
            <form:password class="form-control" path="passwordRepeat" />
        </div>
        <span class="col-md-6 help-block"><form:errors path="passwordRepeat"/></span>
    </div>
    <div class="form-group">
        <div class="col-md-offset-6 col-md-2">
            <button type="submit" data-loading-text="Working..." class="btn btn-primary">Save</button>
            <a href="<spring:url value="/"/>" class="btn btn-default" role="button">Cancel</a>
        </div>
    </div>
</form:form>
<script type="text/javascript" src="<spring:url value="/js/account.js"/>" ></script>