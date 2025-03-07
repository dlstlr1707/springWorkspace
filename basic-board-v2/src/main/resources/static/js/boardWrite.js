$(document).ready(()=>{

    checkToken();
    getUserInfo().then((userInfo)=>{
        $('#welcome-message').text(userInfo.userName + '님 환영합니다!');
    }).catch((error)=>{
        console.error('Error get user info : ',error);
    });
    
    // .click말고 다른 방식
    $('#submitBtn').on('click',(event)=>{
        event.preventDefault();

        let formData = new FormData($('#writeForm')[0]);

    });

});