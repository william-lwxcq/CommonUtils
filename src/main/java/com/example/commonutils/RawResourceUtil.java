/**
 *
 */
package com.example.commonutils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class RawResourceUtil {

    public static String contentOfRawResource(Context context, int rawResourceId) {
        BufferedReader breader = null;
        InputStream is = null;
        try {
            is = context.getResources().openRawResource(rawResourceId);
            breader = new BufferedReader(new InputStreamReader(is));
            StringBuilder total = new StringBuilder();
            String line = null;
            while ((line = breader.readLine()) != null) {
                total.append(line);
            }
            return total.toString();
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            closeQuietly(breader);
            closeQuietly(is);
        }
        return null;
    }

    public static Map<String, List<Integer>> integerMapFromRawResource(Context context, int rawResourceId) {
        String content = contentOfRawResource(context, rawResourceId);
        return JSON.parseObject(content, new TypeReference<Map<String, List<Integer>>>() {
        });
    }


    static public void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
