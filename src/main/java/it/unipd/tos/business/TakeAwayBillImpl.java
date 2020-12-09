////////////////////////////////////////////////////////////////////
// [Marco] [Dello Iacovo] [1193421]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.business.exception.TakeAwayBillException;

public class TakeAwayBillImpl implements TakeAwayBill {

    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) 
            throws TakeAwayBillException{
        double total = 0.0D;

        if(itemsOrdered == null) {
            throw new TakeAwayBillException("La lista itemsOrdered è uguale a null");
        }
        if(itemsOrdered.contains(null)) {
            throw new TakeAwayBillException("La lista itemsOrdered contiene un item uguale a null");
        }
        if(user == null) {
            throw new TakeAwayBillException("utente è uguale a null");
        }
        
        for(MenuItem item: itemsOrdered) {
            total = total + item.getPrice();
        }
        
        return total;
    }

}