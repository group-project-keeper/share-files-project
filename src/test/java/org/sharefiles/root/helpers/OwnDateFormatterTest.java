package org.sharefiles.root.helpers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OwnDateFormatterTest {


    @Test
    void getSimpleDateFormat() {
        System.out.println(OwnDateFormatter.getSimpleDateFormat());
    }

    @Test
    void getNextDayDate() {
        System.out.println(OwnDateFormatter.getNextDayDate());
    }

    @Test
    void getDayAfterTomorrow() {
        System.out.println(OwnDateFormatter.getDayAfterTomorrow());
    }

    @Test
    void getFolderNameToDelete() {
        System.out.println(OwnDateFormatter.getFolderNameToDelete());
    }
}