package com.springboot.empcrud.controller;

import com.springboot.empcrud.entity.Employee;
import com.springboot.empcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

   @Autowired
   public EmployeeController(EmployeeService theemployeeService){
      employeeService=theemployeeService;
   }

    @GetMapping("/list")
    public String listEmployees(Model theModel){

        List<Employee> theEmployees = employeeService.findAll();

        theModel.addAttribute("employees",theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){

        Employee theEmployee = employeeService.findById(theId);

        // set employee in the model to prepopulate the form
        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

        // Save the employee
        employeeService.save(theEmployee);

        // use redirect to prevent duplicate submission
        return "redirect:/employees/list";
    }

    @GetMapping("delete")
    public String delete(@RequestParam("employeeId") int theId){

        employeeService.deleteById(theId);

        return "redirect:/employees/list";
    }
}
