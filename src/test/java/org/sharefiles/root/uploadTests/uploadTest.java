package org.sharefiles.root.uploadTests;

import org.junit.Assert;
import org.junit.Before;


import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.sharefiles.root.RootApplication;
import org.sharefiles.root.config.ShareFilesConfig;
import org.sharefiles.root.controllers.UploadController;
import org.sharefiles.root.controllers.UserController;
import org.sharefiles.root.model.UploadedFile;
import org.sharefiles.root.repository.UploadFilesRepository;
import org.sharefiles.root.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.Test;
import org.springframework.web.context.WebApplicationContext;
//import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Date;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;


//@SpringBootTest
//@RunWith(SpringRunner.class)
//@WebMvcTest(UploadController.class)


//@SpringBootTest
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = UploadController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RootApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class uploadTest {


       // @Autowired
        @MockBean
        private UploadFilesRepository uploadFilesRepository;

        //@Autowired
        @MockBean
        private UploadService uploadService;

        @Autowired
        private MockMvc mockMvc;


        @Spy
        @InjectMocks
        private UploadController controller = new UploadController();


        private MockMultipartFile testFile1;

        @Before
        public void init() {
            testFile1 = new MockMultipartFile("data", "test-file.txt", "text/plain","sample data1".getBytes());
            //mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        }
        @Test
        public void registeredUploadServiceTest() {
            uploadService.uploadFileRegistered(testFile1);
        }


        @Test
        public void anonymousUploadServiceTest() {
            uploadService.uploadFileAnon(testFile1);
        }


        @Test
        public void anonymousUploadControllerTest() throws Exception {
            MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test-file.txt", "text/plain","sample data1".getBytes());

            mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                    .file(mockMultipartFile)
                    .accept(MediaType.ALL_VALUE))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andExpect((ResultMatcher) content().string("File has been uploaded"));
        }

        @Test
        public void testEnumValues() {
            System.out.println(ShareFilesConfig.ANONYMOUS_DIRECTORY);
        }

    }

