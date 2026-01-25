package generic.L7.P1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class QueryBuilderImpl<T> implements SelectStage, FromStage<T>, WhereStage<T>, ConditionStage<T>{

    private Class<T> entity;
    private List<String> fields = new ArrayList<>();
    private List<Predicate<T>> conditions = new ArrayList<>();
    private Integer limit;

    QueryBuilderImpl(String... fields){
        this.fields = List.of(fields);
    }


    @Override
    public WhereStage<T> where(Predicate<T> condition) {
        conditions.add(condition);
        return this;
    }

    @Override
    public <X> FromStage<X> from(Class<X> type) {
        QueryBuilderImpl<X> next = new QueryBuilderImpl<>();
        next.fields = this.fields;
        next.entity = type;
        return next;
    }

    @Override
    public ConditionStage<T> and(Predicate<T> condition) {
        conditions.add(condition);
        return this;
    }

    @Override
    public ConditionStage<T> or(Predicate<T> condition) {
        conditions.add(condition);
        return this;
    }

    @Override
    public ConditionStage<T> limit(int n) {
        this.limit = n;
        return this;
    }

    @Override
    public Query<T> build() {
        return new Query<>(entity, fields, conditions, limit);
    }
}
