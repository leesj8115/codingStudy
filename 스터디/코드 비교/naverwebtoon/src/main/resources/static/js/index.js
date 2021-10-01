
/**
 * 각 버튼 클릭에 대한 이벤트 매칭
 * 
 */
$(function() {
  $("#artist-get").on("click", function() {
    // alert("눌림");
    $.ajax({
      url: "/artists",
      type: "get",
      success: function(result) {
        alert("SUCCESS");
      }
    });
  });

  $("#artist-post").on("click", function() {
    alert("추가 실행");
    
    var id = $("#artist-no");
    var name = $("#artist-name");

    var idVal = id.val();
    var nameVal = name.val();

    var artistObject = {
      id: idVal,
      name : nameVal
    };

    $.ajax({
      url: "/artists",
      type: "post",
      data: artistObject,
      contentType: "application/json; charset=utf-8",
      success: function(result) {
        alert("SUCCESS");
      }
    });
  });
});