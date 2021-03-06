# Copyright (c) 2017, Baidu.com, Inc. All Rights Reserved

# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.

# This file used to compile all thrift files.
BUILD_DIR = ${CURDIR}/../build/

THRIFT = ${CURDIR}/../../thirdparty/installed/bin/thrift

SOURCES = $(shell find ${CURDIR} -name "*.thrift")
OBJECTS = $(patsubst ${CURDIR}/%.thrift, ${BUILD_DIR}/gen_cpp/%_types.cpp, ${SOURCES})
# It doesn't exist really, just use this to compile all thrift to generate java file
JAVA_OBJECTS = $(patsubst ${CURDIR}/%.thrift, ${BUILD_DIR}/java/.%.tm, ${SOURCES})

GEN_SOURCES = $(shell find ${THRIFT} -name "*.thrift")
GEN_OBJECTS = $(patsubst ${BUILD_DIR}/thrift/%.thrift, ${BUILD_DIR}/gen_cpp/%_types.cpp, ${GEN_SOURCES})
GEN_JAVA_OBJECTS = $(patsubst ${BUILD_DIR}/thrift/%.thrift, ${BUILD_DIR}/java/.%.tm, ${GEN_SOURCES})

all: ${GEN_OBJECTS} ${OBJECTS} ${GEN_JAVA_OBJECTS} ${JAVA_OBJECTS}
.PHONY: all

THRIFT_CPP_ARGS = -I ${CURDIR} -I ${BUILD_DIR}/thrift/ --gen cpp -out ${BUILD_DIR}/gen_cpp
THRIFT_JAVA_ARGS = -I ${CURDIR} -I ${BUILD_DIR}/thrift/ --gen java -out ${BUILD_DIR}/java

${BUILD_DIR}/gen_cpp:
	mkdir -p $@
# handwrite thrift
${BUILD_DIR}/gen_cpp/%_types.cpp: ${CURDIR}/%.thrift | ${BUILD_DIR}/gen_cpp
	${THRIFT} ${THRIFT_CPP_ARGS} $<
# generated thrift
${BUILD_DIR}/gen_cpp/%_types.cpp: ${BUILD_DIR}/thrift/%.thrift | ${BUILD_DIR}/gen_cpp
	${THRIFT} ${THRIFT_CPP_ARGS} $<

# generate java object
# TODO(zhaochun): make depends generated java 
# handwrite thrift
${BUILD_DIR}/java/.%.tm: ${CURDIR}/%.thrift | ${BUILD_DIR}/java
	${THRIFT} ${THRIFT_JAVA_ARGS} $<
	touch $@
# generated thrift
${BUILD_DIR}/java/.%.tm: ${BUILD_DIR}/thrift/%.thrift | ${BUILD_DIR}/java
	${THRIFT} ${THRIFT_JAVA_ARGS} $<
	touch $@
