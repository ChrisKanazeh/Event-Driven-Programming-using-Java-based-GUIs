/* Name: Christopher Kanazeh
 * Course: CNT 4714 - Fall 2015
 * Assignment Title: Program 1 - Event-Driven Programming
 * Date: Sunday September 13, 2015
 */

package bookstore;


public class BookStoreOrder {

private BookStore bBookStore;
    
    private int bQuantityForItem;
    
    private float bDiscount;
    private float bSubtotal;

    /**
     * Getters and setters for Order attributes.
     * @param bQuantityForItem,bBookStore,bDiscount,bSubtotal
     */
    public void setQuantityForItem(int bQuantityForItem){
    	this.bQuantityForItem = bQuantityForItem;
    }
    public void setBookStore(BookStore bBookStore){
    	this.bBookStore = bBookStore;
    }
    public void setDiscount(float bDiscount){
    	this.bDiscount = bDiscount;
    }
    public void setSubtotal(float bSubtotal){
    	this.bSubtotal = bSubtotal;
    }
    public BookStore getBookItems(){
    	return this.bBookStore;
    }
    public int getQuantityForItem(){
    	return this.bQuantityForItem;
    }
    public float getDiscount(){
    	return this.bDiscount;
    }
    public float getSubtotal(){
    	return this.bSubtotal;
    }
	
}
