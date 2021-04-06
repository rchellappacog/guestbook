package guestbook.guestbook;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("guestbook")
public class GuestBookController {
   public GuestBookService guestBookService;

    public GuestBookController(GuestBookService guestBookService) {
        this.guestBookService = guestBookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody GuestBookDto review) throws Exception {
        this.guestBookService.create(review);
    }

    @GetMapping
    public List<GuestBookDto> fetchAll() {
        return this.guestBookService.fetchAll();
    }
}
