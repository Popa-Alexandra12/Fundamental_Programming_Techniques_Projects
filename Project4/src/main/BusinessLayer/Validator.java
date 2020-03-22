package BusinessLayer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Validator implements Serializable{
	
	Restaurant rest;
	
	
	public Validator(Restaurant rest) {
		super();
		this.rest = rest;
	}


	public boolean validareAddNewMenuItem(MenuItem mi)
	{
		if(mi==null)
		{
			System.out.println("MenuItem invalid !!!\n");
			return false;
		}
		if(rest.getMenu().contains(mi))
		{
			System.out.println("MenuItem existent in meniu !!!\n");
			return false;
		}
		for(MenuItem iter:rest.getMenu())
		{
			if(iter.getIdItem()==mi.getIdItem())
			{
				System.out.println("MenuItem existent in meniu !!!\n");
				return false;
			}																			//????ar trebui si pt produs compus cv?
		}
		return true;
	}
	
	public boolean validareDeleteMenuItem(int id)
	{
		if(id==0) {
			System.out.println("ID invalid !!!");
			return false;
		}
		int ok=0;
		for(MenuItem iter: rest.getMenu())
		{
			if(id==iter.getIdItem())
			{
				ok=1;
			}
		}
		if(ok==0)
		{
			System.out.println("MenuItem inexistent !!!\n");
			return false;
		}
		return true;
	}
	public boolean validareEditNameOfMenuItem(int id, String newName)
	{
		if(id==0) {
			System.out.println("ID invalid !!!\n");
			return false;
		}
		if(newName==null || newName.equals("")) {
			System.out.println("Nume invalid !!!\n");
			return false;
		}
		int ok=0;
		for(MenuItem iter: rest.getMenu())
		{
			if(id==iter.getIdItem())
			{
				ok=1;
			}
		}
		if(ok==0) {
			{
				System.out.println("MenuItem inexistent !!!\n");
				return false;
			}
		}
		return true;
		
	}
	public boolean validareEditPriceOfMenuItem(int id, int newPrice)
	{
		if(id==0) {
			System.out.println("ID invalid !!!\n");
			return false;
		}
		if(newPrice==0) {
			System.out.println("Pret invalid !!!\n");
			return false;
		}
		int ok=0;
		for(MenuItem iter: rest.getMenu())
		{
			if(id==iter.getIdItem())
			{
				ok=1;
			}
		}
		if(ok==0) {
			{
				System.out.println("MenuItem inexistent !!!\n");
				return false;
			}
		}
		return true;
		
	}
	public boolean validareAddNewOrder(Order o, List<Integer> id)
	{
		if(o==null){
			System.out.println("Comanda invalida !!!\n");
			return false;
		}
		if(id.isEmpty()){
			System.out.println("Comanda nula !!!\n");
			return false;
		}
		if(rest.getRestaurant().containsKey(o)){
			System.out.println("Comanda existenta !!!\n");
			return false;
		}
		int k=0;
		for(int iter:id)
		{
			for(MenuItem iter1:rest.getMenu())
			{
				if(iter==iter1.getIdItem())
				{
					k++;
				}
			}
		}
		if(k!=id.size()) {
			System.out.println("Produse invalide !!!\n");
			return false;
		}
		return true;
	}
	public boolean validareComputePriceForOrder(int id)
	{
		if(id==0) {
			System.out.println("ID invalid !!!\n");
			return false;
		}
		int ok=0;
		for (Map.Entry<Order,List<MenuItem>> iter : rest.getRestaurant().entrySet()) {
			if(iter.getKey().getOrderId()==id)
			{		
				ok=1;
			}
		}
		if(ok==0)
		{
			System.out.println("Comanda inexistenta !!!\n");
			return false;
		}
		return true;
	}
	public boolean validareGenerateBillForOrder(Order o)
	{
		if(o==null) {
			System.out.println("Comanda invalida !!!\n");
			return false;
		}
		int ok=0;
		for (Map.Entry<Order,List<MenuItem>> iter : rest.getRestaurant().entrySet()) {
			if(iter.getKey().getOrderId()==o.getOrderId())
			{	
				ok=1;
				 
			}
		}
		if(ok==0) {
			System.out.println("Comanda inexistenta !!!\n");
			return false;
		}
		return true;
	}
}
