package com.niit.common.utils;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * 文件管理工具类
 */
public class FileUtil {

    /**
     * 建立文件夹
     *
     * @param strFolderPath 建立文件夹的路径名
     * @return 是否成功
     */
    public static boolean makeFolder(String strFolderPath) throws Exception {
        File file = new File(strFolderPath);
        return file.mkdirs();
    }

    /**
     * 重命名文件，此方法不能移动文件
     *
     * @param strFilePath 需要重命名的文件完整路径名
     * @param strNewName  新文件名
     * @return 是否成功
     */
    public static boolean renameFile(String strFilePath, String strNewName) throws Exception {
        File file = new File(strFilePath);
        File newFile = new File(file.getParentFile(), strNewName);
        return file.renameTo(newFile);
    }

    /**
     * 判断文件或目录是否存在
     *
     * @param strFilePath 需要判断的文件完成路径名
     * @return 是否存在
     */
    public static boolean existFile(String strFilePath) {
        File file = new File(strFilePath);
        return file.exists();
    }

    /**
     * 复制文件
     *
     * @param strSrcFilePath 需要被复制的文件完整路径名
     * @param strDstFilePath 复制的新文件完整路径名
     * @param blnOverWrite   是否可写
     * @return 是否成功
     */
    public static boolean copyFile(String strSrcFilePath, String strDstFilePath, boolean blnOverWrite) throws Exception {
        if (!existFile(strDstFilePath) && !blnOverWrite) {
            return false;
        }
        FileInputStream fins = new FileInputStream(strSrcFilePath);
        FileOutputStream fouts = new FileOutputStream(strDstFilePath);
        IoUtil.copyStream(fins, fouts);
        fins.close();
        fouts.close();
        return true;
    }

    /**
     * 复制文件
     *
     * @param strSrcFilePath 需要被复制的文件完整路径名
     * @param strDstFilePath 复制的新文件完整路径名
     * @return 是否成功
     */
    public static boolean copyFile(String strSrcFilePath, String strDstFilePath) throws Exception {
        return copyFile(strSrcFilePath, strDstFilePath, true);
    }

    /**
     * 删除文件或空目录，不能删除非空目录
     *
     * @param strFilePath
     * @return
     * @throws Exception
     */
    public static boolean deleteFile(String strFilePath) throws Exception {
        File file = new File(strFilePath);
        return file.delete();
    }

