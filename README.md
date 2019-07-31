# The SIT Algorithm Injected in the aRMOR service
A simple implementation of the Scene Identification and Tagging algorithm (SIT) injected in a ROS Multi Ontology References service (aRMOR).

# How to run

 - Run the service
```
rosrun sit_armor_injected execute it.emarolab.sitArmorInjected.SitArmorInjectedService
```
 - or launch it from roslaunch
```
<node pkg="sit_armor" type="execute" name="sit_armor_injected" args="it.emarolab.sitArmorInjected.SitArmorInjectedService"/>
```
 - load the ontology through aRMOR (where `ros_ws` is the ROS workspace in the home)
```
rosservice call /armor_interface_srv "armor_request:
  client_name: ''
  reference_name: 'ontoSIT'
  command: 'LOAD'
  primary_command_spec: 'FILE'
  secondary_command_spec: ''
  args: ['src/injected_armor_pkgs/scene_identification_tagging/sit/src/main/resources/simpleSIT.owl', 'http://www.emarolab.it/sit/simple', 'true', 'pellet', 'true']" 
```
 - test some simple scenes
```
rosservice call /sit_armor_injected "header:
  seq: 0
  stamp:
    secs: 0
    nsecs: 0
  frame_id: ''
ontoReference: 'ontoSIT'         
sceneElements:
  element:
  - type: 'SPHERE'
    features: 
     - 0.0 
     - 0.0 
     - 0.0
  - type: 'SPHERE'
    features: 
     - 0.8 
     - 0.0
     - 0.8
learningTreshold: 0.9" 
```
```
rosservice call /sit_armor_injected "header:
  seq: 0
  stamp:
    secs: 0
    nsecs: 0
  frame_id: ''
ontoReference: 'ontoSIT'         
sceneElements:
  element:
  - type: 'SPHERE'
    features: 
     - 0.0 
     - 0.0 
     - 0.0
  - type: 'CONE'
    features: 
     - -0.8 
     - -0.8 
     - -0.0
     - 0
     - 0
     - 1
learningTreshold: 0.9" 

```
```
rosservice call /sit_armor_injected "header:
  seq: 0
  stamp:
    secs: 0
    nsecs: 0
  frame_id: ''
ontoReference: 'ontoSIT'         
sceneElements:
  element:
  - type: 'SPHERE'
    features: 
     - 0.0 
     - 0.0 
     - 0.0
  - type: 'CONE'
    features: 
     - -0.8 
     - -0.8 
     - -0.0
     - 0
     - 0
     - 1
  - type: 'SPHERE'
    features: 
     - 0.8 
     - 0.0 
     - 0.8
learningTreshold: 0.9" 

```
