let selectedFile = null;

$(document).ready(()=>{

    checkToken();
    setupAjax();
    getUserInfo().then((userInfo)=>{
        console.log('user info :: ' + userInfo);
        $('#hiddenUserName').val(userInfo.userName);
        $('#hiddenUserId').val(userInfo.userId);
        $('#userId').val(userInfo.userId);
    }).catch((error)=>{
        console.error('Error get user info : ',error);
    });

    $('#file').on('change',(event)=>{ 
        selectedFile = event.target.files[0];   // 선택한 파일 한 개를 변수에 저장
        updateFileList();
    })

    // .click말고 다른 방식
    $('#submitBtn').on('click',(event)=>{
        event.preventDefault();

        let formData = new FormData($('#writeForm')[0]);
        /*
        for (let [key, value] of formData.entries()) {
            if (value instanceof File) {
                console.log('Key:', key);
                console.log('Name:', value.name);
                console.log('Size:', value.size);
                console.log('Type:', value.type);
            } else {
                console.log(key + ': ' + value);
            }
        }
         */

        // 멀티파트 데이터 보내는 방식
        $.ajax({
            type: 'POST',
            url: '/api/board',
            data: formData,
            contentType: false,
            processData: false,
            success: ()=>{
                alert('게시글이 성공적으로 등록되었습니다.');
                window.location.href = '/';
            },
            error: (error)=>{
                console.log('오류 발생 : ',error);
                alert('게시글 등록 중 오류가 발생했습니다.');
            }
        });
    });

});

// 파일 목록 업데이트 함수 (파일 하나만)
let updateFileList = () => {
    $('#fileList').empty();

    if(selectedFile){
        $('#fileList').append(`
                    <li>
                        ${selectedFile.name} <button type="button" class="remove-btn">X</button>
                    </li>
                `);

        // X 버튼 클릭 시 파일 제거
        $('.remove-btn').on('click', function() {
            selectedFile = null; // 선택된 파일 제거
            $('#file').val(''); // 파일 input 초기화
            updateFileList(); // 파일 목록 갱신
        });
    }
}