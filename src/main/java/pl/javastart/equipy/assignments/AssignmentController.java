package pl.javastart.equipy.assignments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.assets.AssetMapper;

import java.net.URI;
import java.util.ArrayList;

@RestController
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/api/assignments")
    public ResponseEntity<AssignmentDto> addAssignment( @RequestBody AssignmentDto assignmentDtoToAdd ) {
        AssignmentDto assignmentDtoAdded = assignmentService.addAssignment( assignmentDtoToAdd );
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(assignmentDtoAdded.getId())
                .toUri();
        return ResponseEntity.created(location).body(assignmentDtoAdded);
    }
}
