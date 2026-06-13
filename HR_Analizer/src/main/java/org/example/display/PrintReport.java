package org.example.display;

import java.util.List;

public interface PrintReport<T> {
    void print(List<T> rows);
}
