mkdir -p static/lib

cp ant.dist/*.jar static/lib/

cp lib.unsigned/*.jar static/lib/

CODESIGN_ALIAS=g5-java-application-framework

STOREFILE=$(mktemp --suffix=.pkcs)
echo $CODESIGN_STORE | sed 's/ //g' | base64 -d >$STOREFILE

for f in ./static/lib/*.jar; do
  jarsigner -keystore "$STOREFILE" -storepass "$CODESIGN_STORE_PASSWORD" "$f" "$CODESIGN_ALIAS"
done
