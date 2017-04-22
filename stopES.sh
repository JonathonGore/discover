docker ps | grep elasticsearch | awk '{ print $1 }' | xargs docker stop
