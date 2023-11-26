package pl.javastart.equipy.assignments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.equipy.users.UserRepository;
import pl.javastart.equipy.users.UserNotFoundException;

import java.util.ArrayList;

@Service
public class AssignmentService {

    private UserRepository userRepository;
    private AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentService(UserRepository userRepository, AssignmentRepository assignmentRepository) {
        this.userRepository = userRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public ArrayList<Assignment> getAssignmentsForUserId(Long userId ) {
        userRepository
                .findById( userId )
                .orElseThrow( UserNotFoundException::new );
        return (ArrayList<Assignment>) assignmentRepository.findByUserId( userId );
    }
}
