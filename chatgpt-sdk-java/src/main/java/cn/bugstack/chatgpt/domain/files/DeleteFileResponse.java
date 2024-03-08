package cn.bugstack.chatgpt.domain.files;

import lombok.Data;

import java.io.Serializable;

/**
 * @author refain
 * @description 删除文件应答
 * {
 *   "id": "file-XjGxS3KTG0uNmNOK362iJua3",
 *   "object": "file",
 *   "deleted": true
 * }
 */
@Data
public class DeleteFileResponse implements Serializable {

    /** 文件ID */
    private String id;
    /** 对象；file */
    private String object;
    /** 删除；true */
    private boolean deleted;

}
