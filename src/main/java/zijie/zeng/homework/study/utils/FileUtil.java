package zijie.zeng.homework.study.utils;

import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import zijie.zeng.homework.study.exceptions.SfcExtException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Slf4j
public class FileUtil {
    /**
     * 上传文件
     *
     * @param inputStream
     * @param fileName
     * @return
     */
    public static String saveFile(InputStream inputStream, String fileName) {

        OutputStream os = null;
        String url = null;
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            String pathMid = path+ "homework/";

            File tempFile = new File(pathMid);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            url = tempFile.getPath() + File.separator + fileName;
            os = new FileOutputStream(url);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String url) {
        //尝试下载文件开始
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            //获取文件存放的路径
            File file = new File(url);
            String fileName=file.getName();

            if(!file.exists()) {
                //如果文件不存在就跳出
                throw new SfcExtException("文件不存在");
            }
            ips = new FileInputStream(file);
            response.setContentType("multipart/form-data");
            //为文件重新设置名字，采用数据库内存储的文件名称
            response.addHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"),"ISO8859-1") + "");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            log.error("出现错误{}", e);
        }finally {
            try {
                out.close();
                ips.close();
            } catch (IOException e) {
                log.error("关闭流出现异常");
            }
        }
        log.info("success");

        return ;
    }

    /**
     * 获得文件后缀
     *
     * @param file
     * @return
     */
    public static String getFileSuffixName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        List<String> fileNameList = Splitter.on(".").splitToList(fileName);
        if (!fileNameList.isEmpty()) {
            String fileSuffixName = fileNameList.get(fileNameList.size() - 1);
            return fileSuffixName;
        }
        return null;
    }
}
