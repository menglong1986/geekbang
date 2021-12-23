package com.example.demo.pojo;


public class TOrder {

  private long id;
  private long buyerId;
  private String orderNo;
  private String parentOrderNo;
  private String orderNoOut;
  private long tradeType;
  private long tradeStatus;
  private String channel;
  private java.sql.Timestamp paymentTime;
  private double amount;
  private double orginAmount;
  private double deliveryFee;
  private double actualAmount;
  private double discountPrice;
  private String deviceId;
  private long isReverse;
  private String extData;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private String creator;
  private String modifier;
  private long isDeleted;
  private java.sql.Timestamp orderReceiveTime;
  private java.sql.Timestamp orderLogisticsTime;
  private long isParent;
  private String cancelReasonDesc;
  private long cancelType;
  private long cancelOperateId;
  private java.sql.Timestamp cancelTime;
  private long cancelReasonId;
  private String extParam;
  private long isShow;
  private long paymentStatus;
  private java.sql.Timestamp closeTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(long buyerId) {
    this.buyerId = buyerId;
  }


  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }


  public String getParentOrderNo() {
    return parentOrderNo;
  }

  public void setParentOrderNo(String parentOrderNo) {
    this.parentOrderNo = parentOrderNo;
  }


  public String getOrderNoOut() {
    return orderNoOut;
  }

  public void setOrderNoOut(String orderNoOut) {
    this.orderNoOut = orderNoOut;
  }


  public long getTradeType() {
    return tradeType;
  }

  public void setTradeType(long tradeType) {
    this.tradeType = tradeType;
  }


  public long getTradeStatus() {
    return tradeStatus;
  }

  public void setTradeStatus(long tradeStatus) {
    this.tradeStatus = tradeStatus;
  }


  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }


  public java.sql.Timestamp getPaymentTime() {
    return paymentTime;
  }

  public void setPaymentTime(java.sql.Timestamp paymentTime) {
    this.paymentTime = paymentTime;
  }


  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }


  public double getOrginAmount() {
    return orginAmount;
  }

  public void setOrginAmount(double orginAmount) {
    this.orginAmount = orginAmount;
  }


  public double getDeliveryFee() {
    return deliveryFee;
  }

  public void setDeliveryFee(double deliveryFee) {
    this.deliveryFee = deliveryFee;
  }


  public double getActualAmount() {
    return actualAmount;
  }

  public void setActualAmount(double actualAmount) {
    this.actualAmount = actualAmount;
  }


  public double getDiscountPrice() {
    return discountPrice;
  }

  public void setDiscountPrice(double discountPrice) {
    this.discountPrice = discountPrice;
  }


  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }


  public long getIsReverse() {
    return isReverse;
  }

  public void setIsReverse(long isReverse) {
    this.isReverse = isReverse;
  }


  public String getExtData() {
    return extData;
  }

  public void setExtData(String extData) {
    this.extData = extData;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }


  public String getModifier() {
    return modifier;
  }

  public void setModifier(String modifier) {
    this.modifier = modifier;
  }


  public long getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(long isDeleted) {
    this.isDeleted = isDeleted;
  }


  public java.sql.Timestamp getOrderReceiveTime() {
    return orderReceiveTime;
  }

  public void setOrderReceiveTime(java.sql.Timestamp orderReceiveTime) {
    this.orderReceiveTime = orderReceiveTime;
  }


  public java.sql.Timestamp getOrderLogisticsTime() {
    return orderLogisticsTime;
  }

  public void setOrderLogisticsTime(java.sql.Timestamp orderLogisticsTime) {
    this.orderLogisticsTime = orderLogisticsTime;
  }


  public long getIsParent() {
    return isParent;
  }

  public void setIsParent(long isParent) {
    this.isParent = isParent;
  }


  public String getCancelReasonDesc() {
    return cancelReasonDesc;
  }

  public void setCancelReasonDesc(String cancelReasonDesc) {
    this.cancelReasonDesc = cancelReasonDesc;
  }


  public long getCancelType() {
    return cancelType;
  }

  public void setCancelType(long cancelType) {
    this.cancelType = cancelType;
  }


  public long getCancelOperateId() {
    return cancelOperateId;
  }

  public void setCancelOperateId(long cancelOperateId) {
    this.cancelOperateId = cancelOperateId;
  }


  public java.sql.Timestamp getCancelTime() {
    return cancelTime;
  }

  public void setCancelTime(java.sql.Timestamp cancelTime) {
    this.cancelTime = cancelTime;
  }


  public long getCancelReasonId() {
    return cancelReasonId;
  }

  public void setCancelReasonId(long cancelReasonId) {
    this.cancelReasonId = cancelReasonId;
  }


  public String getExtParam() {
    return extParam;
  }

  public void setExtParam(String extParam) {
    this.extParam = extParam;
  }


  public long getIsShow() {
    return isShow;
  }

  public void setIsShow(long isShow) {
    this.isShow = isShow;
  }


  public long getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(long paymentStatus) {
    this.paymentStatus = paymentStatus;
  }


  public java.sql.Timestamp getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(java.sql.Timestamp closeTime) {
    this.closeTime = closeTime;
  }

}
