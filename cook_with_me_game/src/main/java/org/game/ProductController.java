package org.game;

import java.util.HashMap;
import java.util.Map;

public class ProductController {
    public static Map<String, Integer> intializeProducts(){
        return new HashMap<>();
    }
    public static int getMaxCost(){
        return 500;
    }
}
