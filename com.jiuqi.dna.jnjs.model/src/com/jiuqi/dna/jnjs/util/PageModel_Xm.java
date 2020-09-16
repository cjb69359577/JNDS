package com.jiuqi.dna.jnjs.util;

/**
 *
 * @author fee
 * @date 2011-03-31 12:39:07
 * @copyright 
 */
import java.util.List;

import com.jiuqi.dna.jnjs.entity.XmzcEntry;

public class PageModel_Xm {

    private int page = 1; // 当前页
    public int totalPages = 0; // 总页数
    private int pageRecorders;// 每页5条数据
    private int totalRows = 0; // 总数据数
    private int pageStartRow = 0;// 每页的起始数
    private int pageEndRow = 0; // 每页显示数据的终止数
    private boolean hasNextPage = false; // 是否有下一页
    private boolean hasPreviousPage = false; // 是否有前一页
    private List<XmzcEntry>  list;

    // private Iterator it;
    public PageModel_Xm(List<XmzcEntry> list2, int pageRecorders) {
        init(list2, pageRecorders);// 通过对象集，记录总数划分
    }

    /** */
    /**
     * 初始化list，并告之该list每页的记录数
     * @param list
     * @param pageRecorders
     */
    public void init(List<XmzcEntry>  list, int pageRecorders) {
        this.pageRecorders = pageRecorders;
        this.list = list;
        totalRows = list.size();
        // it = list.iterator();
        hasPreviousPage = false;
        if ((totalRows % pageRecorders) == 0) {
            totalPages = totalRows / pageRecorders;
        } else {
            totalPages = totalRows / pageRecorders + 1;
        }

        if (page >= totalPages) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
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

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public String toString(int temp) {
        String str = Integer.toString(temp);
        return str;
    }

    public void description() {

        String description = "共有数据数:" + this.getTotalRows()
                + "共有页数: " + this.getTotalPages()
                + "当前页数为:" + this.getPage()
                + " 是否有前一页: " + this.isHasPreviousPage()
                + " 是否有下一页:" + this.isHasNextPage()
                + " 开始行数:" + this.getPageStartRow()
                + " 终止行数:" + this.getPageEndRow();

        System.out.println(description);
    }

    public List<XmzcEntry>  getNextPage() {
        page = page + 1;

        disposePage();

        System.out.println("用户凋用的是第" + page + "页");
        this.description();
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

        if ((page - 1) > 0) {
            hasPreviousPage = true;
        } else {
            hasPreviousPage = false;
        }

        if (page >= totalPages) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }
    }

    public List<XmzcEntry>  getPreviousPage() {

        page = page - 1;

        if ((page - 1) > 0) {
            hasPreviousPage = true;
        } else {
            hasPreviousPage = false;
        }
        if (page >= totalPages) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }
        this.description();
        return getObjects(page);
    }

    /** */
    /**
     * 获取第几页的内容
     *
     * @param page
     * @return
     */
    public List<XmzcEntry>  getObjects(int page) {
        if (page == 0) {
            this.setPage(1);
        } else {
            this.setPage(page);
        }
        this.disposePage();
        if (page * pageRecorders < totalRows) {// 判断是否为最后一页
            pageEndRow = page * pageRecorders;
            pageStartRow = pageEndRow - pageRecorders;
        } else {
            pageEndRow = totalRows;
            pageStartRow = pageRecorders * (totalPages - 1);
        }

        List<XmzcEntry>  objects = null;
        if (!list.isEmpty()) {
            objects = list.subList(pageStartRow, pageEndRow);
        }
        //this.description();
        return objects;
    }

    public List<XmzcEntry>  getFistPage() {
        if (this.isNext()) {
            return list.subList(0, pageRecorders);
        } else {
            return list;
        }
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<XmzcEntry>  getList() {
        return list;
    }

    public void setList(List<XmzcEntry>  list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageEndRow() {
        return pageEndRow;
    }

    public void setPageEndRow(int pageEndRow) {
        this.pageEndRow = pageEndRow;
    }

    public int getPageRecorders() {
        return pageRecorders;
    }

    public void setPageRecorders(int pageRecorders) {
        this.pageRecorders = pageRecorders;
    }

    public int getPageStartRow() {
        return pageStartRow;
    }

    public void setPageStartRow(int pageStartRow) {
        this.pageStartRow = pageStartRow;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }
/*
    public static void main(String[] args) {


        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");
        list.add("h");
        list.add("i");
        list.add("j");
        list.add("k");
        list.add("l");
        list.add("m");
        PageModel pm = new PageModel(list, 8);//每页显示条数

        List sublist = pm.getObjects(2);//显示第几页
        for (int i = 0; i < sublist.size(); i++) {
            System.out.println(sublist.get(i));
        }
    }*/
}