////////////////////////////////////////////////////////////////////
// [Marco] [Dello Iacovo] [1193421]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayBillTest {
	
	TakeAwayBillImpl takeAwayBill;
    User user;
    List<MenuItem> itemsOrdered;

    @Before
    public void initialize() {
    	takeAwayBill = new TakeAwayBillImpl();
    	user = new User("Marco", "Dello Iacovo", LocalDate.of(1999, 1, 1), "marco.delloiacovo99@gmail.com", "3201234567");
    	itemsOrdered = new ArrayList<MenuItem>(); 
    }
    
    @Test
    public void testItemSum() throws TakeAwayBillException {
        itemsOrdered.add(new MenuItem(MenuItem.item.Budino, "Biancaneve", 4.50));
        itemsOrdered.add(new MenuItem(MenuItem.item.Bevanda, "Cola", 43.6));
        itemsOrdered.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 2.50));
        itemsOrdered.add(new MenuItem(MenuItem.item.Bevanda, "Fanta", 2.20));
        
        assertEquals(52.8, takeAwayBill.getOrderPrice(itemsOrdered, user), 1e-8);
    }
    
    @Test
    public void testNullItemsList() throws TakeAwayBillException {
        itemsOrdered = null;
        
        try {
            takeAwayBill.getOrderPrice(itemsOrdered, user);
        }catch(TakeAwayBillException e) {
            assertEquals("La lista itemsOrdered è uguale a null", e.getMessage());
        }
    }

    @Test
    public void testNullItemInItemsList() {
        itemsOrdered.add(new MenuItem(MenuItem.item.Budino, "Biancaneve", 4.50));
        itemsOrdered.add(null);
        itemsOrdered.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 2.50));
        
        try {
            takeAwayBill.getOrderPrice(itemsOrdered, user);
        }catch(TakeAwayBillException e){
            assertEquals("La lista itemsOrdered contiene un item uguale a null", e.getMessage());
        }
    }
    
    @Test
    public void testNullUser() {
        itemsOrdered.add(new MenuItem(MenuItem.item.Budino, "Biancaneve", 4.50));
        
        try {
            takeAwayBill.getOrderPrice(itemsOrdered, null);
        }catch(TakeAwayBillException e){
            assertEquals("utente è uguale a null", e.getMessage());
        }
    }
    
    @Test
    public void testMoreThan5GelatiDiscount() throws TakeAwayBillException {
        itemsOrdered.add(new MenuItem(MenuItem.item.Gelato, "Fiordilatte", 4.50));
        itemsOrdered.add(new MenuItem(MenuItem.item.Gelato, "Puffo", 2.20));
        itemsOrdered.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 2.50));
        itemsOrdered.add(new MenuItem(MenuItem.item.Gelato, "Stracciatella", 2.20));
        itemsOrdered.add(new MenuItem(MenuItem.item.Gelato, "Cioccolato", 2.20));
        itemsOrdered.add(new MenuItem(MenuItem.item.Gelato, "Amarena", 1.10));
        
        assertEquals(14.15, takeAwayBill.getOrderPrice(itemsOrdered, user), 1e-8);
    }
    
}
