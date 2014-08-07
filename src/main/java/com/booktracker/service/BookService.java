package com.booktracker.service;

import java.util.List;

import com.booktracker.bean.Book;
import com.booktracker.dao.BookDao;
import com.booktracker.properties.User;

public class BookService {

	private BookDao bookDao;
	private User user;

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Book> getBooks(int id) {
		List<Book> list = bookDao.getBooks(id, user.getUser());
		return list;
	}

	public List<Book> getUnreadBooks(int id, String user) {
		List<Book> list = bookDao.getUnreadBooks(id, user);
		System.out.println("unread books for " + user + " " + list);
		return list;
	}

	public void addBook(Book book) {
		book.setUser(user.getUser());
		bookDao.addBook(book);
	}
	
	public void addBook(Book book, String user) {
		book.setUser(user);
		bookDao.addBook(book);
	}
	
	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}
	
	public void deleteBook(int id) {
		bookDao.deleteBook(id);
	}
	
	public Book findById(int id) {
		return bookDao.findById(id);
	}
	
	public int checkTitleExists(String title, int authorId) {
		return bookDao.checkTitleExists(title, authorId, user.getUser());
	}
	public void backup(String fileName) {
		bookDao.backup(fileName);
	}
}
