package pl.javastart.equipy.assignments;

import org.modelmapper.ModelMapper;

public class AssignmentMapper {

    private AssignmentMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static AssignmentDto toAssignmentDto( Assignment assignment ) {
        AssignmentDto dto = AssignmentDto.builder()
                .id( assignment.getId() )
                .start( assignment.getStart() )
                .end( assignment.getEnd())
                .userId( assignment.getUser().getId() )
                .assetId( assignment.getAsset().getId() )
                .build();
        return dto;
    }

    public static AssignmentForUserDto toAssignmentPerUserDto(Assignment assignment) {
        AssignmentForUserDto dto = AssignmentForUserDto.builder()
                .id( assignment.getId() )
                .start( assignment.getStart() )
                .end( assignment.getEnd())
                .userId( assignment.getUser().getId() )
                .assetId( assignment.getAsset().getId() )
                .assetName( assignment.getAsset().getName() )
                .assetSerialNumber( assignment.getAsset().getSerialNumber() )
                .build();
        return dto;
    }
    public static AssignmentForAssetDto toAssignmentPerAssetDto(Assignment assignment) {
        AssignmentForAssetDto dto = AssignmentForAssetDto.builder()
                .id( assignment.getId() )
                .start( assignment.getStart() )
                .end( assignment.getEnd())
                .assetId( assignment.getAsset().getId() )
                .userId( assignment.getUser().getId() )
                .firstName( assignment.getUser().getFirstName() )
                .lastName( assignment.getUser().getLastName() )
                .pesel( assignment.getUser().getPesel() )
                .build();
        return dto;
    }

    public static Assignment toAssignment(AssignmentDto assignmentDto) {
        return new ModelMapper().map(assignmentDto, Assignment.class );
    }

    // todo to trzeba przetestować
    public static Assignment toAssignment(AssignmentForUserDto assignmentForUserDto) {
        return new ModelMapper().map(assignmentForUserDto, Assignment.class );
    }

    // todo to trzeba przetestować
    public static Assignment toAssignment(AssignmentForAssetDto assignmentForAssetDto) {
        return new ModelMapper().map(assignmentForAssetDto, Assignment.class );
    }


}
