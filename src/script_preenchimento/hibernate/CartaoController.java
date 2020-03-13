package script_preenchimento.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CartaoController {
    EntityManagerFactory emf;
    EntityManager em;

    public CartaoController() {
        emf = Persistence.createEntityManagerFactory("usuario");
        em= emf.createEntityManager();
    }

    public void salvarCartao(Cartao cartao){
        try {
            em.getTransaction().begin();
            em.persist(cartao);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

    public void fechar() {
        emf.close();
    }
}
