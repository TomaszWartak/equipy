package pl.javastart.equipy.assets;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Asset {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String description;
    private String serialNumber;
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descroption) {
        this.description = descroption;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset = (Asset) o;
        return Objects.equals(id, asset.id) && Objects.equals(name, asset.name) && Objects.equals(description, asset.description) && Objects.equals(serialNumber, asset.serialNumber) && Objects.equals(category, asset.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, serialNumber, category);
    }

    public static class AssetBuilder {
        private Long id;
        private String name;
        private String description;
        private String serialNumber;
        private String category;

        public AssetBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AssetBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AssetBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AssetBuilder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public AssetBuilder category(String category) {
            this.category = category;
            return this;
        }

        public Asset build() {
            Asset asset = new Asset();
            asset.id = this.id;
            asset.name = this.name;
            asset.description = this.description;
            asset.serialNumber = this.serialNumber;
            asset.category = this.category;
            return asset;
        }
    }

    public static AssetBuilder builder() {
        return new AssetBuilder();
    }

}
