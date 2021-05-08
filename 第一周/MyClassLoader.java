package com.week_one;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class MyClassLoader extends ClassLoader {
    public MyClassLoader() {
    }

    /**
     * 重写findClass方法
     *
     * @param name 是我们这个类的全路径
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream in = null;

        byte[] datas = new MyClassLoader().getData("/com/week_one/Hello.xlass");
        for (int n = 0; n < datas.length; n++) {
            byte b = datas[n];
            datas[n] = (byte) (255 - b);
            System.out.print(String.format("%02X", datas[n]));
        }
        System.out.println("");


        Class log = null;

        if (datas != null) {
            // 将class的字节码数组转换成Class类的实例
            log = defineClass(name, datas, 0, datas.length);
        }
        return log;
    }

    /**
     * 将class文件转化为字节码数组
     *
     * @return
     */
    private byte[] getData(String path) {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = MyClassLoader.class.getResourceAsStream(path);
            out = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int size = 0;
            while ((size = in.read(buffer)) != -1) {
                out.write(buffer, 0, size);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return out.toByteArray();

    }

    public static String format(byte[] datas) {
        StringBuilder result = new StringBuilder();
        for (int n = 0; n < datas.length; n++) {
            if (n % 16 == 0 && n != 0) {
                result.append("\n");
            }
            byte b = datas[n];
            if (n % 16 == 0) {
                result.append(String.format("%05X: ", n));
            }

            result.append(String.format("%02X", b));
            if (n % 2 == 1) {
                result.append(String.format(" ", b));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedEncodingException {

        MyClassLoader myClassLoader = new MyClassLoader();
        Class clazz =  myClassLoader.loadClass("Hello");

        System.out.println(clazz.getCanonicalName());

    }
}
