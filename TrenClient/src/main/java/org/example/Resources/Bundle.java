package org.example.Resources;

import java.util.ListResourceBundle;

public class Bundle extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"hello", "Hello"}

    };
    }
}