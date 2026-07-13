<%@ tag body-content="empty" %>
<%@ tag import="cz.example.todo.domain.TodoPriority" %>
<%@ attribute name="name" type="java.lang.String" required="true" %>
<%@ attribute name="selectedPriority" type="cz.example.todo.domain.TodoPriority" required="true" %>

<select class="form-control" name="<%= name %>">
    <%
        for (var priority : TodoPriority.values()) {
    %>
    <option
            value="<%= priority %>"
            <%= priority == selectedPriority ? "selected" : "" %>
    >
        <%= priority %>
    </option>
    <%
        }
    %>
</select>