package pl.javastart.equipy.assignments;

import org.modelmapper.ModelMapper;

public class AssignmentMapper {

    private AssignmentMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static AssignmentDto toAssignmentDto(Assignment assignment) {
        return new ModelMapper().map(assignment, AssignmentDto.class );
    }
    public static Assignment toAssignment(AssignmentDto assignmentDto) {
        return new ModelMapper().map( assignmentDto, Assignment.class );
    }
}
