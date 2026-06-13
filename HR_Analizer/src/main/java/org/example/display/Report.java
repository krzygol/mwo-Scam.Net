package org.example.display;

import java.util.List;

public interface Report<T> {
    void print(List<T> rows);
}
