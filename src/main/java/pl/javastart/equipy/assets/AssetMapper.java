package pl.javastart.equipy.assets;

import org.modelmapper.ModelMapper;
import pl.javastart.equipy.users.User;
import pl.javastart.equipy.users.UserDto;

public class AssetMapper {
    static AssetDto toAssetDto(Asset asset) {
        return new ModelMapper().map(asset, AssetDto.class );
    }
    static Asset toUser(UserDto assetDto) {
        return new ModelMapper().map( assetDto, Asset.class );
    }
}
