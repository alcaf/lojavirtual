package br.com.loja.java.icts.model.dao;

import br.com.loja.java.icts.model.entity.Produto;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("produtoDao")
public class ProdutoDao {

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    public Produto save(Produto produto){
        entityManager.persist(produto);
        return produto;
    }

    @Transactional
    public Produto update(Produto produto){
        entityManager.merge(produto);
        return produto;
    }

    @Transactional
    public Produto delete(Produto produto){
        entityManager.remove(produto);
        return produto;
    }

    @Transactional(readOnly=true)
    public List<Produto> list(String produto) {

        String jpql = "SELECT p " +
                      "  FROM Produto p " +
                      " WHERE (p.cdProduto LIKE :_produto OR p.dsProduto LIKE :_produto) " +
                      "ORDER BY p.dsProduto";

        Query query = entityManager.createQuery(jpql);

        query.setParameter("_produto","%" + produto + "%");

        List<Produto> result = (List<Produto>)query.getResultList();
        
        return result;

    }

    @Transactional(readOnly=true)
    public Produto listById(String idProduto) {

        String jpql = "SELECT p FROM Produtos p WHERE p.cdProduto = :_idProduto";

        Query query = entityManager.createQuery(jpql);

        query.setParameter("_idProduto",idProduto);

        return (Produto) query.getSingleResult();

    }

    @Transactional(readOnly=true)
    public Produto listByIdEdicao(String idProduto) {

        String jpql = "SELECT p FROM Produto p WHERE p.cdProduto = :_idProduto";

        Query query = entityManager.createQuery(jpql);

        query.setParameter("_idProduto", idProduto);

        Produto p = (Produto) query.getSingleResult();

        return p;

    }

}