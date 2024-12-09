package com.example;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.GenericRepository;
import io.micronaut.data.repository.jpa.criteria.QuerySpecification;

@JdbcRepository(dialect = Dialect.H2, dataSource = "default")
public interface ExampleEntityRepository extends GenericRepository<ExampleEntity, Integer> {
    void save(ExampleEntity entity);
    ExampleEntity find(QuerySpecification<ExampleEntity> querySpecification);
}