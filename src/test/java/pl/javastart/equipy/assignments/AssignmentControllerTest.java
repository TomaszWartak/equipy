package pl.javastart.equipy.assignments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.javastart.equipy.TestHelperData;
import pl.javastart.equipy.utils.DateTimeProvider;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
class AssignmentControllerTest{
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AssignmentService assignmentService;
    private static TestHelperData testHelperData;
    @MockBean
    private DateTimeProvider dateTimeProviderMock;

    @BeforeAll
    static void prepareTestHelperData() {
        testHelperData = TestHelperData.getInstance();
    }

    @BeforeEach
    void prepareExpectedData(){
        testHelperData.prepareAllData();
    }

    @Test
    void AddAssignment__should_return_201__if_assignment_was_added() throws Exception {
        // przygotowanie obiektu zapytania
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        AssignmentDto newAssignmentDto = AssignmentDto.builder()
                .userId( 4L )
                .assetId( 2L )
                .build();
        String jsonNewAssignmentRequest = objectMapper.writeValueAsString( newAssignmentDto );

        // otrzymać potwierdzenie 201

        mockMvc = MockMvcBuilders.standaloneSetup( new AssignmentController(assignmentService) ).build();
        MvcResult result = mockMvc
                .perform( post("/api/assignments" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( jsonNewAssignmentRequest )
                )
                .andExpect( status().isCreated() )
                .andReturn();
    }

    @Test
    void AddAssignment__should_return_json_object__if_assignment_was_added() throws Exception {
        // przygotowanie obiektu zapytania
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        AssignmentDto newAssignmentDto = AssignmentDto.builder()
                .userId( 4L )
                .assetId( 2L )
                .build();
        String jsonNewAssignmentRequest = objectMapper.writeValueAsString( newAssignmentDto );

        // przygotowanie obiektu odpowiedzi
        LocalDateTime now = LocalDateTime.now();
        Mockito.when(dateTimeProviderMock.currentLocalDateTime()).thenReturn( now );

        AssignmentDto addedAssignmentDto = AssignmentDto.builder()
                .id( 8L )
                .start( now )
                .end( null )
                .userId( 4L )
                .assetId( 2L )
                .build();
        String jsonAssignmentsResponse = objectMapper.writeValueAsString(addedAssignmentDto);

        // otrzymać potwierdzenie 201 i obiekt json

        mockMvc = MockMvcBuilders.standaloneSetup( new AssignmentController(assignmentService) ).build();
        MvcResult result = mockMvc
                .perform( post("/api/assignments" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( jsonNewAssignmentRequest )
                )
                .andExpect( status().isCreated() )
                .andExpect( content().json(jsonAssignmentsResponse) )
                .andReturn();
    }
}
