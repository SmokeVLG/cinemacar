package com.cinemacar.interfaces;

import com.cinemacar.adapters.Adapter;

public interface FilmsListInterface {
    void loading();

    void success(Adapter adapter);

    void fail(String keyError);
}
