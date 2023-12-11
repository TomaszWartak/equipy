package pl.javastart.equipy.assignments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class AssignmentController {

    private AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/api/assignments")
    public ResponseEntity<AssignmentDto> addAssignment( @RequestBody AssignmentDto assignmentDtoToAdd ) {
        AssignmentDto assignmentDtoAdded = null;
        try {
            assignmentDtoAdded = assignmentService.addAssignment( assignmentDtoToAdd );
        }
        catch (InvalidAssignmentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(assignmentDtoAdded.getId())
                .toUri();
        return ResponseEntity.created(location).body(assignmentDtoAdded);
    }
}
