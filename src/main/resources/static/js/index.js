var responseHandler = function (e) {
	    if (e.data && e.data.length > 0) {
	        return { "rows": e.data.list, "total": e.data.total };
	    }
	    else {
	        return { "rows": [], "total": 0 };
	    }
	    
	}
	
	window.operateEvents = {
		"click #TableDelete":function(e, value, row, index) {
//			$(this).parent().parent().remove();
			var data = {
				id : row.id
			}
			$.ajax({
				type:'post',
				url : '/content/delete',
				contentType: 'application/json;charset=UTF-8',
				traditional: true,
				data:JSON.stringify(data),
				dataType:'json',
				success:function(data) {
					if (data.code == 200) {
						$('#roleTable').bootstrapTable('hideRow', {index:index});
					} else {
						alert(data.msg);
					}
				}
			})
		}
	}

	$('#roleTable').bootstrapTable({
	    method: 'get', // 服务器数据的请求方式 get or post
	    url: "/content/list", // 服务器数据的加载地址
	    striped: true, //是否显示行间隔色
	    cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	    pagination: true, //是否显示分页（*）
	    sortable: true, //是否启用排序
	    sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
	    pageNumber: 1, //初始化加载第一页，默认第一页
	    pageSize: 25, //每页的记录行数（*）
	    pageList: [5, 25, 50, 100], //可供选择的每页的行数（*）
	    strictSearch: true,
	    minimumCountColumns: 2, //最少允许的列数
	    clickToSelect: true, //是否启用点击选中行
	    uniqueId: "ID", //每一行的唯一标识，一般为主键列
	    cardView: false,
	    detailView: false, //是否显示详细视图
	    smartDisplay: false,
	    responseHandler: function(res) {
	    	return {
	    		"rows" : res.data.list,
	    		"total" : res.data.total
	    	}
	    },
	    queryParams: function(params) {
	        return {
	            pageSize: params.limit,
	            pageNum: params.offset / params.limit + 1
	        };
	    },
	    columns: [{
	        field: 'name',
	        title: '标题'
	    }, {
	        field: 'url',
	        title: '链接地址',
	        formatter: aFormatter
	    }, {
	        field: 'source',
	        title: '来源'
	    }, {
	        field: 'createDate',
	        title: '收藏时间'
	    }, {
	        field: 'sort',
	        title: '分类'
	    }, {
	    	fieId: 'Button',
	    	title: '操作',
	    	events: operateEvents,
	    	formatter:AddFuntionAlty
	    }]
	});
	
	function aFormatter(value, row, index) {
			return "<a href='" + value + "' title='单击打开链接' target='_blank'>" + value + "</a>";
	}
	
	function AddFuntionAlty(value, row, index) {
		return[
			'<button id="TableDelete" type="button" class="btn btn-default">删除</button>'
		].join('')
	}
	
	function submit() {
		var name = $(":input[name = 'name']").val();
		var url = $(":input[name = 'url']").val();
		var source = $(":input[name = 'source']").val();
		var sort = parseInt($("select").val());
		
		var data = {
				name : name,
				url  : url,
				source:source,
				sort :sort
		};
		
		$.ajax({
			type:'post',
			url : '/content/insert',
			contentType: 'application/json;charset=UTF-8',
			traditional: true,
			data:JSON.stringify(data),
			dataType:'json',
			success:function(data) {
				if (data.code == 200) {
					location.reload();
				} else {
					alert(data.msg);
				}
			}
		})
		
	}
	
	$(".btn-link").click(function() {
		window.location.href="/book";
	})