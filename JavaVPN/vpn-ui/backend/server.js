const express = require('express')
const bodyParser = require('body-parser')
const { spawn } = require('child_process')
const app = express()
const port = 5000

app.use(bodyParser.json())

app.post('/fetch', (req, res) => {
    const url = req.body.url
    const java = spawn('java', ['-cp', '../client:../common', 'client.VPNClient'])

    java.stdin.write(url + '\n')

    let output = ''
    java.stdout.on('data', data => {
        output += data.toString()
    })

    java.on('close', () => {
        res.send(output)
    })
})

app.listen(port, () => {
    console.log(`Backend running on http://localhost:${port}`)
})
