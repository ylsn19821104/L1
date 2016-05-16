<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>采购单</title>
    <jsp:include page="common.jsp"/>
  
    <script type="text/javascript" src="resources/js/purchase.js"></script>
    <script type="text/javascript" src="resources/js/databox-formatter.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid" title="采购单" fit="true"
       data-options="pagination:'true',rownumbers:true,singleSelect:false,selectOnCheck:true,url:'purchase/list',method:'get',toolbar:'#menu'">
    <thead data-options="frozen:true">
    <tr>
        <th field="id" width="50" align="center" hidden="true">ID</th>
        <th field="cb" checkbox="true" align="center"></th>
        <th data-options="field:'billNo',width:105">采购单号</th>
        <th data-options="field:'stat',hidden:true">单据状态</th>
        <th data-options="field:'statName',width:80">单据状态</th>
        <th data-options="field:'supplierName',width:80">供应商</th>
        <th data-options="field:'warehouseName',width:80">出库仓库</th>
        <th data-options="field:'total',width:70">采购总金额</th>
        <th data-options="field:'create_by',width:60,hidden:true">操作员</th>
        <th data-options="field:'create_time',width:80,hidden:true">
           制单日期
        </th>
        <th data-options="field:'create_timeStr',width:80,formatter:function(value,row){ if(row.create_time) {return new Date(row.create_time).format('yyyy-MM-dd')};}">
            制单日期
        </th>
    </tr>
    </thead>
</table>
<div id="menu" style="padding:2px 5px;">
    <a id="btn_add" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    <a id="btn_edit" href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
    <a id="btn_remove" href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    <a id="btn_finish" href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true">审核</a>
    <a id="btn_unfinish" href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true">取消审核</a>
