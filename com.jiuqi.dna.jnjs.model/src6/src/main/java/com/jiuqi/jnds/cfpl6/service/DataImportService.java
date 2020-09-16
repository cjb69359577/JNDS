package com.jiuqi.jnds.cfpl6.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * <h1>DataImportService</h1>
 *
 * <p>数据导入Service</p>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 10:31
 */
public interface DataImportService {

    /**
     * 上传Excel处理
     *
     * @param file 文件
     * @return java.lang.String 成功 / 失败
     * @throws Exception 文件读取异常 / 信息查询失败异常
     * @author Wang Xin
     * @date 2020/9/10 10:35
     */
    String upload(MultipartFile file) throws Exception;
}
