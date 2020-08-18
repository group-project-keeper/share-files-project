package org.sharefiles.root;

import org.junit.jupiter.api.Test;
import org.sharefiles.root.config.ShareFilesConfig;
import org.sharefiles.root.model.UploadedFile;
import org.sharefiles.root.repository.UploadFilesRepository;
import org.sharefiles.root.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Date;


@AutoConfigureMockMvc
@SpringBootTest
class RootApplicationTests {

	@Autowired
	private UploadFilesRepository uploadFilesRepository;

	@Autowired
	//@MockBean
    private UploadService uploadService;

	@Test
	void contextLoads() {
		UploadedFile file = new UploadedFile();
		file.setUploadedDate(new Date().toString());
		file.setExpiryDate(new Date(new Date().getTime() + 3000).toString());
		file.setFileName("some-file.jsp");
		uploadFilesRepository.save(file);

	}
	/*
	@Test
    void uploadMethodTest() {
//	    uploadService.uploadFile(new MockMultipartFile("test-file.txt", "sample data1".getBytes()));
    }
	 */

	@Test
	void uploadMethodTest() {
		uploadService.uploadFileAnon(new MockMultipartFile("data", "test-file.txt", "text/plain","sample data1".getBytes()));
	}

	//@Test
	void downloadMethodTest(){

	}

    @Test
	void testEnumValues() {
		System.out.println(ShareFilesConfig.ANONYMOUS_DIRECTORY);
	}

}
