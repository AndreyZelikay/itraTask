package hello.service;

import hello.model.TShirt;
import hello.model.Tag;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Service
public class HibernateSearchService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    public HibernateSearchService(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    @Transactional
    public List<TShirt> SearchTshirt(String searchTerm) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder TShirt = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(TShirt.class).get();
        Query TShirtWildcardQuery = TShirt
                .keyword()
                .wildcard()
                .onFields("Name","Description","Comments.Comment","theme")
                .matching(searchTerm)
                .createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(TShirtWildcardQuery , TShirt.class);
        List<TShirt> TShirtList = null;
        try {
            TShirtList = fullTextQuery.getResultList();
        } catch (NoResultException nre) {
            System.out.println("error");
        }
        return TShirtList;
    }
    @Transactional
    public List<TShirt> SearchByTag(String searchTerm) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder TShirt = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(TShirt.class).get();
        Query TShirtWildcardQuery = TShirt
                .keyword()
                .wildcard()
                .onFields("Tags.body")
                .matching(searchTerm)
                .createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(TShirtWildcardQuery , TShirt.class);
        List<TShirt> TShirtList = null;
        try {
            TShirtList = fullTextQuery.getResultList();
        } catch (NoResultException nre) {
            System.out.println("error");
        }
        return TShirtList;
    }
}
