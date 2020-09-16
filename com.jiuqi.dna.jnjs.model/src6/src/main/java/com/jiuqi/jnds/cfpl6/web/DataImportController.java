package com.jiuqi.jnds.cfpl6.web;

import com.jiuqi.jnds.cfpl6.service.DataImportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <h1>DataImportController</h1>
 *
 * <p>数据导入控制类</p>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 10:27
 */
@RestController
@RequestMapping("data/import")
public class DataImportController {
    private final DataImportService dataImportService;

    public DataImportController(DataImportService dataImportService) {
        this.dataImportService = dataImportService;
    }


    /**
     * 文件上传
     *
     * @param file 文件
     * @return java.lang.String
     * @throws Exception 文件读取异常 / 信息查询失败异常
     * @author Wang Xin
     * @date 2020/9/10 13:57
     */
    @PostMapping("upload")
    public Response upload(@RequestBody MultipartFile file) throws Exception {
    	Response response = Response.success(null);
    	try {
			dataImportService.upload(file);
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.faild("导入失败");
		}
    	return response;
    }
}
