# Discover

## Setting up development environment

### Prerequisites 
* Homebrew must be installed. Installation instructions can be found [here](https://brew.sh/).
* VirtualBox must be installed. Installation instructions can be found [here](https://www.virtualbox.org/wiki/Downloads).

Run the setup.sh script to verify the needed dependencies are installed.

`sh setup.sh`

This script will also create a new docker machine name 'discover'.
After running the setup script we still need to set this new machine as active.
Run this command to make it your active docker-machine:

`eval "$(docker-machine env discover)"`

Now we can verify our machine was created successfully by running the command `docker-machine ls`
It should output something that looks like this:

```
NAME       ACTIVE   DRIVER       STATE     URL                         SWARM   DOCKER        ERRORS
discover   *        virtualbox   Running   tcp://192.168.XX.XXX:2376           v17.04.0-ce
```

Now in order to use elasticsearch we must increase the `vm.max_map_count` on our new docker machine.
Run the following commands:

```
docker-machine ssh discover
sudo su
echo 'sysctl -w vm.max_map_count=262144' >> /var/lib/boot2docker/bootlocal.sh
sh /var/lib/boot2docker/bootlocal.sh
exit
exit
```

Now we can finally start our elasticsearch cluster!
Create an elasticsearch cluster by running:

`docker-compose up -d`

Finally verify the ELK stack is working by running the command `docker ps`

You should see something like this:

```
CONTAINER ID        IMAGE                    COMMAND                  CREATED             STATUS              PORTS                                            NAMES
07eaf914a563        discover_kibana          "/bin/sh -c /usr/l..."   15 hours ago        Up 5 minutes        0.0.0.0:5601->5601/tcp                           discover_kibana_1
f0cba90e21fd        discover_logstash        "/usr/local/bin/do..."   15 hours ago        Up 5 minutes        0.0.0.0:5000->5000/tcp                           discover_logstash_1
57931532c616        discover_elasticsearch   "/bin/bash bin/es-..."   15 hours ago        Up 5 minutes        0.0.0.0:9200->9200/tcp, 0.0.0.0:9300->9300/tcp   discover_elasticsearch_1
```

