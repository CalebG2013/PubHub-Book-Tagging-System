package examples.pubhub.dao;
import java.util.List;

import examples.pubhub.model.*;
public interface TagDAO {

	
	
	public boolean addBookTag(Tag tag);
	public boolean removeBookTag(Tag tag);
	
	
	public List<Tag> getAllTags(String isbn13);
	public List<Book> getAllBooksWithTag(String tag);

}
