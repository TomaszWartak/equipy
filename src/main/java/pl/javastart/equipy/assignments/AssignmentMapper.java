package pl.javastart.equipy.assignments;

import org.modelmapper.ModelMapper;

public class AssignmentMapper {

    private AssignmentMapper() {
        throw new IllegalStateException("Utility class");
    }
    static AssignmentDto toAssignmentDto(Assignment assignment) {
        return new ModelMapper().map(assignment, AssignmentDto.class );
    }
    static Assignment toAssignment(AssignmentDto assignmentDto) {
        return new ModelMapper().map( assignmentDto, Assignment.class );
    }
}
