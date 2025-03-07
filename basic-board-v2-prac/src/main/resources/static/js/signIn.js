$(document).ready(()=>{

    $('#signin').click(()=>{

        let userId = $('#user_id').val();
        let password = $('#password').val();

        let signInData = {
            username : userId,
            password : password
        };

        $.ajax({
            type: 'POST',
            url: '/login',
            data: JSON.stringify(signInData),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: (response)=>{
                console.log(response);
                alert('로그인이 성공했습니다.');
                localStorage.setItem('accessToken',response.token);
                window.location.href = '/';
            },
            error: (error)=>{
                console.log('오류 발생 : ',error);
                alert('로그인 중 오류가 발생했습니다.');
            }
        });
    });

});