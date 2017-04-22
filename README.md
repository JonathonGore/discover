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
discover   *        virtualbox   Running   tcp://192.168.99.100:2376           v17.04.0-ce
```
