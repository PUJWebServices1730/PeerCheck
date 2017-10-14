(function(){
	var css = document.createElement("style");
	css.type = "text/css";
	css.innerHTML = "" +
		"#vpsize {" +
			"position: fixed;" +
			"right: 0;" +
			"transform: translatex(calc(100% - 1em));" +
			"bottom: 30px;" +
			"padding: 0 1em 0 2em;" +
			"line-height: 2;" +
			"-webkit-user-select: none;" +
			"transition: all 0.3s;" +
			"color: black;" +
			"background: rgba(104, 104, 104, 0.2);" +
		"}" +

		"#vpsize.active{" +
			"transform: translatex(0);" +
		"}" +

		"#vpsize:hover {" +
			"color: white;" +
			"background: #686868;" +
		"}";
	document.head.appendChild(css);
	var vpSizeWrapper = document.createElement('div');
	vpSizeWrapper.setAttribute('id', 'vpsize');
	document.body.appendChild(vpSizeWrapper);
	getSize();

	function getSize() {
		var vpWidth, vpHeight, vpSize;
		vpWidth = window.innerWidth;
		vpHeight = window.innerHeight;
		vpSize = vpWidth + " w:h " + vpHeight;
		vpSizeWrapper.innerHTML = vpSize;
	}

	window.addEventListener('resize', getSize);
	vpSizeWrapper.addEventListener('click', () => {
		vpSizeWrapper.classList.toggle('active');
	});
}());
