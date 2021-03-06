package com.example.demo.common;

import com.example.demo.entity.ZyjCommomAttachment;
import com.example.demo.entityDto.ZyjMenuDto;
import com.example.demo.service.ZyjCommomAttachmentService;
import com.example.demo.service.ZyjMenuService;
import com.example.demo.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(value = "公用接口",tags = {"公用接口"})
@RestController
@RequestMapping("common")
public class CommonController extends BaseController{
   @Autowired
   private ZyjMenuService zyjMenuService;
   @Autowired
   private ZyjCommomAttachmentService zyjCommomAttachmentService;
   @Value("${zyj.uploaddir}")
   private String uploadDir;
   @Autowired
   private RedisUtils redisUtils;
    /**
     * 根据登录人获取用户权限
     * @return
     */
    @GetMapping("/getUserMenuList")
    @ApiOperation(value = "分页查询用户列表")
    public R getUserMenuList(){
        boolean o = redisUtils.exists(RedisKeys.getMenu(getRoleId().toString()));
        if (o==true){
            Object o1 = redisUtils.get(RedisKeys.getMenu(getRoleId().toString()));
            return R.ok().put("data",o1);
        }else {
            Long userId = getUserId();
            List<ZyjMenuDto> list;
            Map<String, Object> params = new HashMap<>();
            String menuLevel = "1,2,3";
            params.put("menuType", menuLevel);
            //超级管理员可以访问全部菜单
            if (Constant.SUPER_ADMIN == userId) {
                list = zyjMenuService.findAllMenu(null);
            } else {
                list = zyjMenuService.findUserMenuList(userId, menuLevel, null);
            }
            List<ZyjMenuDto> rootMenu = new ArrayList<>();
            if (StringUtils.isNotEmpty(menuLevel)) {
                for (ZyjMenuDto root : list) {
                    if (null != root && root.getMenuParentId() == 0) {
                        ZyjMenuDto menu = new ZyjMenuDto();
                        menu.setMenuId(root.getMenuId());
                        menu.setMenuCss(root.getMenuCss());
                        menu.setMenuName(root.getMenuName());
                        menu.setMenuParentId(root.getMenuParentId());
                        menu.setMenuUrl(root.getMenuUrl());
                        menu.setSortNo(root.getSortNo());
                        menu.setMenuLevel(root.getMenuLevel());
                        menu.setMenuPermissions(root.getMenuPermissions());
                        menu.setMenuDesc(root.getMenuDesc());
                        rootMenu.add(menu);
                    }
                }

                //如果顶级菜单有数据，开始查找子节点
                if (null != rootMenu && rootMenu.size() > 0) {
                    for (ZyjMenuDto root : rootMenu) {
                        //子节点递归查找添加  传递父节点
                        List<ZyjMenuDto> mlist = getChildren(root.getMenuId(), list);
                        root.setChildren(mlist);
                        if (null != mlist && mlist.size() > 0 && null != mlist.get(0).getChildren() && mlist.get(0).getChildren().size() > 0) {
                            root.setMenuUrl(mlist.get(0).getChildren().get(0).getMenuUrl());
                            mlist.get(0).setMenuUrl(mlist.get(0).getChildren().get(0).getMenuUrl());
                        }
                    }
                }
            }
            redisUtils.set(RedisKeys.getMenu(getRoleId().toString()),rootMenu);
            return R.ok().put("data", rootMenu);
        }
    }
    //递归获取children节点
    @ApiIgnore()
    private   List<ZyjMenuDto>  getChildren(Long pid,List<ZyjMenuDto> list) {
        List<ZyjMenuDto> children = new ArrayList<>();
        if (null != pid){
            list.forEach(data -> {
                //若遍历的数据中的父节点id等于参数id
                //则判定当前节点为该参数id节点下的子节点
                if (null != data && data.getMenuParentId().longValue() == pid.longValue()) {
                    //构造添加结果集合
                    ZyjMenuDto menu = new ZyjMenuDto();
                    menu.setMenuId(data.getMenuId());
                    menu.setMenuCss(data.getMenuCss());
                    menu.setMenuLevel(data.getMenuLevel());
                    menu.setMenuName(data.getMenuName());
                    menu.setMenuParentId(data.getMenuParentId());
                    menu.setMenuUrl(data.getMenuUrl());
                    menu.setSortNo(data.getSortNo());
                    menu.setMenuPermissions(data.getMenuPermissions());
                    menu.setMenuDesc(data.getMenuDesc());
                    children.add(menu);
                }
            });
        }
        //如果children不为空，继续递归遍历children下的子节点
        if (children.size() > 0) {
            children.forEach(data -> {
                data.setChildren(getChildren(data.getMenuId(),list));
                if(null!=data.getChildren()&& data.getChildren().size()>0) {
                    data.setMenuUrl(data.getChildren().get(0).getMenuUrl());
                }
            });
        }
        return children;
    }
    /**
     * 查询静态资源路径
     * @return
     */
    @GetMapping("/getStaticPath")
    @ApiOperation(value="查询静态资源路径")
    public R getStaticPath(){
        HttpServletRequest req = HttpContextUtils.getHttpServletRequest();
        String path = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + Constant.getfile;
        return R.ok().put("data", path);
    }
    /**
     * 上传文件
     * zyj
     * 2020/11/18
     * @param file
     * @param req
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "上传文件", notes="上传文件")
    @PostMapping("/import")
    public R importData(MultipartFile file, HttpServletRequest req) throws IOException {
        // 获取文件上传的当前目录路径
        String format = getCurrentDateStr();
        // 根据文件上传的当前目录路径 获取到目录对应的文件夹
        File folder = initUploadFolder(format) ;
        String oldName = file.getOriginalFilename();
        long size = file.getSize();
        String extendName = getCurrentFileExtendName(oldName);
        String newName = UUID.randomUUID().toString() + "." + extendName;
        file.transferTo(new File(folder,newName));
        String allUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/source" + format + newName;
        String url=format + newName;
        ZyjCommomAttachment commonAttachment = new ZyjCommomAttachment();
        commonAttachment.addPrefixInit(CastUtil.castInt(getUserId().toString()));
        commonAttachment.setAttachmentName(oldName);
        commonAttachment.setExtendName(extendName);
        commonAttachment.setSavePath(url);
        commonAttachment.setFileSize(CastUtil.castInt(size));
        zyjCommomAttachmentService.save(commonAttachment);

        return R.ok().put("data", url).put("attachmentId",commonAttachment.getAttachmentId()).put("attachmentName",oldName).put("extendName",extendName).put("size",size).put("allUrl",allUrl);

    }
    private String getCurrentDateStr(){
        return new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
    }

    private File initUploadFolder(String format){
        String savePath = uploadDir + format;
        File folder = new File(savePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }
    private String getCurrentFileExtendName (String fileSourceName){
        return fileSourceName.substring(fileSourceName .lastIndexOf(".")+1);
    }
}
