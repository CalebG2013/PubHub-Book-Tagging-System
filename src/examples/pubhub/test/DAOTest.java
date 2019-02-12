package examples.pubhub.test;


import java.util.List;

import examples.pubhub.dao.*;
import examples.pubhub.model.*;

public class DAOTest {

	public static void main (String[] args) {

		TagDAO dao = new TagDAOImpl();


		System.out.println("TEST: find and print tags for 111111111111");
		List<Tag> list = dao.getAllTags("1111111111111");

		for(int i = 0; i < list.size(); i++) {
			Tag t = list.get(i);
			System.out.println(t);

		}
		System.out.println("test adding tag to book");
		Tag testTag = new Tag();
		testTag.setIsbn13("111111111111");
		testTag.setTag("FUN!");
		dao.addBookTag(testTag);
		System.out.println(testTag.toString());

		System.out.println();

		System.out.println("test removing tag from book");
		dao.removeBookTag(testTag);
		System.out.println("removed tag");

		System.out.println("Test retriving all books with the same tag");


		List<Book> list2 = dao.getAllBooksWithTag("Multiple Languages");
		System.out.println("testing tag: Multiple Languages");

		for(int i = 0; i < list2.size(); i++) {
			Book t = list2.get(i);
			System.out.println(t);

		}
	
	}
}
