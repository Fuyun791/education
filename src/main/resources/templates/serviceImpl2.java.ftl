package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>
    * ${table.comment!} 服务实现类
    * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Autowired
    private ${table.mapperName} ${cfg.myName}Mapper;

    @Override
    public List<${entity}> find${entity}(${entity} ${cfg.myName}, Integer pageStart, Integer pageSize) {
        //这里根据具体的条件进行扩充
        QueryWrapper<${entity}> queryWrapper=new QueryWrapper<>(${cfg.myName});
        PageHelper.startPage(pageStart,pageSize);
        return ${cfg.myName}Mapper.selectList(queryWrapper);
    }

    @Override
    public int insert${entity}(${entity} ${cfg.myName}) {
        return ${cfg.myName}Mapper.insert(${cfg.myName});
    }

    @Override
    public int update${entity}(${entity} ${cfg.myName}) {
        return ${cfg.myName}Mapper.updateById(${cfg.myName});
    }

    @Override
    public int delete${entity}(int id) {
        return ${cfg.myName}Mapper.deleteById(id);
    }

}
</#if>
