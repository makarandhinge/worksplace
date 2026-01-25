package generic.L7;

public interface SelectStage {
    <T> FromStage<T> from(Class<T> type);
}
