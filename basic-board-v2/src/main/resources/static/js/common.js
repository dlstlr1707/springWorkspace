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
                reject(xhr);
            }
        })
    });
}
