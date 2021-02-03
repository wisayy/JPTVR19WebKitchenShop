/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entitiy.KitchenProduct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Вдуник
 */
@Stateless
public class KitchenFacade extends AbstractFacade<KitchenProduct> {

    @PersistenceContext(unitName = "JPTVR19WebKitchenShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KitchenFacade() {
        super(KitchenProduct.class);
    }
}