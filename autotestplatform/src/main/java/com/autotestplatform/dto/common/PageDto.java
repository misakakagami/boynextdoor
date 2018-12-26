package com.autotestplatform.dto.common;

import java.io.Serializable;

/**
 * 页码dto
 * @author : 孔德华
 * @date : 2018年5月9日 下午4:12:35  
 * @version : 2018年5月9日  孔德华 创建类
 */
public class PageDto implements Serializable {

    /** 
    * @Fields serialVersionUID : TODO 变量名称 
    */
    private static final long serialVersionUID = 8478667077373032854L;

    /**
     * 当前页码
     */
    private Integer           pageNow          = 1;
    /**
     * 上一页（不存在时为空）
     */
    private Integer           lastPage;
    /**
     * 下一页（不存在时为空）
     */
    private Integer           nextPage;
    /**
     * 每页数量
     */
    private Integer           pageSize         = 20;
    /**
     * 总页数（尾页页码）
     */
    private Integer           pageSum;
    /**
     * 数据总数
     */
    private Integer           count;
    /**
     * 数据库查询用当前页码
     */
    private Integer           limitPageNow     = (pageNow - 1) * pageSize;

    public PageDto(Integer pageNow, Integer lastPage, Integer nextPage, Integer pageSize, Integer count,
                   Integer pageSum) {
        super();
        this.pageNow = pageNow;
        this.lastPage = lastPage;
        this.nextPage = nextPage;
        this.pageSize = pageSize;
        this.count = count;
        this.pageSum = pageSum;
    }

    /**
    * <p>方法名 PageDto.java</p> 
    * <p>说明:本类的构造函数</p> 
    * 入参dto可用此构造方法
    * @param pageNow
    * @param pageSize
     */
    public PageDto(Integer pageNow, Integer pageSize) {
        super();
        this.pageNow = pageNow;
        this.pageSize = pageSize;
        this.limitPageNow = (this.pageNow - 1) * this.pageSize;
    }

    /**
    * <p>方法名 PageDto.java</p> 
    * <p>说明:初始化并计算好分页参数</p> 
    * @param pageNow pageSize pageSum
     */
    public PageDto(Integer pageNow, Integer pageSize, Integer count) {
        super();
        this.pageNow = pageNow;
        this.pageSize = pageSize;
        this.count = count;
        this.setPageProperties();
    }

    public PageDto() {
        super();
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSum() {
        return pageSum;
    }

    public void setPageSum(Integer pageSum) {
        this.pageSum = pageSum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLimitPageNow() {
        return limitPageNow;
    }

    public void setLimitPageNow(Integer limitPageNow) {
        this.limitPageNow = limitPageNow;
    }

    /**
     * @Description：设置页码
     * 必传：
     *   总数count，
     * 有默认值：
     *   当前页pageNow，
     *   分页量pageSize
     * @return void: 返回值类型
     * @throws
     */
    private void setPageProperties() {
        //总页数
        this.pageSum = this.count / this.pageSize;
        if (this.pageSum == 0) {
            this.pageSum = 1;
        }
        //上一页
        if (this.pageNow > 1) {
            this.lastPage = this.pageNow - 1;
        } else {
            this.lastPage = null;
        }
        //下一页
        if (this.pageNow < this.pageSum) {
            this.nextPage = this.pageNow + 1;
        } else {
            this.nextPage = null;
        }
        //查询起始
        this.limitPageNow = (this.pageNow - 1) * this.pageSize;
    }

}
