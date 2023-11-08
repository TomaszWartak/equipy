package pl.javastart.equipy.assets;

public class AssetDto {

    private Long id;
    private String name;
    private String description;
    private String serialNumber;
    private String categoryName;

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public static class AssetDtoBuilder {
        private Long id;
        private String name;
        private String description;
        private String serialNumber;
        private String categoryName;

        public AssetDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AssetDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AssetDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AssetDtoBuilder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public AssetDtoBuilder categoryName(String category) {
            this.categoryName = category;
            return this;
        }

        public AssetDto build() {
            AssetDto assetDto = new AssetDto();
            assetDto.id = this.id;
            assetDto.name = this.name;
            assetDto.description = this.description;
            assetDto.serialNumber = this.serialNumber;
            assetDto.categoryName = this.categoryName;
            return assetDto;
        }
    }

    public static AssetDtoBuilder builder() {
        return new AssetDtoBuilder();
    }
    
}
