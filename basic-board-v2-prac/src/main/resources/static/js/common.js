let checkToken = () =>{
    let token = localStorage.getItem("accessToken");
    if(token == null || token.trim() === ''){
        window.location.href = '/member/login';
    }
    // 추후 살아있는지 여부에 따라 추가 로직 필요

}