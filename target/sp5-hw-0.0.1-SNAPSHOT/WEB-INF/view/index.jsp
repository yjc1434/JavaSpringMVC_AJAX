<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Index</title>
	</head>
	<body>
        <div id="header">
        	<img src="/sp5-hw/resource/img/inje_logo.jpg" style="height:70px; margin-bottom:3px;" alt="">
        	<h3>학생 검색 시스템</h3>
        </div>
        <div id="body">
        	<input type="text" id="studnum" placeholder="학번 입력">
        	<button onclick="findStud()">검색</button>
        </div>
        <div id="result">
        </div>
	</body>
	<script>
		let findStud = () => {
			let req = new XMLHttpRequest();
			let data = {'studnum' : document.getElementById('studnum')};
			req.open('post','/sp5-hw/find');
			req.setRequestHeader('Content-type', 'application/json');
			req.onreadystatechange = () => {
				if(req.status == 200 && req.readyState == 4) { //정상적으로 정보를 받았을 때
					let res = JSON.parse(req.response); //response에 서버사이드에서 보낸 정보가 담겨있다.
					document.getElementById('result').innerText = res;
				}
			}
			req.send(JSON.stringify(data));
		}
	</script>
</html>
