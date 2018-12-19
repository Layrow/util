/**
* 版权所有：蚂蚁与咖啡的故事
*====================================================
* 文件名称: FileServerHandler.java
* 修订记录：
* No    日期				作者(操作:具体内容)
* 1.    2017-08-28			liuyuanxian(创建:创建文件)
*====================================================
* 类描述：(说明未实现或其它不应生成javadoc的内容)
* 
*/
package com.niit.service.server.server.handler.processor;


import com.niit.service.server.Result;
import com.niit.service.server.server.parse.RequestParam;

public interface FileServerProcessor {

	public Result process(RequestParam reqParams);
}
