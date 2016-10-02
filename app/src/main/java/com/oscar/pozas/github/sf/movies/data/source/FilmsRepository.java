package com.oscar.pozas.github.sf.movies.data.source;

public class FilmsRepository implements FilmsDataSource {

    private static FilmsRepository INSTANCE = null;

    public static FilmsRepository getInstance() {
        if(INSTANCE == null) {
            return new FilmsRepository();
        }
        return INSTANCE;
    }

    public static void destroyFilmsRepository() { INSTANCE = null; }

    @Override
    public void getFilms(GetFilmsCallback callback) {

    }

}
