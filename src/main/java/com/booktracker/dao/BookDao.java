package com.booktracker.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.booktracker.bean.Book;

public class BookDao extends JdbcDaoSupport {

	public List<Book> getBooks(int id, String user) {

		String sqlQuery = "select * from book where author_id = ? and user = ? order by series, year";

		List<Book> resultList = new ArrayList<Book>();

		resultList = this.getJdbcTemplate().query(sqlQuery, new Object[] { id, user },
				new RowMapper<Book>() {
					Book book = null;

					public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
						if (rs != null) {
							book = new Book();

							book.setBookId(rs.getInt("book_id"));
							book.setTitle(rs.getString("title"));
							book.setAuthorId(rs.getInt("author_id"));
							book.setSeries(rs.getString("series"));
							book.setReadStatus(rs.getBoolean("read_status"));
							book.setOwnStatus(rs.getBoolean("own_status"));
							book.setYear(rs.getInt("year"));
							book.setUser(rs.getString("user"));
							book.setPseudonym(rs.getString("pseudonym"));

						}
						return book;
					}
				});

		return resultList;
	}

	public List<Book> getUnreadBooks(int id, String user) {

		String sqlQuery = "select * from book where author_id = ? and user = ? and read_status = 0 and own_status = 0 order by series, year";

		List<Book> resultList = new ArrayList<Book>();

		resultList = this.getJdbcTemplate().query(sqlQuery, new Object[] { id, user },
				new RowMapper<Book>() {
					Book book = null;

					public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
						if (rs != null) {
							book = new Book();

							book.setBookId(rs.getInt("book_id"));
							book.setTitle(rs.getString("title"));
							book.setAuthorId(rs.getInt("author_id"));
							book.setSeries(rs.getString("series"));
							book.setReadStatus(rs.getBoolean("read_status"));
							book.setOwnStatus(rs.getBoolean("own_status"));
							book.setYear(rs.getInt("year"));
							book.setUser(rs.getString("user"));
							book.setPseudonym(rs.getString("pseudonym"));

						}
						return book;
					}
				});

		return resultList;
	}

	public void addBook(Book book) {

		String sqlQuery = "insert into book (title, author_id, series, read_status, own_status, year, user, pseudonym) values (?, ?, ?, ?, ?, ?, ?, ?)";
		this.getJdbcTemplate().update(sqlQuery, book.getTitle(), book.getAuthorId(),
				book.getSeries(), book.isReadStatus(), book.isOwnStatus(), book.getYear(),
				book.getUser(), book.getPseudonym());

	}

	public void updateBook(Book book) {

		String sqlQuery = "update book set title = ?, series = ? ,read_status = ?, own_status = ?, year = ?, pseudonym = ? where book_id = ?";
		this.getJdbcTemplate().update(sqlQuery, book.getTitle(), book.getSeries(),
				book.isReadStatus(), book.isOwnStatus(), book.getYear(), book.getPseudonym(), book.getBookId());

	}

	public void deleteBook(int id) {

		String sqlQuery = "delete from book where book_id = ?";
		System.out.println("deleting book " + id);
		this.getJdbcTemplate().update(sqlQuery, id);

	}

	public Book findById(int id) {

		String sqlQuery = "select * from book where book_id = ?";
		Book book = (Book) this.getJdbcTemplate().queryForObject(sqlQuery, new Object[] { id },
				new BeanPropertyRowMapper<Book>(Book.class));

		System.out.println("BookDAO " + book.toString());
		return book;
	}

	public int checkTitleExists(String title, int authorId, String user) {
		String sqlQuery = "select count(*) from book where title = ? and author_id = ? and user = ?";
		return this.getJdbcTemplate().queryForInt(sqlQuery, new Object[] { title, authorId, user });
	}

	public void backup(String fileName) {

		String sqlQuery = "SELECT * INTO OUTFILE '" 
				+ fileName
				+ "'"
				+ "FIELDS TERMINATED BY ',' "
			    + "LINES TERMINATED BY '\n'" 
				+ "FROM book";

		this.getJdbcTemplate().execute(sqlQuery);
	}
}
