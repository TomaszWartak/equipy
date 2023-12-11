package pl.javastart.equipy.assignments;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.equipy.assets.Asset;
import pl.javastart.equipy.assets.AssetRepository;
import pl.javastart.equipy.users.User;
import pl.javastart.equipy.users.UserRepository;
import pl.javastart.equipy.utils.DateTimeProvider;

import java.util.Optional;

@Service
public class AssignmentService {

    private AssignmentRepository assignmentRepository;
    private UserRepository userRepository;
    private AssetRepository assetRepository;
    private DateTimeProvider dateTimeProvider;

    @Autowired
    public AssignmentService(
            UserRepository userRepository,
            AssetRepository assetRepository,
            AssignmentRepository assignmentRepository,
            DateTimeProvider dateTimeProvider) {
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
        this.assignmentRepository = assignmentRepository;
        this.dateTimeProvider = dateTimeProvider;
    }

    @Transactional
    public AssignmentDto addAssignment( AssignmentDto assignmentDtoToAdd ) {
        Long userId = assignmentDtoToAdd.getUserId();
        if ( userId==null ) {
            throw new InvalidAssignmentException("Nie podano id użytkownika");
        }
        if ( userNotFound( userId ) ) {
            throw new InvalidAssignmentException("Brak użytkownika z id: "+ userId);
        }

        Long assetId = assignmentDtoToAdd.getAssetId();
        if ( assetId==null ) {
            throw new InvalidAssignmentException("Nie podano id wyposażenia");
        }
        if ( assetNotFound( assetId ) ) {
            throw new InvalidAssignmentException("Brak wyposażenia z id: "+ userId);
        }

        Optional<Assignment> assignmentAssignedWrapped = assignmentRepository.findByIdAndEndIsNotNull( assetId );
        if (assignmentAssignedWrapped.isPresent()) {
            throw new InvalidAssignmentException("Wyposażenie jest już przyporządkowane do użytkownika: "+ userId);
        }

        Assignment assignmentToAdd = AssignmentMapper.toAssignment( assignmentDtoToAdd );
        assignmentToAdd.setStart( dateTimeProvider.currentLocalDateTime() );
        return AssignmentMapper.toAssignmentDto( assignmentRepository.save( assignmentToAdd ));
    }

    private boolean userNotFound( Long userId ) {
        Optional<User> userWrapped = userRepository.findById( userId );
        return userWrapped.isEmpty();
    }

    private boolean assetNotFound( Long assetId ) {
        Optional<Asset> assetWrapped = assetRepository.findById( assetId );
        return assetWrapped.isEmpty();
    }

}
