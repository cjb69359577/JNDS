package com.jiuqi.jnds05.common.utils;

import java.util.List;

import com.jiuqi.jnds05.common.bean.CarSituationIntf;

public class PageModelUtil {
	private int page = 1; // 当前页
    public int totalPages = 0; // 总页数
    private int pageRecorders;// 每页N条数据
    private int totalRows = 0; // 总数据数
    private int pageStartRow = 0;// 每页的起始数
    private int pageEndRow = 0; // 每页显示数据的终止数
    private List<CarSituationIntf> list;

    public PageModelUtil(List<CarSituationIntf> list, int pageRecorders) {
        init(list, pageRecorders);// 通过对象集，记录总数划分
    }
    /**
     * 初始化list，并告之该list每页的记录数
     * @param list
     * @param pageRecorders
     */
    public void init(List<CarSituationIntf> list, int pageRecorders) {
        this.pageRecorders = pageRecorders;
        this.list = list;
        totalRows = list.size();
        if ((totalRows % pageRecorders) == 0) {
            totalPages = totalRows / pageRecorders;
        } else {
            totalPages = totalRows / pageRecorders + 1;
        }


        if (totalRows < pageRecorders) {
            this.pageStartRow = 0;
            this.pageEndRow = totalRows;
        } else {
            this.pageStartRow = 0;
            this.pageEndRow = pageRecorders;
        }
    }

    // 判断要不要分页
    public boolean isNext() {
        return list.size() > 5;
    }

    public String toString(int temp) {
        String str = Integer.toString(temp);
        return str;
    }


    public List<CarSituationIntf> getNextPage() {
        page = page + 1;
        disposePage();
        return getObjects(page);
    }

    /** */
    /**
     * 处理分页
     */
    private void disposePage() {

        if (page == 0) {
            page = 1;
        }
    }

    public List<CarSituationIntf> getPreviousPage() {
        page = page - 1;
        return getObjects(page);
    }

    /** */
    /**
     * 获取第几页的内容
     *
     * @param page
     * @return
     */
    public List<CarSituationIntf> getObjects(int page) {
        if (page == 0) {
            this.page=1;
        } else {
        	this.page=page;
        }
        this.disposePage();
        if (page * pageRecorders < totalRows) {// 判断是否为最后一页
            pageEndRow = page * pageRecorders;
            pageStartRow = pageEndRow - pageRecorders;
        } else {
            pageEndRow = totalRows;
            pageStartRow = pageRecorders * (totalPages - 1);
        }

        List<CarSituationIntf> objects = null;
        if (!list.isEmpty()) {
            objects = list.subList(pageStartRow, pageEndRow);
        }
        return objects;
    }

    public List<CarSituationIntf> getFistPage() {
        if (this.isNext()) {
            return list.subList(0, pageRecorders);
        } else {
            return list;
        }
    }
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
