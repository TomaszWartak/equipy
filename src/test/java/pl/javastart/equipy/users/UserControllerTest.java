package pl.javastart.equipy.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private String jsonResponseEmpty ="[]";

    private String jsonResponseAllUsersData ="[\n" +
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

    String jsonResponseForId1 =
/*            "[" +
                "{" +
                    "id: 1," +
                    "\"firstName\": \"Jan\"," +
                    "\"lastName\": \"Kowalski\"," +
                    "\"pesel\": \"0123456789\"" +
                "}"+
            "]";*/

            "{\n" +
            "    \"id\": 1,\n" +
            "    \"firstName\": \"Jan\",\n" +
            "    \"lastName\": \"Kowalski\",\n" +
            "    \"pesel\": \"0123456789\"\n" +
            "}";

    String jsonRequestForNewUserProperData = "[\n" +
            "    {\n" +
            "        \"id\": 6,\n" +
            "        \"firstName\": \"John\",\n" +
            "        \"lastName\": \"Rambo\",\n" +
            "        \"pesel\": \"0012345678\"\n" +
            "    }\n" +
            "]";


    @Test
    void getUsers__should_return_200() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        mockMvc
                .perform(get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    void getUsers__should_return_empty_json_data__when_no_users_data_are_in_db() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        userRepository.deleteAll();
        mockMvc
                .perform(get("/api/users"))
                .andExpect(content().json(jsonResponseEmpty ));
    }

    @Test
    void getUsers__should_return_five_json_data__when_all_users_data_are_in_db() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        mockMvc
                .perform(get("/api/users"))
                .andExpect(content().json(jsonResponseAllUsersData));
    }

    @Test
    void getUsers__should_return_two_json_data__when_request_is_filtered_ski_phrase() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        mockMvc
                .perform(get("/api/users?lastName=Ski"))
                .andExpect(content().json(jsonResponseForSkiPhrase));
    }

    @Test
    void getUsers__should_return_all_json_data__when_request_is_filtered_empty_phrase() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        mockMvc
                .perform( get("/api/users?lastName=") )
                .andExpect(content().json(jsonResponseAllUsersData));
    }

    @Test
    void addUser__should_return_201_and_user_json_data__after_data_are_saved() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Rambo");
        userDto.setPesel("0012345678");
        String jsonRequest = objectMapper.writeValueAsString(userDto);
        userDto.setId(6L);
        String jsonResponse = objectMapper.writeValueAsString(userDto);
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        mockMvc
            .perform(
                    post("/api/users" )
                    .contentType(MediaType.APPLICATION_JSON)
                    .content( jsonRequest )
            )
            .andExpect(status().is(201))
            .andExpect(content().json(jsonResponse));
    }

    @Test
    void addUser__should_return_409__if_user_data_pesel_exists_in_db() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Rambo");
        userDto.setPesel("0123456789");
        String jsonRequest = objectMapper.writeValueAsString(userDto);
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        mockMvc
                .perform(
                        post("/api/users" )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content( jsonRequest )
                )
                .andExpect(status().is(409));
    }
    @Test
    void addUser__should_return_400__if_id_is_given_in_user_data() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = new UserDto();
        userDto.setId(7L);
        userDto.setFirstName("John");
        userDto.setLastName("Rambo");
        userDto.setPesel("0123456789");
        String jsonRequest = objectMapper.writeValueAsString(userDto);
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        mockMvc
                .perform(
                        post("/api/users" )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content( jsonRequest )
                )
                .andExpect(status().is(400));    }

    @Test
    void getUser__should_return_200_and_Json_user_data__if_given_id_1_exists_in_db() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        MvcResult result = mockMvc
                .perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponseForId1))
                .andReturn();
    }


    @Test
    void getUser__should_return_400__if_given_id_not_exist_in_db() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup( new UserController(userService) ).build();
        MvcResult result = mockMvc
                .perform(get("/api/users/6"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void updateUser__should_return_200_and_Json_user_data__when_data_saving_was_successful() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = new UserDto();
        userDto.setId(5L);
        userDto.setFirstName("John");
        userDto.setLastName("Rambo");
        userDto.setPesel("00000000000");
        String jsonRequest = objectMapper.writeValueAsString(userDto);
        MvcResult result = mockMvc
                .perform( put("/api/users/5" )
                    .contentType(MediaType.APPLICATION_JSON)
                    .content( jsonRequest )
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void updateUser__should_return_409__if_user_pesel_exists_in_db() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = new UserDto();
        userDto.setId(5L);
        userDto.setFirstName("John");
        userDto.setLastName("Rambo");
        userDto.setPesel("1234567890"); // ten pesel nalezy do użytkownika o id 2
        String jsonRequest = objectMapper.writeValueAsString(userDto);
        MvcResult result = mockMvc
                .perform(
                        put("/api/users/5" )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content( jsonRequest )
                )
                .andExpect(status().is(409))
                .andReturn();
    }

    @Test
    void updateUser__should_return_400__if_user_id_is_different_from_id_given_in_url() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = new UserDto();
        userDto.setId(4L);
        userDto.setFirstName("John");
        userDto.setLastName("Rambo");
        userDto.setPesel("4567890123");
        String jsonRequest = objectMapper.writeValueAsString(userDto);
        MvcResult result = mockMvc
                .perform(
                        put("/api/users/5" )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content( jsonRequest )
                )
                .andExpect(status().is(400))
                .andReturn();
    }
    @Test
    void updateUser__should_return_400__if_user_id_is_not_given() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Rambo");
        userDto.setPesel("121212121212");
        String jsonRequest = objectMapper.writeValueAsString(userDto);
        MvcResult result = mockMvc
                .perform(
                        put("/api/users/5" )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content( jsonRequest )
                )
                .andExpect(status().is(400))
                .andReturn();
    }

}