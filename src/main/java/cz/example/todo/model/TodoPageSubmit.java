package cz.example.todo.model;

import cz.example.todo.domain.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoPageSubmit {
    private List<Todo> todos = new ArrayList<>();
}
