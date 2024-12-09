package example;

import io.micronaut.data.repository.jpa.criteria.QuerySpecification;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.example.ExampleEntity;
import com.example.ExampleEntityRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;

import jakarta.inject.Inject;

@MicronautTest
@TestInstance(Lifecycle.PER_CLASS)
class QuerySpecificationTest {
    @Inject
    ExampleEntityRepository repository;

    @BeforeAll
    void createEntity() {
        repository.save(new ExampleEntity(1, "foo", "bar"));
    }

    @Test
    void querySpecificationTestLowercaseColumn() {
        QuerySpecification<ExampleEntity> qs = (root, query, criteriaBuilder) -> {
            query.multiselect(
                root.get("id"),
                root.get("lowercaseColumn"));
            return criteriaBuilder.equal(root.get("id"), 1);
        };
        ExampleEntity entity = repository.find(qs);
        
        assertEquals("bar", entity.lowercaseColumn());
    }

    @Test
    void querySpecificationTestUppercaseColumn() {
        QuerySpecification<ExampleEntity> qs = (root, query, criteriaBuilder) -> {
            query.multiselect(
                root.get("id"),
                root.get("uppercaseColumn"));
            return criteriaBuilder.equal(root.get("id"), 1);
        };
        ExampleEntity entity = repository.find(qs);
        
        assertEquals("foo", entity.uppercaseColumn());
    }

}
