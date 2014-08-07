package com.booktracker.service;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.booktracker.bean.Author;
import com.booktracker.dao.AuthorDao;
import com.booktracker.properties.User;

public class AuthorService {

	private AuthorDao authorDao;
	private User user;

	public AuthorDao getAuthorDao() {
		return authorDao;
	}

	public void setAuthorDao(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Author> getAuthors() {
		List<Author> list = authorDao.getAuthors(user.getUser());
		return list;
	}

	public List<Author> getAuthorsForOtherUser(String user) {
		List<Author> list = authorDao.getAuthors(user);
		return list;
	}

	public void addAuthor(String firstName, String lastName, int rating) {
		authorDao.addAuthor(firstName, lastName, rating, user.getUser());
	}

	public void addAuthor(String firstName, String lastName, int rating, String user) {
		authorDao.addAuthor(firstName, lastName, rating, user);
	}

	public void updateAuthor(Author author) {
		authorDao.updateAuthor(author);
	}

	public void deleteAuthor(int id) {
		authorDao.deleteAuthor(id);
	}

	public Author findById(int id) {
		return authorDao.findById(id, user.getUser());
	}

	public Author findByName(String firstName, String lastName) {
		return authorDao.findByName(firstName, lastName, user.getUser());
	}

	public int findByNameAndUser(String firstName, String lastName, String user) {
		Author author = authorDao.findByName(firstName, lastName, user);
		return author.getAuthorId();
	}

	public int checkAuthorExists(String firstName, String lastName) {
		return authorDao.checkAuthorExists(firstName, lastName, user.getUser());
	}

	public int getNumBooks(int id) {
		return authorDao.getNumBooks(id);
	}

	public void backup(String fileName) {
		authorDao.backup(fileName);
	}
}
