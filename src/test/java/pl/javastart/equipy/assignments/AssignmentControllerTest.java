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
    void addAssignment__should_return_BadRequest_status__if_given_userId_not_found() throws Exception{
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
        // todo dodaj sprawdzenie komunikatu w badrequest, że user...
    }

    @Test
    void addAssignment__should_return_BadRequest_status__if_given_assetId_not_found() throws Exception{
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
        // todo dodaj sprawdzenie komunikatu w badrequest że asset...
    }

    @Test
    void addAssignment__should_return_BadRequest_status__if_given_userId_is_null() throws Exception{
        // przygotowanie obiektu zapytania
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        AssignmentDto newAssignmentDto = AssignmentDto.builder()
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
        // todo dodaj sprawdzenie komunikatu w badrequest, że userid null...
    }

    @Test
    void addAssignment__should_return_BadRequest_status__if_given_assetId_is_null() throws Exception{
        // przygotowanie obiektu zapytania
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        AssignmentDto newAssignmentDto = AssignmentDto.builder()
                .userId( 2L )
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
        // todo dodaj sprawdzenie komunikatu w badrequest, że assetid null...
    }

    @Test
    void addAssignment__should_return_BadRequest_status__if_given_assetId_is_already_assigned() throws Exception{
        // przygotowanie obiektu zapytania
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        AssignmentDto newAssignmentDto = AssignmentDto.builder()
                .userId( 2L )
                .assetId( 1L )
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
        // todo dodaj sprawdzenie komunikatu w badrequest, że wyposażenie jest już przyporządkowan...
    }

    @Test
    void finishAssignment__should_return_OK__if_assignment_finishing_was_successful() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Mockito.when( dateTimeProviderMock.currentLocalDateTime() ).thenReturn( now );

        mockMvc = MockMvcBuilders.standaloneSetup( new AssignmentController(assignmentService) ).build();
        MvcResult result = mockMvc
                .perform( post("/api/assignments/4/end" ) )
                .andExpect( status().isOk() )
                .andReturn();
    }

    @Test
    void finishAssignment__should_return_date_of_end__if_assignment_finishing_was_successful() throws Exception {

        LocalDateTime now = LocalDateTime.now();
        Mockito.when( dateTimeProviderMock.currentLocalDateTime() ).thenReturn( now );
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule( new JavaTimeModule() );
        String jsonAssignmentsResponse = objectMapper.writeValueAsString( now );

        mockMvc = MockMvcBuilders.standaloneSetup( new AssignmentController(assignmentService) ).build();
        MvcResult result = mockMvc
                .perform( post("/api/assignments/4/end" ) )
                .andExpect( status().isOk() )
                .andExpect( content().json(jsonAssignmentsResponse) )
                .andReturn();
    }

    @Test
    void finishAssignment__should_return_404__if_assignment_id_is_not_found() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new AssignmentController(assignmentService) ).build();
        MvcResult result = mockMvc
                .perform( post("/api/assignments/1000/end" ) )
                .andExpect( status().isNotFound() )
                .andReturn();
    }

    @Test
    void finishAssignment__should_return_400__if_assignment_is_finished_already() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new AssignmentController(assignmentService) ).build();
        MvcResult result = mockMvc
                .perform( post("/api/assignments/5/end" ) )
                .andExpect( status().isBadRequest() )
                .andReturn();
    }

}
