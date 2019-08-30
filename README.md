# RayLight
[
![Docker Build Status](https://img.shields.io/docker/build/raynigon/raylight)
![Docker Pulls](https://img.shields.io/docker/pulls/raynigon/raylight)
![Docker Stars](https://img.shields.io/docker/stars/raynigon/raylight)
](https://hub.docker.com/r/raynigon/raylight)

## Description
RayLight is an Web based Light Control Application.
It supports ArtNet with unlimited Universes (ok nearly unlimited).

## Installation
1. Run the Server as a Docker Container: `docker run --restart=always -p 8080:8080 raynigon/raylight`
2. Now you can open the app in your local browser with: [http://localhost:8080](http://localhost:8080)
3. Configure your ArtNet Adapter and start playing around

## Features

 - ArtNet Support
 - Unlimited Universes
 - ...

## Dependencies
 - Angular
 - Spring Boot
 - We use parts of the [ArtNet4j](https://github.com/cansik/artnet4j) Library
