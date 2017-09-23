openssl pkcs12 -export -name servercert -in certs.pem -inkey key.pem -out myp12keystore.p12
keytool -importkeystore -destkeystore mykeystore.jks -srckeystore myp12keystore.p12 -srcstoretype pkcs12 -alias servercert
