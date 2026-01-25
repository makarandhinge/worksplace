package generic.L7.P1;

import java.util.function.Predicate;

public interface ConditionStage<T> {
    ConditionStage<T> and(Predicate<T> condition);
    ConditionStage<T> or(Predicate<T> condition);
    ConditionStage<T> limit(int n);
    Query<T> build();

}
