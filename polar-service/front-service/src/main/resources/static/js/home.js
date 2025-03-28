$(document).ready(() => {
    setupAjax();
    checkToken();
    getCatalog();
});

let getCatalog = () => {
    $.ajax({
        method: 'GET',
        url: '/webs/api/catalog',
        dataType: 'json', // 서버에서 받을 데이터의 타입
        success: (response) => {
            console.log('response :: ', response);

            $('#catalogList').empty(); // 기존 게시글 내용 비우기
            if (response.catalogList.length <= 0) {
                // 게시글이 없는 경우 메시지 출력
                $('#catalogList').append(
                    `<tr>
                        <td colspan="4" style="text-align: center;">책이 존재하지 않습니다.</td>
                    </tr>`
                );
            } else {
                response.catalogList.forEach((catalog) => {
                    $('#catalogList').append(
                        `
                            <tr>
                                <td>${catalog.isbn}</td>
                                <td><a href="/detail?isbn=${catalog.isbn}">${catalog.title}</a></td>
                                <td>${catalog.author}</td>
                                <td>${catalog.price}</td>
                            </tr>
                    `
                    );
                });
            }
        },
        error: ( error ) => {
            console.error("Catalog list error :: " , error);
        }
    });
}

let createCatalog = () => {
    let isbn = $('#isbn').val();
    let title = $('#title').val();
    let author = $('#author').val();
    let price = $('#price').val();

    let sendData = {
        isbn : isbn,
        title : title,
        author : author,
        price : price
    }

    $.ajax({
        method: 'POST',
        url: '/webs/api/catalog',
        data: JSON.stringify(sendData), // 데이터를 JSON 형식으로 변환
        contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
        dataType: 'json', // 서버에서 받을 데이터의 타입
        success: (response) => {
            console.log('response :: ', response)
        },
        error: (xhr) => {
            if (xhr.status === 419) {
                // Refresh Token을 통해 Access Token 재발급 요청
                handleTokenExpiration();
                alert('다시 한번 시도해주세요.');
            } else {
                // 다른 오류 처리
                console.error('요청 오류 발생:', xhr);
            }
            location.reload(true);
        }
    });
}

let orderBook = () => {
    let isbn = $('#order_isbn').val();
    let quantity = $('#quantity').val();

    let orderData = {
        isbn : isbn,
        quantity : quantity
    }

    $.ajax({
        method: 'POST',
        url: '/webs/api/order',
        data: JSON.stringify(orderData), // 데이터를 JSON 형식으로 변환
        contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
        dataType: 'json', // 서버에서 받을 데이터의 타입
        success: (response) => {
            console.log('response :: ', response)
        },
        error: (error) => {
            console.error('order error :: ',error);
        }
    });
}