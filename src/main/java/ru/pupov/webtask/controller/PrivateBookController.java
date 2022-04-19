package ru.pupov.webtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.pupov.webtask.model.Book;
import ru.pupov.webtask.service.BookService;

@Controller
public class PrivateBookController {

    @Autowired
    private BookService bookService;

    //todo удалить все '}'
    @GetMapping("/privateLibrary")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllPrivateBooks());
        return "privateLibraryBookList";
    }


    @GetMapping("/privateLibrary/books/addBook")
    public String showprivateLibraryAddBookPage(Model model) {
        if (!model.containsAttribute("book")) {
            Book book = new Book();
            book.setIsPrivate(true);
            model.addAttribute("book", book);
        }
        return "privateLibraryAddBook";
    }
    @PostMapping("/privateLibrary/books/addBook")
    public String saveBook(RedirectAttributes redirAttrs, @ModelAttribute Book book) {
        if (!book.isCorrectToSave()) {
            redirAttrs.addFlashAttribute("errorMessage", "Некорректно заполненны поля");
            redirAttrs.addFlashAttribute("book", book);
            return "redirect:/privateLibrary/books/addBook";
        }
        bookService.saveBook(book);
        redirAttrs.addFlashAttribute("successMessage", "Книга успешно сохранена");
        return "redirect:/privateLibrary";
    }

    @GetMapping("/privateLibrary/books/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Некорректный ИД: " + id));

        model.addAttribute("book", book);
        return "privateLibraryUpdateBook";
    }
    @PostMapping("/privateLibrary/books/{id}")
    public String updateBook(RedirectAttributes redirAttrs, @ModelAttribute Book book) {
        bookService.saveBook(book);
        redirAttrs.addFlashAttribute("successMessage", "Книга успешно изменена");
        return "redirect:/privateLibrary";
    }

    @GetMapping("/privateLibrary/books/delete/{id}")
    public String deleteUser(RedirectAttributes redirAttrs, @PathVariable("id") long id) {
        Book book = bookService.findById(id).orElse(null);
        if (book != null) {
            bookService.delete(book);
            redirAttrs.addFlashAttribute("successMessage", "Книга успешно удалена");
        } else {
            redirAttrs.addFlashAttribute("errorMessage",
                    "Не удалось удалить книгу. Книги с id " + id + " не существует.");
        }
        return "redirect:/privateLibrary";
    }

    @GetMapping("/privateLibrary/books/move/{id}")
    public String move(RedirectAttributes redirAttrs, @PathVariable("id") long id) {
        Book book = bookService.findById(id).orElse(null);
        if (book != null && book.isCorrectToSave()) {
            book.setIsPrivate(false);
            bookService.saveBook(book);
            redirAttrs.addFlashAttribute("successMessage",
                    "Книга " + book.toShortString() + " успешно перенесена");
        } else {
            redirAttrs.addFlashAttribute("errorMessage", "Ошибка. Не удалось найти запись");
        }
        return "redirect:/privateLibrary";
    }
}
