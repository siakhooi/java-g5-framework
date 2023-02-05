mkdir -p static/lib

cp ant.dist/*.jar static/lib/

cp lib.unsigned/*.jar static/lib/

CODESIGN_ALIAS=g5-java-application-framework

STOREFILE=$(mktemp --suffix=.pkcs)
echo $CODESIGN_STORE | base64 -d >$STOREFILE

TEMPDIR=$(mktemp -d)
(
  cd $TEMPDIR
  curl -LO https://www.freetsa.org/files/tsa.crt
  curl -LO https://www.freetsa.org/files/cacert.pem
)

keytool -importcert \
  -file "$TEMPDIR/tsa.crt" \
  -keystore "$STOREFILE" \
  -storepass "$CODESIGN_STORE_PASSWORD" \
  -noprompt \
  -trustcacerts \
  -alias tsa.crt

keytool -importcert \
  -file "$TEMPDIR/cacert.pem" \
  -storepass "$CODESIGN_STORE_PASSWORD" \
  -storepass "$CODESIGN_STORE_PASSWORD" \
  -noprompt \
  -trustcacerts \
  -alias tsa_ca_cert

for f in ./static/lib/*.jar; do
  jarsigner -keystore "$STOREFILE" \
    -storepass "$CODESIGN_STORE_PASSWORD" \
    -tsa https://freetsa.org/tsr \
    -tsacert tsa.crt \
    "$f" "$CODESIGN_ALIAS"
done
