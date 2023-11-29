package pl.javastart.equipy.assets;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.assignments.AssignmentDto;

import java.net.URI;
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
            return assetService.getAssetsWithNameOrSerialNumberContainingText(text);
        }
    }

    @GetMapping("/api/assets/{id}")
    public ResponseEntity<AssetDto> getAssetById(@PathVariable Long id ) {
        return assetService.getAsset( id )
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/assets")
    public ResponseEntity<AssetDto> addAsset(@RequestBody AssetDto assetDtoToAdd) {
        if (assetDtoToAdd.getId()!=null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Zapisywany obiekt nie może mieć ustawionego id"
            );
        }
        AssetDto assetDtoAdded = assetService.addAsset( assetDtoToAdd );
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(assetDtoAdded.getId())
                .toUri();
        return ResponseEntity.created(location).body(assetDtoAdded);
    }

    @PutMapping("/api/assets/{id}")
    public ResponseEntity<AssetDto> updateAsset(@RequestBody AssetDto assetDtoToUpdate, @PathVariable Long id) {
        if (!id.equals(assetDtoToUpdate.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Aktualizowany obiekt musi mieć id zgodne z id w ścieżce zasobu"
            );
        }
        AssetDto assetUpdated = assetService.updateAsset( assetDtoToUpdate );
        return ResponseEntity.ok(assetUpdated);
    }


    @GetMapping("/api/assets/{assetId}/assignments")
    public List<AssignmentDto> getAssignmentsForAsset( @PathVariable Long assetId ) {
        return assetService.getAssignmentsForAssetId( assetId );
    }
}
