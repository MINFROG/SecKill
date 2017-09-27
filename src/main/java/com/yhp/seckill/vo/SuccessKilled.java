package com.yhp.seckill.vo;

import java.util.Date;

/**
 * Created by ASUS on 2017/9/25.
 */
public class SuccessKilled {
    private Long seckillId;
    private Long userPhone;
    private Integer state;//状态标识:-1:无效 0:成功 1:已付款 2:已发货
    private Date createTime;
    //多对一,因为一件商品在库存中有很多数量，对应的购买明细也有很多。
    private Seckill seckill;

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
