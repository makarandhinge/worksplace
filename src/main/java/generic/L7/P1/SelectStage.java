package generic.L7.P1;

public interface SelectStage {
    <T> FromStage<T> from(Class<T> type);
}
