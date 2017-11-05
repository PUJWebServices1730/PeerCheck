var gulp = require('gulp');
var pug = require('gulp-pug');
var rename = require("gulp-rename");
var sass = require('gulp-sass');
var sourcemaps = require('gulp-sourcemaps');
var autoprefixer = require('gulp-autoprefixer');
var todo = require('gulp-todo');
var notify = require("gulp-notify");
var config = require('./gulpfile_config.json');

gulp.task('pug', () => {
	gulp.src(config.pug.src)
	.pipe(pug(config.pug.plugin))
	.pipe(rename(config.pug.rename))
	.pipe(gulp.dest(config.pug.dest));
	//.pipe(notify("PUG: <%= file.relative %>!"));
});

gulp.task('styles', () => {
	gulp.src(config.sass.src)
	.pipe(sourcemaps.init())
		.pipe(sass(config.sass.plugin).on('error', sass.logError))
		.pipe(autoprefixer(config.sass.autoprefixer))
		//.pipe(notify("SASS: <%= file.relative %>"))
	.pipe(sourcemaps.write(''))
	.pipe(gulp.dest(config.sass.dest));
});

gulp.task('todo', () => {
	gulp.src(config.todo.src)
	.pipe(todo(config.todo.plugin))
	.pipe(gulp.dest(config.todo.dest));
});

// The default task (called when you run `gulp` from cli)
gulp.task('default', () => {
	gulp.watch(config.pug.watch, ['pug']);
	gulp.watch(config.sass.watch, ['styles']);
});
