import React, { useState } from 'react'

function App() {
  const [url, setUrl] = useState('')
  const [data, setData] = useState('')

  const fetchData = async () => {
    const res = await fetch('http://localhost:5000/fetch', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ url })
    })
    const text = await res.text()
    setData(text)
  }

  return (
    <div style={{ padding: 40 }}>
      <h2>Java VPN Client UI</h2>
      <input value={url} onChange={e => setUrl(e.target.value)} style={{ width: 300, marginRight: 10 }} />
      <button onClick={fetchData}>Fetch</button>
      <pre style={{ marginTop: 20, whiteSpace: 'pre-wrap' }}>{data}</pre>
    </div>
  )
}

export default App
