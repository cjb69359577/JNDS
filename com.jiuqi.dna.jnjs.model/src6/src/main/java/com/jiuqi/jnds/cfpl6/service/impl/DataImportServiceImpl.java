package com.jiuqi.jnds.cfpl6.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.jiuqi.jnds.cfpl6.entity.*;
import com.jiuqi.jnds.cfpl6.repository.*;
import com.jiuqi.jnds.cfpl6.service.DataImportService;
import com.jiuqi.jnds.cfpl6.util.DataImportEntity;
import com.jiuqi.jnds.cfpl6.util.DataImportListener;
import com.jiuqi.jnds.cfpl6.util.UUIDUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

/**
 * <h1>DataImportServiceImpl</h1>
 *
 * <p>数据导入实现类</p>
 *
 * @author Wang Xin
 * @version 1.0
 * @create 2020-09-10 10:32
 */
@Service
public class DataImportServiceImpl implements DataImportService {
    private final Logger logger = LoggerFactory.getLogger(DataImportServiceImpl.class);

    private final MDOrgRepository mdOrgRepository;
    private final MDAccountSubjectRepository mdAccountSubjectRepository;
    private final MDProjectRepository mdProjectRepository;
    private final MDExpendFuncClassRepository mdExpendFuncClassRepository;
    private final MDExpendEconClassRepository mdExpendEconClassRepository;
    private final GLVoucherItemRepository glVoucherItemRepository;
    private final GLVoucherRepository glVoucherRepository;
    private final GLAssistCombRepository glAssistCombRepository;

    public DataImportServiceImpl(
            MDOrgRepository mdOrgRepository,
            MDAccountSubjectRepository mdAccountSubjectRepository,
            MDProjectRepository mdProjectRepository,
            MDExpendFuncClassRepository mdExpendFuncClassRepository,
            MDExpendEconClassRepository mdExpendEconClassRepository,
            GLVoucherItemRepository glVoucherItemRepository,
            GLVoucherRepository glVoucherRepository,
            GLAssistCombRepository glAssistCombRepository
    ) {
        this.mdOrgRepository = mdOrgRepository;
        this.mdAccountSubjectRepository = mdAccountSubjectRepository;
        this.mdProjectRepository = mdProjectRepository;
        this.mdExpendFuncClassRepository = mdExpendFuncClassRepository;
        this.mdExpendEconClassRepository = mdExpendEconClassRepository;
        this.glVoucherItemRepository = glVoucherItemRepository;
        this.glVoucherRepository = glVoucherRepository;
        this.glAssistCombRepository = glAssistCombRepository;
    }


