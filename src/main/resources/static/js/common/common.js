	$.ajax({
			type:'get',
			url : '/common/get',
			dataType:'json',
			success:function(data) {
				if (data.code == 200) {
					num(data.data.num);
					sort(data.data.sort);
					weather(data.data.weather);
				} else {
					alert(data.msg);
				}
			}
		})		
	
	function num(data) {
		console.log(data);
		$(".head").append('<span style="font-size:15px">本站访问量：'+ data.viewNum +'</span>' + '  |  '
				 + '<span style="font-size:15px">访问人数：' + data.accessNum +'</span>');
	}
	
	function sort(sort) {
		for (var i = 0; i < sort.length; i++) {
			$("#sort").append('<option value=' + sort[i].id + '>' + sort[i].name +'</option>');
			$("#editSort").append('<option value=' + sort[i].id + '>' + sort[i].name +'</option>');
		}
	}
	
	function weather(data) {
		$(".weather").append('<p>'+ data.city + '</p><span>' + data.data[0].week + '</span><span>' + data.data[0].date
				+ '</span><span>' + data.data[0].wea + '</span><span>空气质量：' + data.data[0].air_level + '</span>');
		
	}