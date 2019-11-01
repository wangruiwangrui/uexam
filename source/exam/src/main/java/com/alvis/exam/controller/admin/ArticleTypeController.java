package com.alvis.exam.controller.admin;


import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.ArticleType;
import com.alvis.exam.service.ArticleTypeService;
import com.alvis.exam.utility.UploadUtils;
import com.alvis.exam.viewmodel.admin.message.MessagePageRequestVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/api/admin/articleType")
@RestController("ArticleTypeController")
public class ArticleTypeController {

    @Resource
    private ArticleTypeService articleTypeService;

    /**
     * 展示文章分类
     *
     * @param
     */
    @RequestMapping(value = "/typeList")
    public RestResponse<PageInfo<ArticleType>> typeList(@RequestBody MessagePageRequestVM model) {
        PageInfo<ArticleType> pageInfo = articleTypeService.pageList(model);
        List<ArticleType> list = pageInfo.getList();
        for (ArticleType articleType : list) {
            String origname = articleType.getOrigname();
            articleType.setPathDeposit("http://192.168.100.185:8091/images/" + origname);
        }
        return RestResponse.ok(pageInfo);
    }

    /**
     * 修改分类
     * @param file
     * @return 图片路径
     */
    @RequestMapping("updateType")
    public RestResponse updateType(MultipartFile file, String type, int id) {
        ArticleType articleType = new ArticleType();
        //file为空的时候
        if (file == null) {
            articleType.setId(id);
            articleType.setTypeName(type);
            articleTypeService.updateArticleType(articleType);
        }
        else {
            //图片上传
            Map<String, String> stringMap = UploadUtils.upload(file);
            String fileNameNew = stringMap.get("fileNameNew");    //图片名称
            String sqlSaveUrl = stringMap.get("sqlSaveUrl");    //图片访问地址
            //存名称
            System.out.println("上传的文件原名称:" + fileNameNew);
            articleType.setOrigname(fileNameNew);

//            String url = "http://192.168.100.185:8091/images/";
            articleType.setPathDeposit(sqlSaveUrl);
            articleType.setTypeName(type);
            articleType.setId(id);
//            System.out.println(url + fileName);
            articleTypeService.updateType(articleType);
            System.out.println("图片修改成功:" + sqlSaveUrl);
        }
        return RestResponse.ok();
    }

    /**
     * 图片存到磁盘，添加分类
     *
     * @param file
     * @return 图片路径
     */
    @RequestMapping("saveType")
    public RestResponse saveType(MultipartFile file, String type) {
        ArticleType articleType = new ArticleType();

        //图片上传
        Map<String, String> stringMap = UploadUtils.upload(file);
        String fileNameNew = stringMap.get("fileNameNew");    //图片原名称
        String sqlSaveUrl = stringMap.get("sqlSaveUrl");    //图片访问地址

        //存名称
        articleType.setOrigname(fileNameNew);
//        String url = "http://192.168.100.185:8091/images/";
        articleType.setPathDeposit(sqlSaveUrl);
        articleType.setTypeName(type);
        articleType.setState(1);
//        System.out.println(url + fileName);
        articleTypeService.saveArticleType(articleType);
        System.out.println("图片上传成功:" + sqlSaveUrl);
        return RestResponse.ok();
    }

    /**
     * 删除文章分类
     * @param
     * @return 返回状态码
     */
    @RequestMapping("deleteType")
    public RestResponse deleteType(@RequestBody ArticleType articleType) {
        articleTypeService.deleteType(articleType);
        return RestResponse.ok();
    }

}