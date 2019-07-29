(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            if(clientWidth>=320){
                docEl.style.fontSize = 100 * (clientWidth / 320) + 'px';
            }else{
                docEl.style.fontSize = '100px';
            }
        };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);

//获取当前网址，如： http://localhost:8088/test/test.jsp
var curPath = window.document.location.href;
//获取主机地址之后的目录，如： test/test.jsp
var pathName = window.document.location.pathname;
var pos = curPath.indexOf(pathName);
//获取主机地址，如： http://localhost:8088
var localhostPath = curPath.substring(0, pos);



/**
 * 文档高度
 */
function getDocumentTop() {
    var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
    if (document.body) {
        bodyScrollTop = document.body.scrollTop;
    }
    if (document.documentElement) {
        documentScrollTop = document.documentElement.scrollTop;
    }
    scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
    console.log("scrollTop:" + scrollTop);
    return scrollTop;
}

/**
 * 可视窗口高度
 */
function getWindowHeight() {
    var windowHeight = 0;
    if (document.compatMode == "CSS1Compat") {
        windowHeight = document.documentElement.clientHeight;
    } else {
        windowHeight = document.body.clientHeight;
    }
    console.log("windowHeight:" + windowHeight);
    return windowHeight;
}


/**
 * 获取滚动条滚动高度
 */
function getScrollHeight() {
    var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
    if (document.body) {
        bodyScrollHeight = document.body.scrollHeight;
    }
    if (document.documentElement) {
        documentScrollHeight = document.documentElement.scrollHeight;
    }
    scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
    console.log("scrollHeight:" + scrollHeight);
    return scrollHeight;
}


/**
 * 预览图片时的关闭按钮点击事件
 * @param ele_father iframe
 * @param ele_child 模态层
 */
$(document).ready(function(){
    $("#img_close").click(function () {
        $("#img_close").css("display", "none");
        $("#tipText").html("");
        $("#img_div").attr("src", " ");
    })
})




/**
 * 获取新增、修改模态层的偏移量和高度
 * @param ele_father iframe
 * @param ele_child 模态层
 */
function getLayerLocation(ele_father , ele_child) {

    var layerLocation = {};
    var add_modify_layer_height = ele_child.innerHeight() + 43;
    var LAY_app_body_height = ele_father.height();
    add_modify_layer_height = add_modify_layer_height > LAY_app_body_height ? LAY_app_body_height : add_modify_layer_height;
    var layer_height = (add_modify_layer_height /  LAY_app_body_height) * 100 + "%";
    var layer_offsetTop = (((LAY_app_body_height - add_modify_layer_height) / 2) / LAY_app_body_height) * 100  + "%";

    layerLocation.layer_height = layer_height;
    layerLocation.layer_offsetTop = layer_offsetTop;

    return layerLocation;


}


/**
 * 获取当前时间，并转换成“YYYY-MM-DD HH:MM:SS”格式
 */
