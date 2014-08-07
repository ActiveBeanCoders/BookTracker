package com.booktracker.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.booktracker.bean.Author;
import com.booktracker.bean.Book;
import com.booktracker.properties.User;
import com.booktracker.service.AuthorService;
import com.booktracker.service.BookService;

@Controller
public class UtilitiesController {

	@Autowired
	private User user;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/utilities", method = RequestMethod.GET)
	public String getAuthors(Model model) {

		System.out.println("GET utilities");
		
		model.addAttribute("userEmail", User.getUserEmail());
		model.addAttribute("user", user.getUser());
		return "utilities";

	}

	@RequestMapping(value = "utilities", method = RequestMethod.POST)
	public String authorAddSubmit(@RequestParam("authorName") String authorName,
			@RequestParam("user") String user, Model model) {
		System.out.println("utilitiesAdd POST " + authorName.toString());
		System.out.println("author = " + authorName + ", user = " + user);

		String[] name = authorName.split(" ");
		Author author = authorService.findByName(name[0], name[1]);
		if (author == null) {
			model.addAttribute("message", "Author does not exist");
		} else {
			try {
				copyAuthorAndRelatedBooks(author, user);
				model.addAttribute("message", "Author and books successfully copied to " + user);
			} catch (DuplicateKeyException e) {
				model.addAttribute("message", "Author and books already copied to " + user);
			}
		}
		model.addAttribute("userEmail", User.getUserEmail());
		return "utilities";
	}

	private void copyAuthorAndRelatedBooks(Author author, String user) {

		System.out.println("copyAuthorAndRelatedBooks = " + author.getAuthorId() + ", user = " + user);

		authorService.addAuthor(author.getFirstName(), author.getLastName(), author.getRating(),
				user);
		int authorId = authorService.findByNameAndUser(author.getFirstName(), author.getLastName(), user);
		
		List<Book> bookList = bookService.getBooks(author.getAuthorId());
		for (Book book : bookList) {
			book.setReadStatus(false);
			book.setAuthorId(authorId);
			bookService.addBook(book, user);
		}

	}

	@RequestMapping(value = "backup", method = RequestMethod.POST)
	public String backup(Model model) {
		System.out.println("backup POST ");

		final String fileLoc = "/Users/paulrothery/develop/backup/";
				
		final String DATE_FORMAT_NOW = "yyyy-MM-dd-HH:mm:ss";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		System.out.println(sdf.format(cal.getTime()));

		authorService.backup(fileLoc + "author-" + sdf.format(cal.getTime()) + ".csv");
		bookService.backup(fileLoc + "book-" + sdf.format(cal.getTime()) + ".csv");
		
		model.addAttribute("message", "Backup created on:\n\t" + fileLoc + "<i>-table-</i>" + sdf.format(cal.getTime()) + ".csv");
		model.addAttribute("userEmail", User.getUserEmail());
		return "utilities";
	}
	
	@RequestMapping(value = "start", method = RequestMethod.GET)
	public String star(Model model) {

		System.out.println("GET star");
	
		return "start";

	}

}