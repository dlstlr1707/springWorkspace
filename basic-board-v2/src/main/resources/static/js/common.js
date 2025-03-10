let handleTokenExpiration = () => {
    $.ajax({
        type: 'POST',
        url: '/refresh-token',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        xhrFields: {
          withCredentials: true //쿠키를 포함해서 요청을 보냄
        },
        success: (response)=>{
            console.log(response);
            localStorage.setItem('accessToken',response.token);
        },
        error: (error)=>{
            console.error(error);
            alert('로그인이 필요합니다. 다시 로그인해주세요.');
            window.location.href = '/member/login'
        }
    });
}

let checkToken = () =>{
    let token = localStorage.getItem("accessToken");
    if(token == null || token.trim() === ''){
        window.location.href = '/member/login';
    }
    // 추후 살아있는지 여부에 따라 추가 로직 필요

}

let setupAjax = () => {
    // 모든 Ajax 요청에 JWT Access Token을 포함 시킴
    $.ajaxSetup({
        beforeSend: (xhr)=>{
            let token = localStorage.getItem('accessToken');
            if(token){
                xhr.setRequestHeader('Authorization','Bearer ' + token);
            }
        }
    })
}


let getUserInfo = () => {
    return new Promise((resolve,reject)=>{
        $.ajax({
            type: 'GET',
            url: '/user/info',
            success:(response)=>{
                resolve(response);
            },
            error: (xhr)=>{
                console.log("xhr :: ",xhr);
                if(xhr.status === 401){
                    // 토큰 만료 에러 메세지에 따라 refreshToken 보냄
                    handleTokenExpiration();
                }else{
                    reject(xhr);
                }
            }
        })
    });
}
