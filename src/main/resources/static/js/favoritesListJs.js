$(document).ready(function () {


// 검색 결과 목록과 마커를 표출하는 함수입니다
    function displayPlaces(places) {
        // var listEl = document.getElementById('placesList'),
        var listEl = document.getElementById('underList'),
            menuEl = document.getElementById('menu_wrap'),
            fragment = document.createDocumentFragment(),
            bounds = new kakao.maps.LatLngBounds(),
            listStr = '';

        // 검색 결과 목록에 추가된 항목들을 제거합니다
        removeAllChildNods(listEl);

        // 지도에 표시되고 있는 마커를 제거합니다
        removeMarker();

        for (var i = 0; i < places.length; i++) {

            // 마커를 생성하고 지도에 표시합니다
            var placePosition = new kakao.maps.LatLng(places[i].latitude, places[i].longitude),
                itemEl = favoritesListItem(i, places[i]);

            bounds.extend(placePosition);

            (function (marker, title) {

                itemEl.onmouseover = function () {
                    displayInfowindow(marker, title);
                };

                itemEl.onmouseout = function () {
                    infowindow.close();
                };
            })(marker, places[i].detailAddr);

            fragment.appendChild(itemEl);
        }


        // "자세히 보기" 버튼 클릭 이벤트 추가
        var detailButton = document.getElementById('detailButton');
        detailButton.addEventListener('click', function() {
            // var url = '/targetPage?time=' + encodeURIComponent(timeText) +
            //     '&address=' + encodeURIComponent(addressText) +
            //     '&key=' + encodeURIComponent(keyText);

            window.location.href = "/auth/wcDtail";
        });

    }

    function favoritesListItem(index, place) {

        var timeText = place.time;
        var addressText = place.addr1 + place.detailAddr;
        var keyText = '********';

        var div = document.createElement('div');
        div.className = 'col-sm-6 mb-5';

        var itemStr = `
        <div class="d-flex position-relative">
            <div class="box_img">
                <img src="/images/step_icon01.svg" alt="1단계 아이콘">
            </div>
            <div class="iconWrap">
                <div class="d-flex icon">
                    <p class="me-3">
                        <img src="/images/thumb_up.svg" alt="좋아요 아이콘">
                        <span class="ms-1">10</span>
                    </p>
                    <p>
                        <img src="/images/thumb_down.svg" alt="싫어요 아이콘"><span class="ms-1">0</span>
                    </p>
                </div>
            </div>
        </div>
        <hr>
        <div class="listBox d-flex">
            <img src="/images/time.svg" alt="시간 아이콘">
            <p>개방시간 : <span>${timeText}</span></p>
        </div>
        <div class="listBox d-flex">
            <img src="/images/address.svg" alt="위치 아이콘">
            <p>주소 : <span>${addressText}</span></p>
        </div>
        <div class="listBox d-flex">
            <img src="/images/key.svg" alt="키 아이콘">
            <p>키 : <span>${keyText}</span></p>
        </div>
        <div>
            <button class="btn btn-primary mt-2" id="detailButton">자세히 보기</button>
        </div>
    `;
        div.innerHTML = itemStr;


        return div;
    }


// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
    function displayPagination(pagination) {
        var paginationEl = document.getElementById('pagination'),
            fragment = document.createDocumentFragment(),
            i;

        // 기존에 추가된 페이지번호를 삭제합니다
        while (paginationEl.hasChildNodes()) {
            paginationEl.removeChild(paginationEl.lastChild);
        }

        for (i = 1; i <= pagination.last; i++) {
            var el = document.createElement('a');
            el.href = "#";
            el.innerHTML = i;

            if (i === pagination.current) {
                el.className = 'on';
            } else {
                el.onclick = (function (i) {
                    return function () {
                        pagination.gotoPage(i);
                    }
                })(i);
            }

            fragment.appendChild(el);
        }
        paginationEl.appendChild(fragment);
    }


});
