var sys = require('sys');
var exec = require('child_process').exec;
var express = require('express');
var app = express();

app.get('/', function (req, res) {
        res.send('Welcome to PiCAST 3! In the URL, type what you want to do...');
});

app.get('/youtube/:url', function (req, res) {
        res.send('Streaming YouTube Video...');
        exec("killall vlc");
	exec("youtube-dl -f 18/worst -o - https://www.youtube.com/watch?v=" + req.params.url + " | vlc --fullscreen -");
});

app.get('/twitch/:url', function (req, res) {
	res.send('Streaming on Twitch...');
	exec("killall vlc");
	exec("livestreamer --player='vlc --fullscreen' --twitch-oauth-token eh45s05tn6jawr06ghz4dfrhbu20fj twitch.tv/" + req.params.url + " low,worst");
});

app.get('/openrec/:url', function (req, res) {
	res.send('Streaming OpenRec...!!!!');
	exec("killall vlc");
	exec("livestreamer --player='vlc --fullscreen' https://www.openrec.tv/live/" + req.params.url + " worst");
});

// Setup PiCAST Server
var srv = app.listen(3000, function () {
        var host = srv.address().address;
        var port = srv.address().port;

        console.log('Access at http://%s:%s', host, port);
});
