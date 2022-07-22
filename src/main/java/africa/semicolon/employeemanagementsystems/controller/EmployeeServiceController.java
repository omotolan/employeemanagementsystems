package africa.semicolon.employeemanagementsystems.controller;

import africa.semicolon.employeemanagementsystems.enums.JobLevel;
import africa.semicolon.employeemanagementsystems.dto.ApiResponse;
import africa.semicolon.employeemanagementsystems.dto.request.DepartmentRequest;
import africa.semicolon.employeemanagementsystems.dto.request.Register;
import africa.semicolon.employeemanagementsystems.exceptions.EmailAlreadyExist;
import africa.semicolon.employeemanagementsystems.services.EmployeeServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/employee/services")
public class EmployeeServiceController {

    private final EmployeeServices employeeServices;

    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody Register register) {
        try {
            var serviceResponse = employeeServices.registerEmployee(register);
            ApiResponse apiResponse = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (EmailAlreadyExist e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allEmployees")
    public ResponseEntity<?> getAllEmployees() {
        try {
            var serviceResponse = employeeServices.getAllEmployee();
            ApiResponse apiResponse = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/departments/employees")
    public ResponseEntity<?> getEmployeeByDepartment(@RequestBody DepartmentRequest department) {
        try {
            var serviceResponse = employeeServices.getEmployeeByDepartment(department);
            ApiResponse apiResponse = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllEmployee() {
        var serviceResponse = employeeServices.deleteAllEmployee();
        ApiResponse apiResponse = new ApiResponse(true, serviceResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("/jobLevel/employee")
    public ResponseEntity<?> getEmployeeByJobLevel(@RequestBody JobLevel jobLevel) {
        try {
            var serviceResponse = employeeServices.getEmployeeByJobLevel(jobLevel);
            ApiResponse apiResponse = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/suspend/employee/{employeeId}")
    public ResponseEntity<?> suspendEmployeeById(@PathVariable String employeeId) {
        try {
            var serviceResponse = employeeServices.suspendEmployeeById(employeeId);
            ApiResponse apiResponse = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (EmailAlreadyExist e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/isSuspended/employee/{employeeId}")
    public ResponseEntity<?> isSuspendEmployeeById(@PathVariable String employeeId) {
        try {
            var serviceResponse = employeeServices.isEmployeeSuspendedById(employeeId);
            ApiResponse apiResponse = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (EmailAlreadyExist e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/unSuspend/employee/{employeeId}")
    public ResponseEntity<?> unSuspendEmployeeById(@PathVariable String employeeId) {
        try {
            var serviceResponse = employeeServices.unSuspendEmployeeById(employeeId);
            ApiResponse apiResponse = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (EmailAlreadyExist e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getAnEmployee(@PathVariable String employeeId) {
        try {
            var serviceResponse = employeeServices.getEmployeeById(employeeId);
            ApiResponse apiResponse = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<?> deleteAnEmployee(@PathVariable String employeeId) {
        try {
            var serviceResponse = employeeServices.deleteEmployeeById(employeeId);
            ApiResponse apiResponse = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            ApiResponse apiResponse = new ApiResponse(false, e.getMessage());
            //ResponseEntity.ok(apiResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }


}
