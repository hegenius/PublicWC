var resultDiv = document.getElementById('roadSpan');
var resultDiv2 = document.getElementById('jusoSpan');


var lat = 35.1560448378911, // 임의 위도
    lon = 129.059564755963; // 임의 경도

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
var startPosition = new kakao.maps.LatLng(lat, lon);
var currentMarker = makeMarker(startPosition);
currentMarker.setMap(map);

changeToJuso(startPosition)

// 마우스 드래그로 지도 이동이 완료되었을 때 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'center_changed', function () {


    // 지도 중심좌표를 얻어옵니다
    var center = map.getCenter();

    currentMarker.setMap();
    currentMarker = makeMarker(center);
    currentMarker.setMap(map);

});

kakao.maps.event.addListener(map, 'dragend', function () {
    // do something
    //TODO: 위도 경도를 주소로 바꿔서 필드에

    var center = map.getCenter();

    changeToJuso(center);
});

function changeToJuso(latLogi) {

    var geocoder = new kakao.maps.services.Geocoder();

    var callback = function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
            resultDiv.innerText = result[0].road_address.address_name;
            resultDiv2.innerText = result[0].address.address_name;
        }
    };

    geocoder.coord2Address(latLogi.getLng(), latLogi.getLat(), callback);

}

function makeMarker(loc) {

    var imageSrc = "/images/marker.gif" // 마커 이미지 url, 스프라이트 이미지를 씁니다
    var imageSize = new kakao.maps.Size(70, 70)  // 마커 이미지의 크기
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize)

    var newMarker = new kakao.maps.Marker({
        position: loc
    });

    return newMarker;
}