module.exports = function(grunt) {
    grunt.initConfig({
        PROJECT_NAME: 'finance-thunder',
        FOREVER_DIR: '../.forever',
        PIDFILE: '<%=FOREVER_DIR%>/<%=PROJECT_NAME%>.pid',
        LOGFILE: '<%=FOREVER_DIR%>/<%=PROJECT_NAME%>.log',
        pkg: grunt.file.readJSON('package.json'),
        exec: {
            logs: {
                cmd: '[ ! -f <%=LOGFILE%> ] && touch <%=LOGFILE%>; tail -f <%=LOGFILE%>'
            },
            clear: {
                cmd: 'rm -f <%=FOREVER_DIR%>/*.log'
            },
            start_server: {
                cmd: 'if [ ! -f <%=PIDFILE%> ]; then touch <%=PIDFILE%> && NODE_ENV=production forever start -p <%=FOREVER_DIR%> -l <%=PROJECT_NAME%>.log -c "node --max-old-space-size=8192 --nouse-idle-notification" -a app.js; else echo "Can\'t start <%=PROJECT_NAME%>: <%=PROJECT_NAME%> is already running."; fi'
            },
            start_dev_server: {
                cmd: 'if [ ! -f <%=PIDFILE%> ]; then touch <%=PIDFILE%> && forever start -w --watchDirectory . -p <%=FOREVER_DIR%> -l <%=PROJECT_NAME%>.log -c "node --max-old-space-size=8192 --nouse-idle-notification" -a app.js; else echo "Can\'t start <%=PROJECT_NAME%>: <%=PROJECT_NAME%> is already running."; fi'
            },
            stop_server: {
                cmd: 'if [ -f <%=PIDFILE%> ]; then rm -f <%=PIDFILE%> && forever stop app.js; else echo "Can\'t stop <%=PROJECT_NAME%>: <%=PROJECT_NAME%> is not running."; fi'
            }
        }
    });

    // Load the plugin that provides the "uglify" task.
    // grunt.loadNpmTasks('grunt-contrib-watch');
    // grunt.loadNpmTasks('grunt-contrib-uglify');
    // grunt.loadNpmTasks('grunt-contrib-jshint');
    // grunt.loadNpmTasks('grunt-contrib-sass');
    grunt.loadNpmTasks('grunt-exec');

    // Default task(s)
    // grunt.registerTask('test', ['jshint']);
    // grunt.registerTask('build', ['uglify', 'sass']);
    grunt.registerTask('log', ['exec:logs']);
    grunt.registerTask('logs', ['exec:logs']);
    grunt.registerTask('start', ['exec:start_server']);
    grunt.registerTask('start-dev', ['exec:start_dev_server']);
    grunt.registerTask('stop', ['exec:stop_server']);
    grunt.registerTask('restart', ['stop', 'start']);

    grunt.registerTask('default', ['build']);
};
