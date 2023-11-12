package pl.javastart.equipy.categories;

import org.modelmapper.ModelMapper;

public class CategoryMapper {
    private CategoryMapper() {
        throw new IllegalStateException("Utility class");
    }
    static CategoryDto toCategoryDto(Category category) {
        return new ModelMapper().map(category, CategoryDto.class );
    }
    static Category toCategory(CategoryDto categoryDto) {
        return new ModelMapper().map( categoryDto, Category.class );
    }
}
