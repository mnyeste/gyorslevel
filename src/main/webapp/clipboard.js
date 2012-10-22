function copy_to_clipboard(text) {
    if (window.clipboardData) {
        window.clipboardData.setData('text', text);
    } else {
        var clipboarddiv = document.getElementById('divclipboardswf');
        if (!clipboarddiv) {
            clipboarddiv = document.createElement('div');
            clipboarddiv.setAttribute("name", "divclipboardswf");
            clipboarddiv.setAttribute("id", "divclipboardswf");
            document.body.appendChild(clipboarddiv);
        }
        clipboarddiv.innerHTML = '<embed src="clipboard.swf" FlashVars="clipboard=' + encodeURIComponent(text) + '" width="0" height="0" type="application/x-shockwave-flash"></embed>';
    }    
}