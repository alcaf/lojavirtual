package br.com.loja.java.icts.controller;
import br.com.loja.java.icts.model.service.ProdutoService;
import br.com.loja.java.icts.util.SessionUtil;
import br.com.loja.java.icts.model.entity.Produto;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class BeanProdutoCadastro {

    /************************************************************    VARIAVEIS    ************************************************************/

    @ManagedProperty("#{produtoService}")
    private ProdutoService produtoService;

    private boolean modoEdicao;
    private String identificacaoEdicao;

    private Produto produtoCadastro;

    /************************************************************    EVENTOS    ************************************************************/
    @PostConstruct
    public void init() {

        if(SessionUtil.getParam("idProdutoEdicao") != null) {
            carregarEdicao();
        }else{
            setModoEdicao(false);
            produtoCadastro = new Produto();
        }

    }

    public String save() {

        try {

            if (!validar()) {
                return "";
            }

            if (!modoEdicao) {

                produtoService.save(produtoCadastro);

                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Produto " + produtoCadastro.getDsProduto() + " incluído.");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);

                /*Iniciar modo de edicao*/
                SessionUtil.setParam("idProdutoEdicao", produtoCadastro.getCdProduto());

                carregarEdicao();

            } else {

                produtoService.update(produtoCadastro);

                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Produto " + produtoCadastro.getDsProduto() + " atualizado.");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            }

            identificacaoEdicao = "Modo de edição do produto "+ produtoCadastro.getDsProduto() + ".";

        } catch (Exception e) {

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ocorreu o seguinte erro: "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            return "";

        }

        return "";

    }

    public String volta() {
        return "voltar";
    }

    /************************************************************    METODOS    ************************************************************/
    public void carregarEdicao(){
        setModoEdicao(true);
        produtoCadastro = produtoService.listByIdEdicao((String) SessionUtil.getParam("idProdutoEdicao"));
        identificacaoEdicao = "Modo de edição do produto "+ produtoCadastro.getDsProduto() + ".";
    }

    public boolean validar() {

        boolean retorno = true;

        try {

            if(produtoCadastro.getCdProduto().isEmpty()) {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Código não pode ser vazio.");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                retorno = false;
            }

            if(produtoCadastro.getDsProduto().isEmpty()) {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Descrição não pode ser vazio.");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                retorno = false;
            }

            return retorno;

        } catch (Exception e) {

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao editar o registro: "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            return false;

        }

    }

    /************************************************************    GETERS E SETERS    ************************************************************/

    public ProdutoService getProdutoService() {
        return produtoService;
    }

    public void setProdutoService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public boolean isModoEdicao() {
        return modoEdicao;
    }

    public void setModoEdicao(boolean modoEdicao) {
        this.modoEdicao = modoEdicao;
    }

    public String getIdentificacaoEdicao() {
        return identificacaoEdicao;
    }

    public void setIdentificacaoEdicao(String identificacaoEdicao) {
        this.identificacaoEdicao = identificacaoEdicao;
    }

    public Produto getProdutoCadastro() {
        return produtoCadastro;
    }

    public void setProdutoCadastro(Produto produtoCadastro) {
        this.produtoCadastro = produtoCadastro;
    }

}
