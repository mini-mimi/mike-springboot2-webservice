let main = {
    init : function() {
        let _this = this;
        $('#btn-save').on('click', function() {
            _this.save();
        });

        $('#btn-update').on('click', function() {
            _this.update();
        })

        $('#btn-delete').on('click', function() {
            _this.delete();
        })
    },
    save : function() {
        let _data = {
            post_title: $('#title').val(),
            post_author: $('#author').val(),
            post_content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(_data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = "/";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    },
    update : function() {
        let _data = {
            post_title: $('#title').val(),
            post_content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(_data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = "/";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function() {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();