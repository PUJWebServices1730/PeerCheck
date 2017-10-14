/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
	$.get("entities.users", function () {
		alert("success");
	})
		.done(function () {
			alert("second success");
		})
		.fail(function () {
			alert("error");
		})
		.always(function () {
			alert("finished");
		});
});