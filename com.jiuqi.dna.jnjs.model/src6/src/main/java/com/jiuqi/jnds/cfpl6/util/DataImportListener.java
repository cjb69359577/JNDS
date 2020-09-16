package com.jiuqi.jnds.cfpl6.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.*;

/**
 * <h1>DataImportListener</h1>
 *
 * <p>数据导入的监听</p>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 10:43
 */
public class DataImportListener extends AnalysisEventListener<DataImportEntity> {
    /**
     * 临时数据集
     */
    private final Map<String, List<DataImportEntity>> allDataMap = new LinkedHashMap<>();

    public DataImportListener() {
    }

    /**
     * 获取所有解析后的数据
     *
     * @return {@code List<DataImportEntity>} 数据集
     * @author Wang Xin
     * @date 2020/9/10 13:55
     */
    public Map<String, List<DataImportEntity>> getAllMapData() {
        return allDataMap;
    }

    public List<DataImportEntity> getAllData() {
        Collection<List<DataImportEntity>> values = allDataMap.values();
        List<DataImportEntity> all = new ArrayList<>();
        for (List<DataImportEntity> value : values) {
            all.addAll(value);
        }
        return all;
    }

    /**
     * 解析
     *
     * @param data    一行数据的结果
     * @param context excel reader 上下文锚点
     */
    @Override
    public void invoke(DataImportEntity data, AnalysisContext context) {
        List<DataImportEntity> entities = allDataMap.computeIfAbsent(data.getCredentialId(), k -> new ArrayList<>());
        entities.add(data);
    }

    /**
     * 全解析完了
     *
     * @param context excel reader 上下文锚点
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
