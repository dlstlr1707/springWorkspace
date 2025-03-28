$(document).ready(()=>{

    $('#signin').click(()=>{

        let userId = $('#user_id').val();
        let password = $('#password').val();

        let signInData = {
            userId : userId,
            password : password
        };

        $.ajax({
            type: 'POST',
            url: '/login',
            data: JSON.stringify(signInData),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: (response)=>{
                // 추후 response가 null이 아닌지 체크하는 방어 코드 작성 필요
                console.log(response);
                alert(response.message);
                localStorage.setItem('accessToken',response.accessToken);
                window.location.href = response.url;
            },
            error: (error)=>{
                console.log('오류 발생 : ',error);
                alert('로그인 중 오류가 발생했습니다.');
            }
        });
    });

});