package com.booktracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.booktracker.bean.Book;
import com.booktracker.service.BookService;


@Controller
public class BookController {

	@Autowired
	private Book book;

	@Autowired
	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(value="/book", method = RequestMethod.GET)
	public String getBooks(@RequestParam("authorId") int authorId, @RequestParam("name") String name, Model model) {

		System.out.println("GET books");
	
		System.out.println(authorId);
		System.out.println(name);
		List<Book> bookList = bookService.getBooks(authorId);
		System.out.println(bookList);

		model.addAttribute("bookList", bookList);
		model.addAttribute("authorId", authorId);
		model.addAttribute("name", name);

		return "book";

	}

	@RequestMapping(value="book", method = RequestMethod.POST)
	public String getBooksp(@RequestParam("authorId") int authorId, @RequestParam("name") String name, Model m) {

		System.out.println("Post books");
	
		System.out.println(authorId);
		System.out.println(name);
		List<Book> bookList = bookService.getBooks(authorId);
		System.out.println(bookList);

		m.addAttribute("bookList", bookList);
		m.addAttribute("authorId", authorId);
		m.addAttribute("name", name);

		return "book";

	}

	@RequestMapping(value = "bookAdd", method = RequestMethod.GET)
	public String bookAdd(@RequestParam("authorId") int authorId, @RequestParam("name") String name, Model model) {
		System.out.println("bookAdd GET " + book.toString() + ",authorId = " + authorId + ",name = " + name);
		model.addAttribute("book", book);
		model.addAttribute("authorId", authorId);
		model.addAttribute("name", name);
		model.addAttribute("action", "Add");
		return "bookAddForm";
	}

	@RequestMapping(value = "bookAdd", method = RequestMethod.POST)
	public String bookAddSubmit(@RequestParam("name") String name, @ModelAttribute Book book, Model model) {
		System.out.println("bookAdd POST " + book.toString());

		if (bookService.checkTitleExists(book.getTitle(), book.getAuthorId()) != 0) {
			model.addAttribute("message", "This title already exists for the author");
		} else {
			bookService.addBook(book);
			model.addAttribute("message", "Successfully saved book: " + book.toString());					
		}
		
		model.addAttribute("authorId", book.getAuthorId());
		model.addAttribute("name", name);
		model.addAttribute("action", "Add");
		bookReset();
		return "bookAddForm";
	}

	@RequestMapping(value = "bookEdit", method = RequestMethod.GET)
	public String bookEdit(@RequestParam("bookId") int bookId, @RequestParam("authorId") int authorId, @RequestParam("name") String name, Model model) {

		book = bookService.findById(bookId);
		System.out.println("bookEdit GET " + book.toString());

		model.addAttribute("book", book);
		model.addAttribute("authorId", authorId);
		model.addAttribute("name", name);
		model.addAttribute("action", "Edit");
		return "bookAddForm";
	}

	@RequestMapping(value = "bookEdit", method = RequestMethod.POST)
	public String bookEditSubmit(@RequestParam("authorId") int authorId, @RequestParam("name") String name, @ModelAttribute Book book, Model model) {

		System.out.println("bookEdit POST " + book.toString());

		book.setAuthorId(authorId);
		model.addAttribute("message", "Successfully saved book: " + book.toString());
		model.addAttribute("authorId", book.getAuthorId());
		model.addAttribute("name", name);
		model.addAttribute("action", "Edit");
		System.out.println("authorId = " + authorId);
		bookService.updateBook(book);
		bookReset();
		return "bookAddForm";

	}

	@RequestMapping(value = "bookDelete", method = RequestMethod.GET)
	public String bookDelete(@RequestParam("bookId") int bookId, @RequestParam("name") String name, Model model) {

		System.out.println("bookDelete" + book.toString());
		book = bookService.findById(bookId);
		bookService.deleteBook(bookId);
		model.addAttribute("message", "Successfully deleted book: " + book.getTitle());
		
		return getBooks(book.getAuthorId(), name, model);
	}
	
	private void bookReset() {
		this.book.setTitle("");
		this.book.setSeries("");
		this.book.setYear(0);
		
	}
}