function addBuddy(name, address) {
    return new Promise((resolve) => {
        let data = null;

        const xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener('readystatechange', function () {
            if (this.readyState === 4) {
                console.log(this.responseText);
                resolve(JSON.parse(this.responseText));
            }
        });

        xhr.open('POST', `http://localhost:8090?name=${name}&address=${encodeURIComponent(address)}`);
        xhr.setRequestHeader('cache-control', 'no-cache');

        xhr.send(data);
    })
}

function getBuddies(name) {
    return new Promise(resolve => {
        var data = null;

        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener('readystatechange', function () {
            if (this.readyState === 4) {
                console.log(this.responseText);
                resolve(JSON.parse(this.responseText));
            }
        });

        xhr.open('GET', `http://localhost:8090?name=${name}`);
        xhr.setRequestHeader('cache-control', 'no-cache');

        xhr.send(data);
    })
}

function createBuddyElement(buddyInfo) {
    const buddyElement = document.createElement('p');
    buddyElement.innerText = `${buddyInfo.name} - ${buddyInfo.address}`;
    return buddyElement;
}

document.getElementById('createForm').addEventListener('submit', function (event) {
    const name = document.getElementById('name').value;
    const address = document.getElementById('address').value;

    event.preventDefault();
    addBuddy(name, address)
        .then(([newBuddy]) => {
            const buddyElement = createBuddyElement(newBuddy);
            const buddiesElement = document.getElementById('buddies');
            buddiesElement.appendChild(buddyElement);
        })

}, false);

document.getElementById('filterForm').addEventListener('submit', function (event) {
    const name = document.getElementById('filter_name').value;

    event.preventDefault();
    getBuddies(name)
        .then((newBuddies) => {
            const buddiesElement = document.getElementById('buddies');
            buddiesElement.innerText = '';

            newBuddies.map(createBuddyElement).forEach(b => buddiesElement.appendChild(b))
        })

}, false);
