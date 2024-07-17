package com.xiongan;

import com.alibaba.fastjson.JSON;
import com.xiongan.common.OkHttpClient;
import com.xiongan.common.SM4Utils;
import com.xiongan.common.SignUtil;
import com.xiongan.request.AttachmentDto;
import com.xiongan.request.SalaryAccountBillDto;
import com.xiongan.request.SalaryAccountDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PushTest {

    @Test
    public void saveSalaryAccountTest() throws Exception {
        String clientCode = "";
        String secretKey = "";
        String encryptKey = "";
        String url = "http://localhost:8001/api/receiver/open/bank/saveSalaryAccount";

        SalaryAccountDto salaryAccountDto = new SalaryAccountDto();
        salaryAccountDto.setProjectCode("110110011");
        salaryAccountDto.setCorpCode(SM4Utils.encryptToBase64("91000000000000", encryptKey));
        salaryAccountDto.setCorpName(SM4Utils.encryptToBase64("河北科技有限公司", encryptKey));
        salaryAccountDto.setSalaryAccountBankCode(SM4Utils.encryptToBase64("104", encryptKey));
        salaryAccountDto.setSalaryAccountBankName(SM4Utils.encryptToBase64("中国银行", encryptKey));
        salaryAccountDto.setSalaryAccount(SM4Utils.encryptToBase64("6217000000000000001", encryptKey));
        salaryAccountDto.setSalaryAccountName(SM4Utils.encryptToBase64("xx专户", encryptKey));
        salaryAccountDto.setOpenAccountDate(SM4Utils.encryptToBase64("2022-01-12", encryptKey));
        salaryAccountDto.setAccountBalance(SM4Utils.encryptToBase64("100", encryptKey));

        List<AttachmentDto> attachmentDtos = new ArrayList<>();

        AttachmentDto attachmentDto1 = new AttachmentDto();
        attachmentDto1.setAttachmentType(1);
        attachmentDto1.setAttachmentName(SM4Utils.encryptToBase64("AttachmentName1", encryptKey));
        attachmentDto1.setAttachmentUrl(SM4Utils.encryptToBase64("AttachmentUrl1", encryptKey));
        AttachmentDto attachmentDto2 = new AttachmentDto();
        attachmentDto2.setAttachmentType(0);
        attachmentDto2.setAttachmentName(SM4Utils.encryptToBase64("AttachmentName2", encryptKey));
        attachmentDto2.setAttachmentUrl(SM4Utils.encryptToBase64("AttachmentUrl2", encryptKey));

        attachmentDtos.add(attachmentDto1);
        attachmentDtos.add(attachmentDto2);

        salaryAccountDto.setAttachments(attachmentDtos);

        salaryAccountDto.setClientCode(clientCode);
        salaryAccountDto.setTimestamp(System.currentTimeMillis());
        String signature = SignUtil.sign(salaryAccountDto.toSignMap(), secretKey);
        salaryAccountDto.setSignature(signature);

        String result = OkHttpClient.post(url, JSON.toJSONString(salaryAccountDto));
        System.out.println(result);
    }

    @Test
    public void saveSalaryAccountBillTest() throws Exception {
        String clientCode = "";
        String secretKey = "";
        String encryptKey = "";
        String url = "http://localhost:8001/api/receiver/open/bank/saveSalaryAccountBill";

        SalaryAccountBillDto salaryAccountBillDto = new SalaryAccountBillDto();
        salaryAccountBillDto.setProjectCode("110110011");
        salaryAccountBillDto.setSalaryAccount(SM4Utils.encryptToBase64("6217000000000000001", encryptKey));
        salaryAccountBillDto.setBillType(1);
        salaryAccountBillDto.setActionType(1);
        salaryAccountBillDto.setBillDate(SM4Utils.encryptToBase64("2022-01-02 15:22:33", encryptKey));
        salaryAccountBillDto.setBillAmount(SM4Utils.encryptToBase64("100", encryptKey));
        salaryAccountBillDto.setAccountBalance(SM4Utils.encryptToBase64("100", encryptKey));
        salaryAccountBillDto.setPayAccountBankCode(SM4Utils.encryptToBase64("104", encryptKey));
        salaryAccountBillDto.setPayAccountBankAccount(SM4Utils.encryptToBase64("6217000000000000001", encryptKey));
        salaryAccountBillDto.setPayAccountBankName(SM4Utils.encryptToBase64("中国银行", encryptKey));
        salaryAccountBillDto.setBatchNumber(SM4Utils.encryptToBase64("11011011", encryptKey));
        salaryAccountBillDto.setSourceAccountName(SM4Utils.encryptToBase64("来源账号户名", encryptKey));
        salaryAccountBillDto.setSourceBankAccount(SM4Utils.encryptToBase64("91000000000000", encryptKey));
        salaryAccountBillDto.setExcerpt("摘要");

        salaryAccountBillDto.setClientCode(clientCode);
        salaryAccountBillDto.setTimestamp(System.currentTimeMillis());
        String signature = SignUtil.sign(salaryAccountBillDto.toSignMap(), secretKey);
        salaryAccountBillDto.setSignature(signature);

        String result = OkHttpClient.post(url, JSON.toJSONString(salaryAccountBillDto));
        System.out.println(result);
    }
}
