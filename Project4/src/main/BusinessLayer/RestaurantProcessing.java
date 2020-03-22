package BusinessLayer;

import java.util.List;

public interface RestaurantProcessing {
	
	/**
	 *  Adaugarea unui nou produs in meniu
	 *  @pre mi!=null && !getMenu().contains( mi) 
	 *  @pre @forall i : [0 .. getMenu().size() -1 ] @ 
	 *                mi.getIdItem()!=getMenu().get(i).getIdItem()
	 *  @post getMenu().size() == getMenu().size()@pre + 1 && getMenu().contains(mi)
	 */
	public void addNewMenuItem(MenuItem mi);
	/**
	 *  Stergerea unui nou produs in meniu
	 *  @pre id!=0 && !getMenu().isEmpty() 
	 *  @pre @exists i : [0 .. getMenu().size() -1 ] @ 
	 *               id==getMenu().get(i).getIdItem()
	 *  @post getMenu().size() == getMenu().size()@pre - 1 
	 */
	public void deleteMenuItem(int id);
	/**
	 *  Editarea denumirii unui produs din meniu
	 *  @pre id!=0 && newName!=null && !getMenu().isEmpty() 
	 *  @pre @exists i : [0 .. getMenu().size() -1 ] @ 
	 *               id==getMenu().get(i).getIdItem()
	 *  @post @exists i : [0 .. getMenu().size() -1 ] @ 
	 *               id==getMenu().get(i).getIdItem() && getMenu().get(i).getName().equals(newName)
	 */
	public void editNameOfMenuItem(int id,String newName);
	/**
	 *  Editarea pretului unui produs din meniu
	 *  @pre id!=0 && !getMenu().isEmpty() 
	 *  @pre @exists i : [0 .. getMenu().size() -1 ] @ 
	 *               id==getMenu().get(i).getIdItem() && getMenu().get(i) instanceof BaseProduct
	 *  @post @exists i : [0 .. getMenu().size() -1 ] @ 
	 *               id==getMenu().get(i).getIdItem() && getMenu().get(i).getPrice()==newPrice
	 */
	public void editPriceOfMenuItem(int id,int newPrice);
	/**
	 *  Adaugarea unui noi comenzi
	 *	@pre o!=null && !getRestaurant().containsKey(o) && !id.isEmpty() 
	 *  @pre @forall i : [0 .. id.size() -1 ] @ 
	 *                @exists j : [0 .. getMenu().size() -1 ] @ 
	 *              	id.get(i)==getMenu().get(j).getIdItem() 
	 *  @post getRestaurant().size() == getRestaurant().size()@pre + 1 && getRestaurant().containsKey(o)
	 */
	public void addNewOrder(Order o,List<Integer> mi);
	/**
	 *  Calcularea pretului pentru o comanda
	 *  @pre id!=0 
	 *  @pre @exists i : [0 .. getRestaurant().size() -1 ] @ 
	 *               id==getRestaurant().get(i).getKey().getOrderId()
	 *  @post @nochange
	 */
	public int computePriceForOrder(int id);  
	/**
	 *  Se genereaza afcatura in format .txt
	 *  @pre o!=null
	 *  @pre @exists i : [0 .. getRestaurant().size() -1 ] @ 
	 *               o.getOrderId()==getRestaurant().get(i).getKey().getOrderId()
	 *  @post @nochange
	 */
	public void generateBillForOrder(Order o); 
	

}
