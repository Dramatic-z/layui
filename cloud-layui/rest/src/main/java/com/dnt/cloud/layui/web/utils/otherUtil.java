package com.dnt.cloud.layui.web.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class otherUtil {



    public static void main(String[] args) throws Exception {

        Path pathIn = Paths.get("C:\\Users\\dramatic\\Desktop\\Bookmarks");
        Path pathOut = Paths.get("C:\\Users\\dramatic\\Desktop\\Bookmarks.json");

        FileChannel inputChannel = FileChannel.open(pathIn, StandardOpenOption.READ);
        FileChannel outputChannel = FileChannel.open(pathOut,StandardOpenOption.WRITE);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true){
            byteBuffer.clear();

            int read = inputChannel.read(byteBuffer);
            if(-1 == read){
                break;
            }

            //读到文件中内容了，翻转缓冲区，准备get其中数据
            byteBuffer.flip();//本例中从ByteBuffer获取数据没有显示的get，而是将其作为参数传给另一个Channel的write方法，但是也是读其中的数据，所以也要进行翻转

            StringBuilder builder = new StringBuilder();
            while (byteBuffer.hasRemaining()){
                builder.append(byteBuffer.get()+",");//1、将bytebuffer中数据，以字节形式取出，每个字节后追加逗号，形成字符串，以便解析字符串时时按,分割还原字节
            }

            CharBuffer charBuffer = CharBuffer.allocate(builder.length());//2、定义一个上面生成字符串长度的ChatBuffer

            charBuffer.put(builder.toString());//3、将字符串写入CharBuffer（A,B,C,D....）
            charBuffer.flip();

            Charset charset= Charset.defaultCharset();//4、将CharBuffer数据准备写入outputChannel，写入之前，需要转换成ByteBuffer，转换时需要按照特定字符集编码形式进行转换
            ByteBuffer byteBuffe1r=charset.encode(charBuffer);
//            byteBuffe1r.flip();
            outputChannel.write(byteBuffe1r);//将byteBuffer中内容写入到Channel中//5、将CharBuffer的ByteBuffer数组写入outputChannel

//            outputChannel.write(byteTemp);//将byteBuffer中内容写入到Channel中
        }

        inputChannel.close();
        outputChannel.close();
    }
}
