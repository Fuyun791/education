package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

import java.util.List;

/**
 * <p>
    * ${table.comment!} 服务类
    * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 查找 ${cfg.myName}
     * @param ${cfg.myName}
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<${entity}> find${entity}(${entity} ${cfg.myName}, Integer pageStart, Integer pageSize);

    /**
     * 添加 ${cfg.myName}
     * @param ${cfg.myName}
     * @return
     */
    int insert${entity}(${entity} ${cfg.myName});

    /**
     * 修改 ${cfg.myName}
     * @param ${cfg.myName}
     * @return
     */
    int update${entity}(${entity} ${cfg.myName});

    /**
     * 删除 ${cfg.myName}
     * @param id
     * @return
     */
    int delete${entity}(int id);

}
</#if>
