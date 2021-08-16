class CompilerService {
    compileCode(code) {
        let data = {code: code};
        let isError = false;
        return new Promise((resolve, reject) => {
            fetch('http://localhost:8080/compile', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    console.log(response);
                    return response.json();
                })
                .then(json => {
                    if (isError) return reject(json);
                    console.log(json.output);
                    resolve(json.output);
                })
                .catch(error => console.error('Error: ', error));
        });
    }
}

export let compilerService = new CompilerService();