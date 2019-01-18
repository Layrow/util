package com.niit.common.utils;


import java.io.*;

/**
 * IO流工具类
 */
public class IoUtil {
    /**
     * 以文本方式读取ClassPath中的文件，返回字符串
     *
     * @param relativePath 相对于ClassPath的相对路径
     * @return 字符串，若失败则返回null
     */
    public static String readFileInClassPath(String relativePath, String encoding) {
        try {
            InputStream fis = ClassLoader.getSystemClassLoader().getResourceAsStream(relativePath);
            byte[] buf = toByteArray(fis, 16384);
            fis.close();
            return new String(buf, encoding);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 以文本方式读取ClassPath中的文件，返回字符串
     *
     * @param relativePath 相对于ClassPath的相对路径
     * @return 字符串，若失败则返回null
     */
    public static String readFileInClassPath(String relativePath) {
        return readFileInClassPath(relativePath, "utf-8");
    }

    /**
     * 以二进制方式读取文件，返货字节数据
     *
     * @param filePath
     * @return
     * @throws Exception
     */

    public static byte[] readFileBytes(String filePath) throws Exception {
        FileInputStream fins = new FileInputStream(filePath);
        byte[] buf = toByteArray(fins, 16384);
        fins.close();
        return buf;
    }

    /**
     * 以文本方式读取文件，返回字符串
     *
     * @param filePath
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String readFile(String filePath, String encoding) throws Exception {
        FileInputStream fins = new FileInputStream(filePath);
        byte[] buf = toByteArray(fins, 16384);
        fins.close();
        return new String(buf, encoding);
    }

    /**
     * 以文本方式读取文件，返回字符串
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String readFile(String filePath) throws Exception {
        return readFile(filePath, "utf-8");
    }

    /**
     * 以文本方式读取文件，返回字符串(通过文件的全名得到energy_logic里面的资源(特殊加包的))
     *
     * @param file 文件全名称  如：map。inf
     * @return
     * @throws Exception
     */
    public static String readSourceFile(String file, String sourcePathDir) throws Exception {
        return readFile(FileUtil.getCanonicalPath() + File.separator + sourcePathDir + File.separator + file, "utf-8");
    }

    /**
     * 保存字节数组为文件，如果文件目录不存在，则创建所有目录
     *
     * @param content 为null时会创建一个空文件
     */
    public static void writeFileBytes(String filePath, byte[] content) throws Exception {
        if (content == null) {
            content = new byte[0];
        }
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fouts = new FileOutputStream(file);
        BufferedOutputStream bouts = new BufferedOutputStream(fouts, 16384);
        bouts.write(content);
        bouts.flush();
        bouts.close();
        fouts.close();
    }

    /**
     * 保存字符串为文本文件，如果文件目录不存在，则创建所有目录
     *
     * @param content 为null时会创建一个空文件
     */
    public static void writeFile(String filePath, String content, String encoding) throws Exception {
        if (content == null) {
            content = "";
        }
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        byte[] buf = content.getBytes(encoding);
        FileOutputStream fouts = new FileOutputStream(file);
        BufferedOutputStream bouts = new BufferedOutputStream(fouts, 16384);
        bouts.write(buf);
        bouts.flush();
        bouts.close();
        fouts.close();
    }

    /**
     * 保存字符串为文本文件，如果文件目录不存在，则创建所有目录
     *
     * @param content 为null时会创建一个空文件
     */
    public static void writeFile(String filePath, String content) throws Exception {
        writeFile(filePath, content, "utf-8");
    }

    /**
     * 将流转换为字节数组
     *
     * @param in
     * @param initlalTargetBufferSize
     * @return
     * @throws Exception
     */
    public static byte[] toByteArray(InputStream in, int initlalTargetBufferSize) throws Exception {
        ByteArrayOutputStream byout = new ByteArrayOutputStream(initlalTargetBufferSize);
        try {
            copyStream(in, byout);
        } finally {
            byout.close();
        }
        return byout.toByteArray();
    }

    /**
     * 复制流，in和out不能为null
     *
     * @param in
     * @param out
     * @throws Exception
     */
    public static void copyStream(InputStream in, OutputStream out) throws Exception {
        final int bufferSize = 2048;
        final byte[] buf = new byte[bufferSize];
        int bytesRead;
        while ((bytesRead = in.read(buf)) != -1) {
            out.write(buf, 0, bytesRead);
        }
    }

    /**
     * 把流保存为文件
     *
     * @param file
     * @param in
     * @param isAppended
     * @return
     * @throws Exception
     */
    public static long saveStreamToFile(String file, InputStream in, boolean isAppended) throws Exception {
        FileOutputStream fouts = new FileOutputStream(file, isAppended);
        byte[] buffer = new byte[8192];
        int readLen = 0;
        long totalLen = 0;
        while ((readLen = in.read(buffer)) > -1) {
            fouts.write(buffer, 0, readLen);
            totalLen += readLen;
        }
        fouts.close();
        return totalLen;
    }

    /**
     * 把流保存为文件
     *
     * @param file
     * @param in
     * @return
     * @throws Exception
     */
    public static long saveStreamToFile(String file, InputStream in) throws Exception {
        return saveStreamToFile(file, in, false);
    }

    /**
     * 追加文件：使用RandomAccessFile
     *
     * @param fileName 文件名
     * @param content  追加的内容
     */
    public static void appendStringToFile(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.write(content.getBytes());
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 追加文件：使用FileWriter
     *
     * @param fileName 文件名
     * @param content  追加的内容
     */
    public static void appendStringToFile2(String fileName, String content) {
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//		/**
//		 * 得到指定文件的编码方式
//		 * @param filename
//		 * @return encode
//		 */
//		public static String guessEncoding(String filename) {
//			/*
//			 * detector是探测器，它把探测任务交给具体的探测实现类的实例完成。
//			 * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、
//			 * JChardetFacade、ASCIIDetector、UnicodeDetector。
//			 * detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的
//			 * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar
//			 * cpDetector是基于统计学原理的，不保证完全正确。
//			 */
//			CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
//			/*
//			 * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
//			 * 指示是否显示探测过程的详细信息，为false不显示。
//			 */
//			detector.add(new ParsingDetector(false));
//			/*
//			 * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
//			 * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以
//			 * 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
//			 */
//			detector.add(JChardetFacade.getInstance());// 用到antlr.jar、chardet.jar
//			// ASCIIDetector用于ASCII编码测定
//			detector.add(ASCIIDetector.getInstance());
//			// UnicodeDetector用于Unicode家族编码的测定
//			detector.add(UnicodeDetector.getInstance());
//			java.nio.charset.Charset charset = null;
//			File f = new File(filename);
//			try {
//				charset = detector.detectCodepage(f.toURI().toURL());
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//			if (charset != null)
//				return charset.name();
//			else
//				return null;
//		}

    /**
     * 将指定的文件按照指定的方式读入
     *
     * @param fileName
     * @param encoding
     * @return string
     */
    public static String readEncoding(String fileName, String encoding) {
        String string = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encoding));
            String str = "";
            while ((str = in.readLine()) != null) {
                string += str + "\n";
            }
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return string;
    }

    /**
     * 将字符串按照指定的编码方式写入到指定的文件中
     *
     * @param fileName
     * @param encoding
     * @param str
     * @return null
     */
    public static void writeEncoding(String fileName, String encoding, String str) {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), encoding));
            out.write(str);
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
