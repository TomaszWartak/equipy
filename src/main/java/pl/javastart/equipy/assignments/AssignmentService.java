package pl.javastart.equipy.assignments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.equipy.users.UserRepository;
import pl.javastart.equipy.utils.DateTimeProvider;

@Service
public class AssignmentService {

    private UserRepository userRepository;
    private AssignmentRepository assignmentRepository;
    private DateTimeProvider dateTimeProvider;

    @Autowired
    public AssignmentService(
            UserRepository userRepository,
            AssignmentRepository assignmentRepository,
            DateTimeProvider dateTimeProvider ) {
        this.userRepository = userRepository;
        this.assignmentRepository = assignmentRepository;
        this.dateTimeProvider = dateTimeProvider;
    }

    public AssignmentDto addAssignment( AssignmentDto assignmentDtoToAdd ) {
        Assignment assignmentToAdd = AssignmentMapper.toAssignment( assignmentDtoToAdd );
        assignmentToAdd.setStart(dateTimeProvider.currentLocalDateTime() );
        return AssignmentMapper.toAssignmentDto( assignmentRepository.save( assignmentToAdd ));
    }
}
