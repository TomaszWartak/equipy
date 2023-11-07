package pl.javastart.equipy.assets;

import org.modelmapper.ModelMapper;

public class AssetMapper {
    static AssetDto toAssetDto(Asset asset) {
        return new ModelMapper().map(asset, AssetDto.class );
    }
    static Asset toAsset(AssetDto assetDto) {
        return new ModelMapper().map( assetDto, Asset.class );
    }
}
