package com.xiongan.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.Map;

//人员实发工资
@Data
public class SalaryDto extends BaseSignRequest{
    //项目编码
    private String projectCode;
    //人员证件号码  AES
    private String idCardNumber;
    //姓名
    private String workName;
    //应发工资 单位元
    private String totalPayAmount;
    //实发工资 单位元
    private String actualAmount;
    //工资发放月份。格式 yyyy-MM-dd ,默认传月份的第一天
    private String payMonth;
    //工资实际发放日期。格式 yyyy-MM-dd
    private String balanceDate;
    //接收工资银行卡号 AES
    private String receiveBankAccount;
    //接收工资银行编码 参考 银行代码字典表
    private String receiveBankCode;
    //接收工资卡号开户银行名称
    private String receiveOpenbankName;
    //工资代发银行卡号 AES
    private String payBankCardNumber;
    //工资代发银行代码, 参考 银行代码字典表
    private String payBankCode;
    //工资代发开户行名称
    private String payBankName;
    //是否为补发（0否1是）
    private Integer isBackPay;
    //补发月份格式 yyyy-MM-dd如果是补发，此字段必填 (非签名字段)
    private String backPayMonth;
    //第三方工资编号
    private String thirdPayRollCode;

    public Map<String, Object> toSignMap() {
        String s = JSON.toJSONString(this, SerializerFeature.IgnoreNonFieldGetter, SerializerFeature.WriteMapNullValue);
        Map map = JSON.parseObject(s, Map.class);
        map.remove("signature");
        map.remove("backPayMonth");
        return map;
    }
}
