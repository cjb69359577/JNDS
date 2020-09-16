package com.jiuqi.jnds05.common.utils;

import java.util.List;

import com.jiuqi.jnds05.common.bean.CarSituationIntf;

public class PageModelUtil {
	private int page = 1; // ��ǰҳ
    public int totalPages = 0; // ��ҳ��
    private int pageRecorders;// ÿҳN������
    private int totalRows = 0; // ��������
    private int pageStartRow = 0;// ÿҳ����ʼ��
    private int pageEndRow = 0; // ÿҳ��ʾ���ݵ���ֹ��
    private List<CarSituationIntf> list;

    public PageModelUtil(List<CarSituationIntf> list, int pageRecorders) {
        init(list, pageRecorders);// ͨ�����󼯣���¼��������
    }
    /**
     * ��ʼ��list������֮��listÿҳ�ļ�¼��
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

    // �ж�Ҫ��Ҫ��ҳ
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
     * �����ҳ
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
     * ��ȡ�ڼ�ҳ������
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
        if (page * pageRecorders < totalRows) {// �ж��Ƿ�Ϊ���һҳ
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
