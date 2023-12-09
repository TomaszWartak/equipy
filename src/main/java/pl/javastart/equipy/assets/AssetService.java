package pl.javastart.equipy.assets;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.javastart.equipy.assignments.AssignmentForAssetDto;
import pl.javastart.equipy.assignments.AssignmentMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public AssetDto addAsset( AssetDto assetDtoToSave ) {
        Optional<Asset> assetWrapped = assetRepository.findBySerialNumber( assetDtoToSave.getSerialNumber() );
        assetWrapped.ifPresent(
                aW -> {throw new DuplicateSerialNumberException( );}
        );
        return AssetMapper.toAssetDto( assetRepository.save( AssetMapper.toAsset( assetDtoToSave )));
    }

    public ArrayList<AssetDto> getAllAssets() {
        return (ArrayList<AssetDto>) assetRepository.findAll()
                .stream()
                .map(AssetMapper::toAssetDto)
                .collect(Collectors.toList());
    }

    public Optional<AssetDto> getAsset( Long id) {
        return assetRepository.findById( id ).map( AssetMapper::toAssetDto );
    }

    public ArrayList<AssetDto> getAssetsWithNameOrSerialNumberContainingText(String textToFind ) {
        return (ArrayList<AssetDto>) assetRepository.findByNameOrSerialNumber(textToFind)
                .stream()
                .map(AssetMapper::toAssetDto)
                .collect(Collectors.toList());
    }

    public AssetDto updateAsset(AssetDto assetDtoToUpdate ) {
        Optional<Asset> assetFromDbWrapped = assetRepository.findBySerialNumber( assetDtoToUpdate.getSerialNumber() );
        assetFromDbWrapped.ifPresent(
                aW -> {
                    if (assetsHaveDifferentIds( aW, assetDtoToUpdate )) throw new DuplicateSerialNumberException( );
                }
        );
        return AssetMapper.toAssetDto( assetRepository.save( AssetMapper.toAsset( assetDtoToUpdate )));
    }

    private boolean assetsHaveDifferentIds(Asset aW, AssetDto assetDtoToUpdate ) {
        return !(aW.getId().equals(assetDtoToUpdate.getId()));
    }


    @Transactional
    public List<AssignmentForAssetDto> getAssignmentsForAssetId(Long assetId) {
        Optional<Asset> assetFoundWrapped = assetRepository.findById( assetId );
        return /*(ArrayList<AssignmentDto>)*/ assetFoundWrapped
                .map( Asset::getAssignments )
                .orElseThrow( AssetNotFoundException::new )
                .stream()
                .map( AssignmentMapper::toAssignmentPerAssetDto )
                .collect( Collectors.toList() );
    }
}
