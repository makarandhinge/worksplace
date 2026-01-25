package generic.L6;

import generic.L4.Identifiable;
import generic.L4.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class QueryEngine {

    static <T extends Identifiable<?>> List<T> filter(Repository<T,?> repo, Predicate<T> condition){
            return repo.findAll().stream().filter(condition).collect(Collectors.toList());
    }

    static <T extends Identifiable<?>, R> List<R> map(Repository<T, ?> repo, Function<T,R> mapper){
        return repo.findAll().stream().map(mapper).collect(Collectors.toList());
    }

    static <T extends Identifiable<?>> Optional<T> findFirst(Repository<T, ?> repo, Predicate<T> condition){
        return repo.findAll().stream().filter(condition).findFirst();
    }
}
