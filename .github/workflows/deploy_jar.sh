
mkdir -p static/lib

cp ant.dist/*.jar static/lib/

cp lib.unsigned/*.jar static/lib/

keytool -genkeypair -v -storetype JKS -keystore myKeystore -alias myself -validity 360 -keyalg RSA -dname "cn=Subang Jaya, ou=GQR Solutions, o=GQR Solutions, c=MY" -storepass welcome

for f in ./static/lib/*.jar; do
  jarsigner -keystore myKeystore -storepass welcome "$f" myself
done