package com.example.airport.hibernatesearch;

import com.example.airport.data.model.CountryEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryDaoSearch {

    @PersistenceContext
    private EntityManager entityManager;

    private QueryBuilder getQueryBuilder() {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(CountryEntity.class)
                .get();
    }

    private FullTextQuery getJpaQuery(Query luceneQuery) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.createFullTextQuery(luceneQuery, CountryEntity.class);
    }

    public List<CountryEntity> searchCountryNameByFuzzyQuery(String text) {
        List<CountryEntity> results = new ArrayList<>();
        if(StringUtils.isNotEmpty(text)){

            Query fuzzyQuery = getQueryBuilder().keyword().wildcard()

                    .onFields("name","code")
                    .matching(text.toLowerCase() + "*")
                    .createQuery();

            results = getJpaQuery(fuzzyQuery).getResultList();

        }

        return results;
    }
}
