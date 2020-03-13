package script_preenchimento.hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "usuario_id")
    int usuario_id;
    @Column(name = "usuario_nome")
    String usuario_nome;
    @Column(name = "usuario_endereco")
    String usuario_endereco;
    @Column(name = "usuario_telefone")
    String usuario_telefone;
    @Column(name = "usuario_email")
    String usuario_email;
    @Column(name = "usuario_local_trabalho")
    String usuario_local_trabalho;
    @Column(name = "usuario_is_revisor")
    int usuario_is_revisor;
    @Column(name = "usuario_is_autor")
    int usuario_is_autor;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY, optional = true)
    private Cartao cartao;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
    private Revisao revisao;

    @OneToMany(mappedBy = "usuario", targetEntity = ArtigoAutor.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArtigoAutor> artigoAutorList;

    public Usuario() {
    }

    public Usuario(String usuario_nome, String usuario_endereco, String usuario_telefone, String usuario_email, String usuario_local_trabalho, int usuario_is_revisor, int usuario_is_autor) {
        super();
        this.usuario_nome = usuario_nome;
        this.usuario_endereco = usuario_endereco;
        this.usuario_telefone = usuario_telefone;
        this.usuario_email = usuario_email;
        this.usuario_local_trabalho = usuario_local_trabalho;
        this.usuario_is_revisor = usuario_is_revisor;
        this.usuario_is_autor = usuario_is_autor;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_nome() {
        return usuario_nome;
    }

    public void setUsuario_nome(String usuario_nome) {
        this.usuario_nome = usuario_nome;
    }

    public String getUsuario_endereco() {
        return usuario_endereco;
    }

    public void setUsuario_endereco(String usuario_endereco) {
        this.usuario_endereco = usuario_endereco;
    }

    public String getUsuario_telefone() {
        return usuario_telefone;
    }

    public void setUsuario_telefone(String usuario_telefone) {
        this.usuario_telefone = usuario_telefone;
    }

    public String getUsuario_email() {
        return usuario_email;
    }

    public void setUsuario_email(String usuario_email) {
        this.usuario_email = usuario_email;
    }

    public String getUsuario_local_trabalho() {
        return usuario_local_trabalho;
    }

    public void setUsuario_local_trabalho(String usuario_local_trabalho) {
        this.usuario_local_trabalho = usuario_local_trabalho;
    }

    public int getUsuario_is_revisor() {
        return usuario_is_revisor;
    }

    public void setUsuario_is_revisor(int usuario_is_revisor) {
        this.usuario_is_revisor = usuario_is_revisor;
    }

    public int getUsuario_is_autor() {
        return usuario_is_autor;
    }

    public void setUsuario_is_autor(int usuario_is_autor) {
        this.usuario_is_autor = usuario_is_autor;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public List<ArtigoAutor> getArtigoAutorList() {
        return artigoAutorList;
    }

    public void setArtigoAutorList(List<ArtigoAutor> artigoAutorList) {
        this.artigoAutorList = artigoAutorList;
    }

    public Revisao getRevisao() {
        return revisao;
    }

    public void setRevisao(Revisao revisao) {
        this.revisao = revisao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario_id=" + usuario_id +
                ", usuario_nome='" + usuario_nome + '\'' +
                ", usuario_endereco='" + usuario_endereco + '\'' +
                ", usuario_telefone='" + usuario_telefone + '\'' +
                ", usuario_email='" + usuario_email + '\'' +
                ", usuario_local_trabalho='" + usuario_local_trabalho + '\'' +
                ", usuario_is_revisor=" + usuario_is_revisor +
                ", usuario_is_autor=" + usuario_is_autor +
                '}';
    }
}
