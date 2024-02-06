package com.example.demo.Controller;



import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/employees")
public class EmployeeController{

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// get the employees from db
		List<Employee> theEmployees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Employee theEmployee = new Employee();

		theModel.addAttribute("employee", theEmployee);

		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,
									Model theModel) {

		// get the employee from the service
		Employee theEmployee = employeeService.findById(theId);

		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);

		// send over to our form
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

		// save the employee
		employeeService.save(theEmployee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int employeeId)
	{
		employeeService.deleteById(employeeId);
		return "redirect:/employees/list";
	}
	
	@GetMapping("/search")
	public String searchform(Model theModel) {
		return "employees/search-employee";
	}
	
	@GetMapping("/findbyEmail")
	public String findbyEmail(@RequestParam("mail") String mail, Model model) {
		// save the employee
		List<Employee> list = employeeService.findByEmail(mail);
		model.addAttribute("employees", list);
		// use a redirect to prevent duplicate submissions
		return "employees/list-employees";
	}
	@GetMapping("/findbyfirstName")
	public String findbyFirstName(@RequestParam("firstName") String firstName, Model model) {
		// save the employee
		List<Employee> list = employeeService.findByEmail(firstName);
		model.addAttribute("employees", list);
		// use a redirect to prevent duplicate submissions
		return "employees/list-employees";
	}
	
}