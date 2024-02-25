package com.xiongan.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.List;
import java.util.Map;

//工资专户详情
@Data
public class SalaryAccountDto extends BaseSignRequest{
    //项目编码
    private String projectCode;
    //工资专户开户企业统一社会信用代码
    private String corpCode;
    //企业名称
    private String corpName;
    //工资专户开户银行代码
    private String salaryAccountBankCode;
    //工资专户开户银行名称
    private String salaryAccountBankName;
    //工资专户账号
    private String salaryAccount;
    //工资专户名称
    private String salaryAccountName;
    //开户日期，yyyy-MM-dd
    private String openAccountDate;
    //账户余额，单位为元
    private String accountBalance;
    //工资专户附件 (非签名字段)
    private List<AttachmentDto> attachments;

    public Map<String, Object> toSignMap() {
        String s = JSON.toJSONString(this, SerializerFeature.IgnoreNonFieldGetter, SerializerFeature.WriteMapNullValue);
        Map map = JSON.parseObject(s, Map.class);
        map.remove("signature");
        map.remove("attachments");
        return map;
    }
}
