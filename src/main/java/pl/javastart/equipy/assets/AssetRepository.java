package pl.javastart.equipy.assets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    @Query( "SELECT a FROM Asset a "+
            "WHERE " +
                "lower(a.name) LIKE lower(concat('%',:textToFind,'%')) OR " +
                "lower(a.serialNumber) LIKE lower(concat('%',:textToFind,'%'))" )
    List<Asset> findByNameOrSerialNumber(@Param("textToFind") String textToFind);

    @Query( "SELECT a FROM Asset a WHERE (a.name) LIKE '%App%' OR (a.serialNumber) LIKE '%App%'" )
    List<Asset> findByNameOrSerialNumber2();

    Optional<Asset> findBySerialNumber(String serialNumber);

}
