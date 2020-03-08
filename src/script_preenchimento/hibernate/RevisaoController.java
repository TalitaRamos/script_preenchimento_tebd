package script_preenchimento.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RevisaoController {
    EntityManagerFactory emf;
    EntityManager em;

    public RevisaoController() {
        emf = Persistence.createEntityManagerFactory("usuario");
        em= emf.createEntityManager();
    }

    public void salvarRevisao(Revisao revisao){
        em.getTransaction().begin();
        em.persist(revisao);
        em.getTransaction().commit();
    }

    public void fechar() {
        emf.close();
    }
}
