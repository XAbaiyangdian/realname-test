package com.xiongan.request;

import lombok.Data;
//工资专户账单详情
@Data
public class SalaryAccountBillDto extends BaseSignRequest{
    //项目编码
    private String projectCode;
    //工资专户账号
    private String salaryAccount;
    //账单类型（1：收入    2:支出）
    private Integer billType;
    //账单行为类型（1：工资发放    2:转账）
    private String actionType;
    //账单日期，格式 yyyy-mm-dd hh:mm:ss
    private String billDate;
    //账单交易金额，单位为元
    private String billAmount;
    //账户余额，单位为元
    private String accountBalance;
    //账单交易银行代码
    private String payAccountBankCode;
    //账单交易银行卡号  AES
    private String payAccountBankAccount;
    //账单交易银行名称
    private String payAccountBankName;
    //账单批次号
    private String batchNumber;
    //来源公司统一社会信用代码(当账单类型为收入时必填)
    private String sourceCorpCode;
    //来源公司名称(当账单类型为收入时必填)
    private String sourceCorpName;
    //摘要(最多100字)
    private String excerpt;
}
