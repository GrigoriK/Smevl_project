package sprinExamples.converters;

public interface Converter<T, U> {
    U convert(T t);
}
