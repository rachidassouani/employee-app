package io.rachidassouani.employeeapp.controller;

import io.rachidassouani.employeeapp.entity.Employee;
import io.rachidassouani.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public String findAll(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "list-employees";
    }

    @GetMapping("showFormForAdd")
    public String showFormForAdd(Model model) {

        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PostMapping("save")
    public String save(@ModelAttribute("employee") Employee employee) {
        // save employee
        employeeService.save(employee);
        return "redirect:/api/v1/employees";
    }

    @GetMapping("showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {
        // get the employee
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("delete")
    public String showFormForDelete(@RequestParam("employeeId") int id) {
        // delete employee by it's Id
        employeeService.deleteById(id);

        // redirect to the list of employees
        return "redirect:/api/v1/employees";
    }

}
