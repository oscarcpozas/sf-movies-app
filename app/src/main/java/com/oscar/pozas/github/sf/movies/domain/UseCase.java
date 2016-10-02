package com.oscar.pozas.github.sf.movies.domain;

public abstract class UseCase<Q extends UseCase.RequestValues, T extends UseCase.ResponseValue> {

    private Q mResquestValue;

    private UseCaseCallback<T> mUseCaseCallback;

    public Q getResquestValue() { return mResquestValue; }

    public void setResquestValue(Q resquestValue) { mResquestValue = resquestValue; }

    public UseCaseCallback<T> getUseCaseCallback() { return mUseCaseCallback; }

    public void setUseCaseCallback(UseCaseCallback<T> useCaseCallback) {
        mUseCaseCallback = useCaseCallback;
    }

    void run() {
        executeUseCase(mResquestValue);
    }

    protected abstract void executeUseCase(Q requestValues);

    /**
     * Data passed to a request.
     */
    public interface RequestValues {}

    /**
     * Data received from a request.
     */
    public interface ResponseValue {}

    /**
     *
     * Callback for UseCase.
     */
    public interface UseCaseCallback<R> {
        void onSuccess(R response);
        void onError();
    }

}
