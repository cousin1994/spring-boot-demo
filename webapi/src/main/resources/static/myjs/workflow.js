function graphTrace(options) {

    var ctx = "http://" + window.location.host;

    var index;

    var _defaults = {
        srcEle: this,
        pid: $(this).attr('pid'),
        pdid: $(this).attr('pdid')
    };
    var opts = $.extend(true, _defaults, options);

    // 处理使用js跟踪当前节点坐标错乱问题
    $('#changeImg').on('click', null, function () {
        $('#workflowTraceDialog').dialog('close');
        if ($('#imgDialog').length > 0) {
            $('#imgDialog').remove();
        }
        $('<div/>', {
            'id': 'imgDialog',
            title: '此对话框显示的图片是由引擎自动生成的，并用红色标记当前的节点<button id="diagram-viewer">Diagram-Viewer</button>',
            html: "<img src='" + ctx + '/workflow/process/trace/auto/' + opts.pid + "' />"
        }).appendTo('body').dialog({
            modal: true,
            resizable: false,
            dragable: false,
            width: document.documentElement.clientWidth * 0.95,
            height: document.documentElement.clientHeight * 0.95
        });
    });

    /*
     用官方开发的Diagram-Viewer跟踪
     */
    $('#diagram-viewer').on('click', null, function () {
        $('#workflowTraceDialog').dialog('close');

        if ($('#imgDialog').length > 0) {
            $('#imgDialog').remove();
        }

        var url = ctx + '/diagram-viewer/index.html?processDefinitionId=' + opts.pdid + '&processInstanceId=' + opts.pid;

        $('<div/>', {
            'id': 'imgDialog',
            title: '此对话框显示的图片是由引擎自动生成的，并用红色标记当前的节点',
            html: '<iframe src="' + url + '" width="100%" height="' + document.documentElement.clientHeight * 0.90 + '" />'
        }).appendTo('body').dialog({
            modal: true,
            resizable: false,
            dragable: false,
            width: document.documentElement.clientWidth * 0.95,
            height: document.documentElement.clientHeight * 0.95
        });
    });

    // 获取图片资源
    var imageUrl = ctx + "/workflow/resource/process-instance?pid=" + opts.pid + "&type=image";
    $.getJSON(ctx + '/workflow/process/trace?pid=' + opts.pid, function (infos) {

        var positionHtml = "";

        // 生成图片
        var varsArray = new Array();
        $.each(infos, function (i, v) {
            var $positionDiv = $('<div/>', {
                'class': 'activity-attr'
            }).css({
                position: 'absolute',
                left: (v.x - 1),
                top: (v.y - 1),
                width: (v.width - 2),
                height: (v.height - 2),
                backgroundColor: 'black',
                opacity: 0,
                zIndex: $.fn.qtip.zindex - 1
            });

            // 节点边框
            var $border = $('<div/>', {
                'class': 'activity-attr-border'
            }).css({
                position: 'absolute',
                left: (v.x - 1),
                top: (v.y - 1),
                width: (v.width - 4),
                height: (v.height - 3),
                zIndex: $.fn.qtip.zindex - 2
            });

            // if (v.currentActiviti) {
            //     $border.addClass('ui-corner-all-12').css({
            //         border: '3px solid red'
            //     });
            // }
            positionHtml += $positionDiv[0].outerHTML + $border[0].outerHTML;
            varsArray[varsArray.length] = v.vars;
        });

        if ($('#workflowTraceDialog').length == 0) {
            $('<div/>', {
                id: 'workflowTraceDialog',
                title: '查看流程（按ESC键可以关闭）<button id="changeImg">如果坐标错乱请点击这里</button><button id="diagram-viewer">Diagram-Viewer</button>',
                html: "<div><img src='" + imageUrl + "' style='position:absolute; left:0px; top:0px;' />" +
                "<div id='processImageBorder'>" +
                positionHtml +
                "</div>" +
                "</div>"
            }).appendTo('body');
        } else {
            $('#workflowTraceDialog img').attr('src', imageUrl);
            $('#workflowTraceDialog #processImageBorder').html(positionHtml);
        }

        // 设置每个节点的data
        $('#workflowTraceDialog .activity-attr').each(function (i, v) {
            $(this).data('vars', varsArray[i]);
        });

        // 打开对话框
        // var url2 = ctx + '/diagram-viewer/index.html?processDefinitionId=' + opts.pdid + '&processInstanceId=' + opts.pid;
        //
        // //用官方的iframe展开
        // index = layer.open({
        //     type: 2,
        //     shade: false,
        //     area: ['80%', '80%'],
        //     maxmin: true,
        //     content: url2,
        //     zIndex: layer.zIndex, //重点1
        //     success: function(layero){
        //         layer.setTop(layero); //重点2
        //     }
        // });

        index = layer.open({
            type: 1
            , title: $("#workflowTraceDialog").attr('title') //不显示标题栏
            , closeBtn: false
            , area: ['80%', '80%']
            , shade: 0.8
            , id: 'LAY_layuipro1' //设定一个id，防止重复弹出
            , resize: false
            , btn: ['创建', '取消']
            , btnAlign: 'c'
            , moveType: 1 //拖拽模式，0或者1
            , content: $("#workflowTraceDialog").html()
            , btn1: function () {
                $('#workflowTraceDialog').remove();
                layer.close(index);
            }
        });

        $('#workflowTraceDialog').remove();


        // $('#workflowTraceDialog').dialog({
        //     modal: true,
        //     resizable: false,
        //     dragable: false,
        //     open: function() {
        //         $('#workflowTraceDialog').dialog('option', 'title', '查看流程（按ESC键可以关闭）<button id="changeImg">如果坐标错乱请点击这里</button><button id="diagram-viewer">Diagram-Viewer</button>');
        //         $('#workflowTraceDialog').css('padding', '0.2em');
        //         $('#workflowTraceDialog .ui-accordion-content').css('padding', '0.2em').height($('#workflowTraceDialog').height() - 75);
        //
        //         // 此处用于显示每个节点的信息，如果不需要可以删除
        //         $('.activity-attr').qtip({
        //             content: function() {
        //                 var vars = $(this).data('vars');
        //                 var tipContent = "<table class='need-border'>";
        //                 $.each(vars, function(varKey, varValue) {
        //                     if (varValue) {
        //                         tipContent += "<tr><td class='label'>" + varKey + "</td><td>" + varValue + "<td/></tr>";
        //                     }
        //                 });
        //                 tipContent += "</table>";
        //                 return tipContent;
        //             },
        //             position: {
        //                 at: 'bottom left',
        //                 adjust: {
        //                     x: 3
        //                 }
        //             }
        //         });
        //         // end qtip
        //     },
        //     close: function() {
        //         $('#workflowTraceDialog').remove();
        //     },
        //     width: document.documentElement.clientWidth * 0.95,
        //     height: document.documentElement.clientHeight * 0.95
        // });

    });
}