</div>
<%--以下为编辑面板的内容--%>
<div id="editPanel" class="easyui-dialog edit-panel" title="编辑" style="height: 650px;height: 500px;"
     data-options="minimizable: false,maximizable: false,resizable: true,modal:true,closed:true,buttons: '#editPanel-buttons'">
    <form id="editForm" method="post">
        <input type="hidden" name="id" id="id">
        <table>
            <tr>
                <td class="label">采购单号</td>
                <td><input class="easyui-textbox input" style="width:173px" data-options="disabled:true" name="billNo"
                           id="billNo" readonly>
                </td>
                <td class="label">单据状态</td>
                <td>
                    <select class="easyui-combobox input" name="stat" id="stat" style="width:173px"  
                            data-options="
                    disabled:true,
                    valueField: 'valueField',
                    textField: 'textField',
                    data:[{valueField:'0',textField:'未审核'},{valueField:'1',textField:'已审核'}]">
                    </select>
                </td>
            </tr>
            <tr>
            	<td class="label">供应商</td>
                <td>
                    <select class="easyui-combobox" name="supplierId" style="width:173px" data-options="
                    required:true,
                    valueField: 'id',
                    textField: 'text',
                    url: '${pageContext.request.contextPath}/supplier/comboList'">
                    </select>

                </td>
                
                <td class="label">入库仓库</td>
                <td>
                    <select class="easyui-combobox input" name="warehouseId" id="warehouseId" style="width:173px" data-options="
                    required:true,
                    editable:false,
                    valueField: 'id',
                    textField: 'text',
                    method:'get',
                    url: 'warehouse/comboList',
                    onSelect:function(ret){
                        var rows = $('#t2_dg').datagrid('getRows');
                        if(rows&&rows.length>0){
                            $.messager.confirm('系统提示','重新选择仓库需要重新填写采购明细,是否继续?？',function(r){
                                if (r){
                                    $('#t2_dg').datagrid('loadData',{total:0,rows:[]});
                                }
                            });
                        }
                    }">
                    </select>
                </td>
            </tr>
            <tr>
                <td class="label">采购总金额</td>
                <td>
                	<input class="easyui-numberbox input" readonly type="text" name="total" id="total" style="width:173px">
                </td>	
            </tr>
            
            <tr>
            	<td class="label">单据日期</td>
                <td>
                    <input id="create_time" class="easyui-datebox" name="create_time"
                            data-options="editable:false,required:true" style="width:173px">
                </td>
                
                <td class="label">操作员</td>
                <td>
                	<input class="easyui-textbox input" readonly type="text" name="create_by" id="create_by" style="width:173px">
                </td>
                </td>
            </tr>
        </table>
    </form>
    <div id="t2_panel">
        <table id="t2_dg" class="easyui-datagrid" title="采购明细"
               data-options="pagination:false,rownumbers:true,singleSelect:false,method:'get',toolbar:'#t2_menu'">
            <thead>
            <tr>
                <th field="cb" checkbox="true" align="center"></th>
                <th data-options="field:'id',width:80,hidden:true">id</th>
                <th data-options="field:'dtlId',width:80,hidden:true">dtlId</th>
                <th data-options="field:'skuId',width:80">SKU</th>
                <th data-options="field:'itemName',width:80">商品名称</th>
                <th data-options="field:'itemPrice',width:80,align:'right'">单价</th>
                <th data-options="field:'itemAmount',width:60,align:'right'">数量</th>
                <th data-options="field:'itemTotal',width:60">金额</th>
            </tr>
            </thead>
        </table>
        <div id="t2_menu" style="padding:2px 5px;">
            <a id="btn_t2_add" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
            <a id="btn_t2_edit" href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
            <a id="btn_t2_remove" href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </div>
        <div id="t2EditPanel" class="easyui-dialog" title="编辑" style="padding:10px;width:700px;"
             data-options="modal:true,closed:true,buttons: '#t2EditPanel-buttons'">
            <form id="t2EditForm">
                <table>
                    <tr>
                        <td>SKU</td>
                        <td>
                            <select class="easyui-combobox" name="skuId" id="skuId" style="width:150px;">
                            </select>
                        </td>

                        <td>商品名称</td>
                        <td>
                            <input class="easyui-textbox" data-options="disabled:true" name="itemName" id="itemName"
                                   style="width:150px">
                        </td>
                        <td rowspan="5"><img id="skuImage" src="resources/images/upload/example.jpg"
                                             style="width: 200px;height: 200px"></td>
                    </tr>
                    <tr>
                        <td>颜色</td>
                        <td>
                            <input class="easyui-textbox" data-options="disabled:true" name="colorName" id="colorName"
                                   style="width:150px">
                        </td>
                        <td>尺码</td>
                        <td>
                            <input class="easyui-textbox" data-options="disabled:true" name="sizeDtlName" id="sizeDtlName"
                                   style="width:150px">
                        </td>
                    </tr>
                    <tr>
                        <td>数量</td>
                        <td>
                            <input class="easyui-numberbox" type="text" name="itemAmount" id="itemAmount"
                                   style="width:150px"
                                   data-options="min:1,precision:0,required:true,missingMessage:'请填写数量',
                               onChange:function(newValue,oldValue){
                                    var itemPrice = $('#itemPrice').numberbox('getValue');
                                    $('#itemTotal').numberbox('setValue',newValue*itemPrice);
                                }">
                        </td>
                        <td>单价</td>
                        <td>
                            <input class="easyui-numberbox" type="text" name="itemPrice" id="itemPrice"
                                   style="width:150px"
                                   data-options="min:0,precision:2,required:true,
                               onChange:function(newValue,oldValue){
                                    var amount= $('#itemAmount').numberbox('getValue');
                                    $('#itemTotal').numberbox('setValue',newValue*amount);
                                }">
                        </td>
                    </tr>

                    <tr>
                        <td>金额</td>
                        <td><input class="easyui-numberbox" data-options="disabled:true" type="text" name="itemTotal"
                                   id="itemTotal"
                                   style="width:150px"></td>
                    </tr>
                </table>
            </form>
            <div id="t2EditPanel-buttons">
                <a id="btn_t2_edit_save" href="javascript:void(0)" class="easyui-linkbutton">确定</a>
                <a id="btn_t2_edit_close" href="javascript:void(0)" class="easyui-linkbutton">取消</a>
            </div>
        </div>
    </div>
</div>
<div id="editPanel-buttons">
    <a id="btn_edit_save" href="javascript:void(0)" class="easyui-linkbutton">保存</a>
    <a id="btn_edit_close" href="javascript:void(0)" class="easyui-linkbutton">取消</a>
</div>

</body>
</html>