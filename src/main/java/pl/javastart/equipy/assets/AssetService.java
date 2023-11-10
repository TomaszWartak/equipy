package pl.javastart.equipy.assets;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public AssetDto saveAsset( AssetDto assetDto ) {
        Optional<Asset> assetWrapped = assetRepository.findBySerialNumber( assetDto.getSerialNumber() );
        assetWrapped.ifPresent(
            aW -> {throw new DuplicateSerialNumberException();}
        );
        return AssetMapper.toAssetDto( assetRepository.save( AssetMapper.toAsset( assetDto )));
    }

}
