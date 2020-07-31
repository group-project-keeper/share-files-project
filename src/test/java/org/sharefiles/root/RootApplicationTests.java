package org.sharefiles.root;

import org.junit.jupiter.api.Test;
import org.sharefiles.root.model.UploadedFile;
import org.sharefiles.root.repository.UploadFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class RootApplicationTests {

	@Autowired
	private UploadFilesRepository uploadFilesRepository;


	@Test
	void contextLoads() {
		UploadedFile file = new UploadedFile();
		file.setUploadedDate(new Date().toString());
		file.setExpiryDate(new Date(new Date().getTime() + 3000).toString());
		file.setFileName("some-file.jsp");
		uploadFilesRepository.save(file);

	}

}
