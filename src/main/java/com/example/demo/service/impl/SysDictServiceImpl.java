package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Vo.DictVo;
import com.example.demo.entity.SysDict;
import com.example.demo.mapper.SysDictMapper;
import com.example.demo.service.SysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.util.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author zyj
 * @since 2020-12-10
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {
    @Autowired
    private SysDictMapper sysDictMapper;
    /**
     * 字典表分页查询
     * @param current
     * @param size
     * @param keyWord
     * @return
     */
    @Override
    public DictVo pageList(Integer current, Integer size, String keyWord) {
        IPage page=new Page<>(current,size);
       QueryWrapper<SysDict> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(SysDict::getDelFlag,Constant.DEL_FLAG_0)
                .like(!StringUtils.isEmpty(keyWord),SysDict::getDictName,keyWord)
                .or()
                .like(!StringUtils.isEmpty(keyWord),SysDict::getDictType,keyWord);
        sysDictMapper.selectPage(page,queryWrapper);
        DictVo dictVo=new DictVo();
        dictVo.setCurrent(current);
        dictVo.setSize(size);
        dictVo.setTotal(page.getTotal());
        dictVo.setPages(page.getPages());
        dictVo.setDictList(page.getRecords());
        return dictVo;
    }
}