    /**
     * 上传Excel处理
     *
     * @param file 文件
     * @return java.lang.String 成功 / 失败
     * @throws Exception 文件读取异常 / 信息查询失败异常
     * @author Wang Xin
     * @date 2020/9/10 10:35
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String upload(MultipartFile file) throws Exception {
        try (
                InputStream is = file.getInputStream()
        ) {
            DataImportListener listener = new DataImportListener();
            EasyExcel.read(is, DataImportEntity.class, listener).sheet().doRead();

            List<DataImportEntity> allDataEntity = listener.getAllData();
            Map<Integer, List<DataImportEntity>> yearDataMap = new LinkedHashMap<>();
            for (DataImportEntity allDatum : allDataEntity) {
                List<DataImportEntity> entities = yearDataMap.computeIfAbsent(Integer.parseInt(allDatum.getYear()), k -> new ArrayList<>());
                entities.add(allDatum);
            }

            yearDataMap.forEach((y, allData) -> {
                Set<String> unitCodeSet = new HashSet<>();
                Set<String> subjectCodeSet = new HashSet<>();
                Set<String> projectCodeSet = new HashSet<>();
                Set<String> functionTypeCodeSet = new HashSet<>();
                Set<String> economicTypeCodeSet = new HashSet<>();

                for (DataImportEntity data : allData) {
                    unitCodeSet.add(data.getUnitCode());
                    subjectCodeSet.add(data.getSubjectCode());
                    projectCodeSet.add(data.getProjectCode());
                    functionTypeCodeSet.add(data.getFunctionTypeCode());
                    economicTypeCodeSet.add(data.getEconomicTypeCode());
                }

                // 获取code对应的id
                Map<String, UUID> unitCodeIdMap = new LinkedHashMap<>();
                if (CollectionUtils.isNotEmpty(unitCodeSet)) {
                    for (String unitCode : unitCodeSet) {
                        if (StringUtils.isEmpty(unitCode)) {
                            continue;
                        }
                        List<MDOrg> mdOrg = mdOrgRepository.findMDOrgByStdcode(unitCode);
                        if (null == mdOrg || mdOrg.size() == 0) {
                            logger.error(String.format("未查到code为 %s 的单位信息", unitCode));
                        } else {
                            unitCodeIdMap.put(unitCode, mdOrg.get(0).getRecid());
                        }
                    }
                }
                Map<String, UUID> subjectCodeIdMap = new LinkedHashMap<>();
                if (CollectionUtils.isNotEmpty(subjectCodeSet)) {
                    for (String subCode : subjectCodeSet) {
                        if (StringUtils.isEmpty(subCode)) {
                            continue;
                        }
                        List<MDAccountSubject> subject = mdAccountSubjectRepository.findMDAccountSubjectByStdcodeAndAcctyear(subCode, y);
                        if (null == subject || subject.size() == 0) {
                            logger.error(String.format("未查到code为 %s , year为 %s 的科目信息", subCode, y));
                        } else {
                            subjectCodeIdMap.put(subCode, subject.get(0).getRecid());
                        }
                    }
                }
                Map<String, UUID> projectCodeIdMap = new LinkedHashMap<>();
                if (CollectionUtils.isNotEmpty(projectCodeSet)) {
                    for (String projCode : projectCodeSet) {
                        if (StringUtils.isEmpty(projCode)) {
                            continue;
                        }
                        List<MDProject> project = mdProjectRepository.findMDProjectByStdcodeAndAcctyear(projCode, y);
                        if (null == project || project.size() == 0) {
                            logger.error(String.format("未查到code为 %s 的项目信息", projCode));
                        } else {
                            projectCodeIdMap.put(projCode, project.get(0).getRecid());
                        }
                    }
                }
                Map<String, UUID> functionTypeCodeIdMap = new LinkedHashMap<>();
                if (CollectionUtils.isNotEmpty(functionTypeCodeSet)) {
                    for (String code : functionTypeCodeSet) {
                        if (StringUtils.isEmpty(code)) {
                            continue;
                        }
                        List<MDExpendFuncClass> funcClass = mdExpendFuncClassRepository.findMDExpendFuncClassByStdcodeAndAcctyear(code, y);
                        if (null == funcClass || funcClass.size() == 0) {
                            logger.error(String.format("未查到code为 %s 的功能分类信息", code));
                        } else {
                            functionTypeCodeIdMap.put(code, funcClass.get(0).getRecid());
                        }
                    }
                }
                Map<String, UUID> economicTypeCodeIdMap = new LinkedHashMap<>();
                if (CollectionUtils.isNotEmpty(economicTypeCodeSet)) {
                    for (String code : economicTypeCodeSet) {
                        if (StringUtils.isEmpty(code)) {
                            continue;
                        }
                        List<MDExpendEconClass> econClass = mdExpendEconClassRepository.findMDExpendEconClassByStdcodeAndAcctyear(code, y);
                        if (null == econClass || econClass.size() == 0) {
                            logger.error(String.format("未查到code为 %s 的经济分类信息", code));
                        } else {
                            economicTypeCodeIdMap.put(code, econClass.get(0).getRecid());
                        }
                    }
                }

                Map<String, UUID> assistCombMap = this.getAssistIdMap(allData, projectCodeIdMap, functionTypeCodeIdMap, economicTypeCodeIdMap);
                this.insertDb(listener, unitCodeIdMap, subjectCodeIdMap, assistCombMap);
            });
            return "success";
        } catch (Exception e) {
            logger.error("Excel导入失败", e);
            throw e;
        }
    }

    /**
     * 获取辅助项id
     *
     * @param allData               allData
     * @param projectCodeIdMap      projectCodeIdMap
     * @param functionTypeCodeIdMap functionTypeCodeIdMap
     * @param economicTypeCodeIdMap economicTypeCodeIdMap
     * @return Map
     * @author Wang Xin
     * @date 2020/9/10 17:48
     */
    private Map<String, UUID> getAssistIdMap(List<DataImportEntity> allData, Map<String, UUID> projectCodeIdMap, Map<String, UUID> functionTypeCodeIdMap, Map<String, UUID> economicTypeCodeIdMap) {
        Map<String, UUID> assistCombMap = new LinkedHashMap<>();
        for (DataImportEntity e : allData) {
            // 年度
            String year = e.getYear();
            // 项目code
            String projectCode = e.getProjectCode();
            UUID projectId = null;
            if (!StringUtils.isEmpty(projectCode)) {
                projectId = projectCodeIdMap.get(projectCode);
            }
            // 功能分类code
            String functionTypeCode = e.getFunctionTypeCode();
            UUID functionTypeId = null;
            if (!StringUtils.isEmpty(functionTypeCode)) {
                functionTypeId = functionTypeCodeIdMap.get(functionTypeCode);
            }
            // 经济分类code
            UUID economicTypeId = null;
            String economicTypeCode = e.getEconomicTypeCode();
            if (!StringUtils.isEmpty(economicTypeCode)) {
                economicTypeId = economicTypeCodeIdMap.get(economicTypeCode);
            }
            List<GLAssistComb> glAssistCom = glAssistCombRepository.findGLAssistCombByAcctyearAndProjectidAndExpendfuncclassidAndExpendeconclassid(Integer.parseInt(year), projectId, functionTypeId, economicTypeId);
            String key = year + "_" + projectCode + "_" + functionTypeCode + "_" + economicTypeCode;
            if (null == glAssistCom || glAssistCom.size() == 0) {
                // 新的
                GLAssistComb glAssistComb = new GLAssistComb();
                glAssistComb.setAcctyear(Integer.parseInt(year));
                glAssistComb.setExpendeconclassid(economicTypeId);
                glAssistComb.setExpendfuncclassid(functionTypeId);
                glAssistComb.setProjectid(projectId);
                UUID recid = UUID.randomUUID();
                glAssistComb.setRecid(recid);
                glAssistCombRepository.save(glAssistComb);
                assistCombMap.put(key, recid);
            } else {
                // 已存在的
                UUID recid = glAssistCom.get(0).getRecid();
                assistCombMap.put(key, recid);
            }
        }
        return assistCombMap;
    }


