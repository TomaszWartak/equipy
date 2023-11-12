package pl.javastart.equipy.assignments;

import org.hibernate.annotations.Source;
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
import pl.javastart.equipy.categories.CategoryController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @ParameterizedTest
    @ValueSource(longs = {3L, 1L})
    void getAssignmentsForUserId__should_return_200( Long userId) throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new AssignmentController( assignmentService) ).build();
        MvcResult result = mockMvc
                .perform(get("/api/users/"+userId+"/assignments"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    void getAssignmentsForUserId__should_return_list_of_assignments__if_user_id_is_given() {


    }
}