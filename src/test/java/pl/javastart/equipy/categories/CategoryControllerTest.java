package pl.javastart.equipy.categories;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CategoryControllerTest {

    private static final Long NOTEBOOKS_ID = 1L;
    private static final Long VEHICLES_ID = 2L;
    private static final Long PHONES_ID = 3L;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryService categoryService;
    private static ArrayList<CategoryDto> categoriesDtoData = new ArrayList<>();
    private static HashMap<String,Category> categoriesData = new HashMap<>();

    @BeforeAll
    static void prepareExpectedData(){
        prepareCategoriesData();
        prepareCategoriesDtoData();
    }

    private static void prepareCategoriesData() {
        categoriesData.put(
                "Laptopy",
                Category.builder()
                        .id(NOTEBOOKS_ID)
                        .name("Laptopy")
                        .description("Laptopy, notebooki itd")
                        .build()
        );
        categoriesData.put(
                "Pojazdy",
                Category.builder()
                        .id(VEHICLES_ID)
                        .name("Pojazdy")
                        .description("Samochody, samoloty, pociągi")
                        .build()
        );
        categoriesData.put(
                "Telefony",
                Category.builder()
                        .id(PHONES_ID)
                        .name("Telefony")
                        .description("Telefony komórkowe")
                        .build()
        );
    }

    private static void prepareCategoriesDtoData() {
        for (Category category: categoriesData.values()) {
            categoriesDtoData.add( CategoryMapper.toCategoryDto(category));
        }
    }

    @Test
    void getCategories__should_return_200() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new CategoryController(categoryService) ).build();
        MvcResult result = mockMvc
                .perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getCategories__should_return_all_categories_data__if_data_are_in_db() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCategoriesData = objectMapper.writeValueAsString(categoriesDtoData);

        mockMvc = MockMvcBuilders.standaloneSetup( new CategoryController(categoryService) ).build();
        MvcResult MvcResult = mockMvc
                .perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonCategoriesData))
                .andReturn();
    }

    @Test
    void getCategoriesNames__should_return_all_categories_names__if_data_are_in_db() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCategoriesNamesData = objectMapper.writeValueAsString(categoriesData.keySet());

        mockMvc = MockMvcBuilders.standaloneSetup( new CategoryController(categoryService) ).build();
        MvcResult MvcResult = mockMvc
                .perform(get("/api/categories/names"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonCategoriesNamesData))
                .andReturn();
    }

}
