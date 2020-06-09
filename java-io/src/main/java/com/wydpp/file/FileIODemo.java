package com.wydpp.file;

import java.io.*;

public class FileIODemo {
    /**
     * 读取字符串文件
     * @throws FileNotFoundException
     */
    public static void readFile() throws Exception {
        //resources/demo.txt
        String filePath = FileIODemo.class.getClassLoader().getResource("demo.txt").getPath();
        System.out.println(filePath);
        File file = new File(filePath);
        if(file.exists()){
            FileInputStream fileInputStream = new FileInputStream(file);
            //readFileByByteArray(fileInputStream);
            readFileByBuffered(fileInputStream);
        }
    }

    /**
     * 通多byte[]读取文件内容
     */
    private static void readFileByByteArray(FileInputStream fileInputStream) throws IOException {
        byte[] tmp = new byte[100];
        int i = 0;
        byte[] allByte = new byte[fileInputStream.available()];
        while((i=fileInputStream.read(tmp)) > 0){
            //此处需要注意的问题，tmp 数组读取之后，如果下次读取的byte数量小于这次，则会有脏数据产生，
            //还有就是读取的时候不能直接转换成string,否则会有编码错误问题（类似socket中的半包读取）
            //传递参数，防止读取脏数据
            String str = new String(tmp,0,i,"utf-8");
            System.out.println(str);
        }
    }

    /**
     * 按行读取文本文件
     */
    private static void readFileByBuffered(FileInputStream fileInputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        byte[] tmp = new byte[100];
        int i = 0;
        byte[] allByte = new byte[bufferedInputStream.available()];
        while((i=bufferedInputStream.read(tmp)) > 0){
            //此处需要注意的问题，tmp 数组读取之后，如果下次读取的byte数量小于这次，则会有脏数据产生，
            //还有就是读取的时候不能直接转换成string,否则会有编码错误问题（类似socket中的半包读取）
            //传递参数，防止读取脏数据
            String str = new String(tmp,0,i,"utf-8");
            System.out.println(str);
        }
    }

    /**
     * 按行读取文本文件
     */
    private static void readFileByLine(FileInputStream fileInputStream){

    }

    /**
     * 读取二进制文件
     * @throws FileNotFoundException
     */
    public static void readByteFile() throws FileNotFoundException {
        //resources/demo.txt
        String fileName = "demo.txt";
        File file = new File(fileName);
        if(file.exists()){
            FileInputStream fileInputStream = new FileInputStream(file);

        }
    }

    public static void main(String[] args) throws Exception {
        readFile();
    }

}
