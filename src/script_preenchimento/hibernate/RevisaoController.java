package script_preenchimento.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RevisaoController {
    EntityManagerFactory emf;
    EntityManager em;

    public RevisaoController() {
        emf = Persistence.createEntityManagerFactory("usuario");
        em= emf.createEntityManager();
    }

    public void salvarRevisao(Revisao revisao){
        try {
            em.getTransaction().begin();
            em.merge(revisao);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public List<Revisao> findRevisaoByArtigo(Integer artigo_id) {
        return em.createQuery("FROM Revisao WHERE artigo_id = "+artigo_id, Revisao.class)
                .getResultList();
    }
    public void fechar() {
        emf.close();
    }
}
