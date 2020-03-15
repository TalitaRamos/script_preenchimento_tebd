package script_preenchimento.hibernate;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Struct;

@Entity
@Table(name="artigo_autor")

public class ArtigoAutor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "artigo_autor_id")
    int artigo_autor_id ;

    @Column(name = "email_autor")
    String email_autor;

    @ManyToOne
    @JoinColumn(name="artigo_id")
    private Artigo artigo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id",insertable=true, updatable=true)
    @Fetch(FetchMode.SELECT)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Usuario usuario;

    public ArtigoAutor(int artigo_autor_id, String email_autor, Artigo artigo, Usuario usuario) {
        super();
        this.email_autor = email_autor;
        this.artigo_autor_id = artigo_autor_id;
        this.artigo = artigo;
        this.usuario = usuario;
    }

    public ArtigoAutor() { }

    public int getArtigo_autor_id() {
        return artigo_autor_id;
    }

    public void setArtigo_autor_id(int artigo_autor_id) {
        this.artigo_autor_id = artigo_autor_id;
    }

    public Artigo getArtigo() {
        return artigo;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEmail_autor() {
        return email_autor;
    }

    public void setEmail_autor(String email_autor) {
        this.email_autor = email_autor;
    }
}
