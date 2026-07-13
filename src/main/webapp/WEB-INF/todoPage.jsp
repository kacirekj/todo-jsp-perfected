<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="cz.example.todo.constant.AppConstant" %>
<%@ page import="cz.example.todo.model.TodoPageModel" %>
<%@ page import="cz.example.todo.model.TodoPageSubmit" %>
<%@ page import="cz.example.todo.domain.Todo" %>
<%@ page import="static cz.example.todo.util.JspPathUtil.*" %>
<%@ taglib prefix="x" tagdir="/WEB-INF/tags" %>

<%
    var pageModel = (TodoPageModel) request.getAttribute(AppConstant.PAGE_MODEL_ATTRIBUTE);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Todo list</title>
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.8/css/bootstrap.min.css"
    >
</head>

<body>
<div class="container py-4">
    <h1 class="mb-4">Todo list</h1>

    <form class="mb-5" method="post" action="<%= AppConstant.TODOS_CONTROLLER %>">
        <div class="table-responsive">
            <table class="table table-striped table-hover align-middle">
                <thead>
                <tr>
                    <th scope="col">Time</th>
                    <th scope="col">Text</th>
                    <th scope="col">Priority</th>
                </tr>
                </thead>

                <tbody>
                <%
                    for (int index = 0; index < pageModel.getProposalTodos().size(); index++) {
                        var proposalTodo = pageModel.getProposalTodos().get(index);
                %>
                <tr>
                    <td>
                        <input
                                class="form-control"
                                type="datetime-local"
                                name='<%= toPath(TodoPageSubmit::getTodos, index, Todo::getTime) %>'
                                value="<%= proposalTodo.getTime() %>"
                        />
                    </td>

                    <td>
                        <input
                                class="form-control"
                                type="text"
                                name='<%= toPath(TodoPageSubmit::getTodos, index, Todo::getText) %>'
                                value="<%= proposalTodo.getText() %>"
                        />
                    </td>

                    <td>
                        <x:todoPrioritySelect
                                name='<%= toPath(TodoPageSubmit::getTodos, index, Todo::getPriority) %>'
                                selectedPriority="<%= proposalTodo.getPriority() %>"
                        />
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>

        <button class="btn btn-primary" type="submit">Add todos</button>
    </form>

    <h2 class="mb-3">Current todos</h2>

    <%
        if (pageModel.getExistingTodos().isEmpty()) {
    %>
    <p class="text-body-secondary">No todo items.</p>
    <%
    } else {
    %>
    <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
            <thead>
            <tr>
                <th scope="col">Time</th>
                <th scope="col">Text</th>
                <th scope="col">Priority</th>
            </tr>
            </thead>

            <tbody>
            <%
                for (var existingTodo : pageModel.getExistingTodos()) {
            %>
            <tr>
                <td><%= existingTodo.getTime() %>
                </td>
                <td><%= existingTodo.getText() %>
                </td>
                <td><%= existingTodo.getPriority() %>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    <%
        }
    %>
</div>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.8/js/bootstrap.bundle.min.js"></script>
</body>
</html>
