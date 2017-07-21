<%--
  Created by IntelliJ IDEA.
  User: ankur
  Date: 20/7/17
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="sendInvitationModal" role="dialog">
    <div class=" modal-dialog" style="z-index: 11000;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Send Invitation</h4>
            </div>

            <div class="modal-body">
                <form method="post" id="sendInvitationForm" action="/sendInvitation">
                    <div class="form-group">
                        <label>Email*:</label>
                        <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Enter email name" required/>
                    </div>
                    <div class="form-group">
                        <label>Topic</label>
                        <input type="search" id="invitationservicetagName" name="topicSearchTag"/>
                    </div>
                    <button id="submitSendInvitationForm" type="submit" class="btn btn-primary">Send</button>
                    <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>

    </div>
</div>
</body>
</html>
