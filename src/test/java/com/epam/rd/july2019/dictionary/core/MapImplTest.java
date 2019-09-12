package com.epam.rd.july2019.dictionary.core;

import com.epam.rd.july2019.dictionary.core.MapImpl;
import org.junit.Assert;
import org.junit.Test;

public class MapImplTest {

    MapImpl<String, Integer> map = new MapImpl<String, Integer>();

    @Test
    public void testPut(){
        //GIVEN
        int resultExpected = 101;
        //WHEN
        map.put("top", 1);
        map.put("top", 2);
        map.put("top", 1);
        for (int i = 0; i < 100; i++) {
            map.put(String.valueOf(i), i);
        }
        int resultActual = map.size();
        //THEN
        Assert.assertEquals(resultExpected, resultActual);

    }

    @Test
    public void testGet(){
        //GIVEN
        Integer resultExpected = 1;
        Integer resultExpectedMore = 67;
        //WHEN
        map.put("top", 1);
        map.put("top", 2);
        map.put("top", 1);
        for (int i = 0; i < 100; i++) {
            map.put(String.valueOf(i), i);
        }
        Integer resultActual = map.get("top");
        Integer resultActualMore = map.get("67");
        //THEN
        Assert.assertEquals(resultExpected, resultActual);
        Assert.assertEquals(resultExpectedMore, resultActualMore);
    }
}
