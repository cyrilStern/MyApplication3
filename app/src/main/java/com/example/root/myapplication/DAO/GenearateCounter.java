package com.example.root.myapplication.DAO;

/**
 * Created by cyrilstern1 on 16/07/2017.
 */

public class GenearateCounter {
    private static float counter = 0f;

    public static float counter() {
        counter += 0.35f;
        return counter;
    }
}
