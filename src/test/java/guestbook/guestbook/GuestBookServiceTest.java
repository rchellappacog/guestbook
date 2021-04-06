package guestbook.guestbook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void fetchAllTest() {
        GuestBookEntity entity1 = new GuestBookEntity("peter", "it is a nice place to visit!");
        GuestBookEntity entity2 = new GuestBookEntity("john", "nice artifacts!");

        when(mockGuestBookRepository.findAll()).thenReturn(
             List.of(entity1, entity2)
        );

        List<GuestBookDto> actual = subject.fetchAll();
        assertThat(actual).isEqualTo(
                List.of(
                        new GuestBookDto("peter", "it is a nice place to visit!"),
                        new GuestBookDto("john", "nice artifacts!")
                )
        );
    }
}
