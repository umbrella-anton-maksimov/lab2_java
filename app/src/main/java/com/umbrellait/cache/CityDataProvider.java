package com.umbrellait.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CityDataProvider implements DataProvider<String>{

    private static List<String> source = new ArrayList<>();

    static {
        source.add("Rostov");
        source.add("Moscow");
        source.add("St. Petersburg");
        source.add("Kazan");
        source.add("Novosibirsk");
    }

    @Override
    public String provide() {
        Random random = new Random();
        return source.get(random.nextInt(source.size()));
    }

}
