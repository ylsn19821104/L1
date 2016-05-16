<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>归还单</title>
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
    <script type="text/javascript" src="resources/js/reversion.js"></script>
    <script type="text/javascript" src="resources/js/databox-formatter.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid" title="归还单" fit="true"
       data-options="pagination:'true',rownumbers:true,singleSelect:false,selectOnCheck:true,url:'reversion/list',method:'get',toolbar:'#menu'">
    <thead data-options="frozen:true">
    <tr>
        <th field="id" width="50" align="center" hidden="true">ID</th>
        <th field="cb" checkbox="true" align="center"></th>
        <th data-options="field:'billNo',width:105">归还单号</th>
        <th data-options="field:'rentBillStat',hidden:true">单据状态Id</th>
        <th data-options="field:'billStat',hidden:true">单据状态Id</th>
        <th data-options="field:'billStatName'">单据状态</th>
        <th data-options="field:'rentBillId',hidden:true">出租单号Id</th>
        <th data-options="field:'rentBillNo',width:80">出租单号</th>
        <th data-options="field:'customerName',width:100">客户</th>
        <th data-options="field:'customerPhone',width:100">联系电话</th>
        <th data-options="field:'customerCard',width:100">证件号</th>
        <th data-options="field:'customerAddr',width:100">地址</th>
        <th data-options="field:'warehouseId',width:80,hidden:true">入库仓库Id</th>
        <th data-options="field:'warehouseName',width:80">入库仓库</th>
        <th data-options="field:'supplierId',width:100,hidden:true">物流Id</th>
        <th data-options="field:'supplierName',width:100">物流公司</th>
        <th data-options="field:'expressBillNo',width:100">归还快递单号</th>
        <th data-options="field:'rentMoney',width:70">租金总金额</th>
        <th data-options="field:'repoMoney',width:60">押金总额</th>
        <th data-options="field:'compensateMoney',width:60">赔偿总金额</th>
        <th data-options="field:'beginDate',width:80,hidden:true">
            使用开始时间
        </th>
        <th data-options="field:'beginDateStr',width:80,formatter:function(value,row){ if(row.beginDate) {return new Date(row.beginDate).format('yyyy-MM-dd')};}">
            使用开始时间
        </th>
        <th data-options="field:'endDate',width:80,hidden:true">
            使用结束时间
        </th>
        <th data-options="field:'endDateStr',width:80,formatter:function(value,row){ if(row.endDate) {return new Date(row.endDate).format('yyyy-MM-dd')};}">
            使用结束时间
        </th>
        <th data-options="field:'reversionDate',width:80,hidden:true">
            归还时间
        </th>
        <th data-options="field:'reversionDateStr',width:80,formatter:function(value,row){ if(row.reversionDate) {return new Date(row.reversionDate).format('yyyy-MM-dd')};}">
            归还时间
        </th>
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
                <td class="label">归还单号</td>
                <td><input class="easyui-textbox input" data-options="disabled:true" name="billNo"
                           id="billNo" readonly>
                </td>
                <td class="label">单据状态</td>
                <td>
                    <select class="easyui-combobox input" name="billStat" id="billStat" style="width:120px;"
                            data-options="
                    disabled:true,
                    valueField: 'valueField',
                    textField: 'textField',
                    data:[{valueField:'0',textField:'未审核'},{valueField:'1',textField:'已审核'}]">
                    </select>
                </td>
            </tr>
            <tr>
                <td class="label">出租单号</td>
                <td>
                    <input type="hidden" name="rentBillId">
                    <select class="easyui-combobox input" name="rentBillNo" id="rentBillNo" style="width:120px;">
                    </select>
                </td>
                <td class="label">出租单状态</td>
                <td>
                    <input type="hidden" name="rentBillStat">
                    <input class="easyui-textbox input" name="rentBillStatName" id="rentBillStatName" style="width:120px;"
                            data-options="disabled:true">
                    </input>
                </td>
            </tr>
            <tr>
                <td class="label">客户</td>
                <td><input class="easyui-textbox input" data-options="disabled:true" type="text" name="customerName">
                </td>
                <td class="label">证件号</td>
                <td><input class="easyui-textbox input" data-options="disabled:true" type="text" name="customerCard">
            </tr>
            <tr>
                <td class="label">联系电话</td>
                <td><input class="easyui-textbox input" data-options="disabled:true" type="text" name="customerPhone">
                </td>
                </td>
                <td class="label">地址</td>
                <td><input class="easyui-textbox input" data-options="disabled:true" type="text" name="customerAddr">
                </td>
            </tr>
            <tr>

                <td class="label">入库仓库</td>
                <td>
                    <select class="easyui-combobox input" name="warehouseId" id="warehouseId" data-options="
                    required:true,
                    editable:false,
                    valueField: 'id',
                    textField: 'text',
                    method:'get',
                    url: 'warehouse/comboList'">
                    </select>
                </td>
                <td class="label">归还时间</td>
                <td><input id="reversionDate" class="easyui-datebox input" name="reversionDate"
                           data-options="editable:false,required:true">
                </td>
            </tr>
            <tr>
                <td class="label">物流供应商</td>
                <td>
                    <input type="hidden" name="supplierId">
                    <input class="easyui-textbox input" data-options="disabled:true" name="supplierName">
                </td>
                <td class="label">归还快递单号</td>
                <td><input class="easyui-textbox input" data-options="disabled:true" type="text" name="expressBillNo">
                </td>

            </tr>
            <tr>
                <td class="label">使用开始时间</td>
                <td><input id="beginDate" class="easyui-textbox input" name="beginDate"
                           data-options="disabled:true">
                </td>


                <td class="label">使用结束时间</td>
                <td><input id="endDate" class="easyui-textbox input" name="endDate"
                           data-options="disabled:true">
                </td>
            </tr>

            <tr>
                <td class="label">租金总金额</td>
                <td><input class="easyui-numberbox input" data-options="disabled:true" type="text" name="rentMoney"
                           id="rentMoney">
                </td>
                <td class="label">押金总金额</td>
                <td><input class="easyui-numberbox input" data-options="disabled:true" type="text" name="repoMoney"
                           id="repoMoney">
                </td>
            </tr>
            <tr>
                <td class="label">赔偿总金额</td>
                <td><input class="easyui-numberbox input" data-options="disabled:true" type="text" name="compensateMoney"
                           id="compensateMoney">
                </td>
                <td class="label">操作员</td>
                <td><input class="easyui-textbox input" readonly type="text" name="createdBy" id="createdBy">
            </tr>
        </table>
    </form>
    <div id="t2_panel">
        <table id="t2_dg" class="easyui-datagrid" title="归还明细"
               data-options="pagination:false,rownumbers:true,singleSelect:true,selectOnCheck:false,method:'get',toolbar:'#t2_menu',
               rowStyler: function(index,row){
                    if (!row.reversionAmount){
                        return 'background-color:#FFDCDC;';
                    }
                }
        ">
            <thead>
            <tr>
                <th field="cb" checkbox="true" align="center"></th>
                <th data-options="field:'id',width:80,hidden:true">id</th>
                <th data-options="field:'reversionId',width:80,hidden:true">dtlId</th>
                <th data-options="field:'skuId',width:80">SKU</th>
                <th data-options="field:'itemId',hidden:true">商品Id</th>
                <th data-options="field:'itemName',width:80">商品名称</th>
                <th data-options="field:'colorId',hidden:true">颜色Id</th>
                <th data-options="field:'colorName',width:80">颜色</th>
                <th data-options="field:'sizeId',hidden:true">尺码Id</th>
                <th data-options="field:'sizeName',width:80">尺码</th>
                <th data-options="field:'reversionStat',hidden:true">归还状态码</th>
                <th data-options="field:'reversionStatName',width:80,align:'right'">归还状态</th>
                <th data-options="field:'itemPrice',width:80,align:'right'">单价</th>
                <th data-options="field:'itemAmount',width:60,align:'right'">出租数量</th>
                <th data-options="field:'reversionAmount',width:60,align:'right'">归还数量</th>
                <th data-options="field:'itemRent',width:60">出租金额</th>
                <th data-options="field:'itemRepo',width:60,align:'center'">押金</th>
                <th data-options="field:'itemCompensate',width:60,align:'center'">赔偿金</th>

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
                <input type="hidden" name="rentDtlId">
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
                        <td>归还状态</td>
                        <td>
                            <select class="easyui-combobox" name="reversionStat"
                                    id="reversionStat"
                                    data-options="required:true,
                                            editable:false,
                                            valueField: 'valueField',
                                            textField: 'textField',
                                            method:'get',
                                            url: 'dic/reversionStatus'"
                                    style="width:150px">
                            </select>
                        </td>
                        <td>单价</td>
                        <td>
                            <input class="easyui-textbox" type="text" name="itemPrice" id="itemPrice"
                                   style="width:150px"
                                   data-options="disabled:true">
                        </td>
                    </tr>

                    <tr>
                        <td>出租数量</td>
                        <td>
                            <input class="easyui-numberbox" type="text" name="itemAmount" id="itemAmount"
                                   style="width:150px"
                                   data-options="disabled:true">
                        </td>
                        <td>归还数量</td>
                        <td>
                            <input class="easyui-numberbox" data-options="required:true" name="reversionAmount" id="reversionAmount"
                                   style="width:150px">
                        </td>
                    </tr>
                    <tr>
                        <td>金额</td>
                        <td><input class="easyui-numberbox" data-options="disabled:true" type="text" name="itemRent"
                                   id="itemRent"
                                   style="width:150px"></td>

                        <td>押金</td>
                        <td><input class="easyui-numberbox" data-options="disabled:true" type="text" name="itemRepo" style="width:150px">
                        </td>
                    </tr>
                    <tr>
                        <td>赔偿金</td>
                        <td><input class="easyui-numberbox" type="text" name="itemCompensate"
                                   id="itemCompensate"
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