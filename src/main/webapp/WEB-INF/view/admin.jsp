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
		<div class="container mt-3" id="admin">
			<h2 class="text-center">- 관리자 메뉴 -</h2>
			<div id="body-insert">
				<h3 class="text-center my-3">추가하기</h3>
				<div class="row">
					<div class="col-4 text-end">
						<label for="name" class="form-label">이름</label>
					</div>
					<div class="col-4">
						<input type="text" class="form-control" id="name" placeholder="이름 입력">
					</div>	
					<div class="col-4">
					</div>	
					<div class="col-4 text-end">
						<label for="studnum" class="form-label">학번</label>
					</div>
					<div class="col-4">
						<input type="text" class="form-control" id="studnum_i" placeholder="학번 입력">
					</div>
					<div class="col-4">
					</div>
					<div class="col-4 text-end">
						<label for="college" class="form-label">단과대학</label>
					</div>
					<div class="col-4">
						<input type="text" class="form-control" id="college" placeholder="단과대학 입력">
					</div>
					<div class="col-4">
					</div>
					<div class="col-4 text-end">
						<label for="department" class="form-label">학과</label>
					</div>
					<div class="col-4">
						<input type="text" class="form-control" id="department" placeholder="학과 입력">
					</div>
					<div class="col-4">
					</div>
					<div class="col-3">
					</div>
					<div class="col-6">
						<button class="btn btn-primary w-100 mt-3" onclick="insertStud()">추가</button>
					</div>	
					<div class="col-3">
					</div>
				</div>
			</div>
			<div id="body-delete">
				<h3 class="text-center my-3">삭제하기</h3>
				<div class="row">
					<div class="col-4 text-end">
						<label for="studnum_d" class="form-label">학번</label>
					</div>
					<div class="col-4">
						<input type="text" class="form-control" id="studnum_d" placeholder="학번 입력">
					</div>
					<div class="col-4">
						<button class="btn btn-danger w-50" onclick="deleteStud()">삭제</button>
					</div>
				</div>
			</div>
			<div id="body-update">
				<h3 class="text-center my-3">수정하기</h3>
				<div class="row">
					<div class="col-4 text-end">
						<label for="studnum_u" class="form-label">대상 학번 입력</label>
					</div>
					<div class="col-4">
						<input type="text" class="form-control" id="studnum_u" placeholder="학번 입력">
					</div>
					<div class="col-4">
					</div>
					<div class="col-4 text-end">
						수정 대상
					</div>
					<div class="col-4">
						<select class="form-select" id="select">
							<option value="name">이름</option>
							<option value="college">단과대학</option>
							<option value="department">학과(부)</option>
						</select>
					</div>
					<div class="col-4">
					</div>
					<div class="col-4 text-end">
						수정할 값
					</div>
					<div class="col-4">
						<input type="text" class="form-control" id="value" placeholder="수정할 값 입력">
					</div>
					<div class="col-4">
					</div>
					<div class="col-3">
					</div>
					<div class="col-6">
						<button class="btn btn-primary w-100 mt-3" onclick="deleteStud()">수정</button>
					</div>
					<div class="col-3">
					</div>
				</div>
			</div>
			<div id="result" class="text-center"></div>
		</div>
	</body>
	<script>
		let insertStud = () => {
			let req = new XMLHttpRequest();
			let data = {
					'name' : document.getElementById('name').value,
					'studNum' : document.getElementById('studnum_i').value,
					'college' : document.getElementById('college').value,
					'department' : document.getElementById('department').value
					};
			req.open('post','/sp5-hw/insert');
			req.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
			req.onreadystatechange = () => {
				if(req.status == 200 && req.readyState == 4) { //정상적으로 정보를 받았을 때
					let res = JSON.parse(req.response);
					document.getElementById('name').value = "";
					document.getElementById('studnum_i').value = "";
					document.getElementById('college').value = "";
					document.getElementById('department').value = "";
					
					if (res.res == "ok") {
						document.getElementById('result').innerText = "추가되었습니다.";
					}
					else {
						document.getElementById('result').innerText = "error";
					}
				}
			}
			req.send(JSON.stringify(data));
		}
		
		let deleteStud = () => {
			let req = new XMLHttpRequest();
			let data = {
					'studNum' : document.getElementById('studnum_d').value
					};
			req.open('post','/sp5-hw/delete');
			req.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
			req.onreadystatechange = () => {
				if(req.status == 200 && req.readyState == 4) { //정상적으로 정보를 받았을 때
					let res = JSON.parse(req.response);
					document.getElementById('studnum_d').value = "";

					if (res.res == "ok") {
						document.getElementById('result').innerText = "삭제되었습니다.";
					}
					else {
						document.getElementById('result').innerText = "error";
					}
				}
			}
			req.send(JSON.stringify(data));
		}
		
		let updateStud = () => {
			let req = new XMLHttpRequest();
			let data = {
					'studNum' : document.getElementById('studnum_u').value,
					};
			
			if (document.getElementById('select').value == "name") {
				data.name = document.getElementById('value').value;
			}
			else if (document.getElementById('select').value == "college") {
				data.college = document.getElementById('value').value;
			}
			else if (document.getElementById('select').value == "department") {
				data.department = document.getElementById('value').value;
			}
			
			req.open('post','/sp5-hw/update');
			req.setRequestHeader('Content-type', 'application/json; charset=UTF-8');
			req.onreadystatechange = () => {
				if(req.status == 200 && req.readyState == 4) { //정상적으로 정보를 받았을 때
					let res = JSON.parse(req.response);
					document.getElementById('studnum_u').value = "";
					document.getElementById('value').value = "";
					
					if (res.res == "ok") {
						document.getElementById('result').innerText = "수정되었습니다.";
					}
					else {
						document.getElementById('result').innerText = "error";
					}
				}
			}
			req.send(JSON.stringify(data));
		}
	</script>
</html>
