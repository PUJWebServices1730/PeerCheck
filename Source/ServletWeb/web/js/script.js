/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
	$.ajax({
		url : 'http://localhost:8080/ServletWeb/webresources/entities.users',
		type : 'GET',
		dataType:'json',
		success : function(data) {
			console.log('data');
			console.log(data);
		},
		error : function(request,error)
		{
			console.log('request');
			console.log(request);
		}
	});
});