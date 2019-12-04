<?xml version="1.0"?>
<globals>
    <global id="hasNoActionBar" type="boolean" value="false" />
    <global id="parentActivityClass" value="" />
    <global id="excludeMenu" type="boolean" value="true" />
    <global id="isLauncher" type="boolean" value="false" />
    <global id="generateActivityTitle" type="boolean" value="false" />
    <global id="relativePackage" value="${ativityPackageName}" />
    <global id="activityClass" value="${pageName}Activity" />
    <#include "../common/common_globals.xml.ftl" />
</globals>

<#macro fileHeader>
/**
 * <pre>
 *     author : jacks
 *     e-mail : 363525749@qq.com
 *     time   : ${.now?string["MM/dd/yyyy HH:mm"]}
 *     version: 1.0
 *     desc   : ${pageName}
 * </pre>
 */
</#macro>