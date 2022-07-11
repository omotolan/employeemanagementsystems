package africa.semicolon.employeemanagementsystems.controller;

import africa.semicolon.employeemanagementsystems.dto.ApiResponse;
import africa.semicolon.employeemanagementsystems.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/department/services")
public class DepartmentServiceController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public ResponseEntity<?> getAllDepartments() {
        var serviceResponse = departmentService.listAllDepartments();
        ApiResponse response = new ApiResponse(true, serviceResponse);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
