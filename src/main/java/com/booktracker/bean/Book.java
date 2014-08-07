package com.booktracker.bean;

public class Book {

	int bookId;
	String title;
	int authorId;
	String series;
	int year;
	boolean readStatus;
	boolean ownStatus;
	String user;
	String pseudonym;
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isReadStatus() {
		return readStatus;
	}

	public void setReadStatus(boolean readStatus) {
		this.readStatus = readStatus;
	}

	public boolean isOwnStatus() {
		return ownStatus;
	}

	public void setOwnStatus(boolean ownStatus) {
		this.ownStatus = ownStatus;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	@Override
	public String toString() {
		return "Book [" +
				"id=" + bookId + 
				",title=" + title + 
				",author id=" + authorId + 
				",series=" + series + 
				",title=" + title + 
				",year=" + year +
				",read status=" + readStatus +
				",own status=" + ownStatus +
				",pseudonym=" + pseudonym +
				"]";
	}
}
