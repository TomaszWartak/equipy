package pl.javastart.equipy;

import pl.javastart.equipy.assets.Asset;
import pl.javastart.equipy.assets.AssetDto;
import pl.javastart.equipy.assets.AssetMapper;
import pl.javastart.equipy.assignments.Assignment;
import pl.javastart.equipy.assignments.AssignmentDto;
import pl.javastart.equipy.assignments.AssignmentMapper;
import pl.javastart.equipy.categories.Category;
import pl.javastart.equipy.users.UserDto;
import pl.javastart.equipy.users.UserMapper;

import java.time.LocalDateTime;
import java.util.HashMap;

public class TestHelperData {

    private static final Long NOTEBOOKS_CATEGORY_ID = 1L;
    private static final Long VEHICLES_CATEGORY_ID = 2L;
    private static final Long PHONES_CATEGORY_ID = 3L;
    private static TestHelperData instance;
    private HashMap<Long, UserDto> usersDtoData;
    private HashMap<Long, AssetDto> assetsDtoData;
    private HashMap<String, Category> categoriesData;
    private HashMap<Long, AssignmentDto> assignmentsDtoData;

    private TestHelperData() {

    }

    public static TestHelperData getInstance() {
        if (instance==null) {
            instance = new TestHelperData();
        }
        return instance;
    }

    public void prepareUsersDtoData() {
        usersDtoData = new HashMap<>();
        usersDtoData.put(
                1L,
                UserDto.builder()
                        .id(1L)
                        .firstName("Jan")
                        .lastName("Kowalski")
                        .pesel("0123456789")
                        .build()
        );
        usersDtoData.put(
                2L,
                UserDto.builder()
                        .id(2L)
                        .firstName("Paweł")
                        .lastName("Zawiał")
                        .pesel("1234567890")
                        .build()
        );
        usersDtoData.put(
                3L,
                UserDto.builder()
                        .id(3L)
                        .firstName("Marta")
                        .lastName("Babiak")
                        .pesel("2345678901")
                        .build()
        );
        usersDtoData.put(
                4L,
                UserDto.builder()
                        .id(4L)
                        .firstName("Karolina")
                        .lastName("Modejska")
                        .pesel("3456789012")
                        .build()
        );
        usersDtoData.put(
                5L,
                UserDto.builder()
                        .id(5L)
                        .firstName("Piotr")
                        .lastName("Pawelski")
                        .pesel("4567890123")
                        .build()
        );
    }
    public void prepareCategoriesData() {
        categoriesData = new HashMap<>();
        categoriesData.put(
                "Laptopy",
                Category.builder()
                        .id(NOTEBOOKS_CATEGORY_ID)
                        .name("Laptopy")
                        .description("Laptopy, notebooki itd")
                        .build()
        );
        categoriesData.put(
                "Pojazdy",
                Category.builder()
                        .id(VEHICLES_CATEGORY_ID)
                        .name("Pojazdy")
                        .description("Samochody, samoloty, pociągi")
                        .build()
        );
        categoriesData.put(
                "Telefony",
                Category.builder()
                        .id(PHONES_CATEGORY_ID)
                        .name("Telefony")
                        .description("Telefony komórkowe")
                        .build()
        );}

    public void prepareAssetsDtoData() {
        prepareCategoriesData();
        assetsDtoData = new HashMap<>();
        assetsDtoData.put(
                1L,
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
        assetsDtoData.put(
                2L,
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
        assetsDtoData.put(
                3L,
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
        assetsDtoData.put(
                4L,
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
        assetsDtoData.put(
                5L,
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

    public void prepareAssignmentsDtoData() {
        assignmentsDtoData = new HashMap<>();
        assignmentsDtoData.put(
                1L,
                AssignmentMapper.toAssignmentDto(
                        Assignment.builder()
                                .id(1L)
                                .start( LocalDateTime.of(
                                        2023, 10, 18,
                                        15, 0, 0, 0 )
                                )
                                .end( LocalDateTime.of(
                                        2023, 10, 29,
                                        16, 0, 0, 0)
                                )
                                .user( UserMapper.toUser( usersDtoData.get(3L) ) )
                                .asset( AssetMapper.toAsset(assetsDtoData.get(1L) ) )
                                .build()
                )
        );
        assignmentsDtoData.put(
                2L,
                AssignmentMapper.toAssignmentDto(
                        Assignment.builder()
                                .id(2L)
                                .start( LocalDateTime.of(
                                        2023, 10, 18,
                                        15, 0, 0, 0)
                                )
                                .user( UserMapper.toUser( usersDtoData.get(3L) ) )
                                .asset( AssetMapper.toAsset(assetsDtoData.get(3L) ) )
                                .build()
                )
        );
        assignmentsDtoData.put(
                3L,
                AssignmentMapper.toAssignmentDto(
                        Assignment.builder()
                                .id(3L)
                                .start( LocalDateTime.of(
                                        2023, 10, 30,
                                        7, 0, 0, 0)
                                )
                                .user( UserMapper.toUser( usersDtoData.get(3L) ) )
                                .asset( AssetMapper.toAsset(assetsDtoData.get(2L) ) )
                                .build()
                )
        );
        assignmentsDtoData.put(
                4L,
                AssignmentMapper.toAssignmentDto(
                        Assignment.builder()
                                .id(4L)
                                .start( LocalDateTime.of(
                                        2023, 10, 18,
                                        15, 0, 0, 0)
                                )
                                .user( UserMapper.toUser( usersDtoData.get(3L) ) )
                                .asset( AssetMapper.toAsset(assetsDtoData.get(4L) ) )
                                .build()
                )
        );
        assignmentsDtoData.put(
                5L,
                AssignmentMapper.toAssignmentDto(
                        Assignment.builder()
                                .id(5L)
                                .start( LocalDateTime.of(
                                        2023, 10, 1,
                                        7, 0, 0, 0)
                                )
                                .end( LocalDateTime.of(
                                        2023, 10, 29,
                                        16, 0, 0, 0)
                                )
                                .user( UserMapper.toUser( usersDtoData.get(1L) ) )
                                .asset( AssetMapper.toAsset(assetsDtoData.get(2L) ) )
                                .build()
                )
        );
        assignmentsDtoData.put(
                6L,
                AssignmentMapper.toAssignmentDto(
                        Assignment.builder()
                                .id(6L)
                                .start( LocalDateTime.of(
                                        2023, 10, 30,
                                        15, 0, 0, 0)
                                )
                                .user( UserMapper.toUser( usersDtoData.get(1L) ) )
                                .asset( AssetMapper.toAsset(assetsDtoData.get(1L) ) )
                                .build()
                )
        );
        assignmentsDtoData.put(
                7L,
                AssignmentMapper.toAssignmentDto(
                        Assignment.builder()
                                .id(7L)
                                .start( LocalDateTime.of(
                                        2023, 10, 1,
                                        15, 0, 0, 0)
                                )
                                .user( UserMapper.toUser( usersDtoData.get(1L) ) )
                                .asset( AssetMapper.toAsset(assetsDtoData.get(5L) ) )
                                .build()
                )
        );
    }

    public HashMap<Long, UserDto> getUserDtosData() {
        return usersDtoData;
    }
    public HashMap<String, Category> getCategoriesData() {
        return categoriesData;
    }
    public HashMap<Long, AssetDto> getAssetDtosData() {
        return assetsDtoData;
    }
    public HashMap<Long, AssignmentDto> getAssignmentsDtoData() {
        return assignmentsDtoData;
    }
}
