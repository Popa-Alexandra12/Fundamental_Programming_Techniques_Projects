package BusinessLayer;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import PresentationLayer.Observer;

/** 
 *   @invariant    
 *   isWellFormed() 
 */
public class Restaurant extends Observable implements RestaurantProcessing,Serializable {
	private static final long serialVersionUID = 1234L;
	private Map<Order,List<MenuItem>> restaurant;
	private List<MenuItem> menu;
	private List<Observer> observers;
	public Validator v;	
	public Restaurant() {
		super();
		restaurant=new HashMap<Order,List<MenuItem>>();    
		menu=new ArrayList<MenuItem>();
		observers = new ArrayList<Observer>();
		v=new Validator(this);
	}
		public List<MenuItem> getMenu() {
		return menu;
	}
	public Map<Order, List<MenuItem>> getRestaurant() {
		return restaurant;
	}
	protected boolean isWellFormed() {
        for (Map.Entry iter : restaurant.entrySet()) {
        	if(iter.getKey()==null || iter.getValue()==null )
        	  return false;
        }
       
        for(MenuItem iter: menu)   
        {
        	if(iter==null)
        		return false;
        }
        return true;
	}
	public void addNewMenuItem(MenuItem mi) {
		if(v.validareAddNewMenuItem(mi)) {
		assert mi!=null && !menu.contains(mi);
		
		for(int i=0;i<menu.size();i++)
		{
			assert mi.getIdItem()!=menu.get(i).getIdItem();
		}
		assert isWellFormed(); 
		int sizePre = menu.size();
		menu.add(mi);
		mi.computePrice();
		int sizePost = menu.size(); 
		assert sizePost == sizePre + 1 && menu.contains(mi);
		assert isWellFormed();	
		}
	}
	public void deleteMenuItem(int id) {
		if(v.validareDeleteMenuItem(id)) {
		assert id!=0 && !menu.isEmpty();
		assert isWellFormed(); 
		int sizePre = menu.size();
		int ok=0;
		for(int i=0;i<menu.size();i++)
		{
			if(id==menu.get(i).getIdItem())
			{
				ok=1;
				menu.remove(menu.get(i));
			}
		}
		assert ok==1;
		int sizePost = menu.size(); 
		assert sizePost == sizePre - 1 ;
		assert isWellFormed();		
		}	
	}
	public void editNameOfMenuItem(int id, String newName) {
		if(v.validareEditNameOfMenuItem(id, newName)) {
		assert id!=0 && newName!=null && !menu.isEmpty();
		assert isWellFormed(); 
		int ok=0;
		for(MenuItem iter: menu)
		{
			if(id==iter.getIdItem())
			{
				ok=1;
				iter.setName(newName);
			}
		}
		assert ok==1;
		int ok1=0;
		for(MenuItem iter: menu)
		{
			if(id==iter.getIdItem() && iter.getName().equals(newName))
			{
				ok1=1;	
			}
		}
		assert ok1==1;
		assert isWellFormed();
		}
	}
	public void editPriceOfMenuItem(int id, int newPrice) {
		if(v.validareEditPriceOfMenuItem(id, newPrice)) {
		assert id!=0  && !getMenu().isEmpty();
		assert isWellFormed();
		int ok=0,ok1=0,ok2=0;
		for(MenuItem iter: menu)
		{	if(id==iter.getIdItem())
			{	ok=1;
				if(iter instanceof BaseProduct)
				{	iter.setPrice(newPrice);
					iter.computePrice();
					for(MenuItem iter1: menu)
					{	if(iter1 instanceof CompositeProduct)
						{	if(((CompositeProduct) iter1).getCompositeProduct().contains(iter))
								iter1.computePrice();
						}
					}
					ok1=1;
				}
			}
		}
		assert ok==1 && ok1==1;
		for(MenuItem iter: menu)
		{	if(id==iter.getIdItem() && iter.getPrice()==newPrice)
			ok2=1;	
		}
		assert ok2==1;
		assert isWellFormed();
		}
	}
	public void addNewOrder(Order o, List<Integer> id) {
		if(v.validareAddNewOrder(o, id)){
		assert o!=null && !restaurant.containsKey(o) && !id.isEmpty();// && menu.containsAll(mi);
		assert isWellFormed(); 
		int sizePre = restaurant.size();
		int k=0;
		List<MenuItem> list=new ArrayList<MenuItem>();
		for(int iter:id)
		{
			for(MenuItem iter1:menu)
			{
				if(iter==iter1.getIdItem())
				{
					k++;
					list.add(iter1);
				}
			}
		}
		if(k==id.size())
		{
			assert true;
			restaurant.put(o, list);
		}
		int sizePost = restaurant.size(); 
		assert sizePost == sizePre + 1 &&  restaurant.containsKey(o);
		assert isWellFormed(); 
		}
		
	}
	public int computePriceForOrder(int id) {
		if(v.validareComputePriceForOrder(id)) {
		assert id!=0 ;
		assert isWellFormed();
		int price=0;
		int ok=0;
		for (Map.Entry<Order,List<MenuItem>> iter : restaurant.entrySet()) {
			if(iter.getKey().getOrderId()==id)
			{		ok=1;
				 for(MenuItem iter1: iter.getValue())   
		         {
		        	 price+=iter1.getPrice();
		         }
			}
		}
		assert ok==1;
		assert isWellFormed();
		return price;
		} return 0;
	}
	public void generateBillForOrder(Order o) {
		if(v.validareGenerateBillForOrder(o)) {
		assert o!=null;
		assert isWellFormed();
		int ok=0;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Order_bill.txt"));
			writer.write("\n-------- Chitanta pentru comanda numarul: "+o.getOrderId()+"--------\n\n\n");
			writer.write("Data comenzii : "+o.getDate()+"\n\nMasa cu numarul : "+o.getTable()+"\n\n");
			writer.write("------------------------------------------------------------------\n\nProdusele comandate sunt: \n\n");
			for (Map.Entry<Order,List<MenuItem>> iter : restaurant.entrySet()) {
				if(iter.getKey().getOrderId()==o.getOrderId())
				{	ok=1;
					 for(MenuItem iter1: iter.getValue())   
			         {	 writer.write(iter1.toString()+"\n\n");
						 if(iter1 instanceof CompositeProduct)
						 {  writer.write("                     Meniul contine :\n\n");
							 for(MenuItem iter2: ((CompositeProduct) iter1).getCompositeProduct())   
					         {
								 writer.write("                     -> "+iter2.getIdItem() + ". " + iter2.getName()+"\n\n");
					         }
						 }
			         }
				}
			}
			writer.write("------------------------------------------------------------------\n\nPret total :"+computePriceForOrder(o.getOrderId())+"\n\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert ok==1;
		assert isWellFormed();
		}
	}
	public Object[][] viewTabelMenu()
	{
		List<MenuItem> produse=new ArrayList<MenuItem>();
		Object[][] tabel=new Object[1000][1000];
		int linie=0;
		for(MenuItem mi:menu)
		{	int coloana=0;
			for (Field field : MenuItem.class.getDeclaredFields()) {
				field.setAccessible(true);
				try {
					tabel[linie][coloana]=field.get(mi);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				coloana++;	
			}
			linie++;
		}
		return tabel;
	}
	public Object[][] viewTabelRestaurant()
	{
		Object[][] tabel=new Object[1000][1000];
		int linie=0;
		for(Map.Entry<Order,List<MenuItem>> iter : restaurant.entrySet())
		{	
			tabel[linie][0]=iter.getKey().getOrderId();
			tabel[linie][1]=iter.getKey().getDate();
			tabel[linie][2]=iter.getKey().getTable();
			String s="";
			for(MenuItem mi: iter.getValue())
			{
				s=s+mi.getName()+" / ";
			}
			tabel[linie][3]=s;
			linie++;
		}
		return tabel;
	}
	public void serialization(Restaurant r)
	{	 String filename = "file.ser";
		 try
	        {  FileOutputStream file = new FileOutputStream(filename); 
	            ObjectOutputStream out = new ObjectOutputStream(file); 
	            out.writeObject(r); 
	            out.close(); 
	            file.close(); 
	            System.out.println("Object has been serialized"); 
	        }  
	        catch(IOException ex) 
	        { 	ex.printStackTrace();
	            System.out.println("IOException is caught"); 
	        } 
	}
	public Restaurant deserialization()
	{	 String filename = "file.ser";
			Restaurant r=null;
		 try
	        {  FileInputStream file = new FileInputStream(filename); 
	            ObjectInputStream in = new ObjectInputStream(file); 
	            r = (Restaurant) in.readObject(); 
	            System.out.println("Object has been deserialized"); 
	            in.close(); 
	            file.close();
	        } 
	        catch(IOException ex) 
	        {	ex.printStackTrace();
	            System.out.println("IOException is caught"); 
	        }
	        catch(ClassNotFoundException ex) 
	        { ex.printStackTrace();
	            System.out.println("ClassNotFoundException is caught"); 
	        } 
		 return r;
	
	}
	public void attach(Observer observer){
		if(observers == null) {
			observers = new ArrayList<Observer>();
		}
	      observers.add(observer);
	      System.out.println(observers);
	   }
		public void notifyAllObservers(){
	      for (Observer observer : observers) {
	         observer.update();
	      }
	   } 	
	public List<Observer> getObservers() {
		return observers;
	}
}
