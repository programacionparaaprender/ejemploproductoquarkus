package org.programacionparaaprender.repositories;


import jakarta.persistence.EntityManager;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.enterprise.context.ApplicationScoped;
import org.programacionparaaprender.entities.Product;

import java.util.List;

@ApplicationScoped
public class ProductRepository {
    @Inject
    EntityManager em;

    @Transactional
    public void createdProduct(Product p){
        em.persist(p);
    }

    @Transactional
    public void deleteProduct(Product p){
        em.remove(p);
    }

    @Transactional
    public Product getProduct(String id){
        String cadena;
        cadena = String.format("select p from Product p where id=%s", id);
        List<Product> products = em.createQuery(cadena).getResultList();
        Product product = products.get(0);
        return product;
    }

    @Transactional
    public List<Product> listProduct(){
        List<Product> products = em.createQuery("select p from Product p").getResultList();
        return products;
    }
}

