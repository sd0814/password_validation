package practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ValidationControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * <b>test</b>: integration test for service <b>/validate</b><br />
     * <b>test condition</b>: input a set of mixtures of lowercase letters and numerical digits<br />
     * <b>expects</b>: HTTP.OK and valid
     */
    @Test
    public void testValidate_valid() throws Exception {
        String testText = "qwe123";

        MockHttpServletResponse response = validateText(testText);

        int status = response.getStatus();
        Map<String, ?> responseMap = objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, ?> dataMap = (Map<String, ?>) responseMap.get("data");
        boolean valid = (Boolean) dataMap.get("valid");

        Assert.assertEquals(HttpStatus.OK.value(), status);
        Assert.assertTrue(valid);
    }

    /**
     * <b>test</b>: integration test for service <b>/validate</b><br />
     * <b>test condition</b>: input a string which has repeat sequence<br />
     * <b>expects</b>: HTTP.OK and invalid
     */
    @Test
    public void testValidate_invalid() throws Exception {
        String testText = "1qweqwe2";

        MockHttpServletResponse response = validateText(testText);

        int status = response.getStatus();
        Map<String, ?> responseMap = objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, ?> dataMap = (Map<String, ?>) responseMap.get("data");
        boolean valid = (Boolean) dataMap.get("valid");

        Assert.assertEquals(HttpStatus.OK.value(), status);
        Assert.assertFalse(valid);
    }

    private MockHttpServletResponse validateText(String text) throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("text", text);

        String url = "/validate";
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(url)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        return response;
    }
}
