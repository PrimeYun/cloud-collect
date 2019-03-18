	$.ajax({
		type:'get',
		url : '/redis/num',
		dataType:'json',
		success:function(data) {
			if (data.code == 200) {
				$(".head").append('<p style="font-size:15px">本站访问量：'+ data.data.viewNum +'</p>');
			} else {
				alert(data.msg);
			}
		}
	})
	
	$.ajax({
		type:'get',
		url : '/redis/sort',
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