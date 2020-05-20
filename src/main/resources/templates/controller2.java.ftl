package ${package.Controller};

import ${package.Entity}.RespBody;

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
    * ${table.comment!} 前端控制器
    * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Api(tags = "${table.controllerName}", description = "${cfg.myTitle}管理")
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>

    private final ${table.serviceName} ${cfg.myName}Service;

    @Autowired
    public ${table.controllerName}(${table.serviceName} ${cfg.myName}Service) {
        this.${cfg.myName}Service = ${cfg.myName}Service;
    }

    @ApiOperation("查询${cfg.myTitle}")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody find${entity}(${entity} ${cfg.myName},
                                 @RequestParam(value = "pageStart",defaultValue = "1")Integer pageStart,
                                 @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize){
        List<${entity}> ${cfg.myName}List = ${cfg.myName}Service.find${entity}(${cfg.myName}, pageStart, pageSize);
        PageInfo<${entity}> pageInfo = new PageInfo<>(${cfg.myName}List);
        return RespBody.ok(pageInfo);
    }

    @ApiOperation("添加${cfg.myTitle}")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBody insert${entity}(${entity} ${cfg.myName}) {
        int result = ${cfg.myName}Service.insert${entity}(${cfg.myName});
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("修改${cfg.myTitle}")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBody update${entity}(${entity} ${cfg.myName}) {
        int result = ${cfg.myName}Service.update${entity}(${cfg.myName});
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

    @ApiOperation("删除${cfg.myTitle}")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public RespBody delete${entity}(@RequestParam("id")int id) {
        int result = ${cfg.myName}Service.delete${entity}(id);
        if (result == 1) {
            return RespBody.ok();
        }
        return RespBody.error();
    }

}
</#if>
