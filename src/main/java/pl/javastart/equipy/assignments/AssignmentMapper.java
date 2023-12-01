package pl.javastart.equipy.assignments;

import org.modelmapper.ModelMapper;
import pl.javastart.equipy.assets.Asset;

public class AssignmentMapper {

    private AssignmentMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static AssignmentPerUserDto toAssignmentPerUserDto(Assignment assignment) {
        // todo return new ModelMapper().map(assignment, AssignmentDto.class );
        AssignmentPerUserDto dto = AssignmentPerUserDto.builder()
                .id( assignment.getId() )
                .start( assignment.getStart() )
                .end( assignment.getStart())
                .userId( assignment.getUser().getId() )
                .assetId( assignment.getAsset().getId() )
                .assetName( assignment.getAsset().getName() )
                .assetSerialNumber( assignment.getAsset().getSerialNumber() )
                .build();
/* todo
        AssignmentPerUserDto dto = new AssignmentPerUserDto();
        dto.setId( assignment.getId() );
        dto.setStart( assignment.getStart() );
        dto.setEnd( assignment.getEnd() );
        dto.setUserId( assignment.getUser().getId() );
        Asset asset = assignment.getAsset();
        dto.setAssetId( asset.getId() );
        dto.setAssetName( asset.getName() );
        dto.setAssetSerialNumber( asset.getSerialNumber() );
*/
        return dto;
    }
    public static AssignmentPerAssetDto toAssignmentPerAssetDto(Assignment assignment) {
        // todo return new ModelMapper().map(assignment, AssignmentDto.class );
        AssignmentPerAssetDto dto = AssignmentPerAssetDto.builder()
                .id( assignment.getId() )
                .start( assignment.getStart() )
                .end( assignment.getStart())
                .assetId( assignment.getAsset().getId() )
                .userId( assignment.getUser().getId() )
                .firstName( assignment.getUser().getFirstName() )
                .lastName( assignment.getUser().getLastName() )
                .pesel( assignment.getUser().getPesel() )
                .build();
/* todo         AssignmentPerAssetDto dto = new AssignmentPerAssetDto();
        dto.setId( assignment.getId() );
        dto.setStart( assignment.getStart() );
        dto.setEnd( assignment.getEnd() );
        dto.setUserId( assignment.getUser().getId() );
        Asset asset = assignment.getAsset();
        dto.setAssetId( asset.getId() );
        dto.setAssetName( asset.getName() );
        dto.setAssetSerialNumber( asset.getSerialNumber() );*/
        return dto;
    }

    // todo to trzeba przetestować
    public static Assignment toAssignment(AssignmentPerUserDto assignmentPerUserDto) {
        return new ModelMapper().map(assignmentPerUserDto, Assignment.class );
    }

    // todo to trzeba przetestować
    public static Assignment toAssignment(AssignmentPerAssetDto assignmentPerAssetDto) {
        return new ModelMapper().map(assignmentPerAssetDto, Assignment.class );
    }


}
