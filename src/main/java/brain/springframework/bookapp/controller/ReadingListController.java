package brain.springframework.bookapp.controller;

import brain.springframework.bookapp.entity.Book;
import brain.springframework.bookapp.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * ReadingListController is annotated with @Controller in order to be picked up by
 * component-scanning and automatically be registered as a bean in the Spring application context.
 * It’s also annotated with @RequestMapping to map all of its handler methods to a base URL path of “/”.
 */

@Controller
@RequestMapping("/")
public class ReadingListController {

    private ReadingListRepository readingListRepository;
    @Autowired
    public ReadingListController(
            ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;

    }

    /**
     * readersBooks()—Handles HTTP GET requests for /{reader} by retrieving a
     * Book list from the repository (which was injected into the controller’s constructor) for the reader specified in the path.
     * It puts the list of Book into the model
     * under the key “books” and returns “readingList” as the logical name of the view
     * to render the model
     * @param reader
     * @param model
     * @return
     */
    @RequestMapping(value="/{reader}", method= RequestMethod.GET)
    public String readersBooks(
            @PathVariable("reader") String reader,
            Model model) {
        List<Book> readingList =
                readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    /**
     * Handles HTTP POST requests for /{reader}, binding the
     * data in the body of the request to a Book object. This method sets the Book
     * object’s reader property to the reader’s name, and then saves the modified
     * Book via the repository’s save() method. Finally, it returns by specifying a redirect to /{reader}
     * (which will be handled by the other controller meth
     * @param reader
     * @param book
     * @return
     */

    @RequestMapping(value="/{reader}", method=RequestMethod.POST)
    public String addToReadingList(
            @PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }
}
