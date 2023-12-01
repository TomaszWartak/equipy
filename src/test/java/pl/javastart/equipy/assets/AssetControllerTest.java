package pl.javastart.equipy.assets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.javastart.equipy.TestHelperData;
import pl.javastart.equipy.assignments.AssignmentPerAssetDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AssetControllerTest {
/*  todo   private static final Long NOTEBOOKS_ID = 1L;
    private static final Long VEHICLES_ID = 2L;
    private static final Long PHONES_ID = 3L;*/

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AssetService assetService;
/*  todo  private ArrayList<AssetDto> assetsDtoData;
    private HashMap<String,Category> categoriesData;*/
    private static TestHelperData testHelperData;

    @BeforeAll
    static void prepareTestHelperData() {
        testHelperData = TestHelperData.getInstance();
    }

    @BeforeEach
    void prepareExpectedData(){
        testHelperData.prepareUsersDtoData();
        testHelperData.prepareAssetsDtoData();
        testHelperData.prepareAssignmentsDtoData();
    }

/* todo
    private void prepareCategoriesData() {
        categoriesData = new HashMap<>();
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

    private void prepareAssetsDtoData() {
        assetsDtoData = new ArrayList<>();
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
*/

    @Test
    void getAssets__should_return_200() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new AssetController(assetService) ).build();
        MvcResult result = mockMvc
                .perform(get("/api/assets"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getAssets__should_return_all_assets_data__if_data_are_in_db() throws Exception {
        List<AssetDto> allAssetDtos = testHelperData.getAssetDtosData()
                .values()
                .stream()
                .collect(Collectors.toList());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonAssetsData = objectMapper.writeValueAsString(allAssetDtos);

        mockMvc = MockMvcBuilders.standaloneSetup( new AssetController(assetService) ).build();
        MvcResult result = mockMvc
                .perform(get("/api/assets"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonAssetsData))
                .andReturn();
    }

    @ParameterizedTest
    @ValueSource( strings = {"App", "app"})
    void getAssets__should_return_three_json_data__when_request_is_filtered_phrase_ignore_case( String textToSearch ) throws Exception {
        ArrayList<AssetDto> filteredAssets = new ArrayList<>();
        filteredAssets.add(testHelperData.getAssetDtosData().get(2L));
        filteredAssets.add(testHelperData.getAssetDtosData().get(4L));
        filteredAssets.add(testHelperData.getAssetDtosData().get(5L));
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonAssetsData = objectMapper.writeValueAsString(filteredAssets);

        mockMvc = MockMvcBuilders.standaloneSetup( new AssetController(assetService) ).build();
        MvcResult result = mockMvc
                .perform(get("/api/assets?text="+textToSearch ))
                .andExpect(content().json(jsonAssetsData))
                .andReturn();
    }

    @Test
    void addAsset__should_return_201_and_added_asset_json_data__after_data_are_saved() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AssetDto assetDto = AssetDto.builder()
                .name("Volkswagen Polo")
                .description("Polo 4d 1.9TDI")
                .serialNumber("VINWXC98909809")
                .categoryName("Pojazdy")
                .build();
        String postRequestAssetJsonData = objectMapper.writeValueAsString(assetDto);
        assetDto.setId(6L);
        String jsonResponse = objectMapper.writeValueAsString(assetDto);
        MvcResult result = mockMvc
                .perform(
                        post( "/api/assets")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content( postRequestAssetJsonData )
                )
                .andExpect(status().is(201))
                .andExpect(content().json(jsonResponse))
                .andReturn();
    }

    @Test
    void addAsset__should_return_409__when_asset_serial_number_exists_in_db() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AssetDto assetDto = AssetDto.builder()
                .name("Volkswagen Polo")
                .description("Polo 4d 1.9TDI")
                .serialNumber("VINDI3576XO526716")
                .categoryName("Pojazdy")
                .build();
        String postRequestAssetJsonData = objectMapper.writeValueAsString(assetDto);
        MvcResult result = mockMvc
                .perform(
                        post( "/api/assets")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content( postRequestAssetJsonData )
                )
                .andExpect(status().is(409))
                .andReturn();
    }

    @Test
    void addAsset__should_return_400__if_id_is_given_in_asset_data() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AssetDto assetDto = AssetDto.builder()
                .id(6L)
                .name("Volkswagen Polo")
                .description("Polo 4d 1.9TDI")
                .serialNumber("VINWXC98909809")
                .categoryName("Pojazdy")
                .build();
        String postRequestAssetJsonData = objectMapper.writeValueAsString(assetDto);
        MvcResult result = mockMvc
                .perform(
                        post( "/api/assets")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content( postRequestAssetJsonData )
                )
                .andExpect(status().is(400))
                .andReturn();
    }

    @Test
    void getAsset__should_return_200_and_Json_asset_data__if_given_id_exists_in_db() throws Exception{
        AssetDto assetDto = testHelperData.getAssetDtosData().get(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String getResultAssetJsonData = objectMapper.writeValueAsString( assetDto );
        MvcResult result = mockMvc
                .perform( get("/api/assets/"+assetDto.getId() ) )
                .andExpect(status().isOk())
                .andExpect(content().json(getResultAssetJsonData))
                .andReturn();
    }
    @ParameterizedTest
    @ValueSource( longs = {0L, 6L})
    void getAsset__should_return_404__if_given_id_doesnt_exist_in_db( Long id ) throws Exception{
        MvcResult result = mockMvc
                .perform( get("/api/assets/"+id ) )
                .andExpect( status().isNotFound() )
                .andReturn();
    }

    @Test
    void updateAsset__should_return_200_and_Json_asset_data__when_data_saving_was_successful() throws Exception{
        AssetDto assetDto = testHelperData.getAssetDtosData().get(3L);
        assetDto.setName( assetDto.getName()+" 4x4");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(assetDto);
        String jsonResponse = jsonRequest;

        MvcResult result = mockMvc
                .perform( put("/api/assets/"+assetDto.getId() )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( jsonRequest )
                )
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse))
                .andReturn();
    }


    @Test
    void updateAsset__should_return_400__if_asset_id_is_different_from_id_given_in_url() throws Exception{
        String jsonRequest = new ObjectMapper().writeValueAsString(testHelperData.getAssetDtosData().get(1L));

        MvcResult result = mockMvc
                .perform( put("/api/assets/3" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( jsonRequest )
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void updateAsset__should_return_400__if_asset_id_is_null() throws Exception{
        AssetDto assetDtoToSave = testHelperData.getAssetDtosData().get(1L);
        assetDtoToSave.setId(null);
        String jsonRequest = new ObjectMapper().writeValueAsString(assetDtoToSave);

        MvcResult result = mockMvc
                .perform( put("/api/assets/3" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( jsonRequest )
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @ParameterizedTest
    @ValueSource( longs = {0L, -1L, -1000L})
    void updateAsset__should_return_400__if_asset_id_is_less_then_1( Long assetId ) throws Exception{
        AssetDto assetDtoToSave = testHelperData.getAssetDtosData().get(1L);
        assetDtoToSave.setId(assetId);
        String jsonRequest = new ObjectMapper().writeValueAsString(assetDtoToSave);

        MvcResult result = mockMvc
                .perform( put("/api/assets/3" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( jsonRequest )
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void updateAsset__should_return_409__if_asset_serial_number_exists_in_other_asset_in_db( ) throws Exception{
        AssetDto assetDtoToSave = testHelperData.getAssetDtosData().get(1L);
        AssetDto otherAsset = testHelperData.getAssetDtosData().get(2L);
        assetDtoToSave.setSerialNumber( otherAsset.getSerialNumber() );
        String jsonRequest = new ObjectMapper().writeValueAsString(assetDtoToSave);

        MvcResult result = mockMvc
                .perform( put("/api/assets/1" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( jsonRequest )
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    @ParameterizedTest
    @ValueSource( longs = {1L, 3L})
    void getAssignmentsForAsset__should_return_all_asset_assignments_data__if_data_are_in_db( Long assetId ) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<AssignmentPerAssetDto> assignmentPerAssetDtos = testHelperData.getAssignmentsPerAssetDtoData()
                .values()
                .stream()
                .filter( assignmentDto -> assignmentDto.getAssetId().equals(assetId) )
                .collect(Collectors.toList());
        String jsonAssetsData = objectMapper.writeValueAsString(assignmentPerAssetDtos);

        mockMvc = MockMvcBuilders.standaloneSetup( new AssetController(assetService) ).build();
        MvcResult result = mockMvc
                .perform(get("/api/assets/"+assetId+"/assignments"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonAssetsData))
                .andReturn();
    }
}