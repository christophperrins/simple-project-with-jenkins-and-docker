/** 
 * @param {("GET"|"POST"|"PUT"|"DELETE")} method 
 * @param {String} url 
 * @param {Object} headers 
 * @param {Object} body 
 * @returns {Promise}
 */
let httpRequest = (method, url, headers, body) => {
    return new Promise((resolve, reject) => {
        let request = new XMLHttpRequest();
        request.open(method, url);
        for (let key in headers) {
            request.setRequestHeader(key, headers[key]);
        }

        request.onload = () => resolve(request);
        request.onerror = () => reject(request);

        body ? request.send(body) : request.send();
    })
}