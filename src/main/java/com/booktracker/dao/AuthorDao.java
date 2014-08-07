package com.booktracker.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.booktracker.bean.Author;

public class AuthorDao extends JdbcDaoSupport {

	public List<Author> getAuthors(String user) {

		String sqlQuery = "select * from author where user = ? order by last_name";

		List<Author> resultList = new ArrayList<Author>();

		resultList = this.getJdbcTemplate().query(sqlQuery, new Object[] { user },
				new RowMapper<Author>() {
					Author author = null;

					public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
						if (rs != null) {
							author = new Author();

							author.setAuthorId(rs.getInt("author_id"));
							author.setFirstName(rs.getString("first_name"));
							author.setLastName(rs.getString("last_name"));
							author.setRating(rs.getInt("rating"));
							author.setUser(rs.getString("user"));

						}
						return author;
					}
				});

		return resultList;
	}

	public void addAuthor(String firstName, String lastName, int rating, String user) {

		String sqlQuery = "insert into author (first_name, last_name, rating, user) values (?, ?, ?, ?)";
		this.getJdbcTemplate().update(sqlQuery, firstName, lastName, rating, user);


	}

	public void updateAuthor(Author author) {

		String sqlQuery = "update author set first_name = ?, last_name = ?, rating = ? where author_id = ?";
		System.out.println("changing author " + author.toString());
		int updated = this.getJdbcTemplate().update(sqlQuery, author.getFirstName(),
				author.getLastName(), author.getRating(), author.getAuthorId());
		System.out.println("updated " + updated + " rows");

	}

	public void deleteAuthor(int id) {

		String sqlQuery = "delete from author where author_id = ?";
		System.out.println("deleting author " + id);
		this.getJdbcTemplate().update(sqlQuery, id);

	}

	public Author findById(int id, String user) {

		String sqlQuery = "select * from author where author_id = ? and user = ?";
		return (Author) this.getJdbcTemplate().queryForObject(sqlQuery, new Object[] { id, user },
				new BeanPropertyRowMapper<Author>(Author.class));

	}

	public Author findByName(String firstName, String lastName, String user) {

		String sqlQuery = "select * from author where first_name = ? and last_name = ? and user = ?";

		Author author = null;
		try {
			author = (Author) this.getJdbcTemplate().queryForObject(sqlQuery,
					new Object[] { firstName, lastName, user }, new BeanPropertyRowMapper<Author>(Author.class));
		} catch (EmptyResultDataAccessException e) {
			
		}
		return author;
	}

	public int checkAuthorExists(String firstName, String lastName, String user) {
		String sqlQuery = "select count(*) from author where first_name = ? and last_name = ? and user = ?";
		return this.getJdbcTemplate().queryForInt(sqlQuery, new Object[] { firstName, lastName, user });
	}

	public int getNumBooks(int id) {

		String sqlQuery = "select count(*) from book where author_id = ?";
		return this.getJdbcTemplate().queryForInt(sqlQuery, new Object[] { id });

	}
	public void backup(String fileName) {

		String sqlQuery = "SELECT * INTO OUTFILE '"
				+ fileName
				+ "'"
				+ "FIELDS TERMINATED BY ',' "
			    + "LINES TERMINATED BY '\n'" 
				+ "FROM author";

		this.getJdbcTemplate().execute(sqlQuery);
	}

}
