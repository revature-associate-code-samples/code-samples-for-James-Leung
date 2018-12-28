/**
 * 
 */

window.onload = function() {
	
	loadHomeView();

	$('#login').on('click', loadLoginView);
	$('#logout').on('click', logoutSession);

	
}

function logoutSession() {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			$('#view').html(xhr.responseText);
			
		}
		
	}
	
	xhr.open("GET", "logout", true);
	xhr.send();	
	
}

function loadHomeView() {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			$('#view').html(xhr.responseText);
			
		}
		
	}
	
	xhr.open("GET", "home.view", true);
	xhr.send();
	
}

function loadLoginView() {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			//do things with response
			$('#view').html(xhr.responseText);
			$('#signin').on('click', login);
			
		}
		
	}
	
	xhr.open("GET", "login.view", true);
	xhr.send();
	
}

function login() {
		
	let user = {
			
		username: $('#username').val(),
		password: $('#password').val()
	
	};
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			$('#view').html(xhr.responseText);
//			loadReimbursements();
			loadVIEW();
			
		}
		
	}
	
	xhr.open("POST", "login", true);
	xhr.setRequestHeader("Content-type", "application/json");
	var toSend = JSON.stringify(user);
	console.log("send in login:" + toSend);
	xhr.send(toSend);
	
	
}

function loadVIEW() {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			$('#view').html(xhr.responseText);
//			loadManagerView();
			
			
		}
		
	}
	
	xhr.open("GET", "test.view", true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send();
	
}

function loadManagerView () {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			$('#view').html(xhr.responseText);
			console.log("inside loadManagerView");
			
		}
		
	}
	
	xhr.open("GET", "manager.view", true);
	xhr.send();
	
}

function loadEmployeeView () {
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		
		if(xhr.readyState == 4 && xhr.status == 200) {
			
			$('#view').html(xhr.responseText);
			
		}
		
	}
	
	xhr.open("GET", "employee.view", true);
	xhr.send();
	
}
