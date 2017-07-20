<%--
  Created by IntelliJ IDEA.
  User: ankur
  Date: 20/7/17
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Create Topic</h4>
            </div>
            <div class="modal-body">
                <form method="post" action="javascript:void(0)">
                    <div class="form-group">
                        <label>Topic Name</label>
                        <input type="text" id="topicName" class="form-control" name="topicName" aria-describedby="emailHelp" placeholder="Enter topic name" required>
                    </div>
                    <div class="form-group">
                        <label>Topic</label>
                        <select id="topicvisibility" class="form-control" name="visibility">
                            <option>PUBLIC</option>
                            <option>PRIVATE</option>
                        </select>
                    </div>
                    <button id="submitForm" type="submit" class="btn btn-primary">Create</button>
                    <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>

    </div>
</div>

</body>
</html>
