package pl.javastart.equipy.assignments;

import org.modelmapper.ModelMapper;
import pl.javastart.equipy.assets.Asset;

public class AssignmentMapper {

    private AssignmentMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static AssignmentDto toAssignmentDto(Assignment assignment) {
        // todo return new ModelMapper().map(assignment, AssignmentDto.class );
        AssignmentDto dto = new AssignmentDto();
        dto.setId( assignment.getId() );
        dto.setStart( assignment.getStart() );
        dto.setEnd( assignment.getEnd() );
        dto.setUserId( assignment.getUser().getId() );
        Asset asset = assignment.getAsset();
        dto.setAssetId( asset.getId() );
        dto.setAssetName( asset.getName() );
        dto.setAssetSerialNumber( asset.getSerialNumber() );
        return dto;
    }
    public static Assignment toAssignment(AssignmentDto assignmentDto) {
        return new ModelMapper().map( assignmentDto, Assignment.class );
    }

}
