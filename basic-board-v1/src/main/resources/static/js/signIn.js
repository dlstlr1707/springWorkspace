$(document).ready(()=>{

    $('#signin').click(()=>{
       let userId = $('#user_id').val();
       let password = $('#password').val();

       let formData = {
           username : userId, // security에 일임하려면 id라도 username으로 설정 해줘야 함
           password : password
       }

        $.ajax({
            type : 'POST',
            url : '/login', // 서버의 엔드포인트 URL
            // 직접 작업 할 때
            //data : JSON.stringify(formData), // 데이터를 JSON 형식으로 변환
            //contentType : 'application/json; charset=utf-8', // 전송 데이터의 타입
            // security에 위임한다는 내용
            data: $.param(formData), // 데이터를 URL 인코딩된 형식으로 변환
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            dataType : 'json', // 서버에서 받을 데이터의 타입
            success: (response) => {
                console.log(response);
            },
            error: (error) => {
                console.log('오류발생 : ',error);
                alert('로그인 중 오류가 발생했습니다!');
            }

        });

    });

});