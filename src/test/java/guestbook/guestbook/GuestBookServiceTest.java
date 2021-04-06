package guestbook.guestbook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GuestBookServiceTest {

    @Mock
    GuestBookRepository mockGuestBookRepository;

    @InjectMocks
    GuestBookService subject;

    @Test
    void create() throws Exception {
        GuestBookDto guestBookDto = new GuestBookDto("peter", "it is a nice place to visit!");
        subject.create(guestBookDto);

        verify(mockGuestBookRepository).save(
                new GuestBookEntity("peter", "it is a nice place to visit!")
        );
    }
}
