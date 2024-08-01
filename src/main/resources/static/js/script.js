
// level3 비밀번호 클릭 시 알림창 표출 -> 확인 누르면 비밀번호 표출
function showPassKey() {
    if($("#keyvalue").is(":checked")){
        $('.keyStyle').prop("type", "password");
    }else{
        $('.keyStyle').prop("type", "text");
    }

    alert("확인 시 키 1개 차감됩니다. 사용하시겠습니까?")
}