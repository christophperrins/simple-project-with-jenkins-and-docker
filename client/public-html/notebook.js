let print = () => { console.log("print") }

let getNotebooks = () => {
    let URL = "http://" + location.hostname + ":8081/notebook/";
    let headers = {
        'Content-Type': 'application/json'
    }
    httpRequest("GET", URL, headers)
        .then(request => renderNotebooks(JSON.parse(request.response)));

}

getNotebooks();

let renderNotebooks = (response) => {
    let notebooks = document.getElementById("notebooks");
    notebooks.innerHTML = "";
    for (let notebook of response) {

        let listItem = document.createElement("li");
        listItem.className = "notebook";
        listItem.style.background = notebook.colour;
        let anchor = document.createElement("a");
        anchor.href = "notes.html?id=" + notebook.id + "&title=" + notebook.title;
        anchor.innerText = notebook.title;
        let button = document.createElement("button");
        button.className = "right";
        button.innerText = "Delete";
        button.addEventListener("click", () => { deleteNotebook(notebook.id) })
        listItem.appendChild(anchor);
        listItem.appendChild(button);
        notebooks.appendChild(listItem);
    }


}

let postNotebook = (event) => {
    let form = event.target;
    let body = {
        title: form.title.value,
        colour: form.colour.value
    }

    if (body.title) {
        let URL = "http://" + location.hostname + ":8081/notebook/";
        let headers = {
            'Content-Type': 'application/json'
        }

        httpRequest("POST", URL, headers, JSON.stringify(body))
            .then(request => getNotebooks())
    }
    return false;
}

let deleteNotebook = (id) => {
    let URL = "http://" + location.hostname + ":8081/notebook/" + id;
    let headers = {
        'Content-Type': 'application/json'
    }
    httpRequest("DElETE", URL, headers)
        .then(request => getNotebooks());
}