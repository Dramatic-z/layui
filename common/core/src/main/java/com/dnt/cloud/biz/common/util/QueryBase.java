package com.dnt.cloud.biz.common.util;

import java.io.Serializable;

import com.dnt.cloud.common.util.money.Money;

/**
 * 分页算法封装。 分页须设置: TotalItem（总条数）,缺省为0, 应该在dao中被设置 PageSize（每页条数），应在web层被设置 QueryBase
 * 缺省为20，子类可以通过覆盖 getDefaultPageSize() 修改 CurrentPage（当前页）,缺省为1，首页， 应在web层被设置
 * 分页后，可以得到：TotalPage（总页数） FristItem(当前页开始记录位置，从1开始记数) PageLastItem(当前页最后记录位置) 页面上，每页显示条数名字应为：
 * lines
 * 
 * @author sean won
 * @version $Id: QueryBase.java, v 0.1 2009-10-10 下午12:43:46 fuyangbiao Exp $
 */
public class QueryBase implements Serializable {
    private static final long    serialVersionUID = 8462451875633214573L;

    private static final Integer defaultPageSize  = new Integer(20);
    private static final Integer defaultFriatPage = new Integer(1);
    private static final Integer defaultTotleItem = new Integer(0);
    private Integer              totalItem;
    private Integer              pageSize;
    private Integer              currentPage;
    private Money                totalSum         = new Money("0");

    private int                  startRow;
    private int                  endRow;

    /** 是否查询汇总结果列表 */
    private boolean              needQeryTotal    = false;
    /** 是否需要删除 */
    private boolean              needDelete       = false;
    /** 是否查询全部 */
    private boolean              needQueryAll     = false;

    /**
     * 获取总金额
     * @return
     */
    public Money getTotalSum() {
        return totalSum;
    }

    /**
     * 设置总金额
     * @param cent
     */
    public void setTotalSum(long cent) {
        this.totalSum.setCent(cent);
    }

    /**
     * 根据各输入条件，判断是否允许查询，在各子类中分别实现，可以将代码中的各处的输入校验汇总到这里面来，不允许校验时，可以把原因置入ResultCode reason
     *
     * @return
     */
    public boolean isAllowedQuery() {
        throw new RuntimeException("Please implement it in sub classes.");
    }

    /**
     * 获取默认每页大小
     * @return Returns the defaultPageSize.
     */
    protected final Integer getDefaultPageSize() {
        return defaultPageSize;
    }

    /**
     * 获取当前页数
     * @return Returns the currentPage.
     */
    public Integer getCurrentPage() {
        if (currentPage == null) {
            return defaultFriatPage;
        }

        return currentPage;
    }

    /**
     * 设置当前页数
     * @param currentPage The currentPage to set.
     */
    public void setCurrentPage(Integer cPage) {
        if ((cPage == null) || (cPage.intValue() <= 0)) {
            this.currentPage = defaultFriatPage;
        } else {
            this.currentPage = cPage;
        }
    }

    /**
     * 设置页数
     * @return Returns the pageSize.
     */
    public Integer getPageSize() {
        if (pageSize == null) {
            return getDefaultPageSize();
        }

        return pageSize;
    }

    /**
     * 判断是否设置了页数
     * @return
     */
    public boolean hasSetPageSize() {
        return pageSize != null;
    }

    /**
     * 设置每页大小
     * @param pageSize The pageSize to set.
     */
    public void setPageSize(Integer pSize) {
        if (pSize == null) {
            throw new IllegalArgumentException("PageSize can't be null.");
        }

        if (pSize.intValue() <= 0) {
            throw new IllegalArgumentException("PageSize must great than zero.");
        }

        this.pageSize = pSize;
    }

    /**
     * 获取总记录数
     * @return Returns the totalItem.
     */
    public Integer getTotalItem() {
        if (totalItem == null) {
            return defaultTotleItem;
        }

        return totalItem;
    }

    /**
     * 设置总记录数
     * @param totalItem The totalItem to set.
     */
    public void setTotalItem(Integer tItem) {
        if (tItem == null) {
            tItem = new Integer(0);
        }

        this.totalItem = tItem;
    }

    /**
     * 获取总页数
     * @return
     */
    public int getTotalPage() {
        int pgSize = this.getPageSize().intValue();
        int total = this.getTotalItem().intValue();
        int result = total / pgSize;

        if ((total % pgSize) != 0) {
            result++;
        }

        return result;
    }

    /**
     * 获取当前也第一条位置
     * @return
     */
    public int getPageFristItem() {
        if (!needQeryTotal) {
            int cPage = this.getCurrentPage().intValue();

            if (cPage == 1) {
                return 1; // 第一页开始当然是第 1 条记录
            }

            cPage--;

            int pgSize = this.getPageSize().intValue();

            return (pgSize * cPage) + 1;
        } else {
            return 0;
        }

    }

    /**
     * 获取当前也最后一条位置
     * @return
     */
    public int getPageLastItem() {
        if (!needQeryTotal) {
            int cPage = this.getCurrentPage().intValue();
            int pgSize = this.getPageSize().intValue();
            int assumeLast = pgSize * cPage;
            int totalItem = getTotalItem().intValue();

            if (assumeLast > totalItem) {
                return totalItem;
            } else {
                return assumeLast;
            }
        } else {
            return getTotalItem().intValue();
        }

    }

    /**
     * 获取结束位置
     * @return Returns the endRow.
     */
    public int getEndRow() {
        return endRow;
    }

    /**
     * 设置结束位置
     * @param endRow The endRow to set.
     */
    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    /**
     * 获取开始位置
     * @return Returns the startRow.
     */
    public int getStartRow() {
        return startRow;
    }

    /**
     * 设置开始位置
     * @param startRow The startRow to set.
     */
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    /**
     * 模糊查询 %
     * @param value
     * @return
     */
    protected String getSQLBlurValue(String value) {
        if (value == null) {
            return null;
        }

        return value + '%';
    }

    public boolean isNeedQeryTotal() {
        return needQeryTotal;
    }

    public void setNeedQeryTotal(boolean needQeryTotal) {
        this.needQeryTotal = needQeryTotal;
    }

    public boolean isNeedDelete() {
        return needDelete;
    }

    public void setNeedDelete(boolean needDelete) {
        this.needDelete = needDelete;
    }

    public boolean isNeedQueryAll() {
        return needQueryAll;
    }

    public void setNeedQueryAll(boolean needQueryAll) {
        this.needQueryAll = needQueryAll;
    }
    
    /**
     * 拷贝分页信息到子类
     * @param <K>
     * @param k
     */
    public <K extends QueryBase> void copyProperties(K k){
        
        if (k == null){
            return;
        }else {
            k.setCurrentPage(currentPage);
            k.setEndRow(endRow);
            k.setNeedDelete(needDelete);
            k.setNeedQeryTotal(needQeryTotal);
            k.setNeedQueryAll(needQueryAll);
//            if (pageSize != null){
//            k.setPageSize(pageSize);
//            }
            k.setStartRow(startRow);
            k.setTotalItem(totalItem);
            if (totalSum != null){
                k.setTotalSum(totalSum.getCent());
            }
            
            
            
        }
        
        
        
        
        
    }

}
