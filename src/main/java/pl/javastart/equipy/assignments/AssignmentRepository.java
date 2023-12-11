package pl.javastart.equipy.assignments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Long> {

    Optional<Assignment> findByIdAndEndIsNotNull( Long assetId );
}
