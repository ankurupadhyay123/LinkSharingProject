<%--
  Created by IntelliJ IDEA.
  User: ankur
  Date: 20/7/17
  Time: 11:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="documentModal" role="dialog">
    <div class="ui-front modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share Document</h4>
            </div>
            <div class="modal-body">
                <form method="post" id="documentForm" enctype="multipart/form-data" action="/createDocumentResource">
                    <div class="form-group">
                        <label>Document*:</label>
                        <input type="file" class="form-control" id="documentFile" name="fileUrl" placeholder="Browse file" required>
                    </div>
                    <div class="form-group">
                        <label>Description*:</label>
                        <textarea id="documentDescription" name="description" class="form-control" placeholder="Description"></textarea>
                    </div>
                    <div class="form-group">
                        <label>Topic</label>
                        <input type="search" name="topic"id="docTagName">
                    </div>
                    <button id="submitdocumentform" type="submit" class="btn btn-primary">Share</button>
                    <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>

    </div>
</div>
</body>
</html>
