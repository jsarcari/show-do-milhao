document.getElementById("audio").onclick = function () {
    var mutedAudio = document.getElementById("audio-home").muted;
    document.getElementById("audio-home"). muted = !mutedAudio;
    document.getElementById("no-audio").classList.toggle("boolean-audio");
}