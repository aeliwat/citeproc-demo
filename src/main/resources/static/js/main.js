$(document).on("ready", init);

function init() {
    $(document).on('change', '#select_citation_style', sendRequest);
}

function sendRequest() {
    var selectCiteStyle = $('#select_citation_style').val();

    if (selectCiteStyle) {

        // add spinner
        $('.cls-response').html("<span>Loading ... </span>");
        console.log("-- Start Processing ---- ");
        // Get the citations that we are supposed to render, in the CSL-json format
        var xhr = getXMLHTTPInstance();
        xhr.open('GET', '/cite/getMetaData', false);
        xhr.send(null);
        var citations = JSON.parse(xhr.responseText);
        console.log("-- Finsh Get MetaData ---- ");
// Initialize a system object, which contains two methods needed by the engine

    citeprocSys = {
            // Given a language tag in RFC-4646 form, this method retrieves the
            // locale definition file.
            retrieveLocale: function (lang) {
                var xhr = getXMLHTTPInstance();
                xhr.open('GET', 'https://raw.githubusercontent.com/Juris-M/citeproc-js-docs/master/locales-en-US.xml', false);
                xhr.send(null);
                return xhr.responseText;
            },

            // Given an identifier, this retrieves one citation item.  This method
            // must return a valid CSL-JSON object.
            retrieveItem: function (id) {
                return citations[id];
            }
        };
        console.log("-- Finsh Read Locale definition file. ---- ");

        var citeproc = getProcessor(selectCiteStyle);
        console.log("-- Finsh Intialize Processer. ---- ");
        var itemIDs = [];
        for (var key in citations) {
            itemIDs.push(key);
        }
        citeproc.updateItems(itemIDs);
        var bibResult = citeproc.makeBibliography();
        $('.cls-response').html(bibResult[1]);
    }

}

function getXMLHTTPInstance() {
    return window.XMLHttpRequest ?
        // IE7+, Firefox, Chrome, Opera, Safari
        new XMLHttpRequest() :
        //  IE6, IE5
        new ActiveXObject("Microsoft.XMLHTTP");
}
// Given the identifier of a CSL style, this function instantiates a CSL.Engine
// object that can render citations in that style.
function getProcessor(styleID) {
    // Get the CSL style as a serialized string of XML
    var xhr = getXMLHTTPInstance();
    xhr.open('GET', '/cite/readCSLFile/' + styleID, false);
    xhr.send(null);
    var styleAsText = xhr.responseText;

    // Instantiate and return the engine
    var citeproc = new CSL.Engine(citeprocSys, styleAsText);
    citeproc.opt.development_extensions.wrap_url_and_doi = true;
    return citeproc;
};
