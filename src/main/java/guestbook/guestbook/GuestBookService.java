package guestbook.guestbook;

import org.springframework.stereotype.Service;

@Service
public class GuestBookService {
    private final GuestBookRepository guestBookRepository;

    public GuestBookService(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }


    public void create(GuestBookDto guestBookDto) {
        this.guestBookRepository.save(
                new GuestBookEntity(guestBookDto.getName(), guestBookDto.getComment())
        );
    }
}
