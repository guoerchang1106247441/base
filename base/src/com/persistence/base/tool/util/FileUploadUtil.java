package com.persistence.base.tool.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
public abstract class FileUploadUtil {

    /**
     * 上传文件到服务器
     *
     * @param originFileName 源文件名
     * @param serverPath     服务端路径
     * @param request        请求
     * @param fileEntity     上传文件
     * @return uploadResultInfo 文件上传结果信息
     * @throws IOException 上传文件异常
     */
    public static UploadResultInfo transferFile2Server(String originFileName, String serverPath, HttpServletRequest request, MultipartFile fileEntity) {
        FileUploadUtil.UploadResultInfo uploadResultInfo = new FileUploadUtil.UploadResultInfo();//文件上传结果信息
        try {
            String realPath = request.getServletContext().getRealPath(serverPath);
            File filePath = new File(realPath, originFileName);

            //判断路径是否存在，如果不存在就创建一个
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }

            fileEntity.transferTo(new File(realPath + File.separator + originFileName));//上传文件

            uploadResultInfo.setCode(1);
            uploadResultInfo.setPath(serverPath);
        } catch (IOException e) {
            uploadResultInfo.setCode(0);
            uploadResultInfo.setPath(null);
            e.printStackTrace();
        }
        return uploadResultInfo;
    }

    /**
     * 文件上传结果信息
     */
    public static class UploadResultInfo {
        /**
         * 上传标识code ，1 成功，0 失败
         */
        private int code;
        /**
         * 文件存储服务端路径
         */
        private String path;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
