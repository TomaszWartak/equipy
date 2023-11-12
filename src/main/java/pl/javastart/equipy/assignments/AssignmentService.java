package pl.javastart.equipy.assignments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.equipy.assets.AssetRepository;

import java.util.ArrayList;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public ArrayList<Assignment> getAssignmentsForUserId(Long userId ) {
        return (ArrayList<Assignment>) assignmentRepository.findByUserId( userId );
    }
}
