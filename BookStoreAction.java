/* Name: Christopher Kanazeh
 * Course: CNT 4714 - Fall 2015
 * Assignment Title: Program 1 - Event-Driven Programming
 * Date: Sunday September 13, 2015
 */

package bookstore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class BookStoreAction {

private ArrayList<BookStore> bBooks;
	
	private BookStoreInvoice bInvoice; 
	
	private boolean bRead = false;
	
	private final float TAX = 0.06f;
	
	private final String FILE = "inventory.txt";
	private final String FILE_OUT = "transactions.txt";
	private final String DISPLAY_TAX_RATE = "6%";
	
	/**
	 * Constructor, initializes the Invoice and on first run reads in the inventory.
	 */
	public BookStoreAction(){
		this.bInvoice = new BookStoreInvoice();
		
		if(!bRead){
			ReadInventory();
			bRead = true;
		}
	}
	
	/**
	 * Adds the current order to the invoices.
	 * @param bBookStore, The current Book object.
	 * @param bQuantity, The amount of that Book the customer wants to buy.
	 * @param bNumItemsInOrder, The total amount of expected orders for the invoice. 
	 */
	public void ProcessOrder(BookStore bBookStore,int bQuantity, int bNumItemsInOrder){
		BookStoreOrder bOrder = new BookStoreOrder();
		bOrder.setBookStore(bBookStore);
		bOrder.setQuantityForItem(bQuantity);
		bOrder.setDiscount(Float.valueOf(getDisplayDiscount(bQuantity)));
		bOrder.setSubtotal(CalculateDiscount(bBookStore,bQuantity));
		
		this.bInvoice.addOrder(bOrder);
		this.bInvoice.setSubtotal(CalculateDiscount(bBookStore,bQuantity));
		this.bInvoice.setGrandtotal(this.bInvoice.getSubtotal() + (this.bInvoice.getSubtotal() * TAX));
		this.bInvoice.setNumItemsInOrder(bNumItemsInOrder);
		
		
	}
	
	/**
	 * Finds a Book based on the ID.
	 * @param bookID, The ID of the Book.
	 * @return The Book object found, if not a blank Book object.
	 */
	public BookStore FindBookStore(int bookID){
		for(BookStore b:bBooks){
			if(b.getID() == bookID){
				return b;
			}
		}
		return new BookStore();
	}
	
	/**
	 * Sets the Book Info field using a decorator pattern.
	 * @param bBookStore, The Current Book object.
	 * @param bQuantity, The quantity of that Book.
	 */
    public void SetBookInfo(BookStore bBookStore, int bQuantity){
    	bBookStore.setInfo(String.valueOf(bBookStore.getID()) +" "+bBookStore.getName()+" "+String.valueOf(bBookStore.getPrice() + 
				" " + String.valueOf(bQuantity) + " " + String.valueOf(getDisplayDiscount(bQuantity))+ "% "+String.valueOf(CalculateDiscount(bBookStore,bQuantity))));
    }
    
    /**
     * Calculates a subtotal solely for display.
     * @param bBookStore, The Current Book object.
     * @param bQuantity, The quantity of that Book.
     * @return Subtotal
     */
    public float getDisplaySubtotal(BookStore bBookStore, int bQuantity){
    	return bInvoice.getSubtotal() + CalculateDiscount(bBookStore,bQuantity);
    }
    
    /**
     * Creates a string containing information of all the processed orders.
     * @return Processed orders
     */
    public String getDisplayViewOrder(){
    	String bViewOrders = "";
    	int index = 1;
    	for(BookStoreOrder bOrder: bInvoice.getOrders()){
    		bViewOrders = bViewOrders + String.valueOf(index)+". "+bOrder.getBookItems().getInfo()+"\n";
    		index++;
    	}
    	return bViewOrders;
    }
	
    /**
     * Writes all of the orders in the object Invoice to a file.
     */
    public void WriteInvoice(){
		FileWriter bFW = null;
		BufferedWriter bBw = null;
		try {
			bFW = new FileWriter(FILE_OUT,true);
		} catch (IOException e) {
			System.out.println("Sorry could not open file for writing!");
			e.printStackTrace();
		}
		bBw = new BufferedWriter(bFW);
		
		for(BookStoreOrder bOrder: this.bInvoice.getOrders()){
			try {
				bBw.write(this.bInvoice.getTransactionStamp()+", "+bOrder.getBookItems().getID()+", "
						+bOrder.getBookItems().getName()+", "+bOrder.getBookItems().getPrice()+", "
						+bOrder.getQuantityForItem()+", "+bOrder.getDiscount()+", "+bOrder.getSubtotal()+", "
						+this.bInvoice.getTimeStamp());
				bBw.newLine();
			} catch (IOException e) {
				System.out.println("Sorry could not write to file!");
				e.printStackTrace();
			}
		}
		try {
			bBw.close();
			bFW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}    
    
    /**
     * Creates a string of all the Invoice information to be displayed in a message dialog.
     * @return Invoice information
     */
    public String DisplayInvoice(){
    	this.bInvoice.setDate();
    	String bMessage = "Date: ";
    	
    	bMessage = bMessage + this.bInvoice.getTimeStamp() + "\n\n";
    	bMessage = bMessage + "Number of line items: " + this.bInvoice.getNumItemsInOrder() + "\n\n";
    	bMessage = bMessage + "Item# / ID / Title / Price / Qty / Disc % / Subtotal:\n\n";
    	bMessage = bMessage + getDisplayViewOrder() + "\n\n";
    	bMessage = bMessage + "Order subtotal: " + this.bInvoice.getSubtotal() + "\n\n";
    	bMessage = bMessage + "Tax rate:    " + DISPLAY_TAX_RATE + "\n\n";
    	bMessage = bMessage + "Tax amount:    $" + (this.bInvoice.getSubtotal() * TAX) + "\n\n";
    	bMessage = bMessage + "Order total:    " + this.bInvoice.getGrandtotal() + "\n\n";
    	bMessage = bMessage + "Thank you for shopping at the Book store! \n\n";
    	
    	return bMessage;
    }
	
    /**
     * Determines the discount amount based on the quantity of the item ordered; solely for display. 
     * @param bQuantity
     * @return Discount amount
     */
    private int getDisplayDiscount(int bQuantity){
    	if(bQuantity < 5){
			return 0;
		}else if(bQuantity < 10){
			return 10;
		}else if(bQuantity < 15){
			return 15;
		}else if(bQuantity >= 15){
			return 20;
		}else return 0;
    }
	
    /**
     * Determines the discounted subtotal. 
     * @param bBookStore
     * @param bQuantity
     * @return Discounted subtotal
     */
    private float CalculateDiscount(BookStore bBookStore, int bQuantity){
		float bSubtotal = bBookStore.getPrice() * (float) bQuantity;
		
		if(bQuantity < 5){
			return bSubtotal;
		}else if(bQuantity < 10){
			return bSubtotal - (bSubtotal * 0.1f);
		}else if(bQuantity < 15){
			return bSubtotal - (bSubtotal * 0.15f);
		}else if(bQuantity >= 15){
			return bSubtotal - (bSubtotal * 0.2f);
		}else return 0f;
	}
	
    /**
     * Reads in the inventory from a file into Book objects and stores them in an ArrayList.
     */
    private void ReadInventory(){
		//Read in the inventory to the Book object
		FileReader bFR = null;
		BufferedReader bBR = null;
		bBooks = new ArrayList<BookStore>();
		try {
			bFR = new FileReader(FILE);
			
		} catch (FileNotFoundException e) {
			JOptionPane bBox = new JOptionPane("Waning could not open file for reading!",JOptionPane.ERROR_MESSAGE);
			bBox.setVisible(true);
			e.printStackTrace();
		}	
		
		try {
			bBR = new BufferedReader(bFR);
			
			String bLine;
			while((bLine = bBR.readLine()) != null){
				String[] bCurrentBook = new String[3];
				BookStore bBookStore = new BookStore();
				
				bCurrentBook = bLine.split(",", 3);
				bBookStore.setID(Integer.valueOf(bCurrentBook[0]));
				bBookStore.setName(bCurrentBook[1]);
				bBookStore.setPrice(Float.valueOf(bCurrentBook[2]));
				
				bBooks.add(bBookStore);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
