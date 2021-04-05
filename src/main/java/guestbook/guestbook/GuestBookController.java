package guestbook.guestbook;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("guestbook")
public class GuestBookController {
    List<String> reviews;

    public GuestBookController() {
        this.reviews = new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody String review) throws Exception {
        this.reviews.add(review);
    }
}
