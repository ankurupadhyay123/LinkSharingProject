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
<div class="modal fade" id="linkModal" role="dialog">
    <div class="ui-front modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share Link</h4>
            </div>

            <div class="modal-body">
                <form method="post" action="javascript:void(0)">
                    <div class="form-group">
                        <label>Link*:</label>
                        <input type="text" class="form-control" name="fileUrl" id="urllink" aria-describedby="emailHelp" placeholder="Enter link name" required>
                    </div>
                    <div class="form-group">
                        <label>Description*:</label>
                        <textarea id="description" class="form-control">Description</textarea>
                    </div>
                    <div class="form-group">
                        <label>Topic</label>
                        <input type="search" id="tagName">
                    </div>
                    <button id="submitlinkform" type="submit" class="btn btn-primary">Share</button>
                    <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>

    </div>
</div>
</body>
</html>
