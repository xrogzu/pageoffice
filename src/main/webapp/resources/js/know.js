$(function(){	
	var clientW=$(window).width();
	var leftW=$(".left-pannel").width();
	$(".right-pannel").width(clientW-leftW-1);
	//
	var tH=$(".accordion-title").height();
	var leftH=$(".left-pannel").height();
	$(".accordion").height(leftH-tH);
	$(".tabs_content").height(leftH);
	//自适应
	$(window).resize(function(){
		var clientW=$(window).width();
		var leftW=$(".left-pannel").width();
		//
		var tH=$(".accordion-title").height();
		var leftH=$(".left-pannel").height();
		$(".accordion").height(leftH-tH);
		$(".tabs_content").height(leftH);
	});

	//滚动条
    $('.boxscroll').niceScroll({
        cursorcolor: "#8f9fb6",//#CC0071 光标颜色
        cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
        touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
        cursorwidth: "3px", //像素光标的宽度
        cursorborder: "0", // 游标边框css定义
        cursorborderradius: "3px",//以像素为光标边界半径
        autohidemode: true //是否隐藏滚动条
    });
});

