# 🔐 Java VPN Tunnel - Secure Proxy Over SSL

A simple SSL-based VPN-like tunnel built with **Java**, **React**, and **Node.js**.  
It establishes an encrypted tunnel between a client and a proxy server, forwarding HTTP requests securely.

---

## 📁 Project Structure
```
JavaVPN/
├── client/ # Java VPN Client
├── server/ # Java VPN Server
├── common/ # SSL Utilities
├── keystore/ # Keystores and Certs (ignored)
└── vpn-ui/ # React + Node UI interface
    ├── backend/
    │   ├── server.js
    └── frontend/
        ├── package.json
        └── src/
            ├── App.js
            ├── index.js
```
---

## ✅ Features

- Java-based SSL/TLS tunnel
- Encrypted client-server communication
- Proxy-style request forwarding
- React UI with Node.js backend trigger
- Easy one-command keystore generation

---

## 🔐 SSL Keystore Setup (Required)

### Before running the project, generate the required keystores and truststores:

```bash
# 1. Create server keystore
keytool -genkeypair -alias serverkey -keyalg RSA -keystore keystore/serverkeystore.jks -storepass password -keypass password -dname "CN=localhost" -validity 365

# 2. Export server certificate
keytool -export -alias serverkey -keystore keystore/serverkeystore.jks -file keystore/server.cer -storepass password

# 3. Import server certificate into client's truststore
keytool -import -alias serverkey -file keystore/server.cer -keystore keystore/clienttruststore.jks -storepass password -noprompt

```

---

## 🚀 Running the Project

### 🟨 1. Compile Java Code
#### javac common/*.java server/*.java client/*.java

### 🟩 2. Start VPN Server
#### java -cp . server.VPNServer

### 🟦 3. Start Node Backend
#### cd vpn-ui/backend
#### npm install
#### node server.js

### 🟪 4. Start React Frontend
#### cd ../frontend
#### npm install
#### npm start

##### Visit http://localhost:3000 to use the UI.

---

## 📢 Notes

### This project is for educational/demo purposes.
### Do not use self-signed certs in production.
### All data is routed through your own local server.

---

## 🛡️ Security Tip

### Do not commit .jks, .cer, or .env files to version control.
### They are ignored via .gitignore in this project.

---

## 📄 License

### MIT License – use it freely with credit.