package guestbook.guestbook;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<GuestBookDto> fetchAll() {
        return this.guestBookRepository.findAll().stream().map(
                guestBookEntity -> new GuestBookDto(guestBookEntity.getName(), guestBookEntity.getComment())
        ).collect(Collectors.toList());
    }
}
