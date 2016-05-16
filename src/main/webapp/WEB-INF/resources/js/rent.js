/**
 * Created by luopotaotao on 2016/4/18.
 */

$(function () {

    $.extend({
        rent: {
            loadInventory: loadInventory,
            loadSkuInfo: loadSkuInfo
        }
    })
    bindHandlers();
    bindT2Handlers();
    var t1Url = 'rent';
    var t2Url = 'rentDtl'

    function bindHandlers() {
        $('a.easyui-linkbutton').unbind();
        // loadDic();
        $('#btn_add').bind('click', toAdd);
        $('#btn_edit').bind('click', toEdit);
        $('#btn_query').bind('click', query);
        $('#btn_remove').bind('click', remove);
        $('#btn_finish').bind('click', finish);
        $('#btn_unfinish').bind('click', unfinish);

        $('#btn_edit_save').bind('click', save);
        $('#btn_edit_close').bind('click', closeEditPanel);
        $('#beginDate').datebox({
            onSelect: function (date) {
                calcRentDays(this);
            }
        });
        $('#endDate').datebox({
            onSelect: function (date) {
                calcRentDays(this);
            }
        });
    }

    function calcRentDays(which) {
        var start = $('#beginDate').datebox('getValue');
        var end = $('#endDate').datebox('getValue');

        if (start && end) {
            start = new Date(start);
            end = new Date(end);
            if (start.getTime() > end.getTime()) {
                $.messager.alert('系统提示!', '结束时间不能小于开始时间!')
                $(which).datebox('setValue', null);
                $('#days').numberbox('setValue', null);
                return;
            }
            var interval = end.getTime() - start.getTime();
            $('#days').numberbox('setValue', (interval / 86400000) + 1);
        }
    }

    function bindT2Handlers() {
        $('#btn_t2_add').bind('click', t2ToAdd);
        $('#btn_t2_edit').bind('click', t2ToEdit);
        $('#btn_t2_query').bind('click', t2Query);

        $('#btn_t2_remove').bind('click', t2Remove);
        $('#btn_t2_edit_save').bind('click', t2Save);
        $('#btn_t2_edit_close').bind('click', t2CloseEditPanel);
    }

    function toAdd() {

        setEditable(true);
        $('#editForm').form('clear');
        $('#editPanel').dialog('open');

        $('#billStat').combobox('setValue', 0);
        $('#stat').combobox('setValue', 1);

        $('#t2_dg').datagrid('loadData', {total: 0, rows: []});
    }

    function formatDate(obj, names) {
        function format(name) {
            return (obj && obj[name]) ? new Date(obj[name]).format("yyyy-MM-dd") : null;
        }

        for (var i in names) {
            obj[names[i]] = format(names[i]);
        }
    }

    function toEdit() {
        var rows = $('#dg').datagrid('getChecked');
        if (rows) {
            if (rows.length > 1) {
                $.messager.alert('系统提示!', '只能对一行进行编辑!')
            } else if (rows.length == 1) {
                var row = rows[0];

                formatDate(row, ['beginDate', 'endDate', 'create_time', 'update_time']);

                $('#editForm').form('load', row);

                setEditable(row.billStat != 1);//已完成状态不可编辑
                t2Query();
                initSkuCombo(row.id);
                $('#editPanel').dialog('open');

                $('#beginDate').datebox('setValue', typeof row.beginDate == 'string' ? row.beginDate : new Date(row.beginDate).format('yyyy-MM-dd'));
                $('#endDate').datebox('setValue', typeof row.endDate == 'string' ? row.endDate : new Date(row.endDate).format('yyyy-MM-dd'));
            }
        } else {
            $.messager.alert('系统提示!', '请选择要编辑的行!')
        }
    }

    function setEditable(flag) {
        if (flag) {
            $('#btn_edit_save').linkbutton('enable');
            $('#t2_menu a').linkbutton('enable');
            bindHandlers();
            bindT2Handlers();
        } else {
            $('#btn_edit_save').linkbutton('disable');
            $('#btn_edit_save').unbind('click');
            $('#t2_menu a').linkbutton('disable');
            $('#t2_menu').unbind('click');
        }
    }

    function remove() {
        var rows = $('#dg').datagrid('getChecked');
        var ids = [];
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                ids.push(v['id']);
            });
        }
        if (ids.length > 0) {
            $.ajax({
                url: t1Url + '/delete',
                data: {ids: ids},
                dataType: 'json',
                type: 'post'
            }).success(function (ret) {
                if (ret && ret.flag > 0) {
                    $.messager.alert('系统提示!', '删除成功!');
                    query();
                } else {
                    $.messager.alert('系统提示!', '删除失败!请重新尝试或联系管理员!');
                }
            }).error(function (err) {
                $.messager.alert('系统提示!', '删除失败!请重新尝试或联系管理员!');
            });
        }
    }

    function query() {
        var url = t1Url + '/list';
        $.ajax({
            url: url,
            dataType: 'json',
            type: 'get'
        }).success(function (ret) {
            if (ret && ret.rows) {
                $('#dg').datagrid('loadData', ret);
            } else {
                $.messager.alert('系统提示!', '获取数据失败!请重新尝试或联系管理员!');
            }
        }).error(function (err) {
            $.messager.alert('系统提示!', '获取数据失败!请重新尝试或联系管理员!');
        });
    }

    function save() {
        if ($('#editForm').form('validate')) {
            var dtls = $('#t2_dg').datagrid('getRows');
            if (!dtls || dtls.length < 1) {
                $.messager.alert('系统提示', '请添加出租明细信息!');
                return;
            }
            var rent = {};
            $('#editForm input').each(function (i, val) {
                var name = $(val).attr('name');
                if (name && $(val).val()) {
                    rent[name] = $(val).val();
                }
            });

            if (rent.id) {
                var deleted = $('#t2_dg').datagrid('getChanges', 'deleted');
                if (deleted && deleted.length > 0) {
                    var deletedIds = [];
                    $.each(deleted, function (i, row) {
                        deletedIds.push(row['id']);
                    });
                    rent.deleted = deletedIds;
                }
                var updated = $('#t2_dg').datagrid('getChanges', 'updated');
                if (updated && updated.length > 0) {
                    rent.updated = JSON.stringify(updated);
                }
            }
            var inserted = $('#t2_dg').datagrid('getChanges', 'inserted');
            if (inserted && inserted.length > 0) {
                rent.inserted = JSON.stringify(inserted);
            }

            $.ajax({
                url: t1Url + '/save',
                type: 'post',
                data: rent
            }).success(function (ret) {
                if (ret && ret.flag) {
                    $.messager.alert('系统提示!', '保存成功!');
                    //如果是修改的话不关闭当前页面,新增的话才关闭
                    if ($('#id').val()) {
                        $.get(t1Url + '/findById', {id: rent.id}, function (data) {
                            formatDate(data, ['beginDate', 'endDate', 'create_time', 'update_time']);
                            $('#editForm').form('load', data);
                        });
                        query();
                        t2Query();
                    } else {
                        $('#editForm').form('clear');
                        $('#editPanel').dialog('close');
                    }

                    query();
                } else {
                    $.messager.alert('系统提示!', '保存失败,请重新尝试或联系管理员!');
                }
            }).error(function (e) {
                $.messager.alert('系统提示!', '保存失败,请重新尝试或联系管理员!');
            }).complete(function (e) {
                $.messager.progress('close');
            });
        } else {
            $.messager.alert('系统提示!', '请修正表单中有问题的字段!!');
        }


    }

    function finish() {
        var rows = $('#dg').datagrid('getChecked');
        var ids = [];
        var warehouseIds=[];
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                if (v['billStat'] ==0) {
                    ids.push(v['id']);
                    warehouseIds.push(v['warehouseId']);
                }
            });
        }
        if (ids && ids.length > 0) {
            $.messager.confirm('确认', '出租单审核是不可逆流程，是否确认审核？', function (r) {
                if (r) {
                    $('body').loading('执行中,请稍后...');
                    $.ajax({
                        url: t1Url + '/finish',
                        type: 'post',
                        dataType: 'json',
                        data: {ids: ids,warehouseIds:warehouseIds}
                    }).success(function (ret) {
                        if (ret && ret.flag) {
                            $.messager.alert('系统提示', '审核完成!');
                            query();
                        } else {
                            $.messager.alert('系统提示', '操作失败,请重新尝试或联系管理员!');
                        }
                    }).error(function (e) {
                        $.messager.alert('系统提示', '操作失败,请重新尝试或联系管理员!');
                    }).complete(function () {
                        $('body').loaded();
                    });
                }
            });

        } else {
            $.messager.alert('系统提示', '请选择未完成的条目进行审核!');
        }
    }

    function unfinish() {
        var rows = $('#dg').datagrid('getChecked');
        var ids = [];
        var warehouseIds=[];
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                if (v['billStat'] ==1) {
                    ids.push(v['id']);
                    warehouseIds.push(v['warehouseId']);
                }
            });
        }
        if (ids && ids.length > 0) {
            $.messager.confirm('确认', '出租单取消审核是不可逆流程，是否取消审核？', function (r) {
                if (r) {
                    $('body').loading('执行中,请稍后...');
                    $.ajax({
                        url: t1Url + '/unfinish',
                        type: 'post',
                        dataType: 'json',
                        data: {ids: ids,warehouseIds:warehouseIds}
                    }).success(function (ret) {
                        if (ret && ret.flag) {
                            $.messager.alert('系统提示', '取消审核完成!');
                            query();
                        } else {
                            $.messager.alert('系统提示', '操作失败,请重新尝试或联系管理员!');
                        }
                    }).error(function (e) {
                        $.messager.alert('系统提示', '操作失败,请重新尝试或联系管理员!');
                    }).complete(function () {
                        $('body').loaded();
                    });
                }
            });

        } else {
            $.messager.alert('系统提示', '请选择未完成的条目进行审核!');
        }
    }

    function t2ToAdd() {
        initSkuCombo();
        var skuInfo = $('#skuId').combobox('getData');
        var rows = $('#t2_dg').datagrid('getRows');
        if ((rows && rows.length) && (!skuInfo || skuInfo.length < 1)) {
            $.messager.alert('系统提示', '该仓库所有sku都已选择,无法继续添加,如需修改,请编辑明细信息!');
            return;
        }
        var warehouseId = $('#warehouseId').combobox('getValue');
        if (!warehouseId) {
            $.messager.alert('系统提示', '填写明细单前请选择出库仓库');
            return;
        }
        $.t2CurrentItem = {rentId: $('#id').val()};
        $('#t2EditForm').form('clear');
        $('#t2EditForm').form('load', $.t2CurrentItem);
        $('#t2EditPanel').dialog('open');

    }

    function t2ToEdit() {
        var rows = $('#t2_dg').datagrid('getChecked');
        if (rows) {
            if (rows.length > 1) {
                $.messager.alert('系统提示!', '只能对一行进行编辑!')
            } else if (rows.length == 1) {
                $('#t2EditForm').form('clear');
                $('#t2EditForm').form('load', rows[0]);
                $('#t2EditPanel').dialog('open');
            }
        } else {
            $.messager.alert('系统提示!', '请选择要编辑的行!')
        }
    }

    function initSkuCombo() {
        var rows = $('#t2_dg').datagrid('getRows');
        if (!rows || rows.length < 1) {
            $('#editPanel').loading('加载SKU列表信息,请稍等...');
            var warehouseId = $('#warehouseId').combobox('getValue');
            $.ajax({
                url: 'sku/getAvailableSkuInfo',
                type: 'get',
                data: {warehouseId: warehouseId, billId: $('#id').val()},
                dataType: 'json',
                async: true
            }).success(function (ret) {
                if($.isArray(ret)){
                    if(ret.length<1){
                        $.messager.alert('系统提示','该仓库内无可选SKU信息,请选择其他仓库!');
                        $('#t2EditPanel').dialog('close');
                        return;
                    }
                    $('#skuId').combobox({
                            valueField: 'id',
                            textField: 'text',
                            data: ret,
                            required: true,
                            missingMessage: '必填字段',
                            formatter: function (row) {
                                var opts = $(this).combobox('options');
                                return row[opts.valueField] + ' ' + row[opts.textField];
                            },
                            onHidePanel: function () {
                                var val = $('input[name=skuId]').val();
                                if (!val) {
                                    return;
                                }
                                var opt = $(this).combobox('options');
                                var data = $(this).combobox('getData');
                                var contains = false;
                                for (var i = 0; i < data.length; i++) {
                                    if (data[i][opt.valueField] == val) {
                                        $(this).combobox('setValue', val + ' ' + data[i][opt.textField]);
                                        $('input[name=skuId]').val(val);
                                        $.rent.loadSkuInfo(val);
                                        return;
                                    }
                                }
                                if (!contains) {
                                    $.messager.alert('系统提示', '只能从下拉框中选择值!');
                                    $(this).combobox('reset');
                                }
                            }
                        }
                    );
                }

            }).error(function () {
                $.messager.alert('系统提示', '加载SKU信息失败!');
            }).complete(function () {
                $('#editPanel').loaded();
            });
        }


    }

    function t2Save() {
        if ($('#t2EditForm').form('validate')) {

            var amount = $('#amount').val();
            if (!$.isNumeric(amount)) {
                $.messager.alert('系统提示', '库存信息不明,请刷新库存信息!');
                return;
            } else {
                if ($('#itemAmount').numberbox('getValue') > amount) {
                    $.messager.alert('系统提示', '数量不可超过当前库存!');
                    return;
                }
            }
            var row = $('#t2_dg').datagrid('getChecked')[0];
            var index = row ? $('#t2_dg').datagrid('getRowIndex', row) : null;
            var item = {};

            $('#t2EditForm input').each(function (i, val) {
                var key = $(val).attr('name');
                if (key) {
                    item[key] = $(val).val();
                }
            });
            //保存时将一选sku从选项中删除
            var skuInfo = $('#skuId').combobox('getData');
            $.each(skuInfo, function (i, rec) {
                if (item.skuId == rec.id) {
                    item.skuName = rec.text;
                    skuInfo.splice(i, 1);
                    $('#skuId').combobox('loadData', skuInfo);
                    return false;
                }
            });
            if (!item.id) {
                $('#t2_dg').datagrid('appendRow', item);
            } else {
                $('#t2_dg').datagrid('updateRow', {index: index, row: item});
            }


            calcTotal();
            delete $.t2CurrentItem;
            $('#t2EditForm').form('clear');
            $('#t2EditPanel').dialog('close');
        } else {
            $.messager.alert('系统提示!', '请修正表单中有问题的字段!!');
        }
    }

    function calcTotal() {
        var rows = $('#t2_dg').datagrid('getRows');
        var rentMoney = null;
        var repoMoney = null;
        if (rows && rows.length) {
            rentMoney = 0;
            repoMoney = 0;
            $.each(rows, function (i, row) {
                var itemRent = parseFloat(row['itemRent']);
                var itemRepo = parseFloat(row['itemRepo']);
                rentMoney += (isNaN(itemRent) ? 0 : itemRent);
                repoMoney += (isNaN(itemRepo) ? 0 : itemRepo);
            });

        }
        $('#rentMoney').numberbox('setValue', rentMoney);
        $('#repoMoney').numberbox('setValue', repoMoney);
    }

    function t2Remove() {
        var grid = $('#t2_dg');
        var rows = grid.datagrid('getChecked');

        var skuInfo = $('#skuId').combobox('getData');
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                var index = grid.datagrid('getRowIndex', v);
                skuInfo.push({id: v.skuId, text: v.skuName});
                grid.datagrid('deleteRow', index);
            });
        }
        skuInfo.sort(function (a, b) {
            return a.id - b.id;
        });
        $('#skuId').combobox('loadData', skuInfo);
        calcTotal();
    }

    function t2Query() {
        var url = t2Url + '/findAllById?rentId=' + $('#id').val();
        $('#t2_dg').datagrid('loadData', {total: 0, rows: []});
        $.ajax({
            url: url,
            dataType: 'json',
            type: 'get'
        }).success(function (ret) {
            if (ret && ret.rows) {
                $('#t2_dg').datagrid('loadData', ret);
            } else {
                $.messager.alert('系统提示!', '获取数据失败!请重新尝试或联系管理员!');
            }
        }).error(function (err) {
            $.messager.alert('系统提示!', '获取数据失败!请重新尝试或联系管理员!');
        });
    }

    function closeEditPanel() {
        $('#editPanel').dialog('close');
    }

    function t2CloseEditPanel() {
        $('#t2EditPanel').dialog('close');
    }

    function loadSkuInfo(id) {
        $('body').loading('查询SKU详细信息,请稍后...');

        $.ajax({
            url: 'sku/findById',
            type: 'get',
            data: {id: id},
            dataType: 'json'
        }).success(function (ret) {
            if (ret && ret.id) {
                ret.skuId = ret.id;
                delete ret.id;
                $('#t2EditForm').form('load', ret);
                if (ret.suffix) {
                    $('#skuImage').attr('src', 'resources/images/upload' + ret.id + ret.suffix);
                }
            } else {
                $.messager.alert('系统提示', '加载SKU信息失败!');
            }
        }).error(function () {
            $.messager.alert('系统提示', '加载SKU信息失败!');
        }).complete(function () {
            $('body').loaded();
        });
    }

    function loadInventory() {
        var warehouseId = $('#warehouseId').combobox('getValue');
        var skuId = $('#skuId').combobox('getValue');
        $('#t2EditForm').loading('查询库存信息,请稍后...');
        $.ajax({
            url: 'inventory/getInventory',
            data: {warehouseId: warehouseId, skuId: skuId},
            type: 'get',
            dataType: 'json'
        }).success(function (ret) {
            if (ret && $.isPlainObject(ret)) {
                $('#amount').numberbox('setValue', ret.amount);
            }
        }).error(function () {
            $.messager.alert('系统提示', '加载库存信息失败,请重新尝试或联系管理员!');
        }).complete(function () {
            $('#t2EditForm').loaded();
        });
    }
})
;