package br.com.loja.java.icts.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {

    /************************************************************    ATRIBUTOS    ************************************************************/
    @Id
    @Column(name = "cd_produto", length = 20, nullable = false)
    private String cdProduto;

    @Column(name = "ds_produto", length = 150, nullable = false)
    private String dsProduto;

    @Column(name = "vl_produto", nullable = false)
    private float vlProduto;

    /************************************************************    AUXILIARES    ************************************************************/

    @Override
    public boolean equals(Object other){
        return other instanceof Produto && equals((Produto)other);
    }

    public boolean equals(Produto other){
        return this.cdProduto.equals(other.cdProduto);
    }

    @Override
    public int hashCode(){
        return cdProduto.hashCode();
    }

    @Override
    public String toString(){
        return this.dsProduto;
    }

    /************************************************************    GETERS E SETERS    ************************************************************/
    public String getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(String cdProduto) {
        this.cdProduto = cdProduto;
    }

    public String getDsProduto() {
        return dsProduto;
    }

    public void setDsProduto(String dsProduto) {
        this.dsProduto = dsProduto;
    }

    public float getVlProduto() {
        return vlProduto;
    }

    public void setVlProduto(float vlProduto) {
        this.vlProduto = vlProduto;
    }

}
