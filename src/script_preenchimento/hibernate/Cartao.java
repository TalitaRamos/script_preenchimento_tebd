package script_preenchimento.hibernate;

import javax.persistence.*;

@Entity
@Table(name="cartao")
public class Cartao {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "cartao_id")
    int cartao_id;
    @Column(name = "cartao_numero")
    String cartao_numero;
    @Column(name = "cartao_data_vencimento")
    String cartao_data_vencimento;
    @Column(name = "cartao_marca")
    String cartao_marca;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Cartao() { }

    public Cartao(String cartao_numero, String cartao_data_vencimento, String cartao_marca, Usuario usuario) {
        super();
        this.cartao_numero = cartao_numero;
        this.cartao_data_vencimento = cartao_data_vencimento;
        this.cartao_marca = cartao_marca;
        this.usuario = usuario;
    }

//    public Cartao(String cartao_numero, String cartao_data_vencimento, String cartao_marca) {
//        super();
//        this.cartao_numero = cartao_numero;
//        this.cartao_data_vencimento = cartao_data_vencimento;
//        this.cartao_marca = cartao_marca;
//    }

    public int getCartao_id() {
        return cartao_id;
    }

    public void setCartao_id(int cartao_id) {
        this.cartao_id = cartao_id;
    }

    public String getCartao_numero() {
        return cartao_numero;
    }

    public void setCartao_numero(String cartao_numero) {
        this.cartao_numero = cartao_numero;
    }

    public String getCartao_data_vencimento() {
        return cartao_data_vencimento;
    }

    public void setCartao_data_vencimento(String cartao_data_vencimento) {
        this.cartao_data_vencimento = cartao_data_vencimento;
    }

    public String getCartao_marca() {
        return cartao_marca;
    }

    public void setCartao_marca(String cartao_marca) {
        this.cartao_marca = cartao_marca;
    }


    @Override
    public String toString() {
        return "Cartao{" +
                "cartao_id=" + cartao_id +
                ", cartao_numero='" + cartao_numero + '\'' +
                ", cartao_data_vencimento='" + cartao_data_vencimento + '\'' +
                ", cartao_marca='" + cartao_marca + '\'' +
                '}';
    }
}
