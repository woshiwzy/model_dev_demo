package com.wangzy.httpmodel;

import android.util.Log;

import com.wangzy.httpmodel.okhttp.JsonSelector;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {





    @Test
    public void testSelector(){

        String json="{\"a\":{\"b\":\"vvvv\"}}";
        String ret= JsonSelector.getJsonObject(json,"a->b");
        Log.i("t",ret);



    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}