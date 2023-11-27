package pl.javastart.equipy.assets;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.categories.CategoryRepository;

@Service
public class AssetMapper {

    private static CategoryRepository categoryRepository;

    private AssetMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public static AssetDto toAssetDto(Asset asset) {
        return AssetDto.builder()
                .id(asset.getId())
                .name( asset.getName() )
                .description( asset.getDescription() )
                .serialNumber( asset.getSerialNumber() )
                .categoryName( asset.getCategory()!=null ?asset.getCategory().getName() :null)
                .build();
    }
    public static Asset toAsset(AssetDto assetDto) {
        Asset asset = Asset.builder()
                .id( assetDto.getId() )
                .name( assetDto.getName() )
                .description( assetDto.getDescription() )
                .serialNumber( assetDto.getSerialNumber() )
                .build();
        categoryRepository.findByName( assetDto.getCategoryName() ).ifPresent(asset::setCategory);
        return asset;
    }
}
