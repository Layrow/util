package com.niit.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
/**
 * 使用正则表达式验证输入格式
 * @author James
 *
 */
public class RegexValidateUtil {

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
                String check = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
                Pattern regex = Pattern.compile(check);
                Matcher matcher = regex.matcher(email);
                flag = matcher.matches();
            }catch(Exception e){
                flag = false;
            }
        return flag;
    }
}
