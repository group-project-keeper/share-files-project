package org.sharefiles.root;

import org.junit.jupiter.api.Test;
import org.sharefiles.root.model.UploadedFile;
import org.sharefiles.root.repository.UploadFilesRepository;
import org.sharefiles.root.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Date;

@SpringBootTest
class RootApplicationTests {

	@Autowired
	private UploadFilesRepository uploadFilesRepository;

	@Autowired
    private UploadService uploadService;

	@Test
	void contextLoads() {
		UploadedFile file = new UploadedFile();
		file.setUploadedDate(new Date().toString());
		file.setExpiryDate(new Date(new Date().getTime() + 3000).toString());
		file.setFileName("some-file.jsp");
		uploadFilesRepository.save(file);

	}

	@Test
    void uploadMethodTest() {
	    uploadService.uploadFile(new MockMultipartFile("test-file.txt", "sample data1".getBytes()));
    }

}
