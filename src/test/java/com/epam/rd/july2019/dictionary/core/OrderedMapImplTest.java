package com.epam.rd.july2019.dictionary.core;

import com.epam.rd.july2019.dictionary.core.OrderedMapImpl;
import org.junit.Assert;
import org.junit.Test;

public class OrderedMapImplTest {

    OrderedMapImpl<String, Integer> orderedMap = new OrderedMapImpl<>();

    @Test
    public void testPut(){
        //GIVEN
        int resultExpected = 1;
        //WHEN
        orderedMap.put("top", 1);
        orderedMap.put("top", 2);
        orderedMap.put("top", 1);
        int resultActual = orderedMap.size();
        //THEN
        Assert.assertEquals(resultExpected, resultActual);

    }

    @Test
    public void testGet(){
        //GIVEN
        Integer resultExpectedMore = 67;
        //WHEN
        for (int i = 0; i < 100; i++) {
            orderedMap.put(String.valueOf(i), i);
        }
        Integer resultActualMore = orderedMap.get("67");
        //THEN
        Assert.assertEquals(resultExpectedMore, resultActualMore);

    }
}
