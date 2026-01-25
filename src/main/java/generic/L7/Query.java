package generic.L7;

import java.util.List;
import java.util.function.Predicate;

public class Query<T> {
    Class<T> entity;
    List<String> fields;
    List<Predicate<T>> conditions;
    Integer limit;

    public Query(Class<T> entity, List<String> fields, List<Predicate<T>> conditions, Integer limit) {
        this.entity = entity;
        this.fields = fields;
        this.conditions = conditions;
        this.limit = limit;
    }

    public static SelectStage select(String... fields) {
        return new QueryBuilderImpl<>(fields);
    }

    @Override
    public String toString() {
        return "Query{" +
                "entity=" + entity +
                ", fields=" + fields +
                ", conditions=" + conditions +
                ", limit=" + limit +
                '}';
    }
}
