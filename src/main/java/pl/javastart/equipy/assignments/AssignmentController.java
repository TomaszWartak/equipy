package pl.javastart.equipy.assignments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/api/users/{userId}/assignments")
    public ArrayList<Assignment> getAssignmentsForUserId(@PathVariable Long userId) {
        return assignmentService.getAssignmentsForUserId( userId );
    }
}
