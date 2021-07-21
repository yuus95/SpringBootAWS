var main = {
    init: function () {
        var _this = this;
        // 화살표함수는 자기자신의 this가 없다.
        $('#btn-save').on('click', () => {
            _this.save();
        });

    },
    save: function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()

        };
        $.ajax({
            type:'POST',
            url:'/api/v1/posts',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)

        }).done(()=>{
            alert('글이 등록되었습니다');
            window.location.href='/';
        }).fail((error)=>{
            alert(JSON.stringify(error));
        });



    }

};


main.init();
