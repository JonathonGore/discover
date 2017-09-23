# Stops all docker containers. Be Wary if you running other 
# docker containers it will stop them as well!
docker ps -q | xargs docker stop
