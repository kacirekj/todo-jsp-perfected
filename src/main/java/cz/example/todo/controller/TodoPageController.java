package cz.example.todo.controller;

import cz.example.todo.constant.AppConstant;
import cz.example.todo.domain.Todo;
import cz.example.todo.domain.TodoPriority;
import cz.example.todo.model.TodoPageModel;
import cz.example.todo.model.TodoPageSubmit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

@Controller
public class TodoPageController {

    private static final int FORM_TODO_COUNT = 5;

    private final List<Todo> todos = new CopyOnWriteArrayList<>();

    @GetMapping(AppConstant.TODOS_CONTROLLER)
    public ModelAndView doGet() {
        TodoPageModel todoPageModel = TodoPageModel.builder()
                .existingTodos(List.copyOf(todos))
                .proposalTodos(
                        List.of(
                                new Todo(LocalDateTime.now(), "", TodoPriority.MEDIUM)
                        )
                )
                .build();

        ModelAndView modelAndView = new ModelAndView(new InternalResourceView(AppConstant.TODOS_PAGE));
        modelAndView.addObject(AppConstant.PAGE_MODEL_ATTRIBUTE, todoPageModel);

        return modelAndView;
    }

    @PostMapping(AppConstant.TODOS_CONTROLLER)
    public String doPost(@ModelAttribute(AppConstant.PAGE_SUBMIT_ATTRIBUTE) TodoPageSubmit formPageSubmit) {
        List<Todo> newTodos = formPageSubmit.getTodos()
                .stream()
                .filter(todo -> todo.getText() != null)
                .filter(todo -> !todo.getText().isBlank())
                .toList();

        todos.addAll(newTodos);


        return "redirect:" + AppConstant.TODOS_CONTROLLER;
    }
}
