package cz.example.todo.model;

import cz.example.todo.domain.Todo;
import cz.example.todo.domain.TodoPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class TodoPageModel {
    private final List<Todo> existingTodos;
    private final List<Todo> proposalTodos;
}
