let token = sessionStorage.getItem("bearerToken");

if(!token) {
    sessionStorage.setItem("redirect", "/home.html");
    window.location.href = "/";
}

function getData() {
    noteRequest("GET");
}

function postNote(event) {
    event.preventDefault();
    let data = event.target.noteValue.value;
    if(data){
        noteRequest("POST", {"text": data});
    } 
    event.target.noteValue.value = "";
}

function updateNote(event){
    event.preventDefault();
    let body ={
        id: event.target.parentElement.parentElement.id,
        text: event.target.noteText.value
    }   

    noteRequest("PUT", body);
}

function deleteNote(event){
    let id = event.target.parentElement.id;
    noteRequest("DELETE", "", id);
}

function showData(request) {
    let list = document.getElementById("notes");
    list.innerHTML = "";

    let notes = JSON.parse(request.response).notes  ;

    let makeNote = (note) => {
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
    }

    notes.forEach(note => makeNote(note))
}

function addInput(event){
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

function noteRequest(method, body, extension) {
    if (!extension){
        extension = "";
    } 
    let endpoint = extension+"/";
    method = method.toUpperCase();
    let callback;
    method == "GET" ? callback = showData : callback = getData; 
    let headers = {
        "Content-Type": "application/json"
    }

    body ? body = JSON.stringify(body) : body = undefined;

    httpRequest(method, endpoint, callback, headers, body);
}


function httpRequest(method, endpoint, callback, headers, body){
    let URL = "http://localhost:8085/api/note/"+endpoint+sessionStorage.getItem("bearerToken");
    let request = new XMLHttpRequest();
    request.open(method, URL);
    request.onload = () => {
        sessionStorage.setItem("bearerToken", JSON.parse(request.response).bearerToken);
        callback(request);
    }
    
    for(let key in headers){
        request.setRequestHeader(key, headers[key]);
    }

    body ? request.send(body) : request.send();
}

getData();
