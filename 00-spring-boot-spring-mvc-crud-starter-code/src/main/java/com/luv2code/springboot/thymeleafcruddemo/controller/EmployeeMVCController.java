package com.luv2code.springboot.thymeleafcruddemo.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafcruddemo.entity.Employee;
import com.luv2code.springboot.thymeleafcruddemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeMVCController {

	private EmployeeService employeeService;

	public EmployeeMVCController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/list")
	public String listEmployees(Model model) {
		List<Employee> employees= employeeService.findAll();
		model.addAttribute("employees", employees);
		return "employees/list-employee";
	}
	
	@GetMapping("/showAddEmployeeForm")
	public String showAddEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "employees/employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee ) {
		employeeService.save(theEmployee);
		return "redirect:/employees/list";
	}
	
	@GetMapping("/showUpdateEmployeeForm")
	public String updateEmployee(@RequestParam("employeeId") int Id , Model model) {
		Employee theEmployee = employeeService.findById(Id);
		model.addAttribute("employee", theEmployee);
		return "employees/employee-form";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId")int Id) {
		employeeService.deleteById(Id);
		return "redirect:/employees/list";
	}
}
