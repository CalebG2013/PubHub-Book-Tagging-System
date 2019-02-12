package examples.pubhub.dao;

import examples.pubhub.utilities.*;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public class TagDAOImpl implements TagDAO{

	private Connection connection = null;
	private PreparedStatement statement = null;


	@Override
	public boolean addBookTag(Tag tag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO \"Tags\" VALUES (?,?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, tag.getIsbn13());
			statement.setString(2, tag.getTag());

			if (statement.executeUpdate() != 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
		// TODO Auto-generated method stub
	

	}




	@Override
	public boolean removeBookTag(Tag tag) { 
		try {
			this.connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM \"Tags\" WHERE isbn_13=? AND tag=?";
			statement = connection.prepareStatement(sql);

			statement.setString(1, tag.getIsbn13());
			statement.setString(2, tag.getTag());

			if (statement.executeUpdate()!=1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	@Override
	public List<Tag> getAllTags(String isbn13) {

		List<Tag> tags = new ArrayList<>();

		try {

			this.connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM \"Tags\" WHERE isbn_13 = ?";
			statement = connection.prepareStatement(sql);

			statement.setString(1, isbn13);

			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				Tag tag = new Tag();
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTag(rs.getString("tag"));

				tags.add(tag);

			}

		}	catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return tags;

	}
	
	


	@Override
	public List<Book> getAllBooksWithTag(String tag) {

		List<Book> books = new ArrayList<Book>();

		try {
			this.connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM books a LEFT JOIN \"Tags\" b ON a.isbn_13 = b.isbn_13 WHERE b.tag LIKE ?";
			this.statement = connection.prepareStatement(sql);
			
			this.statement.setString(1, "%"+tag+"%");

			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				Book b = new Book();

				b.setIsbn13(rs.getString("isbn_13"));
				b.setAuthor(rs.getString("author"));
				b.setPrice(rs.getDouble("price"));
				b.setPublishDate(rs.getDate("publish_date").toLocalDate());
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getBytes("content"));

				books.add(b);



			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return books;
	}






	//closes all the open resources
	private void closeResources() {
		try {
			if(statement != null)
				statement.close();
		} catch (SQLException e) {
			System.out.println("Could not close the statement");
			e.printStackTrace();

		}
		try {
			if(connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("could not close the connection");
			e.printStackTrace();
		}

	}





}