function changeTimeFormat(date) {
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (day >= 0 && day <= 9) {
        day = "0" + day;
    }
    if (hours >= 0 && hours <= 9) {
        hours = "0" + hours;
    }
    if (minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
    }
    if (seconds >= 0 && seconds <= 9) {
        seconds = "0" + seconds;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + day + " " + hours + seperator2 + minutes + seperator2 + seconds;
    console.log(currentdate)
    return currentdate;
}



/**
 * 预览图片
 */
function previewFile(obj) {
    // 标志图片已变化
    flag_img_change = true;

    var preview = $("#img_div");
    file_image = obj.files[0];

    var reader = new FileReader();
    reader.onloadend = function () {
        $("#img_div").attr("src", reader.result);
        $("#img_close").css("display", "block");
    }
    if (file_image) {
        reader.readAsDataURL(file_image);
    } else {
        preview.src = "";
    }
}




/**
 * 清空表单数据 clsModifyForm使用
 * @param formName form的名称,不传默认为 modifyForm
 */
function clearForm(formName) {
    formName = formName == undefined ? "modifyForm" : formName;
    $("#" + formName).find("input[type='text'],input[type='hidden'],textarea").val('');
    $("#" + formName).find("input[type='text'][formatType='formatZeroNull']").val('0');
    $("#" + formName).find("input[type='radio'][value='1']").prop("checked", true);
    $("#" + formName).find("input[type='checkbox']").prop("checked", false);
    $("#" + formName).find("div[type='dictAttachment']").html("");
    $("#" + formName).find("select").each(function (e) {
        $(this).find("option:first").prop("selected", true);
    });
}


/**
 * 隐藏搜索模块
 */
function hide_search_form() {
    $("#search_form").css("top", "-0.75rem");
    setTimeout(function () {
        $("#search_form").addClass("form-hide");
    }, 1000)
}



/**
 * 显示搜索模块
 */
function show_search_form() {
    console.log("近几季");
    $("#search_form").removeClass("form-hide");
    setTimeout(function () {
        $("#search_form").css("top", "0");
    }, 100)
}



/**
 * 行操作：删除
 * @param layer 当前页面layer
 * @param obj 对应行（tr）的DOM结构
 * @param method ajax提交方法（POST/GET）
 * @param url ajax提交url
 * @param ajax_data ajax提交data
 */
function tool_event_delete(layer , obj , method , url , ajax_data) {
    layer.confirm('确定删除吗', function (index) {
        $.ajax({
            type: method,
            dataType: "json",
            url: url,
            data: ajax_data,
            success: function (data) {
                obj.del();
                layer.close(index);
            }
        });

    });
}



/**
 * 行操作：编辑
 * @param layer 当前页面layer
 * @param obj 对应行（tr）的DOM结构
 * @param method ajax提交方法（POST/GET）
 * @param url ajax提交url
 * @param ajax_data ajax提交data
 */
function tool_event_edit(layer , obj , method , url , ajax_data) {
    layer.confirm('确定删除吗', function (index) {
        $.ajax({
            type: method,
            dataType: "json",
            url: url,
            data: ajax_data,
            success: function (data) {
                obj.del();
                layer.close(index);
            }
        });

    });
}



/**
 * 渲染表格
 * @param url ajax提交url
 * @param page_data ajax提交data(关于pageVO的参数)
 */
function render_table(iframe_id , url, page_data) {
    console.log("调用了public中的渲染表格函数");
    layer.closeAll();
    $.ajax({
        type: "GET",
        dataType: "json",
        data: page_data,
        url: url,
        success: function (data) {
            console.log("获取商品信息成功");
            console.log(data);
            $(window.parent.document).contents().find(iframe_id)[0].contentWindow.render_table_success(data, page_data);

        }
    });
}




/**
 * 渲染分页器
 * @param laypage 分页器模块
 * @param laypage_elem 装分页器的容器
 * @param count 总条数
 * @param limit 每页条数
 * @param url ajax提交url
 * @param page_data ajax提交data(关于pageVO的参数)
 */
// function render_laypage(laypage ,laypage_elem , count, limit ,iframe_id , url, page_data) {
//     console.log("调用了public中的渲染分页函数");
//     laypage.render({
//         elem: laypage_elem //分页容器的id
//         , count: count //总页数
//         , limit: limit
//         , skin: '#1E9FFF' //自定义选中色值
//         //,skip: true //开启跳页
//         , jump: function (obj, first) {
//             page_data.pageNum =obj.curr;
//             if (!first) {
//                 render_table(iframe_id ,url, page_data);
//             }
//         }
//     });
// }
function render_laypage(laypage ,laypage_elem , count, iframe_id , url) {
    console.log("调用了public中的渲染分页函数");
    laypage.render({
        elem: laypage_elem //分页容器的id
        , count: count //总页数
        , layout: ['prev', 'page', 'next', 'count', 'limit']
        , limits: [10, 20, 30]
        , skin: '#1E9FFF' //自定义选中色值
        //,skip: true //开启跳页
        , jump: function (obj, first) {
            var page_data = {};
            page_data.pageNum =obj.curr;
            page_data.pageSize =obj.limit;
            if (!first) {
                render_table(iframe_id ,url, page_data);
            }
        }
    });
}



/**
 * 显示layer
 * @param layer 当前页面layer
 * @param title 标题
 * @param offset 偏移量
 * @param area 大小
 * @param content 元素
 */
function layerOpen(layer , title , offset , area , content) {
    console.log("调用了public中的显示layer函数");
    content.removeClass("form-hide");

    layer.open({
        type: 1,
        title: title,
        shadeClose: true, //弹出框之外的地方是否可以点击
        offset: offset,
        area: area,
        content: content,
        cancel: function (index, layero) {
            layer.close(index)
            content.addClass("form-hide");
            return false;
        }
    });
}




/**
 * 上传图片
 * @param new_id 数据库行主键id
 * @param upload_type 上传图片的模块
 * @param file_image 图片
 */
function uploadImage(new_id , upload_type , file_image) {
    console.log("调用了public中的上传图片函数");
    var formDataModify = new FormData();
    formDataModify.append('upload_type', upload_type);
    formDataModify.append('id', new_id);
    formDataModify.append('image', file_image);

    $.ajax({
        url: "/imgUpload/upload",
        type: "POST",
        data: formDataModify,
        contentType: false,
        processData: false,
        async: false,
        mimeType: "multipart/form-data",
        success: function (data) {
            console.log("上传图片成功");
            console.log(data);
        },
        error: function (data) {
            console.log("上传图片失败");
            console.log(data);
        }
    });
}




/**
 * 头工具栏事件-新增
 * @param layer 当前页面layer
 * @param title 标题
 * @param offset 偏移量
 * @param area 大小
 * @param content 元素
 */
function toolbarAddEvent(layer , title , offset , area , content) {
    console.log("调用了public中的头工具栏事件-新增函数");

    $("#submit_btn").attr("lay-filter", "formAdd");
    content.removeClass("form-hide");
    clearForm("add_modify_layer");
    $('#img_div').attr('src', "");
    layerOpen(layer , title , offset , area , content);
}





/**
 * 头工具栏事件-删除
 * @param layer 当前页面layer
 * @param data 选中行数据
 * @param table 当前页面table
 * @param method ajax提交类型
 * @param url ajax提交路径
 */
function toolbarDeleteEvent(layer , data , table , method , url ) {
    console.log("调用了public中的头工具栏事件-删除函数");

    if (data.length === 0) {
        layer.msg('请选择一行', {offset: 'auto'});
    } else {
        var checkStatus = table.checkStatus('table_wrapper'); //idTest 即为基础参数 id 对应的值
        var ids = "";
        for (var i = 0; i < checkStatus.data.length; i++) {
            if (i == checkStatus.data.length - 1) {
                ids += checkStatus.data[i].id;
            } else {
                ids += checkStatus.data[i].id + ",";
            }
        }

        layer.confirm('确定删除选中行吗', function (index) {
            $.ajax({
                type: method,
                dataType: "json",
                url: url,
                data: {
                    "ids":ids
                },
                success: function (data) {
                    console.log("删除成功");
                    parent.location.href = parent.location.href;
                    layer.close(index);
                }
            });

        });
    }
}




