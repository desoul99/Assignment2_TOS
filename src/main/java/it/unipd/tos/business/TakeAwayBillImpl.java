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
        int countGelati = 0;
        double cheapestGelato = Double.MAX_VALUE;

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
            if(item.getItemType() == MenuItem.item.Gelato) {
                countGelati = countGelati + 1;
                if(item.getPrice() < cheapestGelato) {
                    cheapestGelato = item.getPrice();
                }
            }
            total = total + item.getPrice();
        }
        
        if(countGelati > 5) {
            total = total - (cheapestGelato / 2);
        }
        
        return total;
    }

}