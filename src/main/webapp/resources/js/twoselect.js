
function dataSelectAdd() {
    var form1 = document.forms["sourceForm"];
    var selectedItem = form1.elements.source;
    for (var i = 0; i < selectedItem.options.length; i++) {
        var option = selectedItem.options[i];
        if(option.selected) {
            alert( option.value );
        }
    }

    var form2 = document.forms["resultForm"];
    var resultSelect = form2.elements.result;
    resultSelect.add(selectedItem);



}
function dataSelectDelete() {
    var form2 = document.forms["resultForm"];
    var select = form2.elements.result;
    for (var i = 0; i < select.options.length; i++) {
        var option = select.options[i];
        if(option.selected) {
            alert( option.value );
        }
    }
}
