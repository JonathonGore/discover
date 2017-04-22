docker ps -q | xargs docker stop
docker ps -aq | xargs docker rm
