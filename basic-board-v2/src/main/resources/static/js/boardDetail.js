$(document).ready(()=>{
   checkToken();
   setupAjax();
   getUserInfo().then((userInfo)=>{
        $('#hiddenUserId').val(userInfo.userId);
        $('#hiddenUserName').val(userInfo.userName);
        loadBoardDetail();
   }).catch((error)=>{
        console.error('board detail user info error : ',error);
   });

});

let loadBoardDetail = () => {
    let hId = $('#hiddenId').val();
    let hUserId = $('#hiddenUserId').val();
    $.ajax({
        type: 'GET',
        url: '/api/board/' + hId,
        success: (response)=>{
            console.log('loadBoard detail : ', response);
            $('#title').text(response.title);
            $('#content').text(response.content);
            $('#userId').text(response.userId);
            $('#created').text(response.created);

            if(hUserId !== response.userId){
                $('#editBtn').props('disabled',true);
                $('#deleteBtn').props('disabled',true);
            }

            if(response.filePath && response.filePath.length > 0 ){
                let filePath = response.filePath;
                $('#hiddenFilePath').val(filePath);
                let fileName = filePath.substring(filePath.lastIndexOf('\\') + 1);
                let fileElement = `
                            <li>
                                <a href="/api/board/file/download/${fileName}">${fileName}</a> <!-- 다운로드 링크 -->
                            </li>`;
                $('#fileList').append(fileElement);
            }else{
                $('#fileList').append(`<li>첨부된 파일이 없습니다.</li>`);
            }
        },
        error: (error) => {
            console.error('board detail error :: ', error);
        }
    });
}
/*
let editArticle = () => {
    let hId = $('#hiddenId').val();

    window.location.href = "/update?id="+hId+"&userId="+$('#hiddenUserId').val()+"&userName="+$('#hiddenUserName').val();
}

let deleteArticle = () => {
    let hId = $('#hiddenId').val();

    $.ajax({
        type: 'DELETE',
        url: '/api/board/' + hId,
        success: (response)=>{
            alert('게시글이 삭제 되었습니다.');
            console.log('board delete success !!');
            window.location.href = "/";
        },
        error: (error) => {
            console.error('board delete error :: ', error);
        }
    });
}

 */

// 강사님 코드
let editArticle = () => {
    let hId = $('#hiddenId').val();

    window.location.href = "/update/"+hId;
}

let deleteArticle = () => {
    let hId = $('#hiddenId').val();
    let hFilePath = $('#hiddenFilePath').val();

    $.ajax({
        type: 'DELETE',
        url: '/api/board/' + hId,
        data : JSON.stringify({filePath : hFilePath}),
        contentType : 'application/json; charset=utf-8',
        success: (response)=>{
            alert('정상적으로 삭제 되었습니다.');
            window.location.href = "/";
        },
        error: (error) => {
            console.error('board delete error :: ', error);
        }
    });
}