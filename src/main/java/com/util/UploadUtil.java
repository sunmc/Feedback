package com.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.util.bean.Common;
import com.util.bean.Result;

/**
 * 上传工具
 * 
 * @author sunmc
 * @since 2015/11/16
 *
 */
public class UploadUtil {

	// 上传图片
	public static Result<Boolean> uploadImage(String pathname, MultipartFile file) {

		Result<Boolean> res = new Result<Boolean>();
		File targetFile = new File(pathname, file.getOriginalFilename());
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
			res.setFlag(true);
			res.setData(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}

	// 上传文件
	public static Result<String> uploadFile(String userId, MultipartFile file) {
		Result<String> res = new Result<String>();
		// 存储路径
		String path = DateUtil.nowYMD() + "/" + userId + "-/";
		String fileName = file.getOriginalFilename();
		int index = fileName.lastIndexOf(".");
		String type = null;
		if (index != -1) {
			type = fileName.substring(index);
			fileName = DateUtil.nowLong() + type;
		}
		File targetFile = new File(PathUtil.UP_LOAD_PATH + path, fileName);
		File ytFile = new File(PathUtil.UP_LOAD_PATH + path, "yt" + fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		if(!ytFile.exists()){
			ytFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
			//缩小图片
			if(type != null && ".jpg.JPG".contains(type)){
				copyFile(PathUtil.UP_LOAD_PATH + path+ fileName, PathUtil.UP_LOAD_PATH + path+ "yt" + fileName, true);
				int widthdist = Common.width;
				Image src = javax.imageio.ImageIO.read(targetFile);        
			    int width = src.getWidth(null);
				int height = src.getHeight(null); 
				int heightdist = widthdist * (int)(float)height/width;
		        BufferedImage tag= new BufferedImage((int) widthdist, (int) heightdist,        
		                BufferedImage.TYPE_INT_RGB);        
		       
		        tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_SMOOTH), 0, 0,  null);             
		                
		        FileOutputStream out = new FileOutputStream(targetFile);        
		        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);        
		        encoder.encode(tag);        
		        out.close();       
			}
			res.setFlag(true);
			res.setData(path + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	// 上传文件 到指定位置
	public static Result<String> uploadFileToPath(String userId, MultipartFile file, String topath) {
		Result<String> res = new Result<String>();
		// 存储路径
		String fileName = file.getOriginalFilename();
		int index = fileName.lastIndexOf(".");
		String type = null;
		if (index != -1) {
			type = fileName.substring(index);
			fileName = DateUtil.nowLong() + type;
		}
		File targetFile = new File(topath, fileName);

		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
			res.setFlag(true);
			res.setData(fileName);
		} catch (Exception e) {
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	// 上传文件
	public static Result<String> uploadProject(MultipartFile file) {
		Result<String> res = new Result<String>();
		// 存储路径
		String path = "/root/";
		String fileName = file.getOriginalFilename();
		File targetFile = new File(PathUtil.UP_LOAD_PATH + path, fileName);

		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
			res.setFlag(true);
			res.setData(path + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			res.setFlag(false);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	 /** 
     * 复制单个文件 
     *  
     * @param srcFileName 
     *            待复制的文件名 
     * @param descFileName 
     *            目标文件名 
     * @param overlay 
     *            如果目标文件存在，是否覆盖 
     * @return 如果复制成功返回true，否则返回false 
     */  
    public static boolean copyFile(String srcFileName, String destFileName,  
            boolean overlay) {  
        File srcFile = new File(srcFileName);  
  
        // 判断源文件是否存在  
        if (!srcFile.exists()) {  
//            MESSAGE = "源文件：" + srcFileName + "不存在！";  
//            JOptionPane.showMessageDialog(null, MESSAGE);  
            return false;  
        } else if (!srcFile.isFile()) {  
//            MESSAGE = "复制文件失败，源文件：" + srcFileName + "不是一个文件！";  
//            JOptionPane.showMessageDialog(null, MESSAGE);  
            return false;  
        }  
  
        // 判断目标文件是否存在  
        File destFile = new File(destFileName);  
        if (destFile.exists()) {  
            // 如果目标文件存在并允许覆盖  
            if (overlay) {  
                // 删除已经存在的目标文件，无论目标文件是目录还是单个文件  
                new File(destFileName).delete();  
            }  
        } else {  
            // 如果目标文件所在目录不存在，则创建目录  
            if (!destFile.getParentFile().exists()) {  
                // 目标文件所在目录不存在  
                if (!destFile.getParentFile().mkdirs()) {  
                    // 复制文件失败：创建目标文件所在目录失败  
                    return false;  
                }  
            }  
        }  
  
        // 复制文件  
        int byteread = 0; // 读取的字节数  
        InputStream in = null;  
        OutputStream out = null;  
  
        try {  
            in = new FileInputStream(srcFile);  
            out = new FileOutputStream(destFile);  
            byte[] buffer = new byte[1024];  
  
            while ((byteread = in.read(buffer)) != -1) {  
                out.write(buffer, 0, byteread);  
            }  
            return true;  
        } catch (FileNotFoundException e) {  
            return false;  
        } catch (IOException e) {  
            return false;  
        } finally {  
            try {  
                if (out != null)  
                    out.close();  
                if (in != null)  
                    in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
