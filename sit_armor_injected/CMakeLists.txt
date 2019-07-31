##############################################################################
# CMake
##############################################################################

cmake_minimum_required(VERSION 2.8.3)
project(sit_armor_injected)

##############################################################################
# Catkin
##############################################################################

find_package(catkin REQUIRED armor_msgs rosjava_build_tools multi_ontology_reference owl_oop scene_identification_tagging armor)

# Set the gradle targets you want catkin's make to run by default, e.g.
#   catkin_rosjava_setup(installApp)
# Note that the catkin_create_rosjava_xxx scripts will usually automatically
# add tasks to this for you when you create subprojects.
catkin_rosjava_setup(install installApp publishMavenJavaPublicationToMavenRepository deployApp)

catkin_package()

##############################################################################
# Installation
##############################################################################

# Change this to match the maven group name you have specified in the 
# allprojects closure the root build.gradle
install(DIRECTORY ${CATKIN_DEVEL_PREFIX}/${CATKIN_GLOBAL_MAVEN_DESTINATION}/it/emarolab/${PROJECT_NAME}/ 
        DESTINATION ${CATKIN_GLOBAL_MAVEN_DESTINATION}/it/emarolab/${PROJECT_NAME})
