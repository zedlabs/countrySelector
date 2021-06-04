package me.zed.countryselect;

public abstract class ResultRes<T> {
    private ResultRes() {}

    public static final class Success<T> extends ResultRes<T> {
        public T data;

        public Success(T data) {
            this.data = data;
        }
    }

    public static final class Error<T> extends ResultRes<T> {
        public Exception exception;

        public Error(Exception exception) {
            this.exception = exception;
        }
    }
}
