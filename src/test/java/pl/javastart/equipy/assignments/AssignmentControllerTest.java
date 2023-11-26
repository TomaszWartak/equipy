package pl.javastart.equipy.assignments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AssignmentControllerTest{
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AssignmentService assignmentService;
    private HashMap<Long,AssignmentDto> assignmentsDtoData;

    @BeforeEach
    void prepareExpectedData(){
        prepareAssignmentsDtoData();
    }

    private void prepareAssignmentsDtoData() {
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
                    .userId(3L)
                    .assetId(1L)
                    .assetName("Asus MateBook D")
                    .assetSerialNumber("ASMBD198723")
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
                    .userId(3L)
                    .assetId(3L)
                    .assetName("Audi A4 Avant")
                    .assetSerialNumber("VINDI3576XO526716")
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
                    .userId(3L)
                    .assetId(2L)
                    .assetName("Apple MacBook Pro 2015")
                    .assetSerialNumber("MBP15X0925336")
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
                    .userId(3L)
                    .assetId(4L)
                    .assetName("Apple iPhone X")
                    .assetSerialNumber("APLX17287GHX21")
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
                    .userId(1L)
                    .assetId(2L)
                    .assetName("Apple MacBook Pro 2015")
                    .assetSerialNumber("MBP15X0925336")
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
                        .userId(1L)
                        .assetId(1L)
                        .assetName("Asus MateBook D")
                        .assetSerialNumber("ASMBD198723")
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
                        .userId(1L)
                        .assetId(5L)
                        .assetName("Apple iPhone 8")
                        .assetSerialNumber("APL8185652HGT7")
                        .build()
            )
        );
    }

    @Test
    void getAssignmentsForUserId__should_return_200( ) throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new AssignmentController( assignmentService) ).build();
        MvcResult result = mockMvc
                .perform(get("/api/users/1/assignments"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @ParameterizedTest
    @ValueSource(longs = {3L, 1L, 8L})
    void getAssignmentsForUserId__should_return_list_of_assignments__if_user_id_is_given(Long userId) throws Exception {
        List<AssignmentDto> assignmentDtos = new ArrayList<>();
        assignmentDtos = assignmentsDtoData
                .values()
                .stream()
                .filter( assignmentDto -> assignmentDto.getUserId().equals(userId))
                .collect(Collectors.toList());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonAssignmentsData = objectMapper.writeValueAsString(assignmentDtos);

        mockMvc = MockMvcBuilders.standaloneSetup( new AssignmentController( assignmentService) ).build();
        MvcResult result = mockMvc
                .perform(get("/api/users/"+userId+"/assignments"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonAssignmentsData))
                .andReturn();
    }


    @ParameterizedTest
    @ValueSource(longs = {8L, 1000L})
    void getAssignmentsForUserId__should_return_404__if_user_id_not_exists_in_db(Long userId) throws Exception {
        String jsonAssignmentsData = "[]";
        mockMvc = MockMvcBuilders.standaloneSetup( new AssignmentController( assignmentService) ).build();

        MvcResult result = mockMvc
                .perform(get("/api/users/"+userId+"/assignments"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}