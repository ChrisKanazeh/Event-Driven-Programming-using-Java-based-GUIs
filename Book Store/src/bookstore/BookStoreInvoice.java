/* Name: Christopher Kanazeh
 * Course: CNT 4714 - Fall 2015
 * Assignment Title: Program 1 - Event-Driven Programming
 * Date: Sunday September 13, 2015
 */

package bookstore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class BookStoreInvoice {

	private ArrayList<BookStoreOrder> bOrder;
	
	private int bNumItemsInOrder = 0;
    private float bSubtotal = 0;
    private float bGrandtotal = 0;
    
    private String bTimeStamp;
    private String bTransactionStamp;
    
    /**
     * Constructor initializes the Orders ArrayList. 
     */
    public BookStoreInvoice(){
    	this.bOrder = new ArrayList<BookStoreOrder>();
    }
    
    /**
     * Getters and setters for Invoice attributes.
     * @param bNumItemsInOrder,bSubtotal,bGrandtotal,bOrder
     */
    public void setNumItemsInOrder(int bNumItemsInOrder){
    	this.bNumItemsInOrder = bNumItemsInOrder;
    }
    public void setSubtotal(float bSubtotal){
    	this.bSubtotal += bSubtotal;
    }
    public void setGrandtotal(float bGrandtotal){
    	this.bGrandtotal = bGrandtotal;
    }
    public void setDate(){
    	Date bDate = new Date();
    	DateFormat bDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a z");
    	this.bTimeStamp = bDateFormat.format(bDate);
    	bDateFormat = new SimpleDateFormat("yyMMddHHmmss");
    	this.bTransactionStamp = bDateFormat.format(bDate);
    }
    public void addOrder(BookStoreOrder bOrder){
    	this.bOrder.add(bOrder);
    }
    public int getNumItemsInOrder(){
    	return this.bNumItemsInOrder;
    }
    public float getSubtotal(){
    	return this.bSubtotal;
    }
    public float getGrandtotal(){
    	return this.bGrandtotal;
    }
    public ArrayList<BookStoreOrder> getOrders(){
    	return this.bOrder;
    }
    public String getTimeStamp(){
    	return this.bTimeStamp;
    }
    public String getTransactionStamp(){
    	return this.bTransactionStamp;
    }
	
}
