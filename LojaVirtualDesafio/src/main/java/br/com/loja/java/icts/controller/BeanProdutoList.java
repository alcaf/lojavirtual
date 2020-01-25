package br.com.loja.java.icts.controller;

import br.com.loja.java.icts.model.entity.Produto;
import br.com.loja.java.icts.model.service.ProdutoService;
import br.com.loja.java.icts.util.SessionUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@ViewScoped
public class BeanProdutoList {

/************************************************************    VARIAVEIS    ************************************************************/
    @ManagedProperty("#{produtoService}")
    private ProdutoService produtoService;

    private String pesquisa;
    private List<Produto> produtos;

/************************************************************    EVENTOS    ************************************************************/
@PostConstruct
public void init() {
}

    public String busca() {

        try {
            produtos = produtoService.list(pesquisa);
        } catch (Exception e) {

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ocorreu o seguinte erro : "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            return "";

        }

        return "";

    }

    public String novo() {

        try {

            SessionUtil.setParam("idProdutoEdicao", null);
            return "novo";

        } catch (Exception e) {

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ocorreu o seguinte erro: "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            return "novo";

        }

    }

    public String edit(Produto produto) {

        try {

            SessionUtil.setParam("idProdutoEdicao", produto.getCdProduto());
            return "novo";

        } catch (Exception e) {

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ocorreu o seguinte erro: "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            return "editar";

        }

    }

    public String delete(Produto produto) {

        try {

            SessionUtil.setParam("idProdutoEdicao", produto.getCdProduto());
            produtoService.delete(produto);

            return "";

        } catch (Exception e) {

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ocorreu o seguinte erro: "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            return "";

        }

    }

    public ProdutoService getProdutoService() {
        return produtoService;
    }

    public void setProdutoService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