    /**
     * 入库
     *
     * @param listener         listener
     * @param unitCodeIdMap    unitCodeIdMap
     * @param subjectCodeIdMap subjectCodeIdMap
     * @param assistCombMap    assistCombMap
     * @author Wang Xin
     * @date 2020/9/10 17:46
     */
    private void insertDb(DataImportListener listener, Map<String, UUID> unitCodeIdMap, Map<String, UUID> subjectCodeIdMap, Map<String, UUID> assistCombMap) {
        // 入库
        Map<String, List<DataImportEntity>> allMapData = listener.getAllMapData();
        allMapData.forEach((k, v) -> {
            Optional<GLVoucher> glVoucherOptional = glVoucherRepository.findById(UUIDUtils.fromStringToUuid(k));
            if (!glVoucherOptional.isPresent()) {
                // 入库
                DataImportEntity e = v.get(0);
                GLVoucher glVoucher = new GLVoucher();
                UUID glVoucherId = UUIDUtils.fromStringToUuid(e.getCredentialId());
                glVoucher.setAcctperiod(Integer.parseInt(e.getPeriod()));
                glVoucher.setAcctyear(Integer.parseInt(e.getYear()));
                glVoucher.setCreatedate(new Timestamp(e.getCredentialDate().getTime()));
                glVoucher.setRecid(glVoucherId);
                glVoucher.setUnitid(unitCodeIdMap.get(e.getUnitCode()));
                glVoucher.setVchrnum(Integer.parseInt(e.getCredentialNum()));
                glVoucherRepository.save(glVoucher);

                for (DataImportEntity d : v) {
                    String year = d.getYear();
                    // 项目code
                    String projectCode = d.getProjectCode();
                    // 科目code
                    String subjectCode = d.getSubjectCode();
                    // 功能分类code
                    String functionTypeCode = d.getFunctionTypeCode();
                    // 经济分类code
                    String economicTypeCode = d.getEconomicTypeCode();

                    String key = year + "_" + projectCode + "_" + functionTypeCode + "_" + economicTypeCode;
                    UUID assistCombId = assistCombMap.get(key);

                    GLVoucherItem glVoucherItem = new GLVoucherItem();
                    glVoucherItem.setRecid(UUID.randomUUID());
                    glVoucherItem.setAsscombid(assistCombId);
                    glVoucherItem.setCredit(Double.parseDouble(d.getLenderAmount()));
                    glVoucherItem.setDebit(Double.parseDouble(d.getDebitAmount()));
                    glVoucherItem.setVchrid(glVoucherId);
                    if (!StringUtils.isEmpty(subjectCode)) {
                        glVoucherItem.setSubjectid(subjectCodeIdMap.get(subjectCode));
                    }
                    glVoucherItemRepository.save(glVoucherItem);
                }
            }
        });
    }
}
