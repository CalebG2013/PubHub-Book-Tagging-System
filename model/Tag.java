package examples.pubhub.model;

public class Tag {


	private String isbn13;
	private String tag; 



	public String getIsbn13() {
		return this.isbn13;
	}

	public void setIsbn13(String isbn) {
		this.isbn13 = isbn;
	}


	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String toString() {
		
		return "Tag isbn13: " + this.getIsbn13() + "\n" +
	    "tag: " + this.getTag();
		
	}
}

