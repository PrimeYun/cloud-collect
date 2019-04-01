		$.ajax({
			type:'get',
			url : '/common/num',
			dataType:'json',
			success:function(data) {
				if (data.code == 200) {
					$(".head").append('<span style="font-size:15px">本站访问量：'+ data.data.viewNum +'</span>' + '  |  '
							 + '<span style="font-size:15px">访问人数：' + data.data.accessNum +'</span>');
				} else {
					alert(data.msg);
				}
			}
		})	

	$.ajax({
		type:'get',
		url : '/common/sort',
		dataType:'json',
		success:function(data) {
			if (data.code == 200) {
				var sort = data.data.sort;
				for (var i = 0; i < sort.length; i++) {
					$("#sort").append('<option value=' + sort[i].id + '>' + sort[i].name +'</option>');
					$("#editSort").append('<option value=' + sort[i].id + '>' + sort[i].name +'</option>');
				}
			} else {
				alert(data.msg);
			}
		}
	})