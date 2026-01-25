package generic.L7;

import java.util.function.Predicate;

public interface FromStage<T> {
    WhereStage<T> where(Predicate<T> condition);
}
