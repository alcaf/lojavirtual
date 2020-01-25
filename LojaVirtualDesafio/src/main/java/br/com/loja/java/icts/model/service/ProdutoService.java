package br.com.loja.java.icts.model.service;

import br.com.loja.java.icts.model.dao.ProdutoDao;
import br.com.loja.java.icts.model.entity.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("produtoService")
public class ProdutoService {

    @Autowired
    private ProdutoDao dao;

    public Produto save(Produto produto){
        return dao.save(produto);
    }

    public Produto update(Produto produto){
        return dao.update(produto);
    }

    public Produto delete(Produto produto){
        return dao.delete(produto);
    }

    public List<Produto> list(String produto){
        return dao.list(produto);
    }

    public Produto listById(String idProduto){
        return dao.listById(idProduto);
    }

    public Produto listByIdEdicao(String idProduto){
        return dao.listByIdEdicao(idProduto);
    }

}
