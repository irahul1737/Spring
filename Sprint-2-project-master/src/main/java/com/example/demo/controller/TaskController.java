package com.example.demo.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

import java.util.List;

@Controller
public class TaskController {
	@Autowired
	TaskService service;

	@RequestMapping("/login")
	public String loginPage(Model model) {
		return "login";
	}

	/*@RequestMapping("/")
	public String viewHomePage(Model model) {
		return "main";
	}*/
	@RequestMapping("/add")
	public String addTodo(Model model) {
		Task task= new Task();
		model.addAttribute("task",task);
		return "add";

	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("task") Task task) {
		Task otask=service.check(task.getTitle());
		if(Objects.nonNull(otask)) {
			return "redirect:/add";
		}
		else {
			service.save(task);
			return "redirect:/show";
		}
		
	}

	@RequestMapping("/show")
	public String show(Model model) {
		List<Task> listTask = service.listAll();
		model.addAttribute("listTask", listTask);
		return "show";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditTaskPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_task");
		Task task = service.get(id);
		mav.addObject("task", task);
		return mav;
	}

	@RequestMapping("/delete/{id}")
	public String deleteTask(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/show";
	}
	@RequestMapping("/searchid")
	public String searchIDTodo(Model model, @RequestParam("id") long id) {
		Optional<Task> otask = service.checkid(id);
		if (Objects.nonNull(otask)) {
		List<Task> listTask=service.listtask(id);
		model.addAttribute("listTask", listTask);
		return "show";
		}
		else {
			return "show";
		}
		
	}
	@RequestMapping("/searchtitle")
	public String searchTodo(Model model, @RequestParam("title") String title) {
		Task otask = service.check(title);
		if (Objects.nonNull(otask)) {
			List<Task> listTask=service.listtask(title);
			model.addAttribute("listTask", listTask);
			return "show";
			
		}else {
			return "show";
		}
	}
	
	@GetMapping("/")
	 public String viewHomePage(Model model) {
		 return findPaginated(1, model);
	 }

	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable("pageNo") int pageNo, Model model) {
		int pageSize = 5;
		Page<Task> page = service.findPaginated(pageNo, pageSize); 
		List<Task> listTasks = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listTask", listTasks);
		return "show";
	}
	@GetMapping("/sort")
	public String sort(Model model) {
		return findsorted(1, model);
	}
	@RequestMapping("page/{pageNo}")
	public String findsorted(@PathVariable("pageNo") int pageNo, Model model) {
		int pageSize = 3;
		Page<Task> page = service.findsorted(pageNo, pageSize); 
		List<Task> listTasks = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listTask", listTasks);
		return "show";
	}
}