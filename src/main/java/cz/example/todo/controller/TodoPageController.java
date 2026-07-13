package cz.example.todo.controller;

import cz.example.todo.constant.AppConstant;
import cz.example.todo.domain.Todo;
import cz.example.todo.domain.TodoPriority;
import cz.example.todo.model.TodoPageModel;
import cz.example.todo.model.TodoPageSubmit;
import cz.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TodoPageController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(AppConstant.TODOS_CONTROLLER)
    public ModelAndView doGet() {
        TodoPageModel todoPageModel = TodoPageModel.builder()
                .existingTodos(todoRepository.findAll())
                .proposalTodos(
                        List.of(
                                new Todo(null, LocalDateTime.now(), "", TodoPriority.MEDIUM)
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

        todoRepository.saveAll(newTodos);
        todoRepository.deleteAllById(formPageSubmit.getRemoveTodoIds());

        return "redirect:" + AppConstant.TODOS_CONTROLLER;
    }
}
