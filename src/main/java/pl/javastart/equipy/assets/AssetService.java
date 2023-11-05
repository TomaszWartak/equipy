package pl.javastart.equipy.assets;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public ArrayList<AssetDto> getAllAssets() {
        return (ArrayList<AssetDto>) assetRepository.findAll()
                .stream()
                .map(AssetMapper::toAssetDto)
                .collect(Collectors.toList());
    }

    public ArrayList<AssetDto> findAssetsWithNameOrSerialNumberContainingText( String textToFind ) {
        return (ArrayList<AssetDto>) assetRepository.findByNameOrSerialNumber(textToFind)
                .stream()
                .map(AssetMapper::toAssetDto)
                .collect(Collectors.toList());
    }

}
