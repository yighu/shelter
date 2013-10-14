module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    "phonegap-build": {
      options: {
        archive: "app.zip",
        "appId": "com.chase.shelter",
        "user": {
          "email": "cgonciulea@gmail.com",
          "password": ""
        }
      }
    },
    zip: {
      app: {
        src: ["index.html", "Config.xml", "components/**/*.*", "partials/*.html", "js/**/*.js"],
        dest: "app.zip"
      }     
    }
  });

  // Load local tasks.
  grunt.loadNpmTasks('grunt-zipstream');
  grunt.loadNpmTasks('grunt-phonegap-build');

  // Default task.
  grunt.registerTask('default', ['zip', 'phonegap-build']);
};

