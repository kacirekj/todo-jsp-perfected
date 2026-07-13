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
    <link rel="stylesheet" href="<%= request.getContextPath() + "/style/style.css" %>">
</head>

<body>
<h1>Todo list</h1>

<form method="post" action="<%= AppConstant.TODOS_CONTROLLER %>">
    <table>
        <thead>
        <tr>
            <th>Time</th>
            <th>Text</th>
            <th>Priority</th>
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
                        type="datetime-local"
                        name='<%= toPath(TodoPageSubmit::getTodos, index, Todo::getTime) %>'
                        value="<%= proposalTodo.getTime() %>"
                />
            </td>

            <td>
                <input
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

    <button type="submit">Add todos</button>
</form>

<h2>Current todos</h2>

<%
    if (pageModel.getExistingTodos().isEmpty()) {
%>
<p>No todo items.</p>
<%
} else {
%>
<table>
    <thead>
    <tr>
        <th>Time</th>
        <th>Text</th>
        <th>Priority</th>
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
<%
    }
%>
</body>
</html>
