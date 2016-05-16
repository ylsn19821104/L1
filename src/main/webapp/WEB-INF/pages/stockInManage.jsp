<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>移仓入库</title>
    <jsp:include page="common.jsp"/>

    <script type="text/javascript">
        $(function () {
            $.statusDic = {
                <c:forEach items="${statusDic}" var="item" varStatus="status">
                '${item.valueField}': '${item.textField}'<c:if test="${!status.last}">, </c:if>
                </c:forEach>
            };
        });
    </script>
    <script type="text/javascript" src="resources/js/stockIn.js"></script>
    <script type="text/javascript" src="resources/js/databox-formatter.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid" title="移仓入库" fit="true"
       data-options="pagination:'true',rownumbers:true,singleSelect:false,selectOnCheck:true,url:'stockIn/list',method:'get',toolbar:'#menu'">
    <thead data-options="frozen:true">
        <tr>
            <th field="id" width="50" align="center" hidden="true">ID</th>
            <th field="cb" checkbox="true" align="center"></th>
            <th data-options="field:'billNo',width:105">移仓入库单号</th>

        </tr>
    </thead>
    <thead>
        <tr>
            <th data-options="field:'billStat',hidden:true">单据状态Id</th>
            <th data-options="field:'billStatName',width:80">单据状态</th>
            <th data-options="field:'stockOutBillId',hidden:true">移仓出库单号Id</th>
            <th data-options="field:'stockOutBillNo',width:105">移仓出库单号</th>
            <th data-options="field:'stockOutBillStat',width:80,hidden:true">移仓出库单状态</th>
            <th data-options="field:'stockOutWarehouseId',width:80,hidden:true">出库仓库Id</th>
            <th data-options="field:'stockOutWarehouseName',width:80">出库仓库</th>
            <th data-options="field:'stockInWarehouseId',width:80,hidden:true">入库仓库Id</th>
            <th data-options="field:'stockInWarehouseName',width:80">入库仓库</th>
            <th data-options="field:'billDate',hidden:true">制单日期</th>
            <th data-options="field:'billDateStr',width:80,formatter:function(value,row){ if(row.billDate) {return new Date(row.billDate).format('yyyy-MM-dd')};}">制单日期</th>
            <th data-options="field:'totalStockIn',width:80">移仓入库总数</th>
        </tr>
    </thead>
</table>
<div id="menu" style="padding:2px 5px;">
    <a id="btn_query" href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
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
                <td class="label">移仓入库单号</td>
                <td><input class="easyui-textbox input" data-options="disabled:true" name="billNo"
                           id="billNo" readonly>
                </td>
                <td class="label">单据状态</td>
                <td>
                    <input type="hidden" name="billStat">
                    <input class="easyui-textbox input" name="billStatName" data-options="disabled:true">
                </td>
            </tr>
            <tr>
                <td class="label">移仓出库单号</td>
                <td>
                    <input type="hidden" name="stockOutBillId">
                    <select class="easyui-combobox input" name="stockOutBillNo" id="stockOutBillNo" style="width:120px;">
                    </select>
                </td>
            </tr>
            <tr>

                <td class="label">出库仓库</td>
                <td>
                    <input type="hidden" name="stockOutWarehouseId">
                    <input class="easyui-textbox input" name="stockOutWarehouseName" data-options="disabled:true">
                </td>
                <td class="label">入库仓库</td>
                <td>
                    <input type="hidden" name="stockInWarehouseId">
                    <input class="easyui-textbox input" name="stockInWarehouseName" data-options="disabled:true">
                </td>
            </tr>
            <tr>
                <td class="label">移仓入库总数</td>
                <td><input class="easyui-numberbox input" data-options="disabled:true" type="text" name="totalStockIn"
                           id="totalStockIn">
                </td>
                <td class="label">制单日期</td>
                <td><input id="billDate" class="easyui-datebox input" name="billDate"
                           data-options="editable:false,required:true">
                </td>
            </tr>
            <tr>
                <td class="label">操作员</td>
                <td><input class="easyui-textbox input" readonly type="text" name="createdBy" id="createdBy">
            </tr>
        </table>
    </form>
    <div id="t2_panel">
        <table id="t2_dg" class="easyui-datagrid" title="移仓入库明细"
               data-options="pagination:false,rownumbers:true,singleSelect:true,selectOnCheck:false,method:'get',toolbar:'#t2_menu',
               rowStyler: function(index,row){
                    if (!row.stockInAmount){
                        return 'background-color:#FFDCDC;';
                    }
                }
        ">
            <thead>
            <tr>
                <th field="cb" checkbox="true" align="center"></th>
                <th data-options="field:'id',width:80,hidden:true">id</th>
                <th data-options="field:'stockInId',width:80,hidden:true">dtlId</th>
                <th data-options="field:'skuId',width:80">SKU</th>
                <th data-options="field:'itemId',hidden:true">商品Id</th>
                <th data-options="field:'itemName',width:80">商品名称</th>
                <th data-options="field:'colorId',hidden:true">颜色Id</th>
                <th data-options="field:'colorName',width:80">颜色</th>
                <th data-options="field:'sizeId',hidden:true">尺码Id</th>
                <th data-options="field:'sizeName',width:80">尺码</th>
                <th data-options="field:'stockOutAmount',width:60,align:'right'">发货数量</th>
                <th data-options="field:'stockInAmount',width:60,align:'right'">收货数量</th>
            </tr>
            </thead>
        </table>
        <div id="t2_menu" style="padding:2px 5px;">
            <a id="btn_t2_query" href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
            <a id="btn_t2_edit" href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改明细</a>
        </div>
        <div id="t2EditPanel" class="easyui-dialog" title="编辑" style="padding:10px;width:700px;"
             data-options="modal:true,closed:true,buttons: '#t2EditPanel-buttons'">
            <form id="t2EditForm">
                <input type="hidden" name="stockOutDtlId">
                <table>
                    <tr>
                        <td>SKU</td>
                        <td>
                            <input class="easyui-textbox" data-options="disabled:true" name="skuId" id="skuId"
                                   style="width:150px;">
                        </td>
                        <td>商品名称</td>
                        <td>
                            <input type="hidden" name="itemId">
                            <input class="easyui-textbox" data-options="disabled:true" name="itemName" id="itemName"
                                   style="width:150px">
                        </td>
                        <td rowspan="5"><img id="skuImage" src="resources/images/upload/example.jpg"
                                             style="width: 200px;height: 200px"></td>
                    </tr>
                    <tr>
                        <td>颜色</td>
                        <td>
                            <input type="hidden" name="colorId">

                            <input class="easyui-textbox" data-options="disabled:true" name="itemName" id="colorName"
                                   style="width:150px">
                        </td>
                        <td>尺码</td>
                        <td>
                            <input type="hidden" name="sizeId">

                            <input class="easyui-textbox" data-options="disabled:true" name="itemName" id="sizeName"
                                   style="width:150px">
                        </td>
                    </tr>

                    <tr>
                        <td>发货数量</td>
                        <td>
                            <input class="easyui-numberbox" type="text" name="stockOutAmount" id="stockOutAmount"
                                   style="width:150px"
                                   data-options="disabled:true">
                        </td>
                        <td>收货数量</td>
                        <td>
                            <input class="easyui-numberbox" data-options="required:true" name="stockInAmount" id="stockInAmount"
                                   style="width:150px">
                        </td>
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