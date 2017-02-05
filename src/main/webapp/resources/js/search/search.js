var clientW=$(window).width();


function submitFn(obj, evt){
    value = $(obj).find('.search-input').val().trim();
     _html = "";
    if(!value.length){
         var tips ="<div class='input_tips'><span>请输入查询内容</span></div>";
         $(obj).append(tips);
    }else{
    	_html +="<ul class='search-ui'>";
    	for(var i=0;i<5;i++){
    		var num=i+1;
    		_html += "<li><a>"+"<label>"+num+"、</label>" + value + "</a></li>";
    	}
        _html +="</ul>";
        
        //文档内容宽度
        $(".search_lists").show();
        var searchW=$(".search_lists").width(); 
        var leftW=$(".left-pannel").width();
        $(".tabs_content").width(clientW-leftW-searchW-1);
    }
    $('.result-container').html( _html);
    $('.result-container').fadeIn(100);    
    evt.preventDefault();
}
