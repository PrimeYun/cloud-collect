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