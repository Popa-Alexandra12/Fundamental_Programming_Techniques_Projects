package BusinessLayer;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	private int orderId;
	private Date date;
	private int table;
	public Order(int orderId, Date date, int table) {
		super();
		this.orderId = orderId;
		this.date = date;
		this.table = table;
	}
	
	public int getOrderId() {
		return orderId;
	}

	public Date getDate() {
		return date;
	}

	public int getTable() {
		return table;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTable(int table) {
		this.table = table;
	}
  

	@Override
	public int hashCode() {
		final int p = 31;
		int hash = 0;
		int c;
		c=new Integer(orderId).hashCode()*date.hashCode()*new Integer(table).hashCode();
		hash=hash*p+c;
		return hash;
	}

	@Override
	public boolean equals(Object o) {
		if (o==this)
			return true;
		if (o == null)
			return false;
		if (!(o instanceof Order)) return false; 
		if (getClass() != o.getClass())
			return false;
		Order other = (Order) o;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (orderId != other.orderId)
			return false;
		if (table != other.table)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", date=" + date + ", table=" + table + "]";
	}
	

}
