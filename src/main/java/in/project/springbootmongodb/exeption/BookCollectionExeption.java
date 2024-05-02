package in.project.springbootmongodb.exeption;

public class BookCollectionExeption extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookCollectionExeption(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id)
	{
		return "Book with id "+id+" not found";
	}
	
	public static String BookAlreadyExists()
	{
		return "Book with given name already exists";
	}
	
}
