#!/bin/bash

install_if_not_exists () {
	if  [[ !  -z  $(command -v ${1}) ]]
	 then
		# The package is installed
		echo "${1} is installed"
	 else
		# The package is not installed
		echo "Installing ${1}..."
		brew install ${1} 
		echo "Finsihed Installing ${1}"
	fi
}

installed_or_exit () {
	if  [[ !  -z  $(command -v ${1}) ]]
	 then
		# The package is installed
		echo "${1} is installed"
	 else
		echo "${1} is not installed, please install and rerun this script."
		echo "Installation instructions can be found here:"
		echo "		 ${2}"
		exit 1	
		# The package is not installed
	fi
}	

echo "Verifying all needed dependencies are installed..."

# Verify this are installed or exit
installed_or_exit brew 'www.brew.sh'
installed_or_exit virtualbox 'https://www.virtualbox.org/wiki/Downloads'

# Install/Verify dependencies for docker
install_if_not_exists docker
install_if_not_exists docker-machine
install_if_not_exists docker-compose

# Now that Docker is installed lets create an instance

box_name="discover"

# create the docker machine
# trying with 2048 mb of memory because I only have 8gb of ram :-(
docker-machine create --driver "virtualbox" --virtualbox-memory 2048 ${box_name}

# start the docker machine
docker-machine start ${box_name}

# Expose ports for docker vm so we can access via 'localhost'
VBoxManage controlvm ${box_name} natpf1 "elasticsearch,tcp,127.0.0.1,9200,,9200" 2> /dev/null
VBoxManage controlvm ${box_name} natpf1 "elasticsearch-internal,tcp,127.0.0.1,9300,,9300" 2> /dev/null
VBoxManage controlvm ${box_name} natpf1 "logstash,tcp,127.0.0.1,5000,,5000" 2> /dev/null
VBoxManage controlvm ${box_name} natpf1 "kibana,tcp,127.0.0.1,5601,,5601" 2> /dev/null

# this command allows the docker commands to be used in the terminal
# eval "$(docker-machine env ${box_name})"

# !!!!!!!!!!!!!!
# NOTE have to hard code box name on this line can't think of a better way to do it
# !!!!!!!!!!!!!!
# Add this line to profile so the machine can be used in every tab
cat ~/.profile | grep 'eval "$(docker-machine env discover)"' > /dev/null
if [ $? -eq 1 ]
 then
	echo 'eval "$(docker-machine env discover)" 2> /dev/null' >> ~/.profile
fi



