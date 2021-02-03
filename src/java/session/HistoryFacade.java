/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;


import entitiy.Customer;
import entitiy.History;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Вдуник
 */
@Stateless
public class HistoryFacade extends AbstractFacade<History> {

    @PersistenceContext(unitName = "JPTVR19WebKitchenShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoryFacade() {
        super(History.class);
    }

    public List<History> findReadingBooks(Customer customer) {
        try {
            return em.createQuery("SELECT h FROM History h WHERE h.returnDate = NULL AND h.customer = :customer")
                    .setParameter("customer", customer)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}