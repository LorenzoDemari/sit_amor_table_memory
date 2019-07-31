cmake_minimum_required(VERSION 2.8.3)
project(sit_armor_injected_msgs)

## Find catkin macros and libraries
## if COMPONENTS list like find_package(catkin REQUIRED COMPONENTS xyz)
## is used, also find other catkin packages
find_package(catkin REQUIRED COMPONENTS
  message_generation
  std_msgs
)

add_message_files(
  FILES
  SceneElement.msg
  Recognition.msg
  SceneElementVector.msg
  RecognitionVector.msg
)

add_service_files(
  FILES
  ArmorSITScene.srv
)

generate_messages(
  DEPENDENCIES
  std_msgs
)

catkin_package(
   CATKIN_DEPENDS message_runtime
)

include_directories(
  ${catkin_INCLUDE_DIRS}
)


catkin_package(CATKIN_DEPENDS message_runtime )
