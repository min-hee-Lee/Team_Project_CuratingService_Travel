<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/test.css">
<title></title>
</head>
<body>
  <div class="wrapper">
        <img src="images/image1.png"/>
        <img src="images/image2.png"/>
        <img src="images/image3.png"/>
        <img src="images/image4.png"/>
        <img src="images/image5.png"/>
    </div>
    <button id="prevBtn">&laquo;</button>
    <button id="nextBtn">&raquo;</button>
    <script src="js/jquery-3.3.1.js"></script>
    <script>
        var imgLeft=[0, 400, 800, 1200, 1600];
        //오른쪽 버튼 클릭시 사진 움직이기
        $("#nextBtn").on('click',function(){
            // 3. img1의 left가 0일때 슬다이드 멈추기
            if(imgLeft[0]==0){
                return;
            }

            // 1. imgLeft의 배열의 값을 모두 바꿔주기
            for(var i=0; i<imgLeft.length; i++){
                imgLeft[i]+=400;
            }
            // 2. 변경된 배열의 값을 각각의 img 문서에 넣어주기
            $(".wrapper img").each(function(j,item){
                var tmp=imgLeft[j]
                $(item).css("left",tmp+"px");
            });
        });

        //왼쪽 버튼 클릭시 사진 움직이기
        $("#prevBtn").on('click',function(){
            // 3. img1의 left가 0일때 슬다이드 멈추기
            if(imgLeft[4]==0){
                return;
            }

            // 1. imgLeft의 배열의 값을 모두 바꿔주기
            for(var i=0; i<imgLeft.length; i++){
                imgLeft[i]-=400;
            }
            // 2. 변경된 배열의 값을 각각의 img 문서에 넣어주기
            $(".wrapper img").each(function(j,item){
                var tmp=imgLeft[j]
                $(item).css("left",tmp+"px");
            });

        });
    </script>
</body>
</html>