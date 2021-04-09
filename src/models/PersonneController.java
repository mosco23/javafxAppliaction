/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import javax.persistence.TypedQuery;
/**
 *
 * @author mosco
 */

public class PersonneController {
    
    private final EntityManagerFactory emf;
    private final EntityManager em;
    
    public PersonneController(){
        emf = Persistence.createEntityManagerFactory("JavaFXApplicationTPPU");
        em = emf.createEntityManager();
    }
    
    public List<Personne> personnes(){
        TypedQuery<Personne> tq = em.createQuery("SELECT p FROM Personne p", Personne.class);
        return tq.getResultList();
    }

    public boolean add(Personne p){
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public boolean remove(int id){
        try {
            em.getTransaction().begin();
            Personne p = em.find(Personne.class, id);
            em.remove(p);
            em.getTransaction().commit();
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean update(Personne p, int id){
        try {
            em.getTransaction().begin();
            em.find(Personne.class, id);
            em.merge(p);
            em.getTransaction().commit();
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
}
