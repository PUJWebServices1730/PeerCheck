$('#toggle-options').on('click', function(){
	var target = $(this).attr('target');
	$(target).fadeToggle('fast');
});

$('#form-search').submit(function(){
	$.ajax({
		processData: false,
		contentType: false,
		data: $(this)[0],
		dataType: 'json',
		type: $(this).attr('method')
	});
});

var createArticleView = (articleList, eventName, name, authors, topics, grade) => {
	var article = "" +
	"<article class='article_view'>\n" +
	"	<section class='article_view_data'>\n" +
	"		<p class='article_view_event'>" + eventName + "</p>\n" +
	"		<h3 class='article_view_name'>" + name + "</h3>\n" +
	"		<ul class='article_view_authors'>\n";
	authors.forEach((author) => {
		article += "			<li class='article_view_author'>" + author + "</li>\n";
	});
	article += "		</ul>\n";
	article += "		<ul class='article_view_topics'>\n";
	topics.forEach((topic) => {
		article += "			<li class='article_view_topic'>" + topic + "</li>\n";
	});
	article += "		</ul>\n";
	article += "	</section>\n";
	if(grade >= 0) {
		article += "" +
		"	<section class='article_view_grade'>\n" +
		"		<p class='grade'>" + grade + "</p>\n" +
		"		<p class='over'>5.0</p>\n" +
		"	</section>\n";
	}
	article += "</article>\n";
	$(articleList).append(article);
};

$('.acordeon_title').on('click', function() {
	$(this).next('.acordeon_content').slideToggle();
});
