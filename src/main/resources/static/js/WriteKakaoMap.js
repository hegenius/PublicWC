var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

function getInfo() {
    // 지도의 현재 중심좌표를 얻어옵니다
    var center = map.getCenter();

    var message = '지도 중심좌표는 위도 ' + center.getLat();

    $("#jusoSpan").text(message)

    // 개발자도구를 통해 직접 message 내용을 확인해 보세요.
    // ex) console.log(message);
}