package pl.javastart.equipy.assets;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/api/assets")
    public List<AssetDto> getAssets(@RequestParam(required = false) String text) {
        if (text ==null) {
            return assetService.getAllAssets();
        } else {
            return assetService.findAssetsWithNameOrSerialNumberContainingText(text);
        }

    }

}
