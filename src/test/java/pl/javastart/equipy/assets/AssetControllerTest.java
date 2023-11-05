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
import pl.javastart.equipy.users.UserController;
import pl.javastart.equipy.users.UserService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AssetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AssetService assetService;

    private static ArrayList<Asset> assetsData = new ArrayList<>();
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
        assetsData.add(
            Asset.builder()
                .id(1L)
                .name("Asus MateBook D")
                .description("15 calowy laptop, i5, 8GB DDR3, kolor czarny")
                .serialNumber("ASMBD198723")
                .category("Laptopy")
                .build()
        );
        assetsData.add(
            Asset.builder()
                .id(2L)
                .name("Apple MacBook Pro 2015")
                .description("13 calowy laptop, i5, 16GB DDR3, SSD256GB, kolor srebrny")
                .serialNumber("MBP15X0925336")
                .category("Laptopy")
                .build()
        );
        assetsData.add(
            Asset.builder()
                .id(3L)
                .name("Audi A4 Avant")
                .description("Audi Kombi, 1.9TDI, kolor szampan")
                .serialNumber("VINDI3576XO526716")
                .category("Pojazdy")
                .build()
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
        String jsonAssetsData = objectMapper.writeValueAsString(assetsData);

        mockMvc = MockMvcBuilders.standaloneSetup( new AssetController(assetService) ).build();
        MvcResult result = mockMvc.perform(get("/api/assets"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonAssetsData))
                .andReturn();
    }


}