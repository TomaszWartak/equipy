package pl.javastart.equipy.assets;

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
import pl.javastart.equipy.categories.Category;
import pl.javastart.equipy.categories.CategoryRepository;

import java.util.ArrayList;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AssetControllerTest {
    private static final Long NOTEBOOKS_ID = 1L;
    private static final Long VEHICLES_ID = 2L;
    private static final Long PHONES_ID = 3L;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AssetService assetService;
    private static ArrayList<AssetDto> assetsDtoData = new ArrayList<>();
    private static HashMap<String,Category> categoriesData = new HashMap<>();

    /*private String jsonResponseForGetAll = "[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"Asus MateBook D\",\n" +
            "        \"description\": \"15 calowy laptop, i5, 8GB DDR3, kolor czarny\",\n" +
            "        \"serialNumber\": \"ASMBD198723\",\n" +
            "        \"category\": \"Laptopy\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 2,\n" +
            "        \"name\": \"Apple MacBook Pro 2015\",\n" +
            "        \"description\": \"13 calowy laptop, i5, 16GB DDR3, SSD256GB, kolor srebrny\",\n" +
            "        \"serialNumber\": \"MBP15X0925336\",\n" +
            "        \"category\": \"Laptopy\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 3,\n" +
            "        \"name\": \"Dell Inspirion 3576\",\n" +
            "        \"description\": \"13 calowy laptop, i7, 8GB DDR4, SSD 512GB, kolor czarny\",\n" +
            "        \"serialNumber\": \"DI3576XO526716\",\n" +
            "        \"category\": \"Pojazdy\"\n" +
            "    }\n" +
            "]";
*/
    @BeforeAll
    static void prepareExpectedData(){
        prepareCategoriesData();
        prepareAssetsDtoData();
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
        );}

    private static void prepareAssetsDtoData() {
        assetsDtoData.add(
            AssetMapper.toAssetDto(
                Asset.builder()
                    .id(1L)
                    .name("Asus MateBook D")
                    .description("15 calowy laptop, i5, 8GB DDR3, kolor czarny")
                    .serialNumber("ASMBD198723")
                    .category(categoriesData.get("Laptopy"))
                    .build()
            )
        );
        assetsDtoData.add(
            AssetMapper.toAssetDto(
                Asset.builder()
                    .id(2L)
                    .name("Apple MacBook Pro 2015")
                    .description("13 calowy laptop, i5, 16GB DDR3, SSD256GB, kolor srebrny")
                    .serialNumber("MBP15X0925336")
                    .category(categoriesData.get("Laptopy"))
                    .build()
            )
        );
        assetsDtoData.add(
            AssetMapper.toAssetDto(
                Asset.builder()
                    .id(3L)
                    .name("Audi A4 Avant")
                    .description("Audi Kombi, 1.9TDI, kolor szampan")
                    .serialNumber("VINDI3576XO526716")
                    .category(categoriesData.get("Pojazdy"))
                    .build()
            )
        );
        assetsDtoData.add(
            AssetMapper.toAssetDto(
                Asset.builder()
                    .id(4L)
                    .name("Apple iPhone X")
                    .description("Telefon z zestawem słuchawkowym lightning i ładowarką")
                    .serialNumber("APLX17287GHX21")
                    .category(categoriesData.get("Telefony"))
                    .build()
            )
        );
        assetsDtoData.add(
            AssetMapper.toAssetDto(
                Asset.builder()
                    .id(5L)
                    .name("Apple iPhone 8")
                    .description("Telefon z zestawem słuchawkowym lightning i ładowarką")
                    .serialNumber("APL8185652HGT7")
                    .category(categoriesData.get("Telefony"))
                    .build()
            )
        );
    }

    @Test
    void getAssets__should_return_200() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new AssetController(assetService) ).build();
        MvcResult result = mockMvc.perform(get("/api/assets"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getAssets__should_return_all_assets_data__if_data_are_in_db() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonAssetsData = objectMapper.writeValueAsString(assetsDtoData);

        mockMvc = MockMvcBuilders.standaloneSetup( new AssetController(assetService) ).build();
        MvcResult result = mockMvc.perform(get("/api/assets"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonAssetsData))
                .andReturn();
    }


}