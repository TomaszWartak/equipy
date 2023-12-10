package pl.javastart.equipy.assignments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
    void addAssignment__should_return_201__if_assignment_was_added() throws Exception {
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
    void addAssignment__should_return_json_object__if_assignment_was_added() throws Exception {
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

    @Test
    void addAssignment__should_return_BadRequest_status__if_userId_not_found() throws Exception{
        // przygotowanie obiektu zapytania
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        AssignmentDto newAssignmentDto = AssignmentDto.builder()
                .userId( 1000L )
                .assetId( 2L )
                .build();
        String jsonNewAssignmentRequest = objectMapper.writeValueAsString( newAssignmentDto );

        mockMvc = MockMvcBuilders.standaloneSetup( new AssignmentController(assignmentService) ).build();
        MvcResult result = mockMvc
                .perform( post("/api/assignments" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( jsonNewAssignmentRequest )
                )
                .andExpect( status().isBadRequest() )
                .andReturn();
    }

    @Test
    void addAssignment__should_return_BadRequest_status__if_assetId_not_found() throws Exception{
        // przygotowanie obiektu zapytania
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        AssignmentDto newAssignmentDto = AssignmentDto.builder()
                .userId( 2L )
                .assetId( 2000L )
                .build();
        String jsonNewAssignmentRequest = objectMapper.writeValueAsString( newAssignmentDto );

        mockMvc = MockMvcBuilders.standaloneSetup( new AssignmentController(assignmentService) ).build();
        MvcResult result = mockMvc
                .perform( post("/api/assignments" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( jsonNewAssignmentRequest )
                )
                .andExpect( status().isBadRequest() )
                .andReturn();
    }

    /*
    500 Bad Request -
   + jeśli nie znaleziono użytkownika
   + lub wyposażenia o wskazanych id,
  -  którekolwiek id było ustawione na null
  -  lub wyposażenie o wskazanym id jest już przypisane do jakiegokolwiek użytkownika.
     */
}
