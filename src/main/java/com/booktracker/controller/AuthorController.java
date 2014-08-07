package com.booktracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.booktracker.bean.Author;
import com.booktracker.service.AuthorService;

@Controller
public class AuthorController {

	@Autowired
	private Author author;
	@Autowired
	private AuthorService authorService;

	public AuthorService getAuthorService() {
		return authorService;
	}

	public void setAuthorService(AuthorService authorService) {
		this.authorService = authorService;
	}

	@RequestMapping(value="/author", method = RequestMethod.GET)
	public String getAuthors(Model model) {
		
		System.out.println("GET authors");
		List<Author> authorList = authorService.getAuthors();
		System.out.println(authorList);	
		model.addAttribute("authorList", authorList);
		return "author";

	}
	
	@RequestMapping(value="authorAdd", method=RequestMethod.GET)
	public String authorAdd(Model m) {
		System.out.println( "authorAdd GET " + author.toString());
		author.setRating(2);
		m.addAttribute("author", author);
		m.addAttribute("action", "Add");
		return "authorAddForm";
	}
	
	@RequestMapping(value="authorAdd", method=RequestMethod.POST)
	public String authorAddSubmit(@ModelAttribute Author author, Model m) {
		System.out.println( "authorAdd POST " + author.toString());

		if (authorService.checkAuthorExists(author.getFirstName(), author.getLastName()) != 0) {
			m.addAttribute("message", "Author with this name already exists");				
		} else {
			authorService.addAuthor(author.getFirstName(), author.getLastName(), author.getRating());
			m.addAttribute("message", "Successfully saved author: " + author.toString());			
		}
		m.addAttribute("action", "Add");
		authorReset();
		return "authorAddForm";
	}


	@RequestMapping(value="authorEdit", method=RequestMethod.GET)
	public String authorEdit(@RequestParam("authorId") int authorId, Model m) {
		System.out.println( "authorEdit GET " + author.toString() + ",authorId = " + authorId);
		author = authorService.findById(authorId);		
		m.addAttribute("author", author);
		m.addAttribute("action", "Edit");
		return "authorAddForm";
	}	

	@RequestMapping(value="authorEdit", method=RequestMethod.POST)
	public String authorEditSubmit(@ModelAttribute Author author, Model m) {
		System.out.println( "authorEdit POST " + author.toString());

		m.addAttribute("message", "Successfully changed author: " + author.toString());
		m.addAttribute("action", "Edit");
		authorService.updateAuthor(author);
		authorReset();
		return "authorAddForm";
	}
	
	@RequestMapping(value="authorDelete", method=RequestMethod.GET)
	public String authorDelete(@RequestParam("authorId") int authorId, Model m) {

		System.out.println( "authorDelete");
		int numBooks = authorService.getNumBooks(authorId);
		if (numBooks > 0) {
			m.addAttribute("message", "Author has " + numBooks + " books and cannot be deleted");		
		} else {
			authorService.deleteAuthor(authorId);
		}

		return getAuthors(m);
	}

	private void authorReset() {
		this.author.setFirstName("");
		this.author.setLastName("");
		
	}
}