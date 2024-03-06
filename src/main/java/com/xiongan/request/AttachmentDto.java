package com.xiongan.request;

import lombok.Data;

@Data
public class AttachmentDto {
    //附件类型（1：三方协议）
    private Integer attachmentType;
    //附件名称 SM4
    private String attachmentName;
    //附件内容 (base64字符串) SM4
    private String attachmentUrl;
}
