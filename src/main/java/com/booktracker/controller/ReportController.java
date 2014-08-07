package com.booktracker.controller;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.booktracker.service.Emailer;

@Controller
public class ReportController {

	@Autowired
	private User user;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String getAuthors(Model model) {

		System.out.println("GET report");

		// Map<String, String> userEmail = user.getUserEmail();
		// for (Map.Entry<String,String> e : userEmail.entrySet()) {
		// System.out.println("entry = " + e.getValue());
		// }

		model.addAttribute("userEmail", User.getUserEmail());
		model.addAttribute("user", user.getUser());
		return "report";

	}

	@RequestMapping(value = "report", method = RequestMethod.POST)
	public String authorSubmit(@RequestParam("user") String user, Model model) {
		System.out.println("reportSubmit POST " + user);

		List<Author> authorList = authorService.getAuthorsForOtherUser(user);
		System.out.println(authorList);

		StringBuffer report = new StringBuffer();
		String subject = "Unread Book Report for " + user;
		for (Author author : authorList) {
			process(author, user, report);
		}
		report.append("---------- End of Report ---------");
		System.out.println(report.toString());

		System.out.println("sending to -> " + User.getUserEmail().get(user));
		Emailer.sendEmail(User.getUserEmail().get(user), subject, report.toString());
		System.out.println(report.toString());
		model.addAttribute("message", "Report generated");
		model.addAttribute("userEmail", User.getUserEmail());
		return "report";
	}

	private void process(Author author, String user, StringBuffer report) {

		if (authorService.getNumBooks(author.getAuthorId()) > 0) {
			report.append("-----------------------------\n");
			report.append(author.getFirstName() + " " + author.getLastName() + "\n");
			report.append("-----------------------------\n");
			List<Book> bookList = bookService.getUnreadBooks(author.getAuthorId(), user);
			for (Book book : bookList) {
				// String sp = String.format("%1$s%2$" + (30 -
				// book.getTitle().length()) + "s",book.getTitle(), " ");
				String sp = String.format("%1$" + (30 - book.getTitle().length()) + "s", " ");
				report.append("  " + book.getTitle());
				if (book.getPseudonym() != null && book.getPseudonym().length() > 0) {
					report.append(sp + " (" + book.getPseudonym() + ")");
				}
				if (book.getSeries() != null && book.getSeries().length() > 0) {
					report.append(sp + " (" + book.getSeries() + ")");
				}
				report.append("\n");
			}
		}
	}

}
