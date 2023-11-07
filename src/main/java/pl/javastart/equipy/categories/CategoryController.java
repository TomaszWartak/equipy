package pl.javastart.equipy.categories;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/categories")
    public List<CategoryDto> getCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/api/categories/names")
    public List<String> getCategoryNames() {
        return categoryService.getAllCategories()
                .stream()
                .map( categoryDto -> categoryDto.getName() )
                .collect(Collectors.toList());
    }

}
