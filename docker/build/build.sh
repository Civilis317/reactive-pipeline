#!/usr/bin/env bash

set -o pipefail

# include parse_yaml function
parse_yaml() {
   local prefix=$2
   local s='[[:space:]]*' w='[a-zA-Z0-9_]*' fs=$(echo @|tr @ '\034')
   sed -ne "s|^\($s\)\($w\)$s:$s\"\(.*\)\"$s\$|\1$fs\2$fs\3|p" \
        -e "s|^\($s\)\($w\)$s:$s\(.*\)$s\$|\1$fs\2$fs\3|p"  $1 |
   awk -F$fs '{
      indent = length($1)/2;
      vname[indent] = $2;
      for (i in vname) {if (i > indent) {delete vname[i]}}
      if (length($3) > 0) {
         vn=""; for (i=0; i<indent; i++) {vn=(vn)(vname[i])("_")}
         printf("%s%s%s=\"%s\"\n", "'$prefix'",vn, $2, $3);
      }
   }'
}

# read yaml file
eval $(parse_yaml build.yml "config_")

# announce read properties:
echo 'performing build using the following properties:'
echo 'docker-registry host: ' $config_docker_registry_host
echo 'docker-registry port: ' $config_docker_registry_port
echo 'docker image name: ' $config_image_name

IMAGE_NAME=$config_image_name

TAG_NAME=$config_docker_registry_host:$config_docker_registry_port/${IMAGE_NAME}

if [ -z "$1" ];
then
    # no version override parameter provided, look for version in build properties...
    echo "Version derived from pom.xml: ${POM_VERSION}"
    VERSION=${POM_VERSION}

    if [ -z "$VERSION" ]
    then
		# version not in properties and not provided as parameter
		echo "unknown version number, aborting build"
		exit
    fi

else
    echo "Version passed as param: $1"
    VERSION="$1"
fi

docker build --no-cache=true -t ${IMAGE_NAME}:${VERSION} . | tee build.log || exit 1

docker tag ${IMAGE_NAME}:${VERSION} ${TAG_NAME}:${VERSION}
docker tag ${IMAGE_NAME}:${VERSION} ${TAG_NAME}:latest

docker push ${TAG_NAME}:${VERSION}
docker push ${TAG_NAME}:latest


docker images | grep ${IMAGE_NAME}

