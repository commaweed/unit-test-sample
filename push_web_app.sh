#!/usr/bin/env bash
PROJECT_NAME="unit-test-sample"
WEB_APP_DIR="$CATALINA_HOME/webapps/${PROJECT_NAME}"
CODE_DIR="$DATA_DIR/intellij_workspace/${PROJECT_NAME}/target"
CODE_BASE="$CODE_DIR/${PROJECT_NAME}"
TIME_STAMP=$(date +"%Y-%m-%d %H:%M:%S.%N")

echoLine() {
	echo "-------------------------------------------------------------------------------------------"
}

echoBlank() {
	echo
}

if [ -d "$WEB_APP_DIR" ]
then
	clear
	echoBlank

	echo "Executed on: $TIME_STAMP"
	echoBlank

	echoLine
	echo "1.  Removing contents of [$WEB_APP_DIR]"
	echoLine
	ls -al ${WEB_APP_DIR}
	echoLine
	echoBlank

	echoLine
	#set -x
	rm -rf ${WEB_APP_DIR}/*
	#set +x
	echo "2.  Contents of [$WEB_APP_DIR] after removal"
	echoLine
	echoBlank

	if [ -d "$CODE_BASE" ]
	then
		echoLine
		echo "3.  copying web application code from [$CODE_BASE]..."
		echoLine
		echoBlank

		#set -x
		cp -R ${CODE_BASE}/* ${WEB_APP_DIR}/
		#set +x

		echoLine
		echo "4.  Contents of [$WEB_APP_DIR] after code copy..."
		echoLine
		ls -al ${WEB_APP_DIR}
		echoLine
		echoBlank
	else
		echoBlank
		echo "Could not find code directory [$CODE_BASE]; did you build it?"
		echoBlank
		exit
	fi
else
		echoBlank
		echo "The web application directory does not exist [$WEB_APP_DIR]!"
		echoBlank
		exit
fi

