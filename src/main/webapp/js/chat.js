$(document).ready(function () {
    var socket = new SockJS('/chat');
    //var socket = new WebSocket('/chat');
    var stomp = Stomp.over(socket);

    stomp.connect({}, function(frame) {

        console.log('Connected ' + frame);
        var username = frame.headers['user-name'];

        stomp.subscribe("/app/users", function(response) {
            $('#user-tmpl').tmpl(JSON.parse(response.body)).appendTo('#user-list');
        });
        
        stomp.subscribe("/topic/login", function(response){
        	var user = JSON.parse(response.body);
        	
        	//check if user is already existing
        	if(!$('#' + user.username).length && user.username != username){
                $('#user-tmpl').tmpl(user).appendTo('#user-list');
            }
        });
        
        stomp.subscribe("/topic/logout", function(response){
             var user = JSON.parse(response.body);
             
             //if other session logouts, you logout
             if(user.username == username){
            	 window.location.href = '/logout';
             }
             
             $('#user-list').find('#' + user.username).remove();
             removeTab('#' + user.username + "-tab");
        });

        //click the user list
        $('#user-list').on('click', '.list-group-item', function(e){
            var id = $(this).attr('id');
            createTab(id);
            $('a[href="#' + id + '-tab"]').tab('show');
        });

        stomp.subscribe("/topic/message.global", function(response) {
            var message = JSON.parse(response.body);
            blinkTab('global-tabz');
            $('#message-tmpl').tmpl(message).appendTo('#global-textareaz');
        });

        $('#global-form').on('submit', function(e){
            var $textinput = $(this).find("input[type=text]");
            var text = $textinput.val();

            if(text && text.length){
                stomp.send("/app/message.global", {}, JSON.stringify({message:text}));
                $textinput.val('');
            }

            e.preventDefault();
        });

        stomp.subscribe("/user/queue/message.private", function(response) {
            var message = JSON.parse(response.body);
            createTab(message.sender);
            blinkTab(message.sender + '-tab');
            addMessage(message.sender, message);
        });

        $('#chat-tab-panes').on('submit', '.chat-form', function(e){
            var $textinput = $(this).find("input[type=text]");
            var text = $textinput.val();

            if(text && text.length){
                var recipient = $(this).data("username");
                addMessage(recipient, {message:text, sender:username});
                stomp.send("/app/message.private." + recipient, {}, JSON.stringify({message:text}));
                $textinput.val('');
            }

            e.preventDefault();
        });

        function createTab(username){
            var $tabdata = {
                "username": username
            };

            //create tab if none
            if($('a[href="#' + username + '-tab"]').length < 1){
                $('#tab-tmpl').tmpl($tabdata).appendTo('#chat-tabs');
                $('#tab-pane-tmpl').tmpl($tabdata).appendTo('#chat-tab-panes');
            }
        }

        function blinkTab(tabid){
            var $tab = $('a[href="#' + tabid + '"]');
            var active = $tab.closest('li').hasClass('active');
            if(!active){
                $tab.addClass('blink-me');
            }
        }

        function addMessage(target, message){
            $('#message-tmpl').tmpl(message).appendTo('#' + target + '-textarea');
        }

        $('#chat-tabs').on('shown.bs.tab', 'a[data-toggle="tab"]', function (e) {
            console.log(e.target);
            $(e.target).removeClass('blink-me');
        });

        $('#chat-tabs').on('click', '.close-btn', function (e) {
            var tabPaneId = $(this).parent().attr("href");
            removeTab(tabPaneId);
        });
        
        function removeTab(tabPaneId){
        	$('a[href=' + tabPaneId + ']').parent().remove();
            $('#chat-tabs a:last').tab('show');
            $(tabPaneId).remove();
        }

    }, function(error) {
        console.log("STOMP protocol error " + error);
    });
});

