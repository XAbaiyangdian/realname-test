package com.xiongan;

import com.alibaba.fastjson.JSON;
import com.xiongan.common.OkHttpClient;
import com.xiongan.common.SM4Utils;
import com.xiongan.common.SignUtil;
import com.xiongan.request.AttachmentDto;
import com.xiongan.request.SalaryAccountBillDto;
import com.xiongan.request.SalaryAccountDto;
import com.xiongan.request.SalaryDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PushTest {

    @Test
    public void saveSalaryTest() throws Exception {
        String clientCode = "";
        String secretKey = "";
        String encryptKey = "";
        String url = "http://localhost:8001/api/receiver/open/bank/saveSalary";

        SalaryDto salaryDto = new SalaryDto();
        salaryDto.setProjectCode("ProjectCode");
        salaryDto.setIdCardNumber(SM4Utils.encryptToBase64("IdCardNumber", encryptKey));
        salaryDto.setWorkName("WorkName");
        salaryDto.setTotalPayAmount("TotalPayAmount");
        salaryDto.setActualAmount("ActualAmount");
        salaryDto.setPayMonth("PayMonth");
        salaryDto.setBalanceDate("BalanceDate");
        salaryDto.setReceiveBankAccount(SM4Utils.encryptToBase64("ReceiveBankAccount", encryptKey));
        salaryDto.setReceiveBankCode("ReceiveBankCode");
        salaryDto.setReceiveOpenbankName("ReceiveOpenbankName");
        salaryDto.setPayBankCardNumber(SM4Utils.encryptToBase64("PayBankCardNumber", encryptKey));
        salaryDto.setPayBankCode("PayBankCode");
        salaryDto.setPayBankName("PayBankName");
        salaryDto.setIsBackPay(1);
        salaryDto.setBackPayMonth("BackPayMonth");
        salaryDto.setThirdPayRollCode("ThirdPayRollCode");

        salaryDto.setTimestamp(System.currentTimeMillis());
        salaryDto.setClientCode(clientCode);
        String signature = SignUtil.sign(salaryDto.toSignMap(), secretKey);
        salaryDto.setSignature(signature);
        String result = OkHttpClient.post(url, JSON.toJSONString(salaryDto));
        System.out.println(result);
    }
    @Test
    public void saveSalaryAccountTest() throws Exception {
        String clientCode = "";
        String secretKey = "";
        String encryptKey = "";
        String url = "http://localhost:8001/api/receiver/open/bank/saveSalaryAccount";

        SalaryAccountDto salaryAccountDto = new SalaryAccountDto();
        salaryAccountDto.setProjectCode("ProjectCode");
        salaryAccountDto.setCorpCode("CorpCode");
        salaryAccountDto.setCorpName("CorpName");
        salaryAccountDto.setSalaryAccountBankCode("SalaryAccountBankCode");
        salaryAccountDto.setSalaryAccountBankName("SalaryAccountBankName");
        salaryAccountDto.setSalaryAccount("SalaryAccount");
        salaryAccountDto.setSalaryAccountName("SalaryAccountName");
        salaryAccountDto.setOpenAccountDate("OpenAccountDate");
        salaryAccountDto.setAccountBalance("AccountBalance");

        List<AttachmentDto> attachmentDtos = new ArrayList<AttachmentDto>();

        AttachmentDto attachmentDto1 = new AttachmentDto();
        attachmentDto1.setAttachmentType(1);
        attachmentDto1.setAttachmentName("AttachmentName1");
        attachmentDto1.setAttachmentUrl("AttachmentUrl1");
        AttachmentDto attachmentDto2 = new AttachmentDto();
        attachmentDto2.setAttachmentType(0);
        attachmentDto2.setAttachmentName("AttachmentName2");
        attachmentDto2.setAttachmentUrl("AttachmentUrl2");

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
        salaryAccountBillDto.setProjectCode("ProjectCode");
        salaryAccountBillDto.setSalaryAccount("SalaryAccount");
        salaryAccountBillDto.setBillType(1);
        salaryAccountBillDto.setActionType("ActionType");
        salaryAccountBillDto.setBillDate("BillDate");
        salaryAccountBillDto.setBillAmount("BillAmount");
        salaryAccountBillDto.setAccountBalance("AccountBalance");
        salaryAccountBillDto.setPayAccountBankCode("PayAccountBankCode");
        salaryAccountBillDto.setPayAccountBankAccount(SM4Utils.encryptToBase64("PayAccountBankAccount", encryptKey));
        salaryAccountBillDto.setPayAccountBankName("PayAccountBankName");
        salaryAccountBillDto.setBatchNumber("BatchNumber");
        salaryAccountBillDto.setSourceCorpCode("SourceCorpCode");
        salaryAccountBillDto.setSourceCorpName("SourceCorpName");
        salaryAccountBillDto.setExcerpt("Excerpt");

        salaryAccountBillDto.setClientCode(clientCode);
        salaryAccountBillDto.setTimestamp(System.currentTimeMillis());
        String signature = SignUtil.sign(salaryAccountBillDto.toSignMap(), secretKey);
        salaryAccountBillDto.setSignature(signature);

        String result = OkHttpClient.post(url, JSON.toJSONString(salaryAccountBillDto));
        System.out.println(result);
    }
}
