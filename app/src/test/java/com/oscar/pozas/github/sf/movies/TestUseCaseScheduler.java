package com.oscar.pozas.github.sf.movies;

import com.oscar.pozas.github.sf.movies.domain.UseCase;
import com.oscar.pozas.github.sf.movies.domain.UseCaseScheduler;

public class TestUseCaseScheduler implements UseCaseScheduler {

    @Override
    public void execute(Runnable runnable) {
        runnable.run();
    }

    @Override
    public <V extends UseCase.ResponseValue> void notifyResponse(V response,
             UseCase.UseCaseCallback<V> useCaseCallback) {
        useCaseCallback.onSuccess(response);
    }

    @Override
    public <V extends UseCase.ResponseValue> void onError(
            UseCase.UseCaseCallback<V> useCaseCallback) {
        useCaseCallback.onError();
    }

}
