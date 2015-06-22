var cluster = require('cluster');
var http = require('http');

var cpuCount = require('os').cpus().length;

if (cluster.isMaster) {
    var i;
    for (i = 0; i < cpuCount; i++) {
        cluster.fork({PORT: 3000 + i});
        // cluster.fork();
    }

    cluster.on('listening', function (worker, address) {
        console.log('[master] ' + 'listening: worker' + worker.id + ',pid:' + worker.process.pid + ', Address:' + address.address + ":" + address.port);
    });
} else if (cluster.isWorker) {
    console.log('worker start: ' + cluster.worker.id);

    http.createServer(function (req, res) {
        console.log('worker'+cluster.worker.id);
        res.end('worker'+cluster.worker.id+',PID:'+process.pid);
    }).listen(8000);
}
