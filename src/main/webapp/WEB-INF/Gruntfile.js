module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    //TODO: cofiguración de los plugins
    concat: {
      //Concats 3er party minified js libs files
      thirdParyMinifiedJs: {
        src: [
          'src/lib/jquery/dist/jquery.min.js',
          'src/lib/bootstrap/dist/js/bootstrap.min.js',
          'src/lib/angular/angular.min.js',
          'src/lib/angular-route/angular-route.min.js',
          'src/lib/angular-resource/angular-resource.min.js'
        ],
        dest: 'src/tmp/thirdpartyscripts.min.js'
      },
      //Concats all js
      allJs: {
        src: [
          'src/tmp/thirdpartyscripts.min.js',
          'src/tmp/app.min.js'
        ],
        dest: 'src/app/js/application.min.js'
      },
      //Concats 3er Party minified css files
      thirdPartyMinifiedCss: {
        src: [
          'src/lib/bootstrap/dist/css/bootstrap.min.css',
          'src/lib/bootstrap/dist/css/bootstrap-theme.min.css'
        ],
        dest: 'src/tmp/thirdpartycss.min.css'
      },
      //Concats all css
      allCss: {
        src: [
          'src/tmp/thirdpartycss.min.css',
          'src/tmp/app.min.css'
        ],
        dest: 'src/app/css/application.min.css'
      }
    },
    uglify: {
      slgeomin: {
        files: {
          'src/tmp/app.min.js': ['src/app/js/**/*.js', '!src/app/js/application.min.js']
        }  
      }
    },
    cssmin: {
      slgeomin: {
        'src/tmp/app.min.css': ['src/app/css/**/*.css', '!src/app/css/application.min.css']
      }
    },
    copy: {
      fonts: {
        expand: true,
        cwd: 'src/lib/bootstrap/dist/fonts/',
        src: ['**'],
        dest: 'src/app/fonts/'
      },
      main: {
        expand: true,
        cwd: 'src/app/',
        src: ['**', '!js/**', '!css/**', 'js/application.min.js', 'css/application.min.css'],
        dest: 'dist/'
      }
    },
    watch: {
      options: {
        livereload: true
      },
      files: [
        'src/app/**'
      ],
      tasks: [
        'concat:thirdParyMinifiedJs',
        'concat:thirdPartyMinifiedCss',
        'uglify',
        'cssmin',
        'concat:allJs',
        'concat:allCss',
        'copy'
      ]
    }
  });

  // Load the plugin that provides the "concat" task.
  grunt.loadNpmTasks('grunt-contrib-concat');

  // Load the plugin that provides the "uglify" task.
  grunt.loadNpmTasks('grunt-contrib-uglify');
  
  // Load the plugin that provides the "cssmin" task.
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  
  // Load the plugin that provides the "copy" task.
  grunt.loadNpmTasks('grunt-contrib-copy');
  
  // Load the plugin that provides the "watch" task.
  grunt.loadNpmTasks('grunt-contrib-watch');

  // Default task(s).
  grunt.registerTask('default', ['watch']);

};