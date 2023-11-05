package pl.javastart.equipy.assets;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public ArrayList<Asset> getAllAssets() {
        return (ArrayList<Asset>) assetRepository.findAll();
    }
}