    /**
     * 删除目录树，包括下级子目录和文件
     *
     * @param folder
     * @return
     * @throws Exception
     */
    public static boolean deleteFolderTree(File folder) throws Exception {
        if (!folder.isDirectory()) {
            return false;
        }
        //先删除下级文件和目录
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                //递归删除子目录
                if (!deleteFolderTree(file)) {
                    return false;
                }
            } else {
                if (!file.delete()) {
                    return false;
                }
            }
        }
        //再删除本目录
        if (!folder.delete()) {
            return false;
        }
        return true;
    }

    /**
     * 删除目录树，包括下级子目录和文件
     *
     * @param strFolderPath
     * @return
     * @throws Exception
     */
    public static boolean deleteFolderTree(String strFolderPath) throws Exception {
        return deleteFolderTree(new File(strFolderPath));
    }

    /**
     * 获得指定路径下的目录列表（单层结构）
     *
     * @param strFolderPath
     * @return
     * @throws Exception
     */
    public static ArrayList<String> getFolderList(String strFolderPath, boolean isOneLayer) throws Exception {
        if (Tools.isEmpty(strFolderPath)) {
            return null;
        }
        File folder = new File(strFolderPath);
        ArrayList<String> folderList = new ArrayList<String>();
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    folderList.add(file.getName());
                }
            }
        }
        return folderList;
    }

    /**
     * 获得指定路径下的目录列表（多层结构）
     *
     * @param strFolderPath
     * @return
     * @throws Exception
     */
    public static ArrayList<String> getFolderList(String strFolderPath) throws Exception {
        if (Tools.isEmpty(strFolderPath)) {
            return null;
        }
        File folder = new File(strFolderPath);
        ArrayList<String> folderList = new ArrayList<String>();
        if (folder.isDirectory()) {
            //同级目录只取一次文件夹路径
            boolean isadd = false;
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    folderList.addAll(getFolderList(strFolderPath + File.separator + file.getName()));
                } else {
                    if (!isadd) {
                        folderList.add(folder.getPath());
                        isadd = true;
                    }
                }
            }
        }
        return folderList;
    }

    /**
     * 获得指定路径下的文件path列表（多层结构）
     *
     * @param strFolderPath
     * @return
     * @throws Exception
     */
    public static ArrayList<String> getFilePathList(String strFolderPath) throws Exception {
        if (Tools.isEmpty(strFolderPath)) {
            return null;
        }
        File folder = new File(strFolderPath);
        ArrayList<String> list = new ArrayList<String>();
        if (!folder.exists()) {
            return list;
        }
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    String path = strFolderPath + File.separator + file.getName();
                    list.addAll(getFilePathList(path));
                } else {
                    String filePath = file.getPath();
                    if (!Tools.isEmpty(filePath)) {
                        list.add(filePath);
                    }
                }
            }
        } else {
            String filePath = folder.getPath();
            if (!Tools.isEmpty(filePath)) {
                list.add(filePath);
            }
        }
        return list;
    }

    /**
     * 获得指定路径下的文件path列表（多层结构）
     *
     * @param strFolderPath
     * @return
     * @throws Exception
     */
    public static ArrayList<String> getFileNameList(String strFolderPath) throws Exception {
        if (Tools.isEmpty(strFolderPath)) {
            return null;
        }
        File folder = new File(strFolderPath);
        ArrayList<String> list = new ArrayList<String>();
        if (!folder.exists()) {
            return list;
        }
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    String path = strFolderPath + File.separator + file.getName();
                    list.addAll(getFileNameList(path));
                } else {
                    String fileName = file.getName();
                    if (!Tools.isEmpty(fileName)) {
                        list.add(fileName);
                    }
                }
            }
        } else {
            String fileName = folder.getName();
            if (!Tools.isEmpty(fileName)) {
                list.add(fileName);
            }
        }
        return list;
    }

    /**
     * 获得指定路径下的文件path列表（多层结构）
     *
     * @param strFolderPath
     * @return
     * @throws Exception
     */
    public static ArrayList<String> getAllFilePathList(String strFolderPath) throws Exception {
        if (Tools.isEmpty(strFolderPath)) {
            return null;
        }
        File folder = new File(strFolderPath);
        ArrayList<String> list = new ArrayList<String>();
        if (!folder.exists()) {
            return list;
        }
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    String path = strFolderPath + File.separator + file.getName();
                    list.addAll(getAllFilePathList(path));
                } else {
                    String filePath = file.getPath();
                    if (!Tools.isEmpty(filePath)) {
                        list.add(filePath);
                    }
                }
            }
        } else {
            String filePath = folder.getPath();
            if (!Tools.isEmpty(filePath)) {
                list.add(filePath);
            }
        }
        return list;
    }

    /**
     * 获取指定文件最后修改时间
     *
     * @param filePath 文件路径
     * @return 格式化后的时间yyyy-MM-dd HH:mm:ss
     */
    public static String getModifiedTime(String filePath) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTimeInMillis(getModifiedMillisTime(filePath));
        return formatter.format(cal.getTime());
    }

    /**
     * 获取指定文件最后修改时间戳
     *
     * @param filePath 文件路径
     * @return 时间戳
     */
    public static long getModifiedMillisTime(String filePath) {
        File f = new File(filePath);
        long time = f.lastModified();
        return time;
    }

    /**
     * 获取当前运行程序的当前路径
     */
    public static String getCanonicalPath() {
        File directory = new File(".");
        try {
            return directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 如果文件不存在自动new一个文件保存，如果文件存在替换原有的内容,默认采用"utf-8"
     *
     * @param fileContext 需要保存的内容
     * @param filePath    文件路径
     * @return 是否保存成功
     */
    public static boolean saveFile(String fileContext, String filePath) {
        //先删除原有的文件
        File file = new File(filePath);
        if (file.isDirectory()) {
            file.mkdirs();
        } else {
            File folder = new File(file.getParent());
            folder.mkdirs();
        }
        file.delete();
        //新建一个文件
        OutputStreamWriter out = null;
        try {
            file.createNewFile();
            out = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
            out.write(fileContext);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 如果文件不存在自动new一个文件保存，如果文件存在替换原有的内容,如果charset为null或者为""默认采用"utf-8"
     *
     * @param fileContext 需要保存的内容
     * @param filePath    文件路径
     * @param charset     文本编码
     * @return 是否保存成功
     */
    public static boolean saveFile(String fileContext, String filePath, String charset) {
        //先删除原有的文件
        File file = new File(filePath);
        if (file.isDirectory()) {
            file.mkdirs();
        } else {
            File folder = new File(file.getParent());
            folder.mkdirs();
        }
        file.delete();
        //新建一个文件
        OutputStreamWriter out = null;
        try {
            file.createNewFile();
            out = new OutputStreamWriter(new FileOutputStream(file), Tools.isEmpty(charset) ? "utf-8" : charset);
            out.write(fileContext);
        } catch (Exception e) {
            return false;
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * @param fileAbsolutePath 文件绝对路径
     *                         以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFile(String fileAbsolutePath) {
        File file = new File(fileAbsolutePath);
        if (!file.exists()) {
            return null;
        }
        BufferedReader reader = null;
        StringBuffer tempString = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            tempString = new StringBuffer();
            // 一次读入一行，直到读入null为文件结束
            while (reader.readLine() != null) {
                tempString.append(reader.readLine());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tempString.toString();
    }

    /**
     * @param file 文件
     *             以行为单位读取文件，常用于读面向行的格式化文件(默认读取以UTF-8格式)
     */
    public static String readFile(File file) {
        if (!file.exists()) {
            return null;
        }
        BufferedReader reader = null;
        StringBuffer tempString = new StringBuffer();
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
            reader = new BufferedReader(read);
            tempString = new StringBuffer();
            // 一次读入一行，直到读入null为文件结束
            String lineStr = "";
            while ((lineStr = reader.readLine()) != null) {
                tempString.append(lineStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tempString.toString();
    }

    /**
     * 得到目录下所有文件的内容
     *
     * @param strFolderPath 文件目录
     *                      key:文件名字，value:文件内容
     */
    public static HashMap<String, String> getAllFileStr(String strFolderPath) {
        File folder = new File(strFolderPath);
        HashMap<String, String> fileMap = new HashMap<String, String>(16);
        if (!folder.exists()) {
            return fileMap;
        }
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    String path = strFolderPath + File.separator + file.getName();
                    fileMap.putAll(getAllFileStr(path));
                } else {
                    String fileContext = readFile(file);
                    if (!Tools.isEmpty(fileContext)) {
                        fileMap.put(file.getName(), fileContext);
                    }
                }
            }
        } else {
            String fileContext = readFile(folder);
            if (!Tools.isEmpty(fileContext)) {
                fileMap.put(folder.getName(), fileContext);
            }
        }
        return fileMap;
    }

    /**
     * 得到目录下所有文件夹的内容
     *
     * @param strFolderPath 文件目录
     *                      key:文件夹名，value：文件夹里面所有的文件内容
     */
    public static HashMap<String, HashMap<String, String>> getAllFileToFolder(String strFolderPath) {
        File folder = new File(strFolderPath);
        HashMap<String, HashMap<String, String>> folderFile = new HashMap<String, HashMap<String, String>>(16);
        if (!folder.exists()) {
            return null;
        }
        ;
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    folderFile.put(file.getName(), getAllFileStr(strFolderPath + File.separator + file.getName()));
                }
            }
        }
        return folderFile;
    }
}
