package script_preenchimento.hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UsuarioController {
    EntityManagerFactory emf;
    EntityManager em;

    public UsuarioController() {
        emf = Persistence.createEntityManagerFactory("usuario");
        em = emf.createEntityManager();
    }

    public void salvarUpdateUsuario(Usuario usuario){
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

    public void salvarUsuario(Usuario usuario){
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

    public List<Usuario> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> rootEntry = cq.from(Usuario.class);
        CriteriaQuery<Usuario> all = cq.select(rootEntry);
        TypedQuery<Usuario> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public List<Usuario> findUsuarioRevisor() {
        return em.createQuery("FROM Usuario WHERE usuario_is_revisor = 1", Usuario.class)
                .getResultList();
    }

    public void AdicionarUsuarioCartao(Usuario usuario, Cartao cartao, int tipo)throws Exception{
        usuario.setCartao(cartao);
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public void AdicionarArtigoAutor()throws Exception{
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
//        Root<Usuario> root = criteria.from(Usuario.class);
//        criteria.where(
//                builder.equal(root.get("usuario"))
//        );
        List<Usuario> usuarioList =  em.createQuery(criteria).getResultList();

        CriteriaBuilder builder2 = em.getCriteriaBuilder();
        CriteriaQuery<Artigo> criteria2 = builder2.createQuery(Artigo.class);
//        Root<Usuario> root2 = criteria2.from(Usuario.class);
//        criteria.where(
//                builder.equal(root.get("usuario"))
//        );
        List<Artigo> artigoList =  em.createQuery(criteria2).getResultList();
//
//
//        nota.setAluno(aluno);
//        em.getTransaction().begin();
//        em.persist(nota);
//        em.getTransaction().commit();
    }

    public void fechar() {
        emf.close();
    }
}
