package com.example.fabrikam.TodoDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class TodoDemoController {

    @Autowired
    private TodoItemRepository repository;

    @RequestMapping("/")
    public String index(Model model) {
        ArrayList<TodoItem> todoList = (ArrayList<TodoItem>) repository.findAll();
        //model.addAttribute("items", todoList);
        model.addAttribute("newitem", new TodoItem());
        model.addAttribute("items", new TodoListViewModel(todoList));
        return "index";
    }

    @RequestMapping("/add")
    public String addTodo(@ModelAttribute TodoItem requestItem) {
        TodoItem item = new TodoItem(requestItem.getCategory(), requestItem.getName());
        repository.save(item);
        return "redirect:/";
    }

    @RequestMapping("/update")

    public String updateTodo(@ModelAttribute TodoListViewModel requestItems) {
        ArrayList<TodoItem> todoList = (ArrayList<TodoItem>) repository.findAll();
        ArrayList<String> names = new ArrayList<>();

        for (TodoItem requestItem : requestItems.getTodoList() ) {
            TodoItem item = new TodoItem(requestItem.getCategory(), requestItem.getName());
            item.setComplete(requestItem.isComplete());
            item.setId(requestItem.getId());
            repository.save(item);
        }


        for(TodoItem todoItem : todoList){
            if(todoItem.isComplete()){
                repository.delete(todoItem);
            }
            else{
                names.add(todoItem.getName());
            }
        }

        return "redirect:/";

    }


    @RequestMapping("/edit/{id}")
//    @ResponseBody
    public String editTodo(Model model, @PathVariable("id") long id) {
        TodoItem todoItem = repository.findOne(id);
        if(todoItem == null) {
            model.addAttribute("test", "からっぽですよ！！！");
            return "edit";
        } else {
            model.addAttribute("test", todoItem.getName());
            return "edit";
//        return "Get a specific Foo with id=" + id;
        }
    }

}
