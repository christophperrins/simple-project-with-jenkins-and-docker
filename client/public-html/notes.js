function getData() {
    let url = "http://" + location.hostname + ":8081/notebook/" + new URL(location.href).searchParams.get("id");
    let headers = {
        "Content-Type": "application/json"
    }
    httpRequest("GET", url, headers).then(request => renderNotes(request));
}

function postNote(event) {
    event.preventDefault();
    let data = event.target.noteValue.value;
    if (data) {
        let url = "http://" + location.hostname + ":8081/note/" + new URL(location.href).searchParams.get("id");
        let headers = {
            'Content-Type': 'application/json',
        }
        let body = { "text": data }

        httpRequest("POST", url, headers, JSON.stringify(body))
            .then(request => getData());
        event.target.noteValue.value = "";
    }
    return false;
}

function updateNote(event) {
    event.preventDefault();
    let body = {
        id: event.target.parentElement.parentElement.id,
        text: event.target.noteText.value
    }

    let url = "http://" + location.hostname + ":8081/note/";
    let headers = {
        'Content-Type': 'application/json',
    }

    httpRequest("PUT", url, headers, JSON.stringify(body))
        .then(request => getData());
}

function deleteNote(event) {
    let id = event.target.parentElement.id;
    let url = "http://" + location.hostname + ":8081/note/" + id;
    let headers = {
        'Content-Type': 'application/json',
    }

    httpRequest("DELETE", url, headers)
        .then(request => getData());
}

function showData(request) {
    list.innerHTML = "";



}

let renderNotes = (request) => {
    let list = document.getElementById("notes");
    list.innerHTML = "";

    let notes = JSON.parse(request.response);
    notes.forEach(note => {


        let listItem = document.createElement("li");

        let para = document.createElement("p");
        para.innerText = note.text;
        para.setAttribute("onclick", "addInput(event)")
        listItem.setAttribute("id", note.id);

        let button = document.createElement("button");
        button.innerText = "Delete";
        button.setAttribute("onclick", "deleteNote(event)")
        listItem.appendChild(para);
        listItem.appendChild(button);
        list.appendChild(listItem)
    });
}

function addInput(event) {
    let note = event.target;
    note.removeAttribute("onclick");
    let text = note.innerText;
    note.innerText = "";

    let form = document.createElement("form");
    let inputBox = document.createElement("input");
    form.setAttribute("onsubmit", "updateNote(event)");
    inputBox.type = "text";
    inputBox.name = "noteText";
    inputBox.value = text;
    let submit = document.createElement("submit");
    submit.className = "hidden";
    form.appendChild(inputBox);
    form.appendChild(submit);
    note.appendChild(form);
}

getData();