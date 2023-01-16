<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>JAVA SPRING AJAX EXAMPLE</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	</head>
	<body>
        <div class="text-center py-3" id="header" style="font-size:23px; font-weight:bold; border-bottom: 1px solid rgb(189, 189, 189)">
        	학생정보시스템
        </div>
		<div class="container" id="body-search">
        	<h3 class="text-center my-3">학생정보 검색</h3>
        	<div class="row">
				<div class="col-4 text-end">
					<label for="studnum" class="form-label">학번</label>
				</div>
				<div class="col-4">
					<input type="text" class="form-control" id="studnum" placeholder="학번 입력">
				</div>
				<div class="col-4">
					<button class="btn btn-primary w-50" onclick="findStud()">검색</button>
				</div>
        	</div>
        	<div id="result"></div>
        </div>
	</body>
	<script>
		let findStud = () => {
			let req = new XMLHttpRequest();
			let data = {'studNum' : document.getElementById('studnum').value};
			req.open('post','/sp5-hw/find');
			req.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
			req.onreadystatechange = () => {
				if(req.status == 200 && req.readyState == 4) {
					document.getElementById('studnum').value = "";
					let res = JSON.parse(req.response);
					if (res.res == "ok") {
						let answer = res.answer.split(' ');
						let card = `<ul class="list-group list-group-flush text-center">
                    		<li class="list-group-item">· 이름 : ` + answer[1] + `</li>
                    		<li class="list-group-item">· 학과 :  ` + answer[3] + `</li>
                    		<li class="list-group-item">· 단과대학 : ` + answer[5] + `</li>
                    		<li class="list-group-item">· 학과(부) :  ` + answer[7] + `</li>
                		</ul>`
						document.getElementById('result').innerHTML = card;
					}
					else {
						document.getElementById('result').innertHTML = '<div class="text-center">정보가 존재하지 않습니다</div>';
					}
				}
			}
			req.send(JSON.stringify(data));
		}
	</script>
</html>
