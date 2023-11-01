package pl.javastart.equipy.users;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    String jsonResponseEmpty ="[]";

    String jsonResponseAllUsersData ="[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"firstName\": \"Jan\",\n" +
            "        \"lastName\": \"Kowalski\",\n" +
            "        \"pesel\": \"0123456789\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 2,\n" +
            "        \"firstName\": \"Paweł\",\n" +
            "        \"lastName\": \"Zawiał\",\n" +
            "        \"pesel\": \"1234567890\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 3,\n" +
            "        \"firstName\": \"Marta\",\n" +
            "        \"lastName\": \"Babiak\",\n" +
            "        \"pesel\": \"2345678901\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 4,\n" +
            "        \"firstName\": \"Karolina\",\n" +
            "        \"lastName\": \"Modejska\",\n" +
            "        \"pesel\": \"3456789012\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 5,\n" +
            "        \"firstName\": \"Piotr\",\n" +
            "        \"lastName\": \"Pawelski\",\n" +
            "        \"pesel\": \"4567890123\"\n" +
            "    }\n" +
            "]";
    String jsonResponseForSkiPhrase ="[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"firstName\": \"Jan\",\n" +
            "        \"lastName\": \"Kowalski\",\n" +
            "        \"pesel\": \"0123456789\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 5,\n" +
            "        \"firstName\": \"Piotr\",\n" +
            "        \"lastName\": \"Pawelski\",\n" +
            "        \"pesel\": \"4567890123\"\n" +
            "    }\n" +
            "]";


    @Test
    void getUsers__should_return_200() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    void getUsers__should_return_empty_json_data__when_no_users_data_are_in_db() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        mockMvc.perform(get("/api/users"))
                .andExpect(content().json(jsonResponseEmpty ));
    }

    @Test
    void getUsers__should_return_five_json_data__when_all_users_data_are_in_db() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        mockMvc.perform(get("/api/users"))
                .andExpect(content().json(jsonResponseAllUsersData));
    }

    @Test
    void getUsers__should_return_two_json_data__when_request_is_filtered_ski_phrase() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        mockMvc.perform(get("/api/users?lastName=Ski"))
                .andExpect(content().json(jsonResponseForSkiPhrase));
    }

    @Test
    void getUsers__should_return_all_json_data__when_request_is_filtered_empty_phrase() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        mockMvc.perform(get("/api/users?lastName="))
                .andExpect(content().json(jsonResponseAllUsersData));
    }


}