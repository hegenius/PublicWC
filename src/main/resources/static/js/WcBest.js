$(document).ready(function () {
    var wcIdTags = $(".inputWcId");
    var wcIdList = [];

    for (var i = 0; i < wcIdTags.length; i++) {
        var wcId = $(wcIdTags[i]).val();
        wcIdList.push(wcId);
    }

    function loadCounts() {

        $.ajax({
            url: "/location/getCountList",
            type: "GET",
            data: {wcIdList: wcIdList},
            success: function (resData) {
                if (resData != null) {
                    var likeList = resData.likeList;
                    var hateList = resData.hateList;

                    for (var i = 0; i < likeList.length; i++) {
                        parentTag = $(wcIdTags[i]).parent();
                        likeTag = parentTag.find("span.like");
                        hateTag = parentTag.find("span.hate");

                        $(likeTag).text(likeList[i].cnt);
                        $(hateTag).text(hateList[i].cnt);
                    }
                }
            },
            error: function (errData) {
                console.log(errData);
            }
        });
    }
    loadCounts();
});