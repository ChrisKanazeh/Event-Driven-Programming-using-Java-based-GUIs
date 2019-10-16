/* Name: Christopher Kanazeh
 * Event-Driven Programming
 */

//
package bookstore;

public class BookStore {
	
	private int bookID;
    private float bookPrice;
    private String bookName;
    private String bookInfo;
    
    /**
     * Constructor defaults with the info message 
     * "No Book found..." to be set later
     */
    public BookStore(){
    	setInfo("No Book found with that ID!");
    }
    
    /**
     * Getters and setters for Book attributes
     * @param bookID,bookName,bookPrice,bookInfo
     */
    public void setID(int bookID){
    	this.bookID = bookID;
    }
    public void setName(String bookName){
    	this.bookName = bookName;
    }
    public void setPrice(float bookPrice){
    	this.bookPrice = bookPrice;
    }
    public void setInfo(String Info){
    	this.bookInfo = Info;
    }
    public int getID(){
    	return this.bookID;
    }
    public String getName(){
    	return this.bookName;
    }
    public float getPrice(){
    	return this.bookPrice;
    }
    public String getInfo(){
    	return this.bookInfo;
    }	
	
}
