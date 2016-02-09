<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row chat-container">
    <div id="user-list" class="list-group col-md-3">
    </div>
    <div class="col-md-9">
        <ul id="chat-tabs" class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active">
                <a href="#global-tabz" role="tab" data-toggle="tab">global</a>
            </li>
        </ul>
        <div id="chat-tab-panes" class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="global-tabz">
                <div id="global-textareaz" class="row chat-text-area">

                </div>
                <div class="row">
                    <form id="global-form" class="form-inline">
                        <input class="form-control" type="text"/>
                        <input class="btn btn-primary" type="submit" value="Send"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%--templates--%>
<%--user list item template--%>
<script id="user-tmpl" type="text/x-jquery-tmpl">
    <a href="#" id="\${username}" class="list-group-item">
        \${username}
    </a>
</script>

<%--tab template--%>
<script id="tab-tmpl" type="text/x-jquery-tmpl">
    <li role="presentation">
        <a href="#\${username}-tab" role="tab" data-toggle="tab">\${username}
             <span class="close-btn glyphicon glyphicon-remove"></span>
        </a>
    </li>
</script>

<%--tab pane template--%>
<script id="tab-pane-tmpl" type="text/x-jquery-tmpl">
    <div role="tabpanel" class="tab-pane" id="\${username}-tab">
        <div id="\${username}-textarea" class="row chat-text-area">

        </div>
        <div class="row">
            <form class="chat-form form-inline" data-username="\${username}">
                <input class="form-control" type="text"/>
                <input class="btn btn-primary" type="submit" value="Send"/>
            </form>
        </div>
    </div>
</script>

<%--message template--%>
<script id="message-tmpl" type="text/x-jquery-tmpl">
    <div>
            <span class="label label-primary">\${sender}</span>
            <span>\${message}</span>
    </div>
</script>
<%--templates--%>

<script type="text/javascript" src="<spring:url value="/js/chat.js"/>" ></script>


